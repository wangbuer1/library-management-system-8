# 基于jsp的图书馆管理系统8

## 1、项目介绍

基于jsp的图书馆管理系统8拥有两种角色，分别为管理员和学生，具体功能如下：

管理员：图书管理、用户管理、违规处理、权限管理、个人信息修改

学生：借阅图书、归还图书、借阅历史、处罚记录、个人信息修改


## 2、项目技术

后端框架： Servlet、mvc模式、Javabean

前端框架：Layui、jsp、css、JavaScript、JQuery

## 3、开发环境

- JAVA版本：JDK1.8，其它版本理论上可以
- IDE类型：IDEA、Eclipse、Myeclipse都可以。推荐IDEA与Eclipse
- tomcat版本：Tomcat 7.x、8.x、9.x、10.x版本均可
- 数据库版本：MySql 5.x
- maven版本：无限制
- 硬件环境：Windows 或者 Mac OS


## 4、功能介绍

### 4.1 登录与注册

![登录](https://www.codeshop.fun/Typora-Images/20220517000635.jpg)

![注册](https://www.codeshop.fun/Typora-Images/20220517000643.jpg)

学生可以通过上述界面登录、注册，注册后会自动分配ID号

### 4.2 学生模块

![学生-借阅图书](https://www.codeshop.fun/Typora-Images/20220517000726.jpg)

![学生-借阅历史](https://www.codeshop.fun/Typora-Images/20220517000728.jpg)

![学生-归还图书](https://www.codeshop.fun/Typora-Images/20220517000730.jpg)

![学生-处罚记录](https://www.codeshop.fun/Typora-Images/20220517000739.jpg)

![学生-个人信息](https://www.codeshop.fun/Typora-Images/20220517000742.jpg)

- 借阅图书：学生可以通过关键字模糊查询图书信息，然后借阅图书。借阅图书时，需要判断该学生现已借阅的图书数量，若超过3本，则暂时不能借书；若尚有未缴纳罚金，暂时不能借书；否则点击“确认借阅 ”，即完成借阅。

- 归还图书：学生可以通过书号归还图书，以及查看当前借书信息

- 借阅历史：学生可以查看借阅图书历史记录，包括当前已借阅的图书和已归还的图书，记录内容包括读者id、书籍信息和借阅的开始日期以及结束日期；若书籍尚未归还，“结束日期”后会显示“尚未归还”

- 处罚记录：学生可以查看自己的违规记录，如若相关书籍仍未进行线下归还且学生也未缴纳罚金，则提示尚未缴纳罚金；否则显示该条借阅记录的超期天数。

- 个人信息修改：学生可以修改个人信息，包括修改姓名、密码、性别、电话等，其中用户id不可修改。


### 4.3 管理员模块

![管理员-读者管理](https://www.codeshop.fun/Typora-Images/20220517001001.jpg)

![管理员-图书管理](https://www.codeshop.fun/Typora-Images/20220517001004.jpg)

![管理员-增加图书](https://www.codeshop.fun/Typora-Images/20220517001008.jpg)

![管理员-违规处理](https://www.codeshop.fun/Typora-Images/20220517001009.jpg)

![管理员-权限管理](https://www.codeshop.fun/Typora-Images/20220517001011.jpg)

- 图书管理：管理员可以通过关键字模糊查询图书信息，还可以对图书进行增加、删除、修改等操作

- 用户管理：管理员可以通过ID号和姓名查询学生信息，并对学生进行增加、删除、修改等操作，若“黑名单”功能被启用，可以点击“加入黑名单”、“移除黑名单”来控制用户的登录权限。

- 违规处理：管理员可以根据学生ID查询其违规记录，并缴纳罚款，用户缴纳罚款后，可点击“缴纳罚款”，撤销对该生的处罚，恢复其正常借阅和归还权利；

- 权限管理：管理员可以设置最长借阅天数和是否开启黑名单功能

- 个人信息修改：管理员可以个人信息


### 4.4 项目设计文档目录

![设计文档目录](https://www.codeshop.fun/Typora-Images/20220517001031.jpg)



## 6、获取方式


关注公众号： **程序员王不二**，回复 “ **图书馆8**” ，即可获取完整版的项目代码。

 ![](https://www.codeshop.fun/Typora-Images/202205281253739.png)


* [springboot项目](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzkwMjM1MjM0Ng==&action=getalbum&album_id=2387377898791223296#wechat_redirect)

* [简单无框架项目](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzkwMjM1MjM0Ng==&action=getalbum&album_id=2387378317047218183#wechat_redirect)

  

