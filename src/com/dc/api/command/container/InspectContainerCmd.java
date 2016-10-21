package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface InspectContainerCmd extends SyncDockerCmd<JSONObject>{
	
	@CheckForNull
	String getContainerId();
	
	@CheckForNull
	Boolean isShowSize();

	public InspectContainerCmd withContainerId(@Nonnull String containerId);
	public InspectContainerCmd withShowSize(Boolean showSize);
    
    interface Exec extends SyncDockerCmdExec<InspectContainerCmd, JSONObject>{};
}
