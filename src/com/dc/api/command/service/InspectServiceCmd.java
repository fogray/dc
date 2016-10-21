package com.dc.api.command.service;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface InspectServiceCmd extends SyncDockerCmd<JSONObject>{

    @CheckForNull
    String getServiceId();
	
	public InspectServiceCmd withServiceId(@Nonnull String serviceId);
    
    interface Exec extends SyncDockerCmdExec<InspectServiceCmd, JSONObject>{};
}
