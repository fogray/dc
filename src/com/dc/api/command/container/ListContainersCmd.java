package com.dc.api.command.container;

import javax.annotation.CheckForNull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ListContainersCmd extends SyncDockerCmd<JSONArray>{

	@CheckForNull
	Boolean isShowAll();
	@CheckForNull
	Integer getLimit();
	@CheckForNull
	String getSinceId();
	@CheckForNull
	String getBeforeId();
	@CheckForNull
	Boolean isShowSize();
	
    @CheckForNull
    JSONObject getFilters();
    
    ListContainersCmd withShowAll(Boolean showAll);
    ListContainersCmd withShowSize(Boolean showSize);
    ListContainersCmd withLimit(Integer limit);
    ListContainersCmd withSinceId(String since);
    ListContainersCmd withBeforeId(String before);
    ListContainersCmd withFilters(JSONObject filters);
    
    interface Exec extends SyncDockerCmdExec<ListContainersCmd, JSONArray>{};
}
