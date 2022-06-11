## 湘约楼点餐系统(SerMs&扣毛)

### 技术栈:

> 前台技术栈:Vue+ Element Plus+ Vant2 + VueX + router
>
> 后端框架:SpringBoot+Mp+SSM
>
> 缓存中间件: Redis
> 数据库: MySql 主从架构(读写分离)
>
> 其他:建网SMS
>

### 开发历程:

#### 5.7 / 5.11

> 1. 前后台采用Vue+El搭建
> 2. 前后端采用SpringBoot,mp,MySql
> 3. 经历五天的日夜兼程,基本功能已经成熟
> 4. 前端SpringBoot以集成建网SMS(感谢随意哥的短信账号支持)
> 5. V1.0版本以发布到master分支

#### 5.12

> 1. 菜品/套餐改用Redis缓存存储
> 2. 短信验证码改用Redis缓存存储

#### 5.14 Redis缓存优化

> 1. 缓存改用SpringCache
> 2. 完善一些Bug
> 3. 修复一些前端页面问题
> 4. 新加员工删除

#### 5.17 读写分离

> 1. 主从复制已完成
> 2. 读写分离进行中(sharding-JDBC)
>

#### ....

### SwaggerUI接口文档

如有问题请发送邮件到:1839928782@qq.com
个人博客:serms.top