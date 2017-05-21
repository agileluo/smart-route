# smart-route
基于spring cloud的智能路由,功能如下

 * 开发模式：优先调用本地服务， order=0
 * SIT优先：优先调用指定IP服务， order=100
 * 远程调试：远程调试指定服务，其它人员请求不会导流至此节点， order=200
 * A/B测试（计划中）
 * 灰度发布（计划中）
 * 默认规则： 支持跨中心的动态负载ZoneAvoidanceRule实现

# 组件及依赖

 * smart-route-starter: 项目中引入此jar即可实现智能路由
 * smart-route-starter-server: 智能路由配置服务，提供webUI配置
 * zookeeper: 路由配置、路由加载及重置、开发模式服务地址列表都依赖zk

# smart-route-starter
项目引入此包即可使用
	
	<dependency>
		<groupId>io.github.agileluo</groupId>
		<artifactId>smart-route-starter</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>

# smart-route-starter-server
规则配置服务，提供web界面

## 服务运行步骤

1,打包

    mvn clean package

2, 运行

    java -jar smart-route-starter-server-0.0.1-SNAPSHOT.jar
    
3, 打开浏览器

    http://localhost:9001
    
# 规则配置

## 开发模式

配置设置

	develop=true
	
## SIT优先

	sit=ip(,ip)*

可配置多个ip，请求会优先访问这个ip所在的服务

## 远程调试

规则

    debug.[id|ip].[{id}|{ip}]=({serviceId}:{ip},)+
    
字段说明

* debug.id.{id}: 匹配客户端id
* debug.ip.{ip}: 匹配客户ip地址
* {serviceId}.{ip}: 需要调试的服务节点：serviceId-服务名， ip-节点ip，可配置多个

## 例子

    debug.ip.192.168.1.5=order:192.120.40.22
    
表示： 192.120.40.22所在的order服务处于调试模式， ip为192.168.1.5的客户请求会导流到这个order节点，其它请求不会导流到这个节点
