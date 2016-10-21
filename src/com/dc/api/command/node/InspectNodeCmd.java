package com.dc.api.command.node;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface InspectNodeCmd extends SyncDockerCmd<JSONObject>{

    @CheckForNull
    String getNodeId();
	
	public InspectNodeCmd withNodeId(@Nonnull String nodeId);
    
    interface Exec extends SyncDockerCmdExec<InspectNodeCmd, JSONObject>{};
}
