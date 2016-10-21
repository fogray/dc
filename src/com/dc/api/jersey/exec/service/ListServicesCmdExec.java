package com.dc.api.jersey.exec.service;

import static com.google.common.net.UrlEscapers.urlPathSegmentEscaper;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.service.ListServicesCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONArray;

public class ListServicesCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<ListServicesCmd, JSONArray>{

	public ListServicesCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public JSONArray exec(ListServicesCmd command) {
		WebTarget webTarget = getBaseResource().path("/services");

        if (command.getFilters() != null && !command.getFilters().isEmpty()) {
            webTarget = webTarget
                    .queryParam("filters", urlPathSegmentEscaper().escape(command.getFilters().toString()));
        }

        String list = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONArray.fromObject(list);
	}
	
}
