package com.dc.api.command.image;

import javax.annotation.CheckForNull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ListImagesCmd extends SyncDockerCmd<JSONArray>{

	@CheckForNull
	Boolean isAll();
	@CheckForNull
	String getFilter();
	
    @CheckForNull
    JSONObject getFilters();
    
    ListImagesCmd withAll(Boolean all);
    ListImagesCmd withFilter(String filter);
    ListImagesCmd withFilters(JSONObject filters);
    
    interface Exec extends SyncDockerCmdExec<ListImagesCmd, JSONArray>{};
}
