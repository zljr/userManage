<%--
  Created by IntelliJ IDEA.
  User: 浙李鞠人
  Date: 2024/7/14
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>

</head>
<body>
<h1>用户注册</h1>
<form action="register" method="post">
  <label>账&nbsp;&nbsp;&nbsp;号:</label><input type="text"  name="username" onkeyup="existUsername(this)" ><br>
  <lable>密&nbsp;&nbsp;&nbsp;码:</lable><input type="password" name="password"><br>
    <label>验&nbsp;&nbsp;证码:</label><input typr="text" name="checkCode"><br>
    <img id="checkCodeImg" src="verifyCodeServlet">
    <a href="#" id="changeImg">看不清？换一个</a>
    <script>
        document.getElementById("changeImg").onclick=function (){
            document.getElementById("checkCodeImg").src="verifyCodeServlet?"+new Date().getMilliseconds();
        }
    </script>
  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

  <script>
    function existUsername(object){

      var username=object.value;

      axios({
        method:"GET",
        url:"http://localhost:8080${pageContext.request.contextPath}/existUserName",

        params:{
          username:username,
        }

      }).then(function (result){
        document.getElementById("error").innerText=result.data;

      })


    }
  </script>
<span style="color: red" id="error">${errorMsg}</span><br>
  <input type="submit" value="注册" ><br>

</form>
</body>
</html>
