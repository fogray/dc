package com.dc.api.command.container.impl;

import com.dc.api.command.container.RemoveContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public class RemoveContainerCmdImpl extends AbstrDockerCmd<RemoveContainerCmd, Void> 
	implements RemoveContainerCmd{

	private String containerId;
	private Boolean volumes = false, force = false;
	
	public RemoveContainerCmdImpl(SyncDockerCmdExec<RemoveContainerCmd, Void> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public RemoveContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public Boolean isAlsoVolumes() {
		return volumes;
	}

	@Override
	public Boolean isForce() {
		return force;
	}

	@Override
	public RemoveContainerCmd withAlsoVolumes(Boolean volumes) {
		this.volumes = volumes;
		return this;
	}

	@Override
	public RemoveContainerCmd withForce(Boolean force) {
		this.force = force;
		return this;
	}

}
