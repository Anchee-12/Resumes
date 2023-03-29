<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
<p style="color:red">
<%String message=(String)request.getAttribute("message");%>
<%=(message)==null?"":message %></p>
<form action="register.do" method="post" enctype="multipart/form-data">
用户名：<input type ="text" name="userName"><br>
密码：<input type="password" name="password"><br>
头像：<input type="file" name="photo"><br> 
<input type ="submit" value ="提交">
</form>
</body>
</html>