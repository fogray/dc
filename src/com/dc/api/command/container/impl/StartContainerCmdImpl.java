package com.dc.api.command.container.impl;

import com.dc.api.command.container.StartContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public class StartContainerCmdImpl extends AbstrDockerCmd<StartContainerCmd, Void> 
	implements StartContainerCmd{

	private String containerId, detachKeys;
	
	public StartContainerCmdImpl(SyncDockerCmdExec<StartContainerCmd, Void> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public StartContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public String getDetachKeys() {
		return detachKeys;
	}

	@Override
	public StartContainerCmd withDetachKeys(String detachKeys) {
		this.detachKeys = detachKeys;
		return this;
	}

}
