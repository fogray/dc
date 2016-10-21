package com.dc.api.command.service.impl;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.command.service.InspectServiceCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class InspectServiceCmdImpl extends AbstrDockerCmd<InspectServiceCmd, JSONObject> 
	implements InspectServiceCmd{

	@CheckForNull
	private String serviceId;
	
	public InspectServiceCmdImpl(SyncDockerCmdExec<InspectServiceCmd, JSONObject> execution) {
		super(execution);
	}
	@Override
	public String getServiceId() {
		return serviceId;
	}

	@Override
	public InspectServiceCmd withServiceId(@Nonnull String serviceId) {
		this.serviceId = serviceId;
		return this;
	}

    
}
