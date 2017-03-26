# smart-route
基于spring cloud的智能路由,功能如下

 * 本地开发支持， 调用端配置debug.local=true，则优先调用本地服务
 * 测试支持：可将请求定位至指定机器
 * 远程调试支持：远程调试服务， 其它请求不导流至此服务节点（测试人员可做到专包专用的功能，其它服务则共享）
 * A/B测试
 * 灰度发布
 * 默认规则： 支持跨中心的动态负载ZoneAvoidanceRule实现

# smart-route-starter
项目引入此包即可使用

# smart-route-starter-server
动态规则配置服务，动态规则支持需要用到

