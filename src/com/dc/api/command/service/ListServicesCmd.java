package com.dc.api.command.service;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ListServicesCmd extends SyncDockerCmd<JSONArray>{

    JSONObject getFilters();
    
    public ListServicesCmd withFilters(JSONObject filters);
    
    interface Exec extends SyncDockerCmdExec<ListServicesCmd, JSONArray>{};
}
