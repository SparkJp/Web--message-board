# Web--message-board
一个简易的JAVA-Web留言板系统。

数据库采用Mysql，数据类型与ER图如下：

![](https://res.cloudinary.com/xxxhlown/image/upload/v1626926194/2_nyufqv.png)
![](https://res.cloudinary.com/xxxhlown/image/upload/v1626926049/web-message/1_bfpev2.png)

本次简介留言板采用MVC模式，各个部分如下：

Model：	
myDB.class 数据库连接，检测登录用户状态（是否为管理员admin），实现数据库的增删改查，并对多个帖子实现分页。

View：	
index.jsp 展示初始界面，用户可以选择登录 or 注册
login.jsp 由初始界面跳转而来，进行已有用户的登录功能
register.jsp 由初始界面跳转而来，进行新用户的注册功能
jspHead.jsp 留言界面的公共头文件，保存当前webapp的文件路径等
page.jsp 登陆后的留言板首页，可以看帖，发帖，回帖，删帖等功能
userList.jsp 管理员界面，只有当usertype为admin时才可以访问，实现禁言等功能
userp.jsp 看帖界面，若没有被禁言，用户在此界面可以进行回帖
exit.jsp 用户退出界面
error.jsp 错误界面，sql与服务器错误将返回该界面

Controller: 	
myServlet 判断用户是否已存在并进行管理员权限的识别，进行用户注册
contentServlet 进行page页面的初始化，获取管理员状态等
userServlet 进行管理员页面的初始化，并根据相应动作更新数据库状态
