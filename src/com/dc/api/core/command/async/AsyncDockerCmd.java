package com.dc.api.core.command.async;

import com.dc.api.core.command.DockerCmd;

public interface AsyncDockerCmd<CMD_T extends AsyncDockerCmd<CMD_T, A_RES_T>, A_RES_T> extends DockerCmd<Void> {
    <T extends ResultCallback<A_RES_T>> T exec(T resultCallback);
}
