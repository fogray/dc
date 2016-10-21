package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public interface KillContainerCmd extends SyncDockerCmd<Void>{

	@CheckForNull
	String getContainerId();

	@CheckForNull
	String getSignal();
	
	public KillContainerCmd withContainerId(@Nonnull String containerId);
	public KillContainerCmd withSignal(@Nonnull String signal);
    
	interface Exec extends SyncDockerCmdExec<KillContainerCmd, Void>{};
}
