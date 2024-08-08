<%--
  Created by IntelliJ IDEA.
  User: 浙李鞠人
  Date: 2024/7/13
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html lang="ch">
<head>
    <meta charset="utf-8">
    <title>用户登录</title>

</head>
<body>
<h1>用户登录</h1>
<form action="login" method="post">
    <label>账&nbsp;&nbsp;&nbsp;号：</label><input type="text"  name="username" value="${cookie.username.value}" ><br>
    <lable>密&nbsp;&nbsp;&nbsp;码:</lable><input type="password" name="password" value="${cookie.password.value}">
    <label>记住密码</label><input type="checkbox" name="rememberPassword"><br>
    <input type="submit" value="提交" ><br>
<span style="color: red" id="error">${errorMsg}</span>
</form>
</body>


</html>
