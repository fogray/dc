package com.dc.api.command.container.impl;

import com.dc.api.command.container.StatsContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class StatsContainerCmdImpl extends AbstrDockerCmd<StatsContainerCmd, JSONObject> 
	implements StatsContainerCmd{

	private String containerId;
	private Boolean stream = false;
	
	public StatsContainerCmdImpl(SyncDockerCmdExec<StatsContainerCmd, JSONObject> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public StatsContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public Boolean isStream() {
		return stream;
	}

	@Override
	public StatsContainerCmd withStream(Boolean stream) {
		this.stream = stream;
		return this;
	}
}
