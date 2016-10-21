package com.inspur.dc.controller.nodes;

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
import com.dc.api.command.node.ListNodesCmd;
import com.dc.api.command.node.RemoveNodeCmd;
import com.dc.api.jersey.DockerClientImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/nodes")
public class NodesController{
    private Logger log = Logger.getLogger(NodesController.class);
    
    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String list(@RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******NodesController list start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		ListNodesCmd cmd = client.listNodesCmd();
		if (params.containsKey("filters")) {
			cmd.withFilters(JSONObject.fromObject(params.get("filters")));
		}
		JSONArray ja = cmd.exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******NodesController list end******");
        }
        return ja.toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, value={"/{nodeId}/inspect"})
    public String inspect(@PathVariable String nodeId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******NodesController inspect start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONObject jo = client.inspectNodeCmd().withNodeId(nodeId).exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******NodesController inspect end******");
        }
        return jo.toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{nodeId}/update"})
    public void update(@PathVariable String nodeId, @RequestBody Map<String, String> body, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******NodesController update start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		client.updateNodeCmd().withNodeId(nodeId)
							  .withVersion(params.get("version"))
							  .withConf(JSONObject.fromObject(body)).exec();
        
		if (log.isDebugEnabled()) {
            log.debug("******NodesController update end******");
        }
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.DELETE}, value={"/{nodeId}"})
    public void remove(@PathVariable String nodeId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******NodesController remove start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		RemoveNodeCmd cmd = client.removeNodeCmd().withNodeId(nodeId);
		if (params.containsKey("force")) {
			cmd.withForce(Boolean.valueOf(params.get("force")));
		}
		cmd.exec();
        if (log.isDebugEnabled()) {
            log.debug("******NodesController remove end******");
        }
    }
}
