package com.dc.api.command.container;

import javax.annotation.CheckForNull;

import com.dc.api.command.service.model.Frame;
import com.dc.api.core.command.async.AsyncDockerCmd;
import com.dc.api.core.command.async.AsyncDockerCmdExec;

public interface AttachContainerCmd extends AsyncDockerCmd<AttachContainerCmd, Frame>{

	@CheckForNull
	String getContainerId();

	@CheckForNull
	String getDetachKeys();
	@CheckForNull
	Boolean isLogs();
	@CheckForNull
	Boolean isStream();
	@CheckForNull
	Boolean isStdin();
	@CheckForNull
	Boolean isStdout();
	@CheckForNull
	Boolean isStderr();
    
	AttachContainerCmd withContainerId(String containerId);
	AttachContainerCmd withDetachKeys(String detachKeys);
	AttachContainerCmd withLogs(Boolean logs);
	AttachContainerCmd withStream(Boolean stream);
	AttachContainerCmd withStdin(Boolean stdin);
	AttachContainerCmd withStdout(Boolean stdout);
	AttachContainerCmd withStderr(Boolean stderr);
    
    interface Exec extends AsyncDockerCmdExec<AttachContainerCmd, Frame>{};
}
