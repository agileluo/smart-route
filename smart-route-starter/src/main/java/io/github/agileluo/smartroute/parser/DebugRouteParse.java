package io.github.agileluo.smartroute.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.agileluo.smartroute.route.DebugRoute;
import io.github.agileluo.smartroute.route.Route;

public class DebugRouteParse implements RouteParse {
	private static final Logger log = LoggerFactory.getLogger(DebugRouteParse.class);

	@Override
	public boolean canParse(String key, String val, Properties p) {
		return key.startsWith("debug.");
	}

	@Override
	public Route parse(String key, String val, Properties p) {
		String clientIp = null;
		String clientId = null; 
		String[] keyFields = key.split("\\.", 3);
		if(keyFields.length != 3){
			log.error("key不符合规则" + key);
			return null;
		}
		String keyType = keyFields[1];
		String keyValue = keyFields[2];
		if("ip".equals(keyType)){
			clientIp = keyValue;
		}else if("id".equals(keyType)){
			clientId = keyValue;
		}
		String[] services = val.split("\\s*\\,\\s*");
		Map<String, Set<String>> serviceMap = new HashMap<>();
		for(String service : services){
			String[] idAndIp = service.split(":", 2);
			if(idAndIp.length != 2){
				log.error("value不符合规则" + val);
				return null;
			}
			String id = idAndIp[0];
			String ip = idAndIp[1];
			Set<String> ips = serviceMap.get(id);
			if(ips == null){
				ips = new HashSet<>();
				serviceMap.put(id, ips);
			}
			ips.add(ip);
		}
		Route route = new DebugRoute(clientIp, clientId, serviceMap);
		return route;
	}

}
