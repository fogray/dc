package com.dc.api.command.node.impl;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.command.node.RemoveNodeCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public class RemoveNodeCmdImpl extends AbstrDockerCmd<RemoveNodeCmd, Void> 
	implements RemoveNodeCmd{

	@CheckForNull
	private String nodeId;
	
	private Boolean force = false;
	
	public RemoveNodeCmdImpl(SyncDockerCmdExec<RemoveNodeCmd, Void> execution) {
		super(execution);
	}
	@Override
	public String getNodeId() {
		return nodeId;
	}

	@Override
	public RemoveNodeCmd withNodeId(@Nonnull String nodeId) {
		this.nodeId = nodeId;
		return this;
	}
	@Override
	public boolean getForce() {
		return force;
	}
	@Override
	public RemoveNodeCmd withForce(Boolean force) {
		this.force = force;
		return this;
	}
}
