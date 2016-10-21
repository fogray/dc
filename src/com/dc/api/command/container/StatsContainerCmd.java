package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface StatsContainerCmd extends SyncDockerCmd<JSONObject>{

	@CheckForNull
	String getContainerId();
	
	@CheckForNull
	Boolean isStream();
    
	StatsContainerCmd withContainerId(@Nonnull String containerId);
	StatsContainerCmd withStream(Boolean stream);
    
    interface Exec extends SyncDockerCmdExec<StatsContainerCmd, JSONObject>{};
}
