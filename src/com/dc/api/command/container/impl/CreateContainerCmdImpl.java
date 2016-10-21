package com.dc.api.command.container.impl;

import javax.annotation.CheckForNull;

import com.dc.api.command.container.CreateContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class CreateContainerCmdImpl extends AbstrDockerCmd<CreateContainerCmd, JSONObject> 
	implements CreateContainerCmd{

	@CheckForNull
	private String name;
	
	@CheckForNull
	private JSONObject settings;
    
	public CreateContainerCmdImpl(SyncDockerCmdExec<CreateContainerCmd, JSONObject> execution) {
		super(execution);
	}
	
	@Override
	public CreateContainerCmd withSettings(JSONObject settings) {
		this.settings = settings;
		return this;
	}

    public JSONObject getSettings() {
		return settings;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public CreateContainerCmd withName(String name) {
		this.name = name;
		return this;
	}
}
