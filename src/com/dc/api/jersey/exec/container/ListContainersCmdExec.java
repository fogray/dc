package com.dc.api.jersey.exec.container;

import static com.google.common.net.UrlEscapers.urlPathSegmentEscaper;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.dc.api.command.container.ListContainersCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.jersey.AbstrDockerCmdExec;

import net.sf.json.JSONArray;

public class ListContainersCmdExec extends AbstrDockerCmdExec implements SyncDockerCmdExec<ListContainersCmd, JSONArray>{

	public ListContainersCmdExec(WebTarget baseResource) {
		super(baseResource);
	}
	
	@Override
	public JSONArray exec(ListContainersCmd command) {
		
		WebTarget webTarget = getBaseResource().path("/containers/json")
				.queryParam("since", command.getSinceId())
                .queryParam("before", command.getBeforeId());

        webTarget = booleanQueryParam(webTarget, "all", command.isShowAll());
        webTarget = booleanQueryParam(webTarget, "size", command.isShowSize());

        if (command.getLimit() != null && command.getLimit() >= 0) {
            webTarget = webTarget.queryParam("limit", String.valueOf(command.getLimit()));
        }
		
		
        if (command.getFilters() != null && !command.getFilters().isEmpty()) {
            webTarget = webTarget
                    .queryParam("filters", urlPathSegmentEscaper().escape(command.getFilters().toString()));
        }

        String list = webTarget.request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return JSONArray.fromObject(list);
	}
	
}
