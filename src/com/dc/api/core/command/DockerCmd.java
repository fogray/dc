package com.dc.api.core.command;

import java.io.Closeable;

public interface DockerCmd<RES_T> extends Closeable {
	
    @Override
    void close();
}
