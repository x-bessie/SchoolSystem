# SchoolSystem

### 项目的开始
项目部署环境
1、JDK：1.8+  
2、Redis 3.0+  
3、Maven 3.0+
4、MYSQL 5.5.0+
5、Node.js v10+

工具使用
1.wxamppServer   mysql数据库
2.navicat premium 11.0+
3.idea 2019.3 x64
4.vs code 
5.RedisDesktopManager  redis 图形化界面工具

项目具体配置：
自行浏览博客教程,并安装配置好...

#### 前端项目部署
在完成node.js 后，要对项目进行部署。
在项目目录下，dos命令行输出 npm install
备注：若报缺少python环境的错，可能需要python2.7的环境，请参考这个设置。（没有请忽略）
1.https://blog.csdn.net/qq_38053195/article/details/87070583
2.https://www.cnblogs.com/telwanggs/p/10974846.html

#### 前端项目启动
部署完成后  命令行输入  npm dev run  

项目启动好之后，浏览器输入地址：localhost:8013 即可看到系统

###数据导入
sql文件：SchoolSystem/eladmin/database_sql/eladmin.sql
步骤：1.新建一个叫 eladmin 的数据库
2.将eladmin.sql 的sql导入即可

#### 后端项目部署
1.修改数据库配置
在eladmin\eladmin-system\src\main\resources\config  下的
`application-dev.yml` 和`application-prod.yml`
修改里面的数据库账号密码,我这里的密码为空
```
 url: jdbc:log4jdbc:mysql://localhost:3306/eladmin?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false
 	username: root
    password:
```

#### 后端项目启动
1.启动redis    https://blog.csdn.net/luuvyjune/article/details/81016295
 开启：redis-server redis.windows.conf
 关闭 redis-cli.exe shutdown

2.启动mysql数据库 
这里启动wxamppServer下的mysql就可以

3.启动AppRun
`eladmin\eladmin-system\src\main\java\me\zhengjie\AppRun.java`


使用技术指导：
前端：ECMAScript 6
vue.js
element 