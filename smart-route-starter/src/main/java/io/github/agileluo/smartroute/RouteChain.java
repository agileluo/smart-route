package io.github.agileluo.smartroute;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.netflix.loadbalancer.Server;

import io.github.agileluo.smartroute.route.Route;

/**
 * 动态路由
 * @author marlon.luo
 *
 */
public class RouteChain {
	private volatile Set<Route> routes = new HashSet<>();
	/**
	 * 路由
	 * @param req
	 * @return
	 */
	public List<Server> route(RouteRequest req){
		if(CollectionUtils.isNotEmpty(routes)){
			for(Route r : routes){
				r.route(req);
				if(CollectionUtils.isEmpty(req.getServers())){
					return null;
				}
				if(req.getServers().size() == 1){
					return  req.getServers();
				}
			}
		}
		return req.getServers();
	}
	public synchronized void resetRoutes(List<Route> newList){
		if(CollectionUtils.isEmpty(newList)){
			this.routes = new HashSet<>();
			return;
		}
		//重设路由信息
		Set<Route> newRoutes = new HashSet<>(newList);
		this.routes = newRoutes;
	}
}
