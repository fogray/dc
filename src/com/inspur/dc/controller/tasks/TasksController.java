package com.inspur.dc.controller.tasks;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.api.client.DefaultDockerClientConfig;
import com.dc.api.client.DockerClient;
import com.dc.api.command.task.ListTasksCmd;
import com.dc.api.jersey.DockerClientImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/tasks")
public class TasksController{
    private Logger log = Logger.getLogger(TasksController.class);
    
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public String list(@RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******TasksController list start******");
        }

		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		ListTasksCmd cmd = client.listTasksCmd();
		if (params.containsKey("filters")) {
			cmd.withFilters(JSONObject.fromObject(params.get("filters")));
		}
		JSONArray ja = cmd.exec();
		
        if (log.isDebugEnabled()) {
            log.debug("******TasksController list end******");
        }
        return ja.toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, value={"/{taskId}"})
    public String inspect(@PathVariable String taskId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******TasksController inspect start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONObject jo = client.inspectTaskCmd().withTaskId(taskId).exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******TasksController inspect end******");
        }
        return jo.toString();
    }
}
