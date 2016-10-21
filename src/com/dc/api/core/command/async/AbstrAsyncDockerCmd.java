package com.dc.api.core.command.async;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstrAsyncDockerCmd<CMD_T extends AsyncDockerCmd<CMD_T, A_RES_T>, A_RES_T> implements
        AsyncDockerCmd<CMD_T, A_RES_T> {

    protected AsyncDockerCmdExec<CMD_T, A_RES_T> execution;

    public AbstrAsyncDockerCmd(AsyncDockerCmdExec<CMD_T, A_RES_T> execution) {
        checkNotNull(execution, "execution was not specified");
        this.execution = execution;
    }

    @Override
    public <T extends ResultCallback<A_RES_T>> T exec(T resultCallback) {
        execution.exec((CMD_T) this, resultCallback);
        return resultCallback;
    }

    @Override
    public void close() {
    }

}
