package io.github.agileluo.smartroute.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import io.github.agileluo.smartroute.route.Route;

public class RouteParserChain {
	private Set<RouteParse> parses = new HashSet<>();
	
	public RouteParserChain(){
		parses.add(new DebugRouteParse());
	}
	
	public List<Route> parse(Properties p){
		List<Route> routes = new ArrayList<>();
		for(Object keyObj : p.keySet()){
			String key = (String)keyObj;
			String val = p.getProperty(key);
			for(RouteParse parse : parses){
				if(parse.canParse(key, val, p)){
					Route r = parse.parse(key, val, p);
					if(r != null){
						routes.add(r);
					}
					break;
				}
			}
		}
		return routes;
	}
	public RouteParserChain addParse(RouteParse parse){
		parses.add(parse);
		return this;
	}
}
