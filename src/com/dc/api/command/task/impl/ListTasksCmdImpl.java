package com.dc.api.command.task.impl;

import com.dc.api.command.task.ListTasksCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ListTasksCmdImpl extends AbstrDockerCmd<ListTasksCmd, JSONArray> 
	implements ListTasksCmd{

	private JSONObject filters;
	
	public ListTasksCmdImpl(SyncDockerCmdExec<ListTasksCmd, JSONArray> execution) {
		super(execution);
	}

	@Override
	public JSONObject getFilters() {
		return this.filters;
	}

	@Override
	public ListTasksCmd withFilters(JSONObject filters) {
		this.filters = filters;
		return this;
	}
    
}
