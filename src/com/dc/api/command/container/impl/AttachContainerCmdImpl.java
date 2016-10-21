package com.dc.api.command.container.impl;

import com.dc.api.command.container.AttachContainerCmd;
import com.dc.api.command.service.model.Frame;
import com.dc.api.core.command.async.AbstrAsyncDockerCmd;
import com.dc.api.core.command.async.AsyncDockerCmdExec;

public class AttachContainerCmdImpl extends AbstrAsyncDockerCmd<AttachContainerCmd, Frame> 
	implements AttachContainerCmd{

	private String containerId, detachKeys;
	private Boolean logs = false, stream = false, stdin = false, stdout = false, stderr = false;
	
	public AttachContainerCmdImpl(AsyncDockerCmdExec<AttachContainerCmd, Frame> execution) {
		super(execution);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public AttachContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public String getDetachKeys() {
		return detachKeys;
	}

	@Override
	public Boolean isLogs() {
		return logs;
	}

	@Override
	public Boolean isStream() {
		return stream;
	}

	@Override
	public Boolean isStdin() {
		return stdin;
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
	public AttachContainerCmd withDetachKeys(String detachKeys) {
		this.detachKeys = detachKeys;
		return this;
	}

	@Override
	public AttachContainerCmd withLogs(Boolean logs) {
		this.logs = logs;
		return this;
	}

	@Override
	public AttachContainerCmd withStream(Boolean stream) {
		this.stream = stream;
		return this;
	}

	@Override
	public AttachContainerCmd withStdin(Boolean stdin) {
		this.stdin = stdin;
		return this;
	}

	@Override
	public AttachContainerCmd withStdout(Boolean stdout) {
		this.stdout = stdout;
		return this;
	}

	@Override
	public AttachContainerCmd withStderr(Boolean stderr) {
		this.stderr = stderr;
		return this;
	}

}
