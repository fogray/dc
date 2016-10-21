package com.dc.api.command.service.impl;

import com.dc.api.command.service.ListServicesCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ListServicesCmdImpl extends AbstrDockerCmd<ListServicesCmd, JSONArray> 
	implements ListServicesCmd{

	private JSONObject filters;
	
	public ListServicesCmdImpl(SyncDockerCmdExec<ListServicesCmd, JSONArray> execution) {
		super(execution);
	}

	@Override
	public JSONObject getFilters() {
		return this.filters;
	}

	@Override
	public ListServicesCmd withFilters(JSONObject filters) {
		this.filters = filters;
		return this;
	}

    
}
