package com.dc.api.command.image.impl;

import com.dc.api.command.image.ListImagesCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ListImagesCmdImpl extends AbstrDockerCmd<ListImagesCmd, JSONArray> 
	implements ListImagesCmd{

	private Boolean all=false;
	private String filter;
	
	private JSONObject filters;
	
	public ListImagesCmdImpl(SyncDockerCmdExec<ListImagesCmd, JSONArray> execution) {
		super(execution);
	}

	@Override
	public Boolean isAll() {
		return all;
	}

	@Override
	public String getFilter() {
		return filter;
	}

	@Override
	public JSONObject getFilters() {
		return filters;
	}

	@Override
	public ListImagesCmd withAll(Boolean all) {
		this.all = all;
		return this;
	}

	@Override
	public ListImagesCmd withFilter(String filter) {
		this.filter = filter;
		return this;
	}

	@Override
	public ListImagesCmd withFilters(JSONObject filters) {
		this.filters = filters;
		return this;
	}
}
