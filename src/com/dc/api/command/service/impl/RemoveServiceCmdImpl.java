package com.dc.api.command.service.impl;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.command.service.RemoveServiceCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class RemoveServiceCmdImpl extends AbstrDockerCmd<RemoveServiceCmd, JSONObject> 
	implements RemoveServiceCmd{

	@CheckForNull
	private String id;
    
	public RemoveServiceCmdImpl(SyncDockerCmdExec<RemoveServiceCmd, JSONObject> execution) {
		super(execution);
	}
	
	@Override
	public RemoveServiceCmd withServiceId(@Nonnull String id){
		this.id = id;
		return this;
	}

    public String getServiceId() {
		return id;
	}
}
