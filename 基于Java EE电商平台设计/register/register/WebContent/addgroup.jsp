<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    
    <link href='http://fonts.useso.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">
    <link href="css/addfunction.css" rel="stylesheet">
    <link rel="stylesheet" href="css/Cooldog.css">
    <link rel="stylesheet" href="css/iconfont.css">
  <style>
  	h1
  	{
  	     text-align: left;
  	     font-size:small
  	}

  </style>
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品</title>
</head>
<body>
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
            <li><a href="index.html" ><i class="fa fa-home fa-fw"></i>精选</a></li>
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
            <li><a href="shoppingcart.html"><i class="fa fa-eject fa-fw"></i>购物车</a></li>
          </ul>  
        </nav>
      </div>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
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
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	</li>
              	<!--
                              <li><a href="" class="active">Admin panel</a></li>
                <li><a href="">Dashboard</a></li>
                  -->
  
                <li><a href="" class="active">关于我们</a></li>
              </ul>  
            </nav> 
          </div>
        </div>
    
    <div  align="center">
    
    	
            <div class="user-info" align="center">
                <!--标题 -->
                <div class="am-cf am-padding">
                    <br /><br /><br /><br /><div class="am-fl am-cf"><h2><strong class="am-text-danger am-text-lg">添加游戏</strong></h2><br><em>AddGame</em></div>
                </div>
                <hr/>
            </div>
            <!--个人信息 -->

            <form action="addgroup.do" method="post" enctype="multipart/form-data">
                
            <div class="text-place">        
                    游戏名：    <input type="text" name="goodsName" id="goodsName"  ></div><br><br>
        
                    开发商：    <input type="text" name="production" id="production" ><br><br><br>

                    游戏描述：<input type="text" name="description" id="description" ><br><br><br>

                    游戏价格：<input type="text" name="price" id="price"><br><br><br>
                    
                    游戏类别：<input type="text" name="category" id="category"><br><br><br>
            
                    游戏折扣：  <input type="text" name="discount" id="discount"><br><br><br>                
           
                    发行商：     <input type="text" name="publish" id="publish"><br><br><br> 
           
                    游戏图片：<input type="text"  value="在下方选择游戏图片">
                   <input type="file" name="photo" value="选择游戏图片"><br />
                
                     游戏视频：  <input type="text" value="在下方选择游戏视频">             
                     <input type="file" name="video" value="选择游戏视频"><br />      
                 
                    
                
                
                <br><br>
                <div class="info-btn" >
                    <div class="am-btn am-btn-danger">
                    
                    	<input type="submit" value="添加游戏"></div>
                    	<br />  	<br />  	
                  <div align="middle">
                  	<a href="index.jsp">退出添加</a><br><br>
                      <a href="operation.jsp">其余操作</a><br>   	
                  </div>
                 
                </div>
                
            </form>


      
    	
    </div>
      </div>
    </div>
  
    <!-- JS -->
    <script src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
    <script src="js/jquery-migrate-1.2.1.min.js"></script> <!--  jQuery Migrate Plugin -->
    <script src="https://www.google.com/jsapi"></script> <!-- Google Chart -->
    <script>
      /* Google Chart 
      -------------------------------------------------------------------*/
      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart); 
      
      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

          // Create the data table.
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Topping');
          data.addColumn('number', 'Slices');
          data.addRows([
            ['Mushrooms', 3],
            ['Onions', 1],
            ['Olives', 1],
            ['Zucchini', 1],
            ['Pepperoni', 2]
          ]);

          // Set chart options
          var options = {'title':'How Much Pizza I Ate Last Night'};

          // Instantiate and draw our chart, passing in some options.
          var pieChart = new google.visualization.PieChart(document.getElementById('pie_chart_div'));
          pieChart.draw(data, options);

          var barChart = new google.visualization.BarChart(document.getElementById('bar_chart_div'));
          barChart.draw(data, options);
      }

      $(document).ready(function(){
        if($.browser.mozilla) {
          //refresh page on browser resize
          // http://www.sitepoint.com/jquery-refresh-page-browser-resize/
          $(window).bind('resize', function(e)
          {
            if (window.RT) clearTimeout(window.RT);
            window.RT = setTimeout(function()
            {
              this.location.reload(false); /* false to get page from cache */
            }, 200);
          });      
        } else {
          $(window).resize(function(){
            drawChart();
          });  
        }   
      });
      
    </script>
    <script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->
     <script type="text/javascript" src="js/jquery.min.js"></script>
 <script type="text/javascript" src="js/Cooldog.js"></script> 
</body>
</html>