package com.dc.api.command.service.impl;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.command.service.UpdateServiceCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class UpdateServiceCmdImpl extends AbstrDockerCmd<UpdateServiceCmd, JSONObject> 
	implements UpdateServiceCmd{

	@CheckForNull
	private String serviceId, version;
	
	@CheckForNull
	private JSONObject conf;
    
	public UpdateServiceCmdImpl(SyncDockerCmdExec<UpdateServiceCmd, JSONObject> execution) {
		super(execution);
	}
	
	@Override
	public UpdateServiceCmd withServiceId(@Nonnull String serviceId){
		this.serviceId = serviceId;
		return this;
	}

    public String getServiceId() {
		return serviceId;
	}

	@Override
	public UpdateServiceCmd withConf(JSONObject conf) {
		this.conf = conf;
		return this;
	}

	@Override
	public JSONObject getConf() {
		return conf;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public UpdateServiceCmd withVersion(String version) {
		this.version = version;
		return this;
	}
}
