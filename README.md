# smart-route
基于spring cloud的智能路由,功能如下

 * 本地开发支持， 调用端配置debug.local=true，则优先调用本地服务
 * 远程调试支持：远程调试服务， 其它请求不导流至此服务节点（测试人员可做到专包专用的功能，其它服务则共享）
 * 测试支持：可将请求定位至指定机器（计划中）
 * A/B测试（计划中）
 * 灰度发布（计划中）
 * 默认规则： 支持跨中心的动态负载ZoneAvoidanceRule实现

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

## debug配置说明

规则

    debug.[id|ip].[{id}|{ip}]=({serviceId}:{ip},)+
    
字段说明

* debug.id.{id}: 匹配客户端id
* debug.ip.{ip}: 匹配客户ip地址
* {serviceId}.{ip}: 需要调试的服务节点：serviceId-服务名， ip-节点ip，可配置多个

## 例子

    debug.ip.192.168.1.5=order:192.120.40.22
    
表示： 192.120.40.22所在的order服务处于调试模式， ip为192.168.1.5的客户请求会导流到这个order节点，其它请求不会导流到这个节点
