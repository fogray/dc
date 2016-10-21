package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public interface StopContainerCmd extends SyncDockerCmd<Void>{

	@CheckForNull
	String getContainerId();

	@CheckForNull
	Long getWaitSeconds();
	
	public StopContainerCmd withContainerId(@Nonnull String containerId);
	public StopContainerCmd withWaitSeconds(@Nonnull Long seconds);
    
	interface Exec extends SyncDockerCmdExec<StopContainerCmd, Void>{};
}
