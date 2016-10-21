package com.dc.api.command.container.impl;

import com.dc.api.command.container.PauseContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public class PauseContainerCmdImpl extends AbstrDockerCmd<PauseContainerCmd, Void> 
	implements PauseContainerCmd{

	private String containerId;
	
	public PauseContainerCmdImpl(SyncDockerCmdExec<PauseContainerCmd, Void> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public PauseContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

}
