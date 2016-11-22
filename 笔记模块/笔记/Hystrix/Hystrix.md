Hystrix：http://blog.csdn.net/MrTitan/article/details/51565074
    提供了以下重要功能： 
- 同步/异步操作封装 
- Fallback 
- ThreadPool的隔离 
- 请求Cache 
- 请求合并

http://www.cnblogs.com/java-zhao/p/5509593.html

hystrix的执行方式
同步执行：超时时间起作用
异步执行：超时时间不起作用（1.4.0之前的版本，在调用get()的时候开始计时起作用）
hystrix的隔离级别
HystrixCommandGroupKey：这个的名称设置为一个被调用的服务，eg.hotelService，所有这个服务下的方法都用同一个线程池（前提是没有配置ThreadPoolKey）
HystrixCommandKey：这个名称通常是被调用服务的一个方法的名字（实际上就是被调用服务某一个controller中的一个对外方法），eg.getHotelInfo()
ThreadPoolKey：这个用的很少，除非一个被调用服务中的有些被调用方法快、有的被调用方法慢，这样的话，就需要分别使用一个ThreadPoolKey，为每一个方法单独分配线程池

http://blog.csdn.net/xiaoyu411502/article/details/50601687 官方的中文总结

https://stonetingxin.gitbooks.io/hystrix/content/ 基本上是官方的中文翻译

https://github.com/Netflix/Hystrix/wiki/Configuration hystrix配置介绍

http://blog.vicoder.com/hystrix-configuration/ 配置介绍

http://www.insaneprogramming.be/blog/2014/08/19/hystrix-spring-boot/ boot集成hystrix

https://yq.aliyun.com/articles/61510 Spring Cloud -- Hystrix 配置说明

断路器、资源隔离与自我修复

断路器(Cricuit Breaker)是一种能够在远程服务不可用时自动熔断(打开开关)，并在远程服务恢复时自动恢复(闭合开关)的设施，Spring Cloud通过Netflix的Hystrix组件提供断路器、资源隔离与自我修复功能。

资源隔离

首选，Hystrix对每一个依赖服务都配置了一个线程池，对依赖服务的调用会在线程池中执行。例如，我们设计服务 I 的线程池大小为20, 那么 Hystrix会最多允许有20个容器线程调用服务 I, 如果超出20，Hystrix会拒绝并快速失败。这样即使服务 I 长时间未响应，容器最多也只能堵塞20个线程，剩余80个线程仍然可以处理用户请求。

快速失败

快速失败是防止资源耗尽的关键一点。当 Hystrix 发现在过去某段时间内对服务 I 的调用出错率达到某个阀值时，Hystrix 就会“熔断”该服务，后续任何向服务 I 的请求都会快速失败，而不是白白让调用线程去等待。

自我修复

处于熔断状态的服务，在经过一段时间后，Hystrix会让其进入“半关闭”状态，即允许少量请求通过，然后统计调用的成功率。如果这个请求都能成功，Hystrix 会恢复该服务，从而达到自我修复的效果。其中，在服务被熔断到进入半关闭状态之间的时间，就是留给开发人员排查错误并恢复故障的时间，开发人员可以通过监控措施得到提醒并线上排查。
