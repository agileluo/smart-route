package io.github.agileluo.smartroute.context;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 上下文中继切面
 * @author marlon.luo
 *
 */
public class ContextRelayRequestInterceptor implements RequestInterceptor {
	@Override
	public void apply(RequestTemplate templ) {
		String context = ContextUtil.getContext();
		templ.header(ContextUtil.CONTEXT, context);
	}
}
