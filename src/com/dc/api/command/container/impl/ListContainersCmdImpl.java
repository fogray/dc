package com.dc.api.command.container.impl;

import com.dc.api.command.container.ListContainersCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ListContainersCmdImpl extends AbstrDockerCmd<ListContainersCmd, JSONArray> 
	implements ListContainersCmd{

	private Boolean showAll=false, showSize;
	private Integer limit;
	private String since, before;
	
	private JSONObject filters;
	
	public ListContainersCmdImpl(SyncDockerCmdExec<ListContainersCmd, JSONArray> execution) {
		super(execution);
	}

	@Override
	public JSONObject getFilters() {
		return filters;
	}

	@Override
	public Boolean isShowAll() {
		return showAll;
	}

	@Override
	public Integer getLimit() {
		return limit;
	}

	@Override
	public String getBeforeId() {
		return before;
	}

	@Override
	public Boolean isShowSize() {
		return showSize;
	}

	@Override
	public String getSinceId() {
		return since;
	}

	@Override
	public ListContainersCmd withShowAll(Boolean showAll) {
		this.showAll = showAll;
		return this;
	}

	@Override
	public ListContainersCmd withShowSize(Boolean showSize) {
		this.showSize = showSize;
		return this;
	}

	@Override
	public ListContainersCmd withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	@Override
	public ListContainersCmd withSinceId(String since) {
		this.since = since;
		return this;
	}

	@Override
	public ListContainersCmd withBeforeId(String before) {
		this.before = before;
		return this;
	}

	@Override
	public ListContainersCmd withFilters(JSONObject filters) {
		this.filters = filters;
		return this;
	}
}
