package com.dc.api.command.service;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface RemoveServiceCmd extends SyncDockerCmd<JSONObject>{

    @CheckForNull
    String getServiceId();
	
	public RemoveServiceCmd withServiceId(@Nonnull String id);
    
	interface Exec extends SyncDockerCmdExec<RemoveServiceCmd, JSONObject>{};
}
