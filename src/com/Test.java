package com;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.dc.api.client.DefaultDockerClientConfig;
import com.dc.api.client.DockerClient;
import com.dc.api.jersey.DockerClientImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	public static void main(String[] args) {
		DockerClient client = DockerClientImpl.getInstance("http://192.168.89.128:2375");
//		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
//		DockerClient client = DockerClientImpl.getInstance(config);
//		List<Container> s = client.listContainersCmd().exec();
		/**Images**/
//		JSONArray images = client.listImagesCmd().withFilter("apm").exec();
//		System.out.println(images);
		
		/**Services list**/
//		String filters = "{\"name\":[\"iam\"]}";
//		JSONArray s = client.listServicesCmd().withFilters(JSONObject.fromObject(filters)).exec();
		/**Create service**/
//		Map<String, Object> image = new HashMap<String, Object>();
//		image.put("Image", "iam");
//		Map<String, Object> taskTemplate = new HashMap<String, Object>();
//		taskTemplate.put("ContainerSpec", image);
//		JSONArray services = client.listServicesCmd().exec();
//		System.out.println(services);
		String settingStr = "{\"Name\":\"service-tomcat-test\",\"Labels\":{\"label_user\":\"fogray\",\"build-date\":\"20160729\",\"license\":\"GPLv2\",\"name\":\"CentOS Base Image\",\"vendor\":\"CentOS\"},\"TaskTemplate\":{\"ContainerSpec\":{\"Image\":\"mysql:latest\",\"Args\":[\"\"],\"Env\":[\"env_test=test\",\"PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/java/bin\",\"JAVA_HOME=/java\",\"JRE_HOME=/java\",\"CLASSPATH=.:/java/lib:/java/lib\"],\"Dir\":\"\",\"User\":\"\",\"Labels\":{\"label_user\":\"fogray\",\"build-date\":\"20160729\",\"license\":\"GPLv2\",\"name\":\"CentOS Base Image\",\"vendor\":\"CentOS\"},\"Mounts\":[{\"Target\":\"/tomcat/logs\",\"Source\":\"/tmp/logs\",\"ReadOnly\":false}]},\"Resources\":{},\"RestartPolicy\":{\"Condition\":\"none\"}},\"Mode\":{\"Replicated\":{\"Replicas\":2}},\"UpdateConfig\":{},\"Networks\":[{\"Target\":\"ingress\"}],\"EndpointSpec\":{\"Mode\":\"vip\",\"Ports\":[{\"Protocol\":\"tcp\",\"PublishedPort\":3306}]}}";
		JSONObject result = client.createServiceCmd().withSettings(JSONObject.fromObject(settingStr)).exec();
		
		System.out.println(result.toString());
//		String ss = "{\"Name\":\"ap\",\"Labels\":{\"build-date\":\"20160729\",\"license\":\"GPLv2\",\"name\":\"CentOS Base Image\",\"vendor\":\"CentOS\"},\"TaskTemplate\":{\"ContainerSpec\":{\"Image\":\"apm:latest\",\"Command\":[\"/bin/sh\",\"-c\",\"/bin/bash /start_tomcat.sh\"],\"Args\":[\"abs\"],\"Env\":[\"PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/java/bin\",\"JAVA_HOME=/java\",\"JRE_HOME=/java\",\"CLASSPATH=.:/java/lib:/java/lib\"],\"Dir\":\"/\",\"User\":\"\",\"Labels\":{\"build-date\":\"20160729\",\"license\":\"GPLv2\",\"name\":\"CentOS Base Image\",\"vendor\":\"CentOS\"},\"Mounts\":[{\"Target\":\"/\",\"Source\":\"/\",\"ReadOnly\":false}]},\"Resources\":{\"Limits\":{\"MemoryBytes\":536870912,\"NanoCPUs\":200},\"Reservation\":{\"MemoryBytes\":536870912,\"NanoCPUs\":120}},\"RestartPolicy\":{\"Condition\":\"none\"}},\"Mode\":{\"Global\":{}},\"UpdateConfig\":{\"Parallelism\":2},\"Networks\":[{\"Target\":\"ingress\"}],\"EndpointSpec\":{\"Mode\":\"vip\",\"Ports\":[{\"Protocol\":\"tcp\",\"TargetPort\":8080}]}}";
//		JSONObject jo = client.createServiceCmd().withSettings(JSONObject.fromObject(ss)).exec();
//		System.out.println(jo);
//		String serviceConfig = "{\"Name\":\"ap\",\"Labels\":{\"build-date\":\"20160729\",\"license\":\"GPLv2\",\"name\":\"CentOS Base Image\",\"vendor\":\"CentOS\"},\"TaskTemplate\":{\"ContainerSpec\":{\"Image\":\"apm:latest\",\"Command\":[\"/bin/sh\",\"-c\",\"/bin/bash /start_tomcat.sh\"],\"Args\":[],\"Env\":[\"PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/java/bin\",\"JAVA_HOME=/java\",\"JRE_HOME=/java\",\"CLASSPATH=.:/java/lib:/java/lib\"],\"Dir\":\"/\",\"User\":\"\",\"Labels\":{\"build-date\":\"20160729\",\"license\":\"GPLv2\",\"name\":\"CentOS Base Image\",\"vendor\":\"CentOS\"},\"Mounts\":[{\"Target\":\"/\",\"Source\":\"/\",\"ReadOnly\":false}]},\"Resources\":{\"Limits\":{},\"Reservation\":{}},\"RestartPolicy\":{\"Condition\":\"none\"}},\"Mode\":{\"Global\":{}},\"UpdateConfig\":{\"Parallelism\":2},\"Networks\":[{\"Target\":\"ingress\"}],\"EndpointSpec\":{\"Mode\":\"vip\",\"Ports\":[{\"Protocol\":\"tcp\",\"TargetPort\":8080}]}}";
//		System.out.println(JSONObject.fromObject(serviceConfig));
//		JSONObject service = client.inspectServiceCmd().withServiceId("cv9ot0n8vtmooj37qxc01tck8").exec();
//		int version = service.getJSONObject("Version").getInt("Index");
//		JSONObject spec = service.getJSONObject("Spec");
//		JSONObject mode = spec.getJSONObject("Mode");
//		if (mode.containsKey("Replicated")) {
//			mode.getJSONObject("Replicated").put("Replicas", 5);
//		}
//		spec.put("Mode", mode);
//		JSONObject result = client.updateServiceCmd().withServiceId("cv9ot0n8vtmooj37qxc01tck8")
//								 .withConf(JSONObject.fromObject(spec))
//								 .withVersion(String.valueOf(version))
//								 .exec();
//		System.out.println(result);
		/**Container**/
//		AttachContainerResultCallback acr = new AttachContainerResultCallback();
//		client.attachContainerCmd().withContainerId("d2e9e8b74f83").withStream(true).exec(acr);
//		String settings = "{\"Image\":\"apm\"}";
//		JSONObject s1 = client.createContainerCmd().withName("test").withSettings(JSONObject.fromObject(settings)).exec();
//		System.out.println(s1);
//		JSONObject s = client.inspectContainerCmd().withContainerId("6436dd6b3db99e6cb8d1c65a824d084b53511cbc5ebdf66ef517c88ee80b2e49").withShowSize(true).exec();
//		JSONArray s = client.inspectContainerChangesCmd().withContainerId("d2e9e8b74f83").exec();
//		JSONArray s = client.listContainersCmd().exec();

//		JSONArray containers = client.listContainersCmd()
//								.withFilters(JSONObject.fromObject("{\"label\":[\"com.docker.swarm.service.id=cv9ot0n8vtmooj37qxc01tck8\"]}"))
//								.withShowAll(true).withShowSize(true).exec();
//		System.out.println(containers);
//		for (Object obj : containers) {
//			JSONObject jo = (JSONObject) obj;
//			System.out.println(jo.getJSONArray("Names").getString(0));
//		}
//		client.startContainerCmd().withContainerId("6436dd6b3db99e6cb8d1c65a824d084b53511cbc5ebdf66ef517c88ee80b2e49").exec();
//		client.reStartContainerCmd().withContainerId("6436dd6b3db99e6cb8d1c65a824d084b53511cbc5ebdf66ef517c88ee80b2e49").exec();
//		client.renameContainerCmd().withContainerId("6436dd6b3db99e6cb8d1c65a824d084b53511cbc5ebdf66ef517c88ee80b2e49").withNewName("apm-test").exec();
//		JSONObject s = client.statsContainerCmd().withStream(true).withContainerId("apm-test").exec();
//		client.pauseContainerCmd().withContainerId("6436dd6b3db99e6cb8d1c65a824d084b53511cbc5ebdf66ef517c88ee80b2e49").exec();
//		client.unPauseContainerCmd().withContainerId("6436dd6b3db99e6cb8d1c65a824d084b53511cbc5ebdf66ef517c88ee80b2e49").exec();
//		JSONObject s = client.topContainerCmd().withContainerId("6436dd6b3db99e6cb8d1c65a824d084b53511cbc5ebdf66ef517c88ee80b2e49").exec();
//		JSONObject s = client.updateContainerCmd().withContainerId("6436dd6b3db99e6cb8d1c65a824d084b53511cbc5ebdf66ef517c88ee80b2e49").withSettings(JSONObject.fromObject("{ \"CpuPeriod\": 100000, \"RestartPolicy\": { \"MaximumRetryCount\": 4, \"Name\": \"on-failure\" } }")).exec();
//		JSONObject s = client.waitContainerCmd().withContainerId("6436dd6b3db99e6cb8d1c65a824d084b53511cbc5ebdf66ef517c88ee80b2e49").exec();
//		client.stopContainerCmd().withContainerId("6436dd6b3db99e6cb8d1c65a824d084b53511cbc5ebdf66ef517c88ee80b2e49").exec();
//		client.killContainerCmd().withContainerId("6436dd6b3db9").exec();
//		client.removeContainerCmd().withContainerId("6436dd6b3db9").withAlsoVolumes(true).withForce(true).exec();
		
		/**Node**/
//		JSONArray s = client.listNodesCmd().exec();
//		JSONObject s = client.inspectNodeCmd().withNodeId("65lxue9vi48qtu5c60a5yj28z").exec();
//		System.out.println(s);
//		httpclient();
		
		/**Task**/
//		String filters = "{\"service\":[\"cv9ot0n8vtmooj37qxc01tck8\"]}";
//		JSONArray tasks = client.listTasksCmd().withFilters(JSONObject.fromObject(filters)).exec();
//		System.out.println(tasks);
	}
	
	public static void httpclient(){
		HttpClient client = new DefaultHttpClient();
		HttpPost method = new HttpPost("http://localhost:8080/dd/api/services/5apfv0bzakguityn9n8tnnzpd/stop");
		try {
			HttpResponse hr = client.execute(method);
			System.out.println(EntityUtils.toString(hr.getEntity()));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
