package io.github.agileluo.smartroute.constant;

public interface SmartRuleConstant {
	/** 配置地址 */
	String CONFIG_PATH = "/smart-rule/config";
	
	interface RuleOrder{
		/** 调试 */
		int DEBUG = 0;
		/** 测试 */
		int TEST = 100;
		/** AB测试 */
		int AB = 200;
		/** 灰度发布 */
		int GRAY = 300;
	}
}
