package com.dc.api.jersey;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.client.WebTarget;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import com.dc.api.client.DockerClientConfig;
import com.dc.api.core.SSLConfig;
import com.dc.api.exception.DockerClientException;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class JerseyDockerClientConnector implements Closeable{


    private Client client;

    private WebTarget baseResource;

    private Integer readTimeout = null;

    private Integer connectTimeout = null;

    private Integer maxTotalConnections = null;

    private Integer maxPerRouteConnections = null;

    private ClientRequestFilter[] clientRequestFilters = null;

    private ClientResponseFilter[] clientResponseFilters = null;

    private DockerClientConfig dockerClientConfig;

    private PoolingHttpClientConnectionManager connManager = null;


    public void init(String url) {
        checkNotNull(url, "url was not specified");

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.connectorProvider(new ApacheConnectorProvider());
        clientConfig.property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);

        clientConfig.register(JacksonJsonProvider.class);

        if (readTimeout != null) {
            clientConfig.property(ClientProperties.READ_TIMEOUT, readTimeout);
        }
        if (connectTimeout != null) {
            clientConfig.property(ClientProperties.CONNECT_TIMEOUT, connectTimeout);
        }
        if (clientResponseFilters != null) {
            for (ClientResponseFilter clientResponseFilter : clientResponseFilters) {
                if (clientResponseFilter != null) {
                    clientConfig.register(clientResponseFilter);
                }
            }
        }
        if (clientRequestFilters != null) {
            for (ClientRequestFilter clientRequestFilter : clientRequestFilters) {
                if (clientRequestFilter != null) {
                    clientConfig.register(clientRequestFilter);
                }
            }
        }

        URI originalUri = URI.create(url);

        connManager = new PoolingHttpClientConnectionManager(getSchemeRegistry(originalUri, null)) {
	            @Override
	            public void close() {
	                super.shutdown();
	            }};

        if (maxTotalConnections != null) {
            connManager.setMaxTotal(maxTotalConnections);
        }
        if (maxPerRouteConnections != null) {
            connManager.setDefaultMaxPerRoute(maxPerRouteConnections);
        }
        clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, connManager);

        // Configure connection pool timeout
         clientConfig.property(ApacheClientProperties.REQUEST_CONFIG, RequestConfig.custom()
        		 	.setConnectionRequestTimeout(1000).build());
        if (originalUri.toString().startsWith("tcp://")) {
            try {
            	originalUri = new URI(originalUri.toString().replaceFirst("tcp", "http"));
    		} catch (Exception e) {
    			throw new DockerClientException("Error in SSL Configuration", e);
    		}
        }
			
        ClientBuilder clientBuilder = ClientBuilder.newBuilder().withConfig(clientConfig);
        client = clientBuilder.build();
        baseResource = client.target(originalUri);
    }
    
    public void init(DockerClientConfig dockerClientConfig) {
        checkNotNull(dockerClientConfig, "config was not specified");
        this.dockerClientConfig = dockerClientConfig;

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.connectorProvider(new ApacheConnectorProvider());
        clientConfig.property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);

//        clientConfig.register(ResponseStatusExceptionFilter.class);
//        clientConfig.register(JsonClientFilter.class);
        clientConfig.register(JacksonJsonProvider.class);

        // logging may disabled via log level
//        clientConfig.register(new SelectiveLoggingFilter(LOGGER, true));

        if (readTimeout != null) {
            clientConfig.property(ClientProperties.READ_TIMEOUT, readTimeout);
        }
        if (connectTimeout != null) {
            clientConfig.property(ClientProperties.CONNECT_TIMEOUT, connectTimeout);
        }
        if (clientResponseFilters != null) {
            for (ClientResponseFilter clientResponseFilter : clientResponseFilters) {
                if (clientResponseFilter != null) {
                    clientConfig.register(clientResponseFilter);
                }
            }
        }
        if (clientRequestFilters != null) {
            for (ClientRequestFilter clientRequestFilter : clientRequestFilters) {
                if (clientRequestFilter != null) {
                    clientConfig.register(clientRequestFilter);
                }
            }
        }

        URI originalUri = dockerClientConfig.getDockerHost();
        String protocol = null;
        SSLContext sslContext = null;
        try {
            final SSLConfig sslConfig = dockerClientConfig.getSSLConfig();
            if (sslConfig != null) {
                sslContext = sslConfig.getSSLContext();
            }
        } catch (Exception ex) {
            throw new DockerClientException("Error in SSL Configuration", ex);
        }
        if (sslContext != null) {
            protocol = "https";
        } else {
            protocol = "http";
        }

        if (!originalUri.getScheme().equals("unix")) {
            try {
                originalUri = new URI(originalUri.toString().replaceFirst("tcp", protocol));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            configureProxy(clientConfig, protocol);
        }

        connManager = new PoolingHttpClientConnectionManager(getSchemeRegistry(originalUri, sslContext)) {
	            @Override
	            public void close() {
	                super.shutdown();
	            }};

        if (maxTotalConnections != null) {
            connManager.setMaxTotal(maxTotalConnections);
        }
        if (maxPerRouteConnections != null) {
            connManager.setDefaultMaxPerRoute(maxPerRouteConnections);
        }
        clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, connManager);

        // Configure connection pool timeout
         clientConfig.property(ApacheClientProperties.REQUEST_CONFIG, RequestConfig.custom()
        		 	.setConnectionRequestTimeout(1000).build());

        ClientBuilder clientBuilder = ClientBuilder.newBuilder().withConfig(clientConfig);
        if (sslContext != null) {
            clientBuilder.sslContext(sslContext);
        }
        client = clientBuilder.build();
        baseResource = client.target(originalUri).path(dockerClientConfig.getApiVersion().asWebPathPart());
    }

    private void configureProxy(ClientConfig clientConfig, String protocol) {

        List<Proxy> proxies = ProxySelector.getDefault().select(dockerClientConfig.getDockerHost());

        for (Proxy proxy : proxies) {
            InetSocketAddress address = (InetSocketAddress) proxy.address();
            if (address != null) {
                String hostname = address.getHostName();
                int port = address.getPort();

                clientConfig.property(ClientProperties.PROXY_URI, "http://" + hostname + ":" + port);

                String httpProxyUser = System.getProperty(protocol + ".proxyUser");
                if (httpProxyUser != null) {
                    clientConfig.property(ClientProperties.PROXY_USERNAME, httpProxyUser);
                    String httpProxyPassword = System.getProperty(protocol + ".proxyPassword");
                    if (httpProxyPassword != null) {
                        clientConfig.property(ClientProperties.PROXY_PASSWORD, httpProxyPassword);
                    }
                }
            }
        }
    }

    private Registry<ConnectionSocketFactory> getSchemeRegistry(final URI originalUri,
            SSLContext sslContext) {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
        registryBuilder.register("http", PlainConnectionSocketFactory.getSocketFactory());
        if (sslContext != null) {
            registryBuilder.register("https", new SSLConnectionSocketFactory(sslContext));
        }
//        registryBuilder.register("unix", new UnixConnectionSocketFactory(originalUri));
        return registryBuilder.build();
    }

    protected WebTarget getBaseResource() {
        checkNotNull(baseResource, "Factory not initialized, baseResource not set. You probably forgot to call init()!");
        return baseResource;
    }

    protected DockerClientConfig getDockerClientConfig() {
        checkNotNull(dockerClientConfig,
                "Factor not initialized, dockerClientConfig not set. You probably forgot to call init()!");
        return dockerClientConfig;
    }

    @Override
    public void close() throws IOException {
        checkNotNull(client, "Factory not initialized. You probably forgot to call init()!");
        client.close();
        connManager.close();
    }

    public JerseyDockerClientConnector withReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public JerseyDockerClientConnector withConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public JerseyDockerClientConnector withMaxTotalConnections(Integer maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
        return this;
    }

    public JerseyDockerClientConnector withMaxPerRouteConnections(Integer maxPerRouteConnections) {
        this.maxPerRouteConnections = maxPerRouteConnections;
        return this;
    }

    public JerseyDockerClientConnector withClientResponseFilters(ClientResponseFilter... clientResponseFilter) {
        this.clientResponseFilters = clientResponseFilter;
        return this;
    }

    public JerseyDockerClientConnector withClientRequestFilters(ClientRequestFilter... clientRequestFilters) {
        this.clientRequestFilters = clientRequestFilters;
        return this;
    }

}
