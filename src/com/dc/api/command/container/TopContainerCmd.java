package com.dc.api.command.container;

import javax.annotation.CheckForNull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface TopContainerCmd extends SyncDockerCmd<JSONObject>{

	@CheckForNull
	String getContainerId();
	@CheckForNull
	String getPsArgs();
    
	TopContainerCmd withContainerId(String containerId);
	TopContainerCmd withPsArgs(String psArgs);
    
    interface Exec extends SyncDockerCmdExec<TopContainerCmd, JSONObject>{};
}
