package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public interface ResizeContainerCmd extends SyncDockerCmd<Void>{

	@CheckForNull
	String getContainerId();

	@CheckForNull
	Integer getHeight();
	@CheckForNull
	Integer getWidth();
	
	public ResizeContainerCmd withContainerId(@Nonnull String containerId);
	public ResizeContainerCmd withHeight(@Nonnull Integer height);
	public ResizeContainerCmd withWidth(@Nonnull Integer width);
    
	interface Exec extends SyncDockerCmdExec<ResizeContainerCmd, Void>{};
}
