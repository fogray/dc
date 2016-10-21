package com.dc.api.command.container.impl;

import com.dc.api.command.container.UnPauseContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public class UnPauseContainerCmdImpl extends AbstrDockerCmd<UnPauseContainerCmd, Void> 
	implements UnPauseContainerCmd{

	private String containerId;
	
	public UnPauseContainerCmdImpl(SyncDockerCmdExec<UnPauseContainerCmd, Void> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public UnPauseContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

}
