package com.inspur.dc.controller.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.api.client.DefaultDockerClientConfig;
import com.dc.api.client.DockerClient;
import com.dc.api.command.service.ListServicesCmd;
import com.dc.api.jersey.DockerClientImpl;
import com.dc.api.util.Const;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/services")
public class ServicesController{
    private Logger log = Logger.getLogger(ServicesController.class);
    
    
    /**
     * service列表
     * @param params
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String list(@RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController list start******");
        }

		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		ListServicesCmd cmd = client.listServicesCmd();
		if (params.containsKey("filters")) {
			cmd.withFilters(JSONObject.fromObject(params.get("filters")));
		}
		JSONArray l = cmd.exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController list end******");
        }
        return l.toString();
    }

    /**
     * 查看某个service
     * @param serviceId
     * @param queryParams
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, value={"/{serviceId}/inspect"})
    public String inspect(@PathVariable String serviceId, @RequestParam Map<String, String> queryParams, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController inspect start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONObject jo = client.inspectServiceCmd().withServiceId(serviceId).exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController inspect end******");
        }
        return jo.toString();
    }
    
    /**
     * 创建service
     * @param serviceConfig
     * @param request
     * @return
     */
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/create"})
    @ResponseBody
    public String create(@RequestBody Map<String, String> serviceConfig, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController create start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONObject jo = client.createServiceCmd().withSettings(JSONObject.fromObject(serviceConfig)).exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController create end******");
        }
        return jo.toString();
    }

    /**
     * 删除某个service
     * @param serviceId
     * @param request
     */
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.DELETE}, value={"/{serviceId}"})
    @ResponseBody
    public String remove(@PathVariable String serviceId, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController remove start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONObject result = client.removeServiceCmd().withServiceId(serviceId).exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController remove end******");
        }
        return result.toString();
    }

    /**
     * 更新某个service
     * @param serviceId
     * @param serviceConfig
     * @param params
     * @param request
     */
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{serviceId}/update"})
    @ResponseBody
    public String update(@PathVariable String serviceId, @RequestBody Map<String, String> serviceConfig
    		, @RequestParam Map<String, String> params , HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController update start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONObject result = client.updateServiceCmd().withServiceId(serviceId)
								 .withConf(JSONObject.fromObject(serviceConfig))
								 .withVersion(params.get("version"))
								 .exec();

		if (log.isDebugEnabled()) {
            log.debug("******ServicesController update end******");
        }
		return result.toString();
    }

    /**
     * Scale某个service
     * @param serviceId
     * @param serviceConfig
     * @param params
     * @param request
     */
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{serviceId}/scale"})
    @ResponseBody
    public String scale(@PathVariable String serviceId, @RequestBody Map<String, String> serviceConfig
    		, @RequestParam Map<String, String> params , HttpServletRequest request) throws Exception{
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController scale start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		
		JSONObject service = client.inspectServiceCmd().withServiceId(serviceId).exec();
		int version = service.getJSONObject("Version").getInt("Index");
		JSONObject spec = service.getJSONObject("Spec");
		JSONObject mode = spec.getJSONObject("Mode");
		if (mode.containsKey("Replicated")) {
			mode.getJSONObject("Replicated").put("Replicas", Integer.parseInt(serviceConfig.get("replicas").toString()));
		}
		spec.put("Mode", mode);
		JSONObject result = client.updateServiceCmd().withServiceId(serviceId)
								 .withConf(JSONObject.fromObject(spec))
								 .withVersion(String.valueOf(version))
								 .exec();

		if (log.isDebugEnabled()) {
            log.debug("******ServicesController scale end******");
        }
		return result.toString();
    }

    /**
     * 启动service
     * @param serviceId
     * @param params
     * @param request
     */
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{serviceId}/start"})
    @ResponseBody
    public String start(@PathVariable String serviceId, @RequestParam Map<String, String> params , HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController start start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		//获取service的所有task
		String filters = "{\"service\":[\""+serviceId+"\"]}";
		JSONArray ja = client.listTasksCmd().withFilters(JSONObject.fromObject(filters)).exec();
		//获取container id list
		for (int i = 0; i < ja.size(); i ++) {
			JSONObject statusJo = ja.getJSONObject(i).getJSONObject(Const.SERVICE_KEY_STATUS);
			String state = statusJo.getString(Const.SERVICE_KEY_STATE);
			if (!Const.CONTAINER_STATE_RUNNING.equals(state)) {
				String cid = statusJo.getJSONObject(Const.SERVICE_KEY_CONTAINER_STATUS).getString(Const.SERVICE_KEY_CONTAINER_ID);
				//启动container
				client.startContainerCmd().withContainerId(cid).exec();
			}
		}
		
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController start end******");
        }
        return "{}";
    }

    /**
     * 停止某个service
     * @param serviceId
     * @param params
     * @param request
     */
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{serviceId}/stop"})
    @ResponseBody
    public String stop(@PathVariable String serviceId, @RequestParam Map<String, String> params , HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController stop start******");
        }
        //TODO
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		//获取service的所有task
		String filters = "{\"service\":[\""+serviceId+"\"]}";
		JSONArray ja = client.listTasksCmd().withFilters(JSONObject.fromObject(filters)).exec();
		//获取container id list
		for (int i = 0; i < ja.size(); i ++) {
			JSONObject statusJo = ja.getJSONObject(i).getJSONObject(Const.SERVICE_KEY_STATUS);
			String state = statusJo.getString(Const.SERVICE_KEY_STATE);
			if (Const.CONTAINER_STATE_RUNNING.equals(state)) {
				String cid = statusJo.getJSONObject(Const.SERVICE_KEY_CONTAINER_STATUS).getString(Const.SERVICE_KEY_CONTAINER_ID);
				//停止container
				client.stopContainerCmd().withContainerId(cid).exec();
			}
		}
		
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController stop end******");
        }
        return "{}";
    }

    /**
     * 查看某个service运行信息
     * @param serviceId
     * @param params
     * @param request
     */
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, value={"/{serviceId}/info"})
    @ResponseBody
    public String info(@PathVariable String serviceId, @RequestParam Map<String, String> params , HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController info start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONObject service = client.inspectServiceCmd().withServiceId(serviceId).exec();
		
		JSONArray containers = client.listContainersCmd()
								.withFilters(JSONObject.fromObject("{\"label\":[\"com.docker.swarm.service.id="+serviceId+"\"]}"))
								.withShowAll(true).withShowSize(true).exec();
		JSONArray scs = new JSONArray();
		JSONObject sc = null;
		for (Object obj : containers) {
			sc = new JSONObject();
			JSONObject container = (JSONObject) obj;
			sc.put("Id", container.getString("Id"));
			sc.put("Name", container.getJSONArray("Names").getString(0));
			sc.put("State", container.getString("State"));
			sc.put("Status", container.getString("Status"));
			scs.add(sc);
		}
		service.put("ContainerInfo", scs);
		
        if (log.isDebugEnabled()) {
            log.debug("******ServicesController info end******");
        }
        return service.toString();
    }
}
