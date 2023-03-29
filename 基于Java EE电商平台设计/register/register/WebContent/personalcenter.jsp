<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.etc.vo.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <meta name="description" content="">
    <meta name="author" content="templatemo">

    <style>
    	    .p1 {margin-left:0 px;
		    margin-right: 0px;
		  
		    color:black; 
	      	 }
    	
    	
             img{
             	width:100px;
              height:100px;
             	padding: 0;
             	
             }
  
   			 .blue {
               background-color:white;
                 color:black;
              }
              h7
              {
              	vertical-align: middle;
              	color:black;
              	font-size: large;
              	
              }
    </style>
    <link href='http://fonts.useso.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">
    
		<title>个人中心</title>
</head>
<body>

<p style="color:green">
<%String message=(String)request.getAttribute("message");%>
<%=(message)==null?"":message %></p>    
<%User  user=(User)request.getAttribute("userInfo"); %>   
                                   
  <script LANGUAGE="JavaScript">
    function show(){
    	var result = document.getElementById("payway").value;
        location.href="form.jsp?value="+result;
    }
</script>

		<!-- Left column -->
		<div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
          <div class="square"></div>
          <h1>Stream</h1>
        </header>
   
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
            <li><a href="index.html" class="active"><i class="fa fa-home fa-fw"></i>精选</a></li>
            <li><a href="data-visualization.html"><i class="fa fa-bar-chart fa-fw"></i>夏日特促</a></li>
           <li><a href="data-visualization.html" ><i class="fa fa-database fa-fw"></i>游戏</a>
                  <ul class="sub_menu" >
                  	<h4><li><a href="#">动作</a></li></h4>
                  	<h4><li><a href="#">冒险</a></li></h4>
                  	<h4><li><a href="#">休闲</a></li></h4> 
                  	<h4><li><a href="#">射击</a></li></h4>
                  	<h4><li><a href="#">策略</a></li></h4>
                  </ul>
            </li>
            <li><a href="maps.html"><i class="fa fa-map-marker fa-fw"></i>软件</a></li>
            <li><a href="manage-users.html"><i class="fa fa-users fa-fw"></i>硬件</a></li>
            <li><a href="preferences.html"><i class="fa fa-sliders fa-fw"></i>视频</a></li>
             <li><a href="maps.html"><i class="fa fa-eject fa-fw"></i>购物车</a></li>
          </ul>  
        </nav>
      </div>
     <!-- Main content -->
     
      <div class="templatemo-content-widget white-bg col-1 text-center">
                    <div class="templatemo-top-nav-container">
          <div class="row">
            <nav class="templatemo-top-nav col-lg-12 col-md-12">
              <ul class="text-uppercase">
              	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	</li>
           
                <li><a href="" class="active">关于我们</a></li>
           
              </ul>  
            </nav> 
          </div>
        </div>
       
     <br />
     <br />
     <br />
    
               <div>
              <h1 class="text-uppercase p1">个人中心</h1>
              	</div>
     <br />
     <br />
     <br />
     <div class="templatemo-top-nav-container">
          <div class="row">
            <nav class="templatemo-top-nav col-lg-12 col-md-12">
              <ul class="text-uppercase">
             
           
     <h3> <div style="float: left;">头像:</div> </h3>
           <img src="<%=request.getAttribute("url") %>" alt=" " > <hr>
           <h3>  <div style="float: left;"> 用户名：&nbsp;&nbsp;&nbsp;<%=user.getUserName() %></div><br/><br/></h3><hr>
       <h3><div style="float: left;">性别：&nbsp;&nbsp;&nbsp;&nbsp;<%=user.getGender() %></div><br/><br/></h3><hr>
       <h3> <div style="float: left;">年龄：&nbsp;&nbsp;&nbsp;&nbsp;<%=user.getAge() %></div><br/><br/></h3><hr>
       <h3> <div style="float: left;"> 邮箱：&nbsp;&nbsp;&nbsp;&nbsp;<%=user.getEmail() %></div><br/><br/>  </h3><hr>
       <h3> <div style="float: left;"> 地域：&nbsp;&nbsp;&nbsp;&nbsp;<%=user.getRegion() %></div><br/><br/>  </h3><hr>
           
              </ul>  
            </nav> 
          </div>
        </div>
  <div class="edit">
  <br/> <h3> <a href="editor.jsp">编辑</a></h3><br/>
</div>

<div class="login-out">
    <h3><a href="index.jsp">退出</a></h3>
</div>
     

    
    </div>
</body>
</html>