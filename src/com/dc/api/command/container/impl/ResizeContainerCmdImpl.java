package com.dc.api.command.container.impl;

import com.dc.api.command.container.ResizeContainerCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public class ResizeContainerCmdImpl extends AbstrDockerCmd<ResizeContainerCmd, Void> 
	implements ResizeContainerCmd{

	private String containerId;
	private Integer height, width;
	
	public ResizeContainerCmdImpl(SyncDockerCmdExec<ResizeContainerCmd, Void> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public ResizeContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public Integer getHeight() {
		return height;
	}

	@Override
	public Integer getWidth() {
		return width;
	}

	@Override
	public ResizeContainerCmd withHeight(Integer height) {
		this.height = height;
		return this;
	}

	@Override
	public ResizeContainerCmd withWidth(Integer width) {
		this.width = width;
		return this;
	}

}
