package com.dc.api.command.container;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface CreateContainerCmd extends SyncDockerCmd<JSONObject>{
	
	/**
	 * specify container name
	 * @return
	 */
	@CheckForNull
	String getName();

	JSONObject getSettings();
	
	public CreateContainerCmd withName(String name);
	public CreateContainerCmd withSettings(@Nonnull JSONObject settings);
    
	interface Exec extends SyncDockerCmdExec<CreateContainerCmd, JSONObject>{};
}
