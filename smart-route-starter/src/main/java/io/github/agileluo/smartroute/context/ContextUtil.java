package io.github.agileluo.smartroute.context;

import org.slf4j.MDC;

import com.alibaba.fastjson.JSONObject;

import io.github.agileluo.smartroute.vo.SmartContext;

/**
 * 上下文中继
 * 
 * @author marlon.luo
 *
 */
public class ContextUtil {
	public static final String CLIENT_ID = "smartRouteclientId";
	public static final String CLIENT_IP = "smartRouteclientIp";
	// 上下文json串
	public static final String CONTEXT = "smartRouteContext";

	public static String getClientId() {
		return MDC.get(CLIENT_ID);
	}
	public static String getClientIp() {
		return MDC.get(CLIENT_IP);
	}
	public static String getContext() {
		return MDC.get(CONTEXT);
	}
	public static void initContext(String context) {
		SmartContext vo = JSONObject.parseObject(context, SmartContext.class);
		MDC.put(CLIENT_ID, vo.getClientId());
		MDC.put(CLIENT_IP, vo.getClientIp());
		MDC.put(CONTEXT, context);
	}
	public static void initContext(SmartContext vo) {
		MDC.put(CLIENT_ID, vo.getClientId());
		MDC.put(CLIENT_IP, vo.getClientIp());
		MDC.put(CONTEXT, JSONObject.toJSONString(vo));
	}
	public static void clear(){
		MDC.clear();
	}
}
