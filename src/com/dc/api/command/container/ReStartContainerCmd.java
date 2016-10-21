package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public interface ReStartContainerCmd extends SyncDockerCmd<Void>{

	@CheckForNull
	String getContainerId();

	@CheckForNull
	Long getWaitSeconds();
	
	public ReStartContainerCmd withContainerId(@Nonnull String containerId);
	public ReStartContainerCmd withWaitSeconds(@Nonnull Long seconds);
    
	interface Exec extends SyncDockerCmdExec<ReStartContainerCmd, Void>{};
}
