<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品</title>
<script type="text/javascript">
			//window.onload=loadProcince();
			function loadgroup(){
			var opt=document.createElement("option");
			opt.innerText="商品描述";
			opt.value="1";
			opt.name="description"
			document.getElementById("group").appendChild(opt);
			opt=document.createElement("option");
			opt.innerText="商品类别";
			opt.value="2";
			opt.name="category"
			document.getElementById("group").appendChild(opt);
			opt=document.createElement("option");
			opt.innerText="商品价格";
			opt.value="3";
			opt.name="price"
			document.getElementById("group").appendChild(opt);
			opt=document.createElement("option");
			opt.innerText="开发商";
			opt.value="4";
			opt.name="production"
			document.getElementById("group").appendChild(opt);
			opt=document.createElement("option");
			opt.innerText="商品图";
			opt.value="5";
			opt.name="photo"
			document.getElementById("group").appendChild(opt);
			
}
</script>
</head>
<body onload="loadgroup()">
<p style="color:red">
<%String message=(String)request.getAttribute("message");%>
<%=(message)==null?"":message %></p>
<form action="updategroup.do" method="post">
商品名：<input type ="text" name="goodsName"><br><br>
<select id="group">
	<option  name="">请选择要修改的项目</option>
</select>
请输入修改的内容：<input type ="text" name="content"><br><br>
<input type ="submit" value ="修改商品"><br><br>
<a href="index.jsp">退出修改</a><br><br>
<a href="operation.jsp">其余操作</a><br>
</body>
</html>