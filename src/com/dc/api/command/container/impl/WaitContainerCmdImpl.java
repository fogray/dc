package com.dc.api.command.container.impl;

import com.dc.api.command.container.WaitContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class WaitContainerCmdImpl extends AbstrDockerCmd<WaitContainerCmd, JSONObject> 
	implements WaitContainerCmd{

	private String containerId;
	
	public WaitContainerCmdImpl(SyncDockerCmdExec<WaitContainerCmd, JSONObject> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public WaitContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

}
