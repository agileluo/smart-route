package io.github.agileluo.rest;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.KeeperException.NodeExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.agileluo.smartroute.constant.SmartRuleConstant;
import io.github.agileluo.smartroute.context.IpUtil;
import io.github.agileluo.vo.Rule;

@RestController
@RequestMapping("/rule")
public class RuleController {
	private static final Logger log = LoggerFactory.getLogger(RuleController.class);
	
	@Autowired
	CuratorFramework client;
	private static String ENCODE = "UTF-8";
	
	@RequestMapping("/get")
	public Rule get() throws Exception{
		String rule = getRule();
		return new Rule().setRule(rule);
	}
	@RequestMapping("/set")
	public boolean set(@RequestBody Rule rule) throws  Exception{
		if(StringUtils.equals(rule.getRule(), getRule())){
			return true;
		}
		try {
			client.setData().forPath(SmartRuleConstant.CONFIG_PATH, rule.getRule().getBytes(ENCODE));
		} catch (NodeExistsException | NoNodeException e) {
			client.create().creatingParentContainersIfNeeded().forPath(SmartRuleConstant.CONFIG_PATH, rule.getRule().getBytes(ENCODE));
		}
		return true;
	}
	@RequestMapping("/getip")
	public String getIp(HttpServletRequest req){
		String ip = req.getRemoteHost();
		if(IpUtil.isLocalIp(ip)){
			ip = IpUtil.getLocalRemoteIp();
		}
		return ip;
	}
	private String getRule(){
		try {
			byte[] data = client.getData().forPath(SmartRuleConstant.CONFIG_PATH);
			if(data == null){
				return null;
			}
			return new String(data, ENCODE);
		} catch (Exception e) {
			log.error("获取角色出错", e);
		}
		return null;
	}
}
