package com.inspur.dc.controller.containers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.api.client.DefaultDockerClientConfig;
import com.dc.api.client.DockerClient;
import com.dc.api.command.container.AttachContainerCmd;
import com.dc.api.command.container.CreateContainerCmd;
import com.dc.api.command.container.InspectContainerCmd;
import com.dc.api.command.container.KillContainerCmd;
import com.dc.api.command.container.LogContainerCmd;
import com.dc.api.command.container.ReStartContainerCmd;
import com.dc.api.command.container.RemoveContainerCmd;
import com.dc.api.command.container.StartContainerCmd;
import com.dc.api.command.container.StatsContainerCmd;
import com.dc.api.command.container.StopContainerCmd;
import com.dc.api.command.container.TopContainerCmd;
import com.dc.api.jersey.DockerClientImpl;
import com.dc.api.jersey.exec.container.AttachContainerResultCallback;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/containers")
public class ContainersController{
    private Logger log = Logger.getLogger(ContainersController.class);
    
    
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public String list(Model model, HttpServletRequest req){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController list start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONArray l = client.listContainersCmd().exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController list end******");
        }
        return l.toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, value={"/{containerId}/inspect"})
    public String inspect(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController inspect start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		InspectContainerCmd cmd = client.inspectContainerCmd().withContainerId(containerId);
		if (params.containsKey("size")) {
			String showSize = params.get("size");
			cmd.withShowSize(showSize.isEmpty());
		}
		JSONObject jo = cmd.exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController inspect end******");
        }
        return jo.toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, value={"/{containerId}/changes"})
    public String changes(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController inspect start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONArray ja = client.inspectContainerChangesCmd().withContainerId(containerId).exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController inspect end******");
        }
        return ja.toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/create"})
    public String create(@RequestBody Map<String, String> body, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController create start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		CreateContainerCmd cmd = client.createContainerCmd();
		if (params.containsKey("name")) {
			cmd.withName(params.get("name"));
		}
		JSONObject jo = cmd.withSettings(JSONObject.fromObject(body)).exec();
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController create end******");
        }
        return jo.toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{containerId}/update"})
    public String update(@PathVariable String containerId, @RequestBody Map<String, String> body, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController update start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONObject jo = client.updateContainerCmd().withContainerId(containerId).withSettings(JSONObject.fromObject(body)).exec();
        
		if (log.isDebugEnabled()) {
            log.debug("******ContainersController update end******");
        }
        return jo.toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{containerId}/kill"})
    public void kill(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController kill start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		KillContainerCmd cmd = client.killContainerCmd().withContainerId(containerId);
		if (params.containsKey("signal")) {
			cmd.withSignal(params.get("signal"));
		}
        cmd.exec();
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController kill end******");
        }
    }

    //TODO
    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{containerId}/attach"})
    public String attach(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController kill start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		AttachContainerCmd cmd = client.attachContainerCmd().withContainerId(containerId);
		if (params.containsKey("detachKeys")) {
			cmd.withDetachKeys(params.get("detachKeys"));
		}
		if (params.containsKey("logs")) {
			cmd.withLogs(Boolean.valueOf(params.get("logs")));
		}
		if (params.containsKey("stream")) {
			cmd.withStream(Boolean.valueOf(params.get("stream")));
		}
		if (params.containsKey("stdin")) {
			cmd.withStdin(Boolean.valueOf(params.get("stdin")));
		}
		if (params.containsKey("stdout")) {
			cmd.withStdout(Boolean.valueOf(params.get("stdout")));
		}
		if (params.containsKey("stderr")) {
			cmd.withStderr(Boolean.valueOf(params.get("stderr")));
		}
//		cmd.exec(new AttachContainerResultCallback());
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController kill end******");
        }
        return cmd.exec(new AttachContainerResultCallback()).toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, value={"/{containerId}/logs"})
    public String logs(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController logs start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		LogContainerCmd cmd = client.logContainerCmd().withContainerId(containerId);
		if (params.containsKey("details")) {
			cmd.withDetails(Boolean.valueOf(params.get("details")));
		}
		if (params.containsKey("follow")) {
			cmd.withFollow(Boolean.valueOf(params.get("follow")));
		}
		if (params.containsKey("stdout")) {
			cmd.withStdout(Boolean.valueOf(params.get("stdout")));
		}
		if (params.containsKey("stderr")) {
			cmd.withStderr(Boolean.valueOf(params.get("stderr")));
		}
		if (params.containsKey("timestamps")) {
			cmd.withTimestamps(Boolean.valueOf(params.get("timestamps")));
		}
		if (params.containsKey("since")) {
			cmd.withSince(Long.valueOf(params.get("since")));
		}
		if (params.containsKey("tail")) {
			cmd.withTail(params.get("tail"));
		}
//		cmd.exec(new AttachContainerResultCallback());
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController logs end******");
        }
        return cmd.exec(new AttachContainerResultCallback()).toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{containerId}/pause"})
    public void pause(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController pause start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		client.pauseContainerCmd().withContainerId(containerId).exec();
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController pause end******");
        }
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{containerId}/unpause"})
    public void unpause(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController unpause start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		client.unPauseContainerCmd().withContainerId(containerId).exec();
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController unpause end******");
        }
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.DELETE}, value={"/{containerId}"})
    public void remove(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController remove start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		RemoveContainerCmd cmd = client.removeContainerCmd().withContainerId(containerId);
		if (params.containsKey("force")) {
			cmd.withForce(Boolean.valueOf(params.get("force")));
		}
		if (params.containsKey("v")) {
			cmd.withAlsoVolumes(Boolean.valueOf(params.get("v")));
		}
		cmd.exec();
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController remove end******");
        }
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{containerId}/rename"})
    public void rename(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController rename start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		client.renameContainerCmd().withContainerId(containerId).withNewName(params.get("name"));
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController rename end******");
        }
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{containerId}/resize"})
    public void resize(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController resize start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		client.resizeContainerCmd().withContainerId(containerId)
				.withHeight(Integer.valueOf(params.get("h")))
				.withWidth(Integer.valueOf(params.get("w")))
				.exec();

		if (log.isDebugEnabled()) {
            log.debug("******ContainersController resize end******");
        }
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{containerId}/start"})
    public void start(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController start restart******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		StartContainerCmd cmd = client.startContainerCmd().withContainerId(containerId);
		if (params.containsKey("detachKeys")) {
			cmd.withDetachKeys(params.get("detachKeys"));
		}
		cmd.exec();

		if (log.isDebugEnabled()) {
            log.debug("******ContainersController start end******");
        }
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{containerId}/restart"})
    public void restart(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController restart restart******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		ReStartContainerCmd cmd = client.reStartContainerCmd().withContainerId(containerId);
		if (params.containsKey("t")) {
			cmd.withWaitSeconds(Long.valueOf(params.get("t")));
		}
		cmd.exec();

		if (log.isDebugEnabled()) {
            log.debug("******ContainersController restart end******");
        }
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{containerId}/stop"})
    public void stop(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController stop restart******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		StopContainerCmd cmd = client.stopContainerCmd().withContainerId(containerId);
		if (params.containsKey("t")) {
			cmd.withWaitSeconds(Long.valueOf(params.get("t")));
		}
		cmd.exec();

		if (log.isDebugEnabled()) {
            log.debug("******ContainersController stop end******");
        }
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, value={"/{containerId}/stats"})
    public String stats(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController stats restart******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		StatsContainerCmd cmd = client.statsContainerCmd().withContainerId(containerId);
		if (params.containsKey("stream")) {
			cmd.withStream(Boolean.valueOf(params.get("stream")));
		}
		JSONObject jo = cmd.exec();

		if (log.isDebugEnabled()) {
            log.debug("******ContainersController stats end******");
        }
		return jo.toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, value={"/{containerId}/top"})
    public String top(@PathVariable String containerId, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController top restart******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		TopContainerCmd cmd = client.topContainerCmd().withContainerId(containerId);
		if (params.containsKey("ps_args")) {
			cmd.withPsArgs(params.get("ps_args"));
		}
		JSONObject jo = cmd.exec();

		if (log.isDebugEnabled()) {
            log.debug("******ContainersController top end******");
        }
		return jo.toString();
    }


    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/{containerId}/wait"})
    public String wait(@PathVariable String containerId, @RequestBody Map<String, String> body, @RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ContainersController wait start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONObject jo = client.waitContainerCmd().withContainerId(containerId).exec();
        
		if (log.isDebugEnabled()) {
            log.debug("******ContainersController wait end******");
        }
        return jo.toString();
    }
}
