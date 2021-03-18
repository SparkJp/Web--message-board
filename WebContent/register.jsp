<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <link rel="stylesheet" type="text/css" href="Login.css" />
  <style>
    html {
      width: 100%;
      height: 100%;
      overflow: hidden;
      font-style: sans-serif;
    }

    body {
      width: 100%;
      height: 100%;
      font-family: 'Fira code', sans-serif;
      margin: 0;
      background-color: #4A374A;
    }

    #register_box {
      width: 300px;
      height: 300px;
      position: absolute;
      top: 50%;
      left: 50%;
      margin-left: -150px;
      margin-top: -150px;
    }

    #register_box h1 {
      color: #fff;
      text-shadow: 0 0 10px;
      letter-spacing: 1px;
      text-align: center;
    }

    h1 {
      font-size: 2em;
      margin: 0.67em 0;
    }

    input {
      width: 278px;
      height: 18px;
      margin-bottom: 10px;
      outline: none;
      padding: 10px;
      font-size: 13px;
      color: #fff;
      text-shadow: 1px 1px 1px;
      border-top: 1px solid #312E3D;
      border-left: 1px solid #312E3D;
      border-right: 1px solid #312E3D;
      border-bottom: 1px solid #56536A;
      border-radius: 4px;
      background-color: #2D2D3F;
    }

    .but {
      width: 300px;
      min-height: 20px;
      display: block;
      background-color: #4a77d4;
      border: 1px solid #3762bc;
      color: #fff;
      padding: 9px 14px;
      font-size: 15px;
      line-height: normal;
      border-radius: 5px;
      margin: 0;
    }
  </style>
</head>

<body>
  <c:if test="${not empty msg}">
    ${msg}
    <%request.removeAttribute("msg"); %>
  </c:if>
  <div id="register_box">
    <h1>Register</h1>
    <form action="myseverlet" method="post">
      <input type="hidden" name="action" value="register">
      <input type="text" required="required" placeholder="id" name="sid"></input><br>
      <input type="text" required="required" placeholder="用户名" name="username"></input><br>
      <input type="password" required="required" placeholder="密码" name="password1"></input><br>
      <input type="password" required="required" placeholder="再次输入密码" name="password2"></input><br>
      <input type="text" required="required" placeholder="请确认身份" name="usertype"></input>
      <button class="but" type="submit">提交注册</button>
    </form>
  </div>
</body>

</html>