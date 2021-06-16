本项目是TGAM脑电模块-疲劳驾驶检测一个基于Spring Boot + Vue.js构建的一个前后端分离项目框架的后端项目代码部分。  
## 后端技术栈
+ SpringBoot   
+ Mybatis plus  
+ Swagger2-ui+knife4j
+ aliyun-sdk-oss
+ jwt

### 提示 
+ 代码存放于 https://github.com/zhangjunze1/TGAM_project
+ 对应前端代码 https://github.com/zhangjunze1/TGAM_web_project (端口8080)
+ Swagger2-ui+knife4j接口文档
    + 本地启动后端 (端口：8083)
    + 在网页输入 localhost:8083/doc.html
+ 本项目是本人做的第二个前后端分离且全栈的项目,熟练使用了swagger开发工具,对Springboot框架有了更深的理解,熟悉了Mybatis-plus的运作过程。
+ 本人联系方式：QQ: 412057605  WX: Zjzhys

### 运行提示
1. cn.edu.shu.xj.ser.config.oss
```javascript
    String endpoint = "............";
    String accessKeyId = "............";
    String accessKeySecret = "............";
    String bucketName = "............";
```
此类中，所属的阿里云的Oss的参数填上自己的（前端有上传图片到阿里云oss的功能）
2. 在application.properties中
+ 运用自己的数据库运行账号密码运行
3. 对应数据库的sql文件 在resources目录下tgam.sql
+ 管理员 账号：admin 密码：123456
+ 用户 账号：13867917373 密码：123456 （其余用户密码详见数据库）