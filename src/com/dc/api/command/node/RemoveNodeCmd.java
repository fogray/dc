package com.dc.api.command.node;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

public interface RemoveNodeCmd extends SyncDockerCmd<Void>{

    @CheckForNull
    String getNodeId();

    boolean getForce();
    
	public RemoveNodeCmd withNodeId(@Nonnull String nodeId);
	
	public RemoveNodeCmd withForce(Boolean force);
    
    interface Exec extends SyncDockerCmdExec<RemoveNodeCmd, Void>{};
}
