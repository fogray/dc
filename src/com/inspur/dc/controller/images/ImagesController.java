package com.inspur.dc.controller.images;

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
import com.dc.api.command.image.ListImagesCmd;
import com.dc.api.jersey.DockerClientImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/images")
public class ImagesController{
    private Logger log = Logger.getLogger(ImagesController.class);
    
    
    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String list(@RequestParam Map<String, String> params, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ImagesController list start******");
        }

		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		ListImagesCmd cmd = client.listImagesCmd();
		if (params.containsKey("all")) {
			cmd.withAll(Boolean.valueOf(params.get("all")));
		}
		if (params.containsKey("filter")) {
			cmd.withFilter(params.get("filter"));
		}
		if (params.containsKey("filters")) {
			cmd.withFilters(JSONObject.fromObject(params.get("filters")));
		}
		JSONArray l = cmd.exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******ImagesController list end******");
        }
        return l.toString();
    }

    @ResponseBody
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, value={"/{imageId}/inspect"})
    public String inspect(@PathVariable String imageId, @RequestParam Map<String, String> queryParams, HttpServletRequest request){
        if (log.isDebugEnabled()) {
            log.debug("******ImagesController inspect start******");
        }
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientImpl.getInstance(config);
		JSONObject jo = client.inspectImageCmd().withImageId(imageId).exec();
        
        if (log.isDebugEnabled()) {
            log.debug("******ImagesController inspect end******");
        }
        return jo.toString();
    }
}
