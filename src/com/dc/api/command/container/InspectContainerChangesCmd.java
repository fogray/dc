package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;

public interface InspectContainerChangesCmd extends SyncDockerCmd<JSONArray>{

	@CheckForNull
	String getContainerId();
    
	InspectContainerChangesCmd withContainerId(@Nonnull String containerId);
    
    interface Exec extends SyncDockerCmdExec<InspectContainerChangesCmd, JSONArray>{};
}
