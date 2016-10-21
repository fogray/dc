package com.dc.api.core.command.async;

import com.dc.api.core.command.DockerCmd;

public interface AsyncDockerCmdExec<CMD_T extends DockerCmd<Void>, A_RES_T> {

    Void exec(CMD_T command, ResultCallback<A_RES_T> resultCallback);

}
