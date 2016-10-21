package com.dc.api.command.service;

import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface CreateServiceCmd extends SyncDockerCmd<JSONObject>{

	public JSONObject getSettings();
	
	public CreateServiceCmd withSettings(@Nonnull JSONObject settings);
    
	interface Exec extends SyncDockerCmdExec<CreateServiceCmd, JSONObject>{};
}
