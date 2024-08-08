<%--
  Created by IntelliJ IDEA.
  User: 浙李鞠人
  Date: 2024/8/8
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax用户注册</title>

</head>
<body>
<h1>用户注册</h1>
<%--<form action="register" method="post">--%>
    <label>账&nbsp;&nbsp;&nbsp;号:</label><input type="text" id="username" name="username" onkeyup="existUsername(this)" ><br>
    <lable>密&nbsp;&nbsp;&nbsp;码:</lable><input type="password" id="password" name="password"><br>
    <label>验&nbsp;&nbsp;证码:</label><input typr="text" name="checkCode" id="checkCode"><br>
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
    <script>
        function ajaxRegister(){
            var username=document.getElementById("username").value;
            var password=document.getElementById("password").value;
            var checkCode=document.getElementById("checkCode").value;
            axios({
                method:"POST",
                url:"http://localhost:8080${pageContext.request.contextPath}/userAjaxRegister",

                params:{
                    username:username,
                    password:password,
                    checkCode:checkCode
                }

            }).then(function (result){
                var jsonData=result.data;
                if(jsonData.code!="200"){
                    alert(jsonData.msg);
                    return;
                }
                alert("注册成功");
                window.location.href="http://localhost:8080${pageContext.request.contextPath}/login";
            })

        }
    </script>
    <span style="color: red" id="error">${errorMsg}</span><br>
    <input type="submit" value="注册" onclick="ajaxRegister()" ><br>

<%--</form>--%>
</body>
</html>
