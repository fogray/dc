package com.dc.api.core.command;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.dc.api.exception.DockerException;


public abstract class AbstrDockerCmd<CMD_T extends DockerCmd<RES_T>, RES_T> implements SyncDockerCmd<RES_T>{

    protected SyncDockerCmdExec<CMD_T, RES_T> execution;

    public AbstrDockerCmd(SyncDockerCmdExec<CMD_T, RES_T> execution) {
        checkNotNull(execution, "execution was not specified");
        this.execution = execution;
    }

    @Override
    public RES_T exec() throws DockerException {
        return execution.exec((CMD_T) this);
    }

    @Override
    public void close() {
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
