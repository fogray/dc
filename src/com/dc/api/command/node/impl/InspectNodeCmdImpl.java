package com.dc.api.command.node.impl;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.command.node.InspectNodeCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class InspectNodeCmdImpl extends AbstrDockerCmd<InspectNodeCmd, JSONObject> 
	implements InspectNodeCmd{

	@CheckForNull
	private String nodeId;
	
	public InspectNodeCmdImpl(SyncDockerCmdExec<InspectNodeCmd, JSONObject> execution) {
		super(execution);
	}
	@Override
	public String getNodeId() {
		return nodeId;
	}

	@Override
	public InspectNodeCmd withNodeId(@Nonnull String nodeId) {
		this.nodeId = nodeId;
		return this;
	}
}
