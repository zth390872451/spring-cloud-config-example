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