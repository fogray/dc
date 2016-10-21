package com.dc.api.command.node;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ListNodesCmd extends SyncDockerCmd<JSONArray>{

    JSONObject getFilters();
    
    public ListNodesCmd withFilters(JSONObject filters);
    
    interface Exec extends SyncDockerCmdExec<ListNodesCmd, JSONArray>{};
}
