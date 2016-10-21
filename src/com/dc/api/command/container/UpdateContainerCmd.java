package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface UpdateContainerCmd extends SyncDockerCmd<JSONObject>{
	
	@CheckForNull
	String getContainerId();
	@CheckForNull
	JSONObject getSettings();
	
	public UpdateContainerCmd withContainerId(@Nonnull String containerId);
	public UpdateContainerCmd withSettings(@Nonnull JSONObject settings);
    
	interface Exec extends SyncDockerCmdExec<UpdateContainerCmd, JSONObject>{};
}
