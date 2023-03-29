<%@page import="com.etc.vo.shangpin"%>
<%@page import="com.etc.dao.GameDao"%>
<%@page import="com.etc.dao.xiangxi"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Stream |  Base On Steam Better Than Steam</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    
    <link href='http://fonts.useso.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">
		<meta charset="UTF-8">
		<title>游戏详情</title>
	</head>
	<body>
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
          <div class="square"></div>
          <h1>Stream</h1>
        </header>
        <div class="profile-photo-container">
          <img src="images/profile-photo.jpg" alt="Profile Photo" class="img-responsive">  
          <div class="profile-photo-overlay"></div>
        </div>      
        <!-- Search box -->
        <form class="templatemo-search-form" role="search">
          <div class="input-group">
              <button type="submit" class="fa fa-search"></button>
              <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">           
          </div>
        </form>
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
        </div>
        <nav class="templatemo-left-nav">          
          <ul>
            <li><a href="#" class="active"><i class="fa fa-home fa-fw"></i>精选</a></li>
            <li><a href="data-visualization.html"><i class="fa fa-bar-chart fa-fw"></i>夏日特促</a></li>
            <li><a href="data-visualization.html"><i class="fa fa-database fa-fw"></i>游戏</a></li>
            <li><a href="maps.html"><i class="fa fa-map-marker fa-fw"></i>软件</a></li>
            <li><a href="manage-users.html"><i class="fa fa-users fa-fw"></i>硬件</a></li>
            <li><a href="preferences.html"><i class="fa fa-sliders fa-fw"></i>视频</a></li>
            <li><a href="login.html"><i class="fa fa-eject fa-fw"></i>新闻</a></li>
             <li><a href="login.html"><i class="fa fa-eject fa-fw"></i>购物车</a></li>
          </ul>  
        </nav>
        </div>
        
        <%
        request.setCharacterEncoding("utf-8");
       //  String nname=request.getParameter("name");
        xiangxi xi=new xiangxi();
       shangpin game= xi.selectName(request.getParameter("name"));
       //System.out.print("游戏名为："+nname);
    
       System.out.print("游戏名为："+game.getName());
       
       %>

        <div class="templatemo-content-widget white-bg col-1 text-center">
    	 <h1 class="text-uppercase"><font color="black"><%= game.getName() %></h1>
    	   <video width="800" autoplay="autoplay" controls="controls"><source src="<%=game.getShiping() %>"video/webm">
    	   	
    	   </source></video>
    	 	<div style="margin-top: 50px">
    	 	<h5><font color="black"><%= game.getKaifashang() %></font></h5>
    	    <h5><font color="black"><%= game.getFaxingshang()%></font></h5>
    	    </div>
    	    <div><h5><font color="black">$<%= game.getPrice() %></h5>
    	    <button type="button" class="yellow" style="width:100px;height:60px ;"> <font color="dodgerblue"><a href="xiangqin.jsp?name=<%=game.getName() %>">添加至购物车</a></font></button>
    	    </div>
    	    <div> 
    	    	
         <h5><font color="black">  最低配置:</td></h5>
         <h5><font color="black"> 操作系统: Windows 7 or newer</td></h5>
         <h5><font color="black">处理器: Dual core from Intel or AMD at 2.8 GHz</td></h5>
         <h5><font color="black"> 内存: 4 GB RAM</td></h5>
         <h5><font color="black"> 图形: nVidia GeForce 8600/9600GT, ATI/AMD Radeon HD2600/3600</td></h5>
         <h5><font color="black"> DirectX 版本: 9.0c</td></h5>
         <h5><font color="black"> 网络: 宽带互联网连接</td></h5>
         <h5><font color="black">存储空间: 需要 15 GB 可用空间</td></h5>
         <h5><font color="black">声卡: DirectX Compatible</td></h5>
           </div>
        
  
      	
	</body>
</html>