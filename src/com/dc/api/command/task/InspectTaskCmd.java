package com.dc.api.command.task;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface InspectTaskCmd extends SyncDockerCmd<JSONObject>{

    @CheckForNull
    String getTaskId();
	
	public InspectTaskCmd withTaskId(@Nonnull String taskId);
    
    interface Exec extends SyncDockerCmdExec<InspectTaskCmd, JSONObject>{};
}
