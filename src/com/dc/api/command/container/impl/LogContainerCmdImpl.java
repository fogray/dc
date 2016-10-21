package com.dc.api.command.container.impl;

import com.dc.api.command.container.LogContainerCmd;
import com.dc.api.command.service.model.Frame;
import com.dc.api.core.command.async.AbstrAsyncDockerCmd;
import com.dc.api.core.command.async.AsyncDockerCmdExec;

public class LogContainerCmdImpl extends AbstrAsyncDockerCmd<LogContainerCmd, Frame> 
	implements LogContainerCmd{

	private String containerId, tail="all";
	private Boolean showDetails = false, follow = false, stdout = false, stderr = false, showTime = false;
	private Long sinceTime = 0l;
	
	public LogContainerCmdImpl(AsyncDockerCmdExec<LogContainerCmd, Frame> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public LogContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public Boolean isShowDetails() {
		return showDetails;
	}

	@Override
	public Boolean isFollow() {
		return follow;
	}

	@Override
	public Boolean isStdout() {
		return stdout;
	}

	@Override
	public Boolean isStderr() {
		return stderr;
	}

	@Override
	public Long getSinceTime() {
		return sinceTime;
	}

	@Override
	public Boolean isShowTime() {
		return showTime;
	}

	@Override
	public String getTail() {
		return tail;
	}

	@Override
	public LogContainerCmd withDetails(Boolean showDetails) {
		this.showDetails = showDetails;
		return this;
	}

	@Override
	public LogContainerCmd withFollow(Boolean follow) {
		this.follow = follow;
		return this;
	}

	@Override
	public LogContainerCmd withStdout(Boolean showStdout) {
		this.stdout = showStdout;
		return this;
	}

	@Override
	public LogContainerCmd withStderr(Boolean showStderr) {
		this.stderr = showStderr;
		return this;
	}

	@Override
	public LogContainerCmd withSince(Long sinceTime) {
		this.sinceTime = sinceTime;
		return this;
	}

	@Override
	public LogContainerCmd withTimestamps(Boolean showTime) {
		this.showTime = showTime;
		return this;
	}

	@Override
	public LogContainerCmd withTail(String tail) {
		this.tail = tail;
		return this;
	}
}
