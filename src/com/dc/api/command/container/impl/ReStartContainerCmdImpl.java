package com.dc.api.command.container.impl;

import com.dc.api.command.container.ReStartContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public class ReStartContainerCmdImpl extends AbstrDockerCmd<ReStartContainerCmd, Void> 
	implements ReStartContainerCmd{

	private String containerId;
	private Long waitSeconds;
	
	public ReStartContainerCmdImpl(SyncDockerCmdExec<ReStartContainerCmd, Void> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public ReStartContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public Long getWaitSeconds() {
		return waitSeconds;
	}

	@Override
	public ReStartContainerCmd withWaitSeconds(Long seconds) {
		this.waitSeconds = seconds;
		return this;
	}
}
