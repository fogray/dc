package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public interface PauseContainerCmd extends SyncDockerCmd<Void>{

	@CheckForNull
	String getContainerId();

	public PauseContainerCmd withContainerId(@Nonnull String containerId);
    
	interface Exec extends SyncDockerCmdExec<PauseContainerCmd, Void>{};
}
