package com.dc.api.jersey.exec.image;

import static com.google.common.net.UrlEscapers.urlPathSegmentEscaper;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.image.ListImagesCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONArray;

public class ListImagesCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<ListImagesCmd, JSONArray>{

	public ListImagesCmdExec(WebTarget baseResource) {
		super(baseResource);
	}
	
	@Override
	public JSONArray exec(ListImagesCmd command) {
		
		WebTarget webTarget = getBaseResource().path("/images/json");

        webTarget = booleanQueryParam(webTarget, "all", command.isAll());

        if (command.getFilter() != null) {
            webTarget = webTarget.queryParam("filter", command.getFilter());
        }
		
        if (command.getFilters() != null && !command.getFilters().isEmpty()) {
            webTarget = webTarget
                    .queryParam("filters", urlPathSegmentEscaper().escape(command.getFilters().toString()));
        }
        System.out.println(webTarget.toString());
        String list = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONArray.fromObject(list);
	}
	
}
