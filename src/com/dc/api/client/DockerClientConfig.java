package com.dc.api.client;

import java.net.URI;

import com.dc.api.core.RemoteApiVersion;
import com.dc.api.core.SSLConfig;
import com.dc.api.core.model.AuthConfig;
import com.dc.api.core.model.AuthConfigurations;

public interface DockerClientConfig {

    URI getDockerHost();

    RemoteApiVersion getApiVersion();

    String getRegistryUsername();

    String getRegistryPassword();

    String getRegistryEmail();

    String getRegistryUrl();

    AuthConfig effectiveAuthConfig(String imageName);

    AuthConfigurations getAuthConfigurations();

    /**
     * Returns an {@link SSLConfig} when secure connection is configured or null if not.
     */
    SSLConfig getSSLConfig();

}
