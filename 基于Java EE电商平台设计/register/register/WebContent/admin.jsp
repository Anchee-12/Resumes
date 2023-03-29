<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.etc.vo.User" %>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>admin</title>
</head>
<body>
	<%
		//强制转换，获取的是对象Object类型
		Object user = (User) (session.getAttribute("userInfo"));
		if (user == null) {
			request.setAttribute("message", "提示信息：该资源需要登录后访问");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	%>
	登录成功！这是admin.jsp
</body>
</html>