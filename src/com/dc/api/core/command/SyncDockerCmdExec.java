package com.dc.api.core.command;

public interface SyncDockerCmdExec<CMD_T extends DockerCmd<RES_T>, RES_T>{

    RES_T exec(CMD_T command);
}

