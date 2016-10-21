package com.dc.api.command.service.impl;

import javax.annotation.CheckForNull;

import com.dc.api.command.service.CreateServiceCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class CreateServiceCmdImpl extends AbstrDockerCmd<CreateServiceCmd, JSONObject> 
	implements CreateServiceCmd{

	@CheckForNull
	private JSONObject settings;
    
	public CreateServiceCmdImpl(SyncDockerCmdExec<CreateServiceCmd, JSONObject> execution) {
		super(execution);
	}
	
	@Override
	public CreateServiceCmd withSettings(JSONObject settings) {
		this.settings = settings;
		return this;
	}

    public JSONObject getSettings() {
		return settings;
	}
}
