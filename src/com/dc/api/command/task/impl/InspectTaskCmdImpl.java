package com.dc.api.command.task.impl;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.command.task.InspectTaskCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class InspectTaskCmdImpl extends AbstrDockerCmd<InspectTaskCmd, JSONObject> 
	implements InspectTaskCmd{

	@CheckForNull
	private String taskId;
	
	public InspectTaskCmdImpl(SyncDockerCmdExec<InspectTaskCmd, JSONObject> execution) {
		super(execution);
	}
	@Override
	public String getTaskId() {
		return taskId;
	}

	@Override
	public InspectTaskCmd withTaskId(@Nonnull String taskId) {
		this.taskId = taskId;
		return this;
	}
}
