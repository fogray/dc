package com.dc.api.command.container.impl;

import com.dc.api.command.container.RenameContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public class RenameContainerCmdImpl extends AbstrDockerCmd<RenameContainerCmd, Void> 
	implements RenameContainerCmd{

	private String containerId, newName;
	
	public RenameContainerCmdImpl(SyncDockerCmdExec<RenameContainerCmd, Void> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public RenameContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public String getNewName() {
		return newName;
	}

	@Override
	public RenameContainerCmd withNewName(String newName) {
		this.newName = newName;
		return this;
	}
}
