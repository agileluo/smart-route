package io.github.agileluo.smartroute.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import io.github.agileluo.smartroute.vo.SmartContext;

/**
 * 上下文中继filter
 * @author marlon.luo
 *
 */
public class ContextFilter implements Filter {
	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		String context = request.getHeader(ContextUtil.CONTEXT);
		if(StringUtils.isNotEmpty(context)){
			System.out.println(context);
			ContextUtil.initContext(context);
		}else{
			String clientIp = request.getRemoteHost();
			String clientId = request.getHeader(ContextUtil.CLIENT_ID);
			ContextUtil.initContext(new SmartContext().setClientIp(clientIp).setClientId(clientId));
		}
		try {
			chain.doFilter(req, resp);
		} finally {
			ContextUtil.clear();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
