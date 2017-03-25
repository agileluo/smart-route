package io.github.agileluo.smartroute.route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.netflix.loadbalancer.Server;

import io.github.agileluo.smartroute.RouteRequest;
import io.github.agileluo.smartroute.constant.SmartRuleConstant;

/**
 * 调试路由
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
