package com.dc.api.command.container.impl;

import com.dc.api.command.container.StopContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public class StopContainerCmdImpl extends AbstrDockerCmd<StopContainerCmd, Void> 
	implements StopContainerCmd{

	private String containerId;
	private Long waitSeconds;
	
	public StopContainerCmdImpl(SyncDockerCmdExec<StopContainerCmd, Void> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public StopContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public Long getWaitSeconds() {
		return waitSeconds;
	}

	@Override
	public StopContainerCmd withWaitSeconds(Long seconds) {
		this.waitSeconds = seconds;
		return this;
	}
}
