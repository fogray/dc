package com.dc.api.command.container.impl;

import com.dc.api.command.container.KillContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public class KillContainerCmdImpl extends AbstrDockerCmd<KillContainerCmd, Void> 
	implements KillContainerCmd{

	private String containerId;
	private String signal="KILL";
	
	public KillContainerCmdImpl(SyncDockerCmdExec<KillContainerCmd, Void> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public KillContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public String getSignal() {
		return signal;
	}

	@Override
	public KillContainerCmd withSignal(String signal) {
		this.signal = signal;
		return this;
	}
}
