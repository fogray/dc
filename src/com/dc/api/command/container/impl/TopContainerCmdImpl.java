package com.dc.api.command.container.impl;

import com.dc.api.command.container.TopContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class TopContainerCmdImpl extends AbstrDockerCmd<TopContainerCmd, JSONObject> 
	implements TopContainerCmd{

	private String containerId, psArgs = "-ef";
	
	public TopContainerCmdImpl(SyncDockerCmdExec<TopContainerCmd, JSONObject> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public String getPsArgs() {
		return psArgs;
	}

	@Override
	public TopContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public TopContainerCmd withPsArgs(String psArgs) {
		this.psArgs = psArgs;
		return this;
	}

}
