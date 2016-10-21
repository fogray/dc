package com.dc.api.command.container.impl;

import javax.annotation.CheckForNull;

import com.dc.api.command.container.InspectContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class InspectContainerCmdImpl extends AbstrDockerCmd<InspectContainerCmd, JSONObject> 
	implements InspectContainerCmd{

	@CheckForNull
	private String containerId;
	private Boolean showSize=false;
	
	public InspectContainerCmdImpl(SyncDockerCmdExec<InspectContainerCmd, JSONObject> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public Boolean isShowSize() {
		return showSize;
	}

	@Override
	public InspectContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public InspectContainerCmd withShowSize(Boolean showSize) {
		this.showSize = showSize;
		return this;
	}

    
}
