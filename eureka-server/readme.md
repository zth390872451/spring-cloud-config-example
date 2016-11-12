参考链接：http://blog.didispace.com/springcloud7/
spring-cloud-config各个模块总结：
rabbitmq作为消息总线的组件
eureka-server作为注册中心
config-server-eureka作为配置服务器
rabbit-node1和node2作为客户端，可从配置服务器设置的git仓库按照
指定规则获取配置信息并启动或者刷新应用

配置文件结构：
    resources目录：
        application.properties：设置spring boot 应用的相关信息，如应用名称、端口等
        bootstrap.properties:设置cloud配置信息，如 配置服务ID、消息总线bus的配置等
启动步骤：
    依次开启Eureka-server注册中心，config-server-eureka 配置服务组件，
    config-client-eureka-rabbit-node01、config-client-eureka-rabbit-node02 客户端。

客户端与服务端的配置 bus/push:    
    只在config-client-eureka-rabbit-node01、config-client-eureka-rabbit-node02 客户端添加
    1、bootstrap.propertie：加入rabbitmq配置
    2、pom.xml:加入消息总线所需要的依赖包bus-amqp和spring-retry(重试机制)
    功能：在其中任意一个客户端发送 /bus/refresh 请求，总线将会发送事件推送，提醒其他客户端去配置服务器的git仓库
    pull拉取配置信息，刷新应用。
    
   3、如果在上述基础上，在config-server 配置服务器应用中加入上述 1、2 信息。
    额外功能：
        a、可向 config-server 配置服务器应用 发送 /bus/refresh 请求，通知所有client去pull拉取信息。
        b、可定向提醒部分client去pull拉取信息。
    
    
访问:先访问两个config-client-eureka的/from请求，会返回当前github上 config-repo/didispace-dev.properties中的from属性。
    接着，我们直接在github上修改config-repo/didispace-dev.properties中的from属性值，并发送POST请求到其中的一个/bus/refresh
    (注意事项：在github上修改之后，可能有一定的延迟，当发送bus/refresh之后，不会发现已经修改了配置信息)。
    最后，我们再分别访问启动的两个config-client-eureka的/from请求，此时这两个请求都会返回最新的config-repo/didispace-dev.properties中的from属性。
    到这里，我们已经能够通过Spring Cloud Bus来实时更新总线上的属性配置了。
    
番外：参考的链接所共享的文章对应的github项目中，在config-server是没有必要在resources目录下添加多余的didispace*.properties文件的。
    
    
    