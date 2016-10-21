package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public interface RemoveContainerCmd extends SyncDockerCmd<Void>{

	@CheckForNull
	String getContainerId();
	
	@CheckForNull
	Boolean isAlsoVolumes();
	@CheckForNull
	Boolean isForce();

	public RemoveContainerCmd withContainerId(@Nonnull String containerId);
	public RemoveContainerCmd withAlsoVolumes(Boolean volumes);
	public RemoveContainerCmd withForce(Boolean force);
    
	interface Exec extends SyncDockerCmdExec<RemoveContainerCmd, Void>{};
}
