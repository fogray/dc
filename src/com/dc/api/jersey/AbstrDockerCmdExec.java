package com.dc.api.jersey;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.ws.rs.client.WebTarget;

public abstract class AbstrDockerCmdExec {

    private final WebTarget baseResource;

    public AbstrDockerCmdExec(WebTarget baseResource) {
        checkNotNull(baseResource, "baseResource was not specified");
        this.baseResource = baseResource;
    }

    protected WebTarget getBaseResource() {
        return baseResource;
    }

    protected boolean bool(Boolean bool) {
        return bool != null && bool;
    }

    protected WebTarget booleanQueryParam(WebTarget webTarget, String name, Boolean value) {
        if (bool(value)) {
            webTarget = webTarget.queryParam(name, bool(value) + "");
        }
        return webTarget;
    }
}
