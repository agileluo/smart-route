package io.github.agileluo.smartroute.autoconfigure;

import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.integration.zookeeper.config.CuratorFrameworkFactoryBean;

@Configuration
@ConditionalOnMissingBean(value={CuratorFramework.class})
public class ZkAutoConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean
	public CuratorFramework init() throws Exception{
		String key = env.getProperty("zk.connection.key");
		if(StringUtils.isBlank(key)){
			key = "zk.connect";
		}
		String url = env.getProperty(key);
		if(StringUtils.isEmpty(url)){
			throw new RuntimeException("zookeeper地址不能为空${" + key + "}");
		}
		CuratorFramework client = new CuratorFrameworkFactoryBean(url).getObject();
		client.start();
		return client;
	}
}
