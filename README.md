## 环境需求
>- JDK 1.8
>- IDEA
>- Maven
>- Redis
>- MySql 

## 实现架构
![架构说明](https://github.com/lilililixiangpeng/img-folder/blob/master/%E7%88%AC%E8%99%AB.PNG)

## 项目说明
- 本项目可以直接爬取古诗文网站的古诗文，利用线程池实现多线程爬去，充分使用性能，但是由于代理IP池使用的是免费ip，运行时的检测会非常慢，请耐心等候。
- 为了考虑性能的关系，采用了后台线程将redis缓存储存到数据库。
**详细的ip代理池请参考：**[ip-proxy](https://github.com/lilililixiangpeng/IP-Proxy)

## 使用说明
- 在Config.properties中配置你想要爬去的线程数和页数
- 在Redis-config.properties中配置你的Redis信息
- 将项目中card.sql用mysql读取。
- 更改你的数据库连接信息
- 运行GetInformation类

