package io.github.agileluo.smartroute;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.zookeeper.config.CuratorFrameworkFactoryBean;

@Configuration
public class RuleConfig {
	
	@Value("${zookeeper.connect}")
	private String url;
	
	@Bean
	public CuratorFramework init() throws Exception{
		return new CuratorFrameworkFactoryBean(url).getObject();
	}
}
