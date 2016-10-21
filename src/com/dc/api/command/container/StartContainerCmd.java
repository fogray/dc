package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public interface StartContainerCmd extends SyncDockerCmd<Void>{

	@CheckForNull
	String getContainerId();

	@CheckForNull
	String getDetachKeys ();
	
	public StartContainerCmd withContainerId(@Nonnull String containerId);
	public StartContainerCmd withDetachKeys(@Nonnull String detachKeys);
    
	interface Exec extends SyncDockerCmdExec<StartContainerCmd, Void>{};
}
