package com.dc.api.command.node.impl;

import javax.annotation.CheckForNull;

import com.dc.api.command.node.UpdateNodeCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class UpdateNodeCmdImpl extends AbstrDockerCmd<UpdateNodeCmd, Void> 
	implements UpdateNodeCmd{

	@CheckForNull
	private String nodeId;
	
	@CheckForNull
	private String version;
	
	private JSONObject conf;
	
	public UpdateNodeCmdImpl(SyncDockerCmdExec<UpdateNodeCmd, Void> execution) {
		super(execution);
	}
	@Override
	public String getNodeId() {
		return nodeId;
	}

	@Override
	public String getVersion() {
		return version;
	}
	@Override
	public JSONObject getConf() {
		return conf;
	}
	@Override
	public UpdateNodeCmd withNodeId(String nodeId) {
		this.nodeId = nodeId;
		return this;
	}
	@Override
	public UpdateNodeCmd withVersion(String version) {
		this.version = version;
		return this;
	}
	@Override
	public UpdateNodeCmd withConf(JSONObject conf) {
		this.conf = conf;
		return this;
	}
}
