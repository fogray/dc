package com.dc.api.command.container.impl;

import javax.annotation.Nonnull;

import com.dc.api.command.container.InspectContainerChangesCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;

public class InspectContainerChangesCmdImpl extends AbstrDockerCmd<InspectContainerChangesCmd, JSONArray> 
	implements InspectContainerChangesCmd{

	private String containerId;
	
	public InspectContainerChangesCmdImpl(SyncDockerCmdExec<InspectContainerChangesCmd, JSONArray> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public InspectContainerChangesCmd withContainerId(@Nonnull String containerId) {
		this.containerId = containerId;
		return this;
	}
}
