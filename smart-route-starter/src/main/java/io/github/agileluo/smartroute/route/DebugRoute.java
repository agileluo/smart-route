package io.github.agileluo.smartroute.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.netflix.loadbalancer.Server;

import io.github.agileluo.smartroute.RouteRequest;
import io.github.agileluo.smartroute.constant.SmartRuleConstant;
import io.github.agileluo.smartroute.context.IpUtil;

/**
 * 调试路由
 * <p>
 * 1,规则内的请求，导流至规则内节点<br>
 * 2,规则外的请求，不会导流至规则内节点， 以达到远程调试代码效果<br>
 * <p> 
 * 
 * @author marlon.luo
 *
 */
public class DebugRoute implements Route {
	private String clientIp;
	private String clientId;
	private Map<String, Set<String>> debugServices = new HashMap<>();
	
	public DebugRoute(String clientIp, String clientId, Map<String, Set<String>> debugServices) {
		super();
		this.clientIp = clientIp;
		this.clientId = clientId;
		this.debugServices = debugServices;
	}

	@Override
	public void route(RouteRequest req) {
		String requestServiceId = req.getServiceId();
		
		Set<String> debugHosts = debugServices.get(requestServiceId);
		if(debugHosts == null){
			return;
		}
		
		boolean isDebug = false;
		if(clientId != null && clientId.equals(req.getClientId())){
			isDebug = true;
		}else if(clientIp != null && clientIp.equals(req.getClientIp())){
			isDebug = true;
		}else if(IpUtil.isAllLocalIp(clientIp, req.getClientIp())){
			isDebug = true;
		}
		List<Server> newServer = new ArrayList<>();
		if(isDebug){
			for(Server s : req.getServers()){
				if(debugHosts.contains(s.getHost())){
					newServer.add(s);
				}
			}
		}else{
			for(Server s : req.getServers()){
				if(!debugHosts.contains(s.getHost())){
					newServer.add(s);
				}
			}
		}
		req.setServers(newServer);
	}
	
	@Override
	public int compareTo(Route o) {
		return SmartRuleConstant.RuleOrder.DEBUG;
	}
	
}
