package io.github.agileluo.smartroute.vo;

/**
 * 上下文中继类
 * @author marlon.luo
 *
 */
public class SmartContext {
	private String clientId;
	private String clientIp;
	
	public String getClientId() {
		return clientId;
	}
	public SmartContext setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}
	public String getClientIp() {
		return clientIp;
	}
	public SmartContext setClientIp(String clientIp) {
		this.clientIp = clientIp;
		return this;
	}
}
