package com.dc.api.command.node;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface UpdateNodeCmd extends SyncDockerCmd<Void>{

    @CheckForNull
    String getNodeId();

    String getVersion();
    
    JSONObject getConf();
    
	public UpdateNodeCmd withNodeId(@Nonnull String nodeId);
	
	public UpdateNodeCmd withVersion(String version);
	
	public UpdateNodeCmd withConf(JSONObject conf);
    
    interface Exec extends SyncDockerCmdExec<UpdateNodeCmd, Void>{};
}
