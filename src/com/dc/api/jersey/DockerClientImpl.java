package com.dc.api.jersey;

import java.io.Closeable;
import java.io.IOException;

import com.dc.api.client.DockerClient;
import com.dc.api.client.DockerClientConfig;
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
import com.dc.api.command.container.impl.AttachContainerCmdImpl;
import com.dc.api.command.container.impl.CreateContainerCmdImpl;
import com.dc.api.command.container.impl.InspectContainerChangesCmdImpl;
import com.dc.api.command.container.impl.InspectContainerCmdImpl;
import com.dc.api.command.container.impl.KillContainerCmdImpl;
import com.dc.api.command.container.impl.ListContainersCmdImpl;
import com.dc.api.command.container.impl.LogContainerCmdImpl;
import com.dc.api.command.container.impl.PauseContainerCmdImpl;
import com.dc.api.command.container.impl.ReStartContainerCmdImpl;
import com.dc.api.command.container.impl.RemoveContainerCmdImpl;
import com.dc.api.command.container.impl.RenameContainerCmdImpl;
import com.dc.api.command.container.impl.ResizeContainerCmdImpl;
import com.dc.api.command.container.impl.StartContainerCmdImpl;
import com.dc.api.command.container.impl.StatsContainerCmdImpl;
import com.dc.api.command.container.impl.StopContainerCmdImpl;
import com.dc.api.command.container.impl.TopContainerCmdImpl;
import com.dc.api.command.container.impl.UnPauseContainerCmdImpl;
import com.dc.api.command.container.impl.UpdateContainerCmdImpl;
import com.dc.api.command.container.impl.WaitContainerCmdImpl;
import com.dc.api.command.image.InspectImageCmd;
import com.dc.api.command.image.ListImagesCmd;
import com.dc.api.command.image.impl.InspectImageCmdImpl;
import com.dc.api.command.image.impl.ListImagesCmdImpl;
import com.dc.api.command.node.InspectNodeCmd;
import com.dc.api.command.node.ListNodesCmd;
import com.dc.api.command.node.RemoveNodeCmd;
import com.dc.api.command.node.UpdateNodeCmd;
import com.dc.api.command.node.impl.InspectNodeCmdImpl;
import com.dc.api.command.node.impl.ListNodesCmdImpl;
import com.dc.api.command.node.impl.RemoveNodeCmdImpl;
import com.dc.api.command.node.impl.UpdateNodeCmdImpl;
import com.dc.api.command.service.CreateServiceCmd;
import com.dc.api.command.service.InspectServiceCmd;
import com.dc.api.command.service.ListServicesCmd;
import com.dc.api.command.service.RemoveServiceCmd;
import com.dc.api.command.service.UpdateServiceCmd;
import com.dc.api.command.service.impl.CreateServiceCmdImpl;
import com.dc.api.command.service.impl.InspectServiceCmdImpl;
import com.dc.api.command.service.impl.ListServicesCmdImpl;
import com.dc.api.command.service.impl.RemoveServiceCmdImpl;
import com.dc.api.command.service.impl.UpdateServiceCmdImpl;
import com.dc.api.command.task.InspectTaskCmd;
import com.dc.api.command.task.ListTasksCmd;
import com.dc.api.command.task.impl.InspectTaskCmdImpl;
import com.dc.api.command.task.impl.ListTasksCmdImpl;
import com.dc.api.jersey.exec.container.AttachContainerCmdExec;
import com.dc.api.jersey.exec.container.CreateContainerCmdExec;
import com.dc.api.jersey.exec.container.InspectContainerChangesCmdExec;
import com.dc.api.jersey.exec.container.InspectContainerCmdExec;
import com.dc.api.jersey.exec.container.KillContainerCmdExec;
import com.dc.api.jersey.exec.container.ListContainersCmdExec;
import com.dc.api.jersey.exec.container.LogContainerCmdExec;
import com.dc.api.jersey.exec.container.PauseContainerCmdExec;
import com.dc.api.jersey.exec.container.ReStartContainerCmdExec;
import com.dc.api.jersey.exec.container.RemoveContainerCmdExec;
import com.dc.api.jersey.exec.container.RenameContainerCmdExec;
import com.dc.api.jersey.exec.container.ResizeContainerCmdExec;
import com.dc.api.jersey.exec.container.StartContainerCmdExec;
import com.dc.api.jersey.exec.container.StatsContainerCmdExec;
import com.dc.api.jersey.exec.container.StopContainerCmdExec;
import com.dc.api.jersey.exec.container.TopContainerCmdExec;
import com.dc.api.jersey.exec.container.UnPauseContainerCmdExec;
import com.dc.api.jersey.exec.container.UpdateContainerCmdExec;
import com.dc.api.jersey.exec.container.WaitContainerCmdExec;
import com.dc.api.jersey.exec.image.InspectImageCmdExec;
import com.dc.api.jersey.exec.image.ListImagesCmdExec;
import com.dc.api.jersey.exec.node.InspectNodeCmdExec;
import com.dc.api.jersey.exec.node.ListNodesCmdExec;
import com.dc.api.jersey.exec.node.RemoveNodeCmdExec;
import com.dc.api.jersey.exec.node.UpdateNodeCmdExec;
import com.dc.api.jersey.exec.service.CreateServiceCmdExec;
import com.dc.api.jersey.exec.service.InspectServiceCmdExec;
import com.dc.api.jersey.exec.service.ListServicesCmdExec;
import com.dc.api.jersey.exec.service.RemoveServiceCmdExec;
import com.dc.api.jersey.exec.service.UpdateServiceCmdExec;
import com.dc.api.jersey.exec.task.InspectTaskCmdExec;
import com.dc.api.jersey.exec.task.ListTasksCmdExec;

public class DockerClientImpl implements DockerClient, Closeable{

	private JerseyDockerClientConnector connector;
	
	public JerseyDockerClientConnector getConnector() {
		return connector;
	}
	public void setConnector(JerseyDockerClientConnector connector) {
		this.connector = connector;
	}
	private DockerClientImpl(String url){
		connector = new JerseyDockerClientConnector();
		connector.init(url);
	}
	private DockerClientImpl(DockerClientConfig config){
		connector = new JerseyDockerClientConnector();
		connector.init(config);
	}

	public static DockerClient getInstance(String url) {
		return new DockerClientImpl(url);
	}

	public static DockerClient getInstance(DockerClientConfig config) {
		return new DockerClientImpl(config);
	}
	
	@Override
	public void close() throws IOException {
		this.close();
	}
	
	/** Nodes API **/
	
	@Override
	public ListNodesCmd listNodesCmd() {
		return new ListNodesCmdImpl(new ListNodesCmdExec(connector.getBaseResource()));
	}

	@Override
	public InspectNodeCmd inspectNodeCmd() {
		return new InspectNodeCmdImpl(new InspectNodeCmdExec(connector.getBaseResource()));
	}

	@Override
	public UpdateNodeCmd updateNodeCmd() {
		return new UpdateNodeCmdImpl(new UpdateNodeCmdExec(connector.getBaseResource()));
	}

	@Override
	public RemoveNodeCmd removeNodeCmd() {
		return new RemoveNodeCmdImpl(new RemoveNodeCmdExec(connector.getBaseResource()));
	}

	/** Services API **/
	/**
	 * 显示service列表
	 */
	@Override
	public ListServicesCmd listServicesCmd() {
		return new ListServicesCmdImpl(new ListServicesCmdExec(connector.getBaseResource()));
	}
	/**
	 * 新建service
	 */
	@Override
	public CreateServiceCmd createServiceCmd() {
		return new CreateServiceCmdImpl(new CreateServiceCmdExec(connector.getBaseResource()));
	}
	/**
	 * 显示指定service信息
	 */
	@Override
	public InspectServiceCmd inspectServiceCmd() {
		return new InspectServiceCmdImpl(new InspectServiceCmdExec(connector.getBaseResource()));
	}
	/**
	 * 更新指定service
	 */
	@Override
	public UpdateServiceCmd updateServiceCmd() {
		return new UpdateServiceCmdImpl(new UpdateServiceCmdExec(connector.getBaseResource()));
	}
	/**
	 * 删除指定service
	 */
	@Override
	public RemoveServiceCmd removeServiceCmd() {
		return new RemoveServiceCmdImpl(new RemoveServiceCmdExec(connector.getBaseResource()));
	}
	
	/** Tasks API **/
	/**
	 * 显示task列表
	 */
	@Override
	public ListTasksCmd listTasksCmd() {
		return new ListTasksCmdImpl(new ListTasksCmdExec(connector.getBaseResource()));
	}

	/**
	 * 显示指定task信息
	 */
	@Override
	public InspectTaskCmd inspectTaskCmd() {
		return new InspectTaskCmdImpl(new InspectTaskCmdExec(connector.getBaseResource()));
	}
	
	/**
	 * Container API
	 */
	/**
	 * 显示container列表
	 */
	@Override
	public ListContainersCmd listContainersCmd() {
		return new ListContainersCmdImpl(new ListContainersCmdExec(connector.getBaseResource()));
	}

	@Override
	public CreateContainerCmd createContainerCmd() {
		return new CreateContainerCmdImpl(new CreateContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public AttachContainerCmd attachContainerCmd() {
		return new AttachContainerCmdImpl(new AttachContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public InspectContainerChangesCmd inspectContainerChangesCmd() {
		return new InspectContainerChangesCmdImpl(new InspectContainerChangesCmdExec(connector.getBaseResource()));
	}

	@Override
	public InspectContainerCmd inspectContainerCmd() {
		return new InspectContainerCmdImpl(new InspectContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public KillContainerCmd killContainerCmd() {
		return new KillContainerCmdImpl(new KillContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public LogContainerCmd logContainerCmd() {
		return new LogContainerCmdImpl(new LogContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public PauseContainerCmd pauseContainerCmd() {
		return new PauseContainerCmdImpl(new PauseContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public RemoveContainerCmd removeContainerCmd() {
		return new RemoveContainerCmdImpl(new RemoveContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public RenameContainerCmd renameContainerCmd() {
		return new RenameContainerCmdImpl(new RenameContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public ResizeContainerCmd resizeContainerCmd() {
		return new ResizeContainerCmdImpl(new ResizeContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public ReStartContainerCmd reStartContainerCmd() {
		return new ReStartContainerCmdImpl(new ReStartContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public StartContainerCmd startContainerCmd() {
		return new StartContainerCmdImpl(new StartContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public StatsContainerCmd statsContainerCmd() {
		return new StatsContainerCmdImpl(new StatsContainerCmdExec(connector.getBaseResource()));
	}
	
	@Override
	public StopContainerCmd stopContainerCmd() {
		return new StopContainerCmdImpl(new StopContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public TopContainerCmd topContainerCmd() {
		return new TopContainerCmdImpl(new TopContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public UnPauseContainerCmd unPauseContainerCmd() {
		return new UnPauseContainerCmdImpl(new UnPauseContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public UpdateContainerCmd updateContainerCmd() {
		return new UpdateContainerCmdImpl(new UpdateContainerCmdExec(connector.getBaseResource()));
	}

	@Override
	public WaitContainerCmd waitContainerCmd() {
		return new WaitContainerCmdImpl(new WaitContainerCmdExec(connector.getBaseResource()));
	}

	/** Images API **/
	@Override
	public ListImagesCmd listImagesCmd() {
		return new ListImagesCmdImpl(new ListImagesCmdExec(connector.getBaseResource()));
	}
	@Override
	public InspectImageCmd inspectImageCmd() {
		return new InspectImageCmdImpl(new InspectImageCmdExec(connector.getBaseResource()));
	}
}
