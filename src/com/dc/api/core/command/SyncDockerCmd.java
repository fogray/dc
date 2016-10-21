package com.dc.api.core.command;

public interface SyncDockerCmd<RES_T> extends DockerCmd<RES_T>{
	RES_T exec();
}
