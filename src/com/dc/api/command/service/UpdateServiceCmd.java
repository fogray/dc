package com.dc.api.command.service;

import javax.annotation.CheckForNull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface UpdateServiceCmd extends SyncDockerCmd<JSONObject>{

    @CheckForNull
    String getServiceId();
    
    @CheckForNull
    String getVersion();
    
	public JSONObject getConf();
	
	public UpdateServiceCmd withServiceId(String serviceId);
	public UpdateServiceCmd withVersion(String version);
	public UpdateServiceCmd withConf(JSONObject conf);
	
    interface Exec extends SyncDockerCmdExec<UpdateServiceCmd, JSONObject>{};
}
