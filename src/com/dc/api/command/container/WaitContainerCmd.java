package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface WaitContainerCmd extends SyncDockerCmd<JSONObject>{

	@CheckForNull
	String getContainerId();

	public WaitContainerCmd withContainerId(@Nonnull String containerId);
    
	interface Exec extends SyncDockerCmdExec<WaitContainerCmd, JSONObject>{};
}
