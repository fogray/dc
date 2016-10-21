package com.dc.api.client;

import com.dc.api.command.container.AttachContainerCmd;
import com.dc.api.command.container.CreateContainerCmd;
import com.dc.api.command.container.InspectContainerChangesCmd;
import com.dc.api.command.container.InspectContainerCmd;
import com.dc.api.command.container.KillContainerCmd;
import com.dc.api.command.container.ListContainersCmd;
import com.dc.api.command.container.LogContainerCmd;
import com.dc.api.command.container.PauseContainerCmd;
import com.dc.api.command.container.ReStartContainerCmd;
import com.dc.api.command.container.RemoveContainerCmd;
import com.dc.api.command.container.RenameContainerCmd;
import com.dc.api.command.container.ResizeContainerCmd;
import com.dc.api.command.container.StartContainerCmd;
import com.dc.api.command.container.StatsContainerCmd;
import com.dc.api.command.container.StopContainerCmd;
import com.dc.api.command.container.TopContainerCmd;
import com.dc.api.command.container.UnPauseContainerCmd;
import com.dc.api.command.container.UpdateContainerCmd;
import com.dc.api.command.container.WaitContainerCmd;
import com.dc.api.command.image.InspectImageCmd;
import com.dc.api.command.image.ListImagesCmd;
import com.dc.api.command.node.InspectNodeCmd;
import com.dc.api.command.node.ListNodesCmd;
import com.dc.api.command.node.RemoveNodeCmd;
import com.dc.api.command.node.UpdateNodeCmd;
import com.dc.api.command.service.CreateServiceCmd;
import com.dc.api.command.service.InspectServiceCmd;
import com.dc.api.command.service.ListServicesCmd;
import com.dc.api.command.service.RemoveServiceCmd;
import com.dc.api.command.service.UpdateServiceCmd;
import com.dc.api.command.task.InspectTaskCmd;
import com.dc.api.command.task.ListTasksCmd;

public interface DockerClient {
	
	/** Nodes API **/
	ListNodesCmd listNodesCmd();
	InspectNodeCmd inspectNodeCmd();
	UpdateNodeCmd updateNodeCmd();
	RemoveNodeCmd removeNodeCmd();
	
	/** Services API **/
	ListServicesCmd listServicesCmd();
	CreateServiceCmd createServiceCmd();
	InspectServiceCmd inspectServiceCmd();
	UpdateServiceCmd updateServiceCmd();
	RemoveServiceCmd removeServiceCmd();
	
	/** Tasks API **/
	ListTasksCmd listTasksCmd();
	InspectTaskCmd inspectTaskCmd();
	
	
	/** Container API**/
	ListContainersCmd listContainersCmd();
	CreateContainerCmd createContainerCmd();
	AttachContainerCmd attachContainerCmd();
	InspectContainerChangesCmd inspectContainerChangesCmd();
	InspectContainerCmd inspectContainerCmd();
	KillContainerCmd killContainerCmd();
	LogContainerCmd logContainerCmd();
	PauseContainerCmd pauseContainerCmd();
	RemoveContainerCmd removeContainerCmd();
	RenameContainerCmd renameContainerCmd();
	ResizeContainerCmd resizeContainerCmd();
	ReStartContainerCmd reStartContainerCmd();
	StartContainerCmd startContainerCmd();
	StatsContainerCmd statsContainerCmd();
	StopContainerCmd stopContainerCmd();
	TopContainerCmd topContainerCmd();
	UnPauseContainerCmd unPauseContainerCmd();
	UpdateContainerCmd updateContainerCmd();
	WaitContainerCmd waitContainerCmd();
	
	/** Images API **/
	ListImagesCmd listImagesCmd();
	InspectImageCmd inspectImageCmd();
}
