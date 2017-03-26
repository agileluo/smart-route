package io.github.agileluo.smartroute.autoconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

import io.github.agileluo.smartroute.RouteChain;
import io.github.agileluo.smartroute.SmartRule;

@Configuration
public class SmartRuleAutoConfiguration {
	@Bean
	public IRule getRule(){
		System.err.println("init smart rule");
		return new SmartRule();
	}
	@Bean
	public RouteChain routeChain(){
		return new RouteChain();
	}
}
