package com.dc.api.command.task;

import com.dc.api.core.command.SyncDockerCmd;
import com.dc.api.core.command.SyncDockerCmdExec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ListTasksCmd extends SyncDockerCmd<JSONArray>{

    JSONObject getFilters();
    
    public ListTasksCmd withFilters(JSONObject filters);
    
    interface Exec extends SyncDockerCmdExec<ListTasksCmd, JSONArray>{};
}
