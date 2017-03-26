package io.github.agileluo.smartroute;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.bouncycastle.crypto.tls.ServerSRPParams;

import com.netflix.loadbalancer.Server;

/**
 * 路由请求
 * 
 * @author marlon.luo
 *
 */
public class RouteRequest {
	
	//客户端ip
	private String clientIp;
	//客户id
	private String clientId;
	//服务列表
	private List<Server> servers;
	//服务Id
	private String serviceId;
	
	public RouteRequest(String clientId, String clientIp, List<Server> servers) {
		super();
		this.clientId = clientId;
		this.clientIp = clientIp;
		this.servers = servers;
		if(CollectionUtils.isNotEmpty(servers)){
			this.serviceId = servers.get(0).getMetaInfo().getAppName();
		}
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public List<Server> getServers() {
		return servers;
	}
	public void setServers(List<Server> servers) {
		this.servers = servers;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
}
