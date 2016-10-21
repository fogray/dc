package com.dc.api.command.image.impl;

import javax.annotation.CheckForNull;

import com.dc.api.command.image.InspectImageCmd;
import com.dc.api.core.command.AbstrDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONObject;

public class InspectImageCmdImpl extends AbstrDockerCmd<InspectImageCmd, JSONObject> 
	implements InspectImageCmd{

	@CheckForNull
	private String imageId;
	
	public InspectImageCmdImpl(SyncDockerCmdExec<InspectImageCmd, JSONObject> execution) {
		super(execution);
	}

	@Override
	public String getImageId() {
		return imageId;
	}

	@Override
	public InspectImageCmd withImageId(String imageId) {
		this.imageId = imageId;
		return this;
	}
}
