package io.github.agileluo.smartroute.autoconfigure;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

import feign.RequestInterceptor;
import io.github.agileluo.smartroute.RouteChain;
import io.github.agileluo.smartroute.SmartRule;
import io.github.agileluo.smartroute.context.ContextFilter;
import io.github.agileluo.smartroute.context.ContextRelayRequestInterceptor;

@Configuration
@AutoConfigureBefore(RibbonAutoConfiguration.class)
public class SmartRuleAutoConfiguration {
	@Bean
	public PropertiesFactory fac(){
		return new PropertiesFactory(){
			@Override
			public String getClassName(Class clazz, String name) {
				if(clazz == IRule.class){
					return SmartRule.class.getName();
				}
				return super.getClassName(clazz, name);
			}
		};
	}
	@Bean
	public RouteChain routeChain(){
		return new RouteChain();
	}
	/**
	 * 配置上下文中继filter
	 * @return
	 */
	@Bean
	public Filter contextFilter(){
		return new ContextFilter();
	}
	/**
	 * 配置上下文中继RequestInterceptor
	 * @return
	 */
	@Bean
	public RequestInterceptor contextInterceptor(){
		return new ContextRelayRequestInterceptor();
	}
}
