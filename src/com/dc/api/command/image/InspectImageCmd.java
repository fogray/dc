package com.dc.api.command.image;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public interface InspectImageCmd extends SyncDockerCmd<JSONObject>{
	
	@CheckForNull
	String getImageId();

	public InspectImageCmd withImageId(@Nonnull String imageId);
    
    interface Exec extends SyncDockerCmdExec<InspectImageCmd, JSONObject>{};
}
