package com.dc.api.command.container;

import javax.annotation.CheckForNull;

import com.dc.api.command.service.model.Frame;
import com.dc.api.core.command.async.AsyncDockerCmd;
import com.dc.api.core.command.async.AsyncDockerCmdExec;

import net.sf.json.JSONArray;

public interface LogContainerCmd extends AsyncDockerCmd<LogContainerCmd, Frame>{

	@CheckForNull
	String getContainerId();
	
	@CheckForNull
	Boolean isShowDetails();
	@CheckForNull
	Boolean isFollow();
	@CheckForNull
	Boolean isStdout();
	@CheckForNull
	Boolean isStderr();
	@CheckForNull
	Long getSinceTime();
	@CheckForNull
	Boolean isShowTime();
	@CheckForNull
	String getTail();
    
	LogContainerCmd withContainerId(String containerId);
	LogContainerCmd withDetails(Boolean showDetails);
	LogContainerCmd withFollow(Boolean follow);
	LogContainerCmd withStdout(Boolean showStdout);
	LogContainerCmd withStderr(Boolean showStderr);
	LogContainerCmd withSince(Long sinceTime);
	LogContainerCmd withTimestamps(Boolean showTime);
	LogContainerCmd withTail(String tail);
    
    interface Exec extends AsyncDockerCmdExec<LogContainerCmd, JSONArray>{};
}
