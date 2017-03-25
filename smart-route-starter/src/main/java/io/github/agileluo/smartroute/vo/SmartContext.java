package io.github.agileluo.smartroute.vo;

/**
 * 上下文中继类
 * @author marlon.luo
 *
 */
public class SmartContext {
	//事务id
	private String transId;
	//用户id
	private String userId;
	//调试机器
	private String debugHost;
	
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDebugHost() {
		return debugHost;
	}
	public void setDebugHost(String debugHost) {
		this.debugHost = debugHost;
	}
	public SmartContext(String transId, String userId, String debugHost) {
		super();
		this.transId = transId;
		this.userId = userId;
		this.debugHost = debugHost;
	}
}
