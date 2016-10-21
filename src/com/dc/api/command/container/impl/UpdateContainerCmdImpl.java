package com.dc.api.command.container.impl;

import javax.annotation.CheckForNull;

import com.dc.api.command.container.UpdateContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class UpdateContainerCmdImpl extends AbstrDockerCmd<UpdateContainerCmd, JSONObject> 
	implements UpdateContainerCmd{

	@CheckForNull
	private String containerId;
	
	@CheckForNull
	private JSONObject settings;
    
	public UpdateContainerCmdImpl(SyncDockerCmdExec<UpdateContainerCmd, JSONObject> execution) {
		super(execution);
	}
	
	@Override
	public UpdateContainerCmd withSettings(JSONObject settings) {
		this.settings = settings;
		return this;
	}

    public JSONObject getSettings() {
		return settings;
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public UpdateContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

}
