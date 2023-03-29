<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>uploading</title>
</head>
<body>
<h1 style="color: red;">添加商品至数据库</h1>
<form method="post" action="shangchuan.do" enctype="multipart/form-data">   
输入游戏名    ： <input type="text" name="name" />
输入游戏价格 ：<input type="text" name="price" />
输入游戏折扣 ：<input type="text" name="discount" />
输入游戏标签 ：<input type="text" name="describe" />
选择图片        :<input type="file" name="photo" />
    <br/><br/>
    <input type="submit" value="上传" />
</form>

</body>
</html>