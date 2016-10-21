package com.dc.api.jersey;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.WebTarget;

import com.dc.api.core.command.DockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;
import com.dc.api.exception.DockerException;

public abstract class AbstrSyncDockerCmdExec<CMD_T extends DockerCmd<RES_T>, RES_T>
        implements SyncDockerCmdExec<CMD_T, RES_T> {

    public AbstrSyncDockerCmdExec(WebTarget baseResource) {
    }

    @Override
    public RES_T exec(CMD_T command) {
        try (CMD_T cmd = command) {
            try {
                return execute(cmd);
            } catch (ProcessingException e) {
                if (e.getCause() instanceof DockerException) {
                    throw (DockerException) e.getCause();
                } else {
                    throw e;
                }
            }
        }
    }

    protected abstract RES_T execute(CMD_T command);
}
