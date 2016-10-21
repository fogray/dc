package com.dc.api.command.node.impl;

import com.dc.api.command.node.ListNodesCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ListNodesCmdImpl extends AbstrDockerCmd<ListNodesCmd, JSONArray> 
	implements ListNodesCmd{

	private JSONObject filters;
	
	public ListNodesCmdImpl(SyncDockerCmdExec<ListNodesCmd, JSONArray> execution) {
		super(execution);
	}

	@Override
	public JSONObject getFilters() {
		return this.filters;
	}

	@Override
	public ListNodesCmd withFilters(JSONObject filters) {
		this.filters = filters;
		return this;
	}
    
}
