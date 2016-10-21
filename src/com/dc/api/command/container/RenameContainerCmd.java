package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public interface RenameContainerCmd extends SyncDockerCmd<Void>{

	@CheckForNull
	String getContainerId();

	@CheckForNull
	String getNewName ();
	
	public RenameContainerCmd withContainerId(@Nonnull String containerId);
	public RenameContainerCmd withNewName(String newName);
    
	interface Exec extends SyncDockerCmdExec<RenameContainerCmd, Void>{};
}
