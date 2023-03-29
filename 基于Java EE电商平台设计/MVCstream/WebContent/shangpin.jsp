<%@page import="com.etc.dao.xiangxi"%>
<%@page import="com.etc.vo.shangpin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etc.dao.GameDao" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
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
      <link rel="stylesheet" href="css/Cooldog.css">
    <link rel="stylesheet" href="css/iconfont.css">
  
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
		
		#liebiao
			{
				width: 100%;
				height: 100%;
				padding: 0;
				border: 0;
				list-style: none;
			}
			#hang{
				width: 700px;
				height: 100px;
						border-bottom: 5px solid white;
						border-left: 1px solid white;
						
					
				} 
				#tupian
				{
					width:30%;
					height: 100%;
					padding: 0;
					border: 0;
					
					float: left;
					cursor:pointer;  
			
				}
				#wenzi
				{
					width:100%;
					height: 100%;
					padding: 0;
					border: 0;
					
					cursor:pointer; 
				} 
				
					
				
				img{
					width: 100%;
					width:100%;
					height: 100%;
					padding: 0;
					border: 0;
				}
		
		
		</style>
		
  </head>
  <body>  
    <!-- 左侧导航栏 -->
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
        <!-- 搜索框 -->
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
          </ul>  
        </nav>
      </div>
      <!-- 主content --> 
      <div class="templatemo-content col-1 white-bg">
        <div class="templatemo-top-nav-container" >
          <div class="row">
            <nav class="templatemo-top-nav col-lg-12 col-md-12 ">
              <ul class="text-uppercase" >
              	<li >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
  
                <li><a href="">关于我们</a></li> 
                                             <!--
                                             	作者：offline
                                             	时间：2018-07-06
                                             	描述：新链接
                                             -->
                <li><a href="login.html">Login</a></li>
              </ul>  
            </nav> 
          </div>
        </div>
        
        
          <div class="templatemo-content-container">
           <div class="templatemo-flex-row flex-content-row">
            <div class="templatemo-content-widget white-bg col-2">
              <div class="square"></div>
              <h2 class="templatemo-inline-block  "><font color="black">休闲类游戏</font>></h2><hr>
              
              
             <ul id="liebiao">
             <%
/* UserDao userdao = new UserDao();
ArrayList<shangpin> sps = userdao.selectBytype("休闲类"); */
ArrayList<shangpin> sps = (ArrayList<shangpin>)request.getAttribute("sp");
System.out.println("sps："+sps.size());
for (shangpin game : sps) {
//
System.out.println("photo："+game.getPhoto());
%> 


             	<li id="hang" style="background: lightgray;">
             		<div id="tupian"  onclick="window.open(href='xiangqin.jsp?name=<%=game.getName() %>' )    ">
             		
				      <img src="<%=game.getPhoto() %>" >
			        </div>  
				      <div id="wenzi"  onclick="window.open(href='xiangqin.jsp?name=<%=game.getName() %>' )    ">
					   <table border="0" cellspacing="9" cellpadding="0" >
						
							<tr> <td><font color="black"><%=game.getName() %></font></td></tr>
                            <tr> <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                            	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                            	&emsp;&emsp;<font color="black" style="background-color: green;" ><%=game.getDiscount() %></font>
                            	&emsp;&emsp;&emsp;&emsp;<font color="cornflowerblue" style="text-align:right;">$<%=game.getPrice()%></font></td></tr> 
                            <tr> <td><font color="black"><%=game.getDescribe1() %></font></td></tr>
						
					</table>
					
				</div>
			</li>
			
             	<%}%>
             	
             	
        
            
             	
             </ul> 
           
            </div>
            
       
            <div class="templatemo-content-widget bl-bg col-1 text-center">
     
              <h2 class="text-uppercase"><font size="5" color="black">正在浏览休闲类型的游戏<br />                             
</font></h2>
              <h3 class="text-uppercase margin-bottom-10">  <font size="1" color="black">浏览 Steam 上最新、最热销和打折的休闲产品
</font></h3>
	<div style="align-content:center;">
								<table style="margin: auto;" >
									<tr style="border-bottom: 10px solid white;"> 
									<td><button style="width:100px;height: 50px;" onclick="window.open(href='shangping.do?type=dongzuo' )" type="button">动作 </button> &emsp;&emsp;</td>	
                  <td><button style="width:100px;height: 50px;" onclick="window.open(href='shangping.do?type=xiuxian' )" type="button">休闲 </button> </td>									
									</tr>
									
										<tr style="border-bottom: 10px solid white;"> 
									<td><button style="width:100px;height: 50px;" onclick="window.open(href='shangping.do?type=duli' )" type="button">独立 </button> &emsp;&emsp;</td>	
                  <td><button style="width:100px;height: 50px;" onclick="window.open(href='shangping.do?type=maoxian' )" type="button">冒险 </button> </td>									
									</tr>
									
										<tr style="border-bottom: 10px solid white;"> 
									<td><button style="width:100px;height: 50px;" onclick="window.open(href='shangping.do?type=moni' )" type="button">模拟 </button> &emsp;&emsp;</td>	
                  <td><button style="width:100px;height: 50px;" onclick="window.open(href='shangping.do?type=celue>' )" type="button">策略 </button> </td>									
									</tr>
									
										<tr style="border-bottom: 10px solid white;"> 
									<td><button style="width:100px;height: 50px;" onclick="window.open(href='shangping.do?type=danren' )" type="button">单人 </button> &emsp;&emsp;</td>	
                  <td><button style="width:100px;height: 50px;" onclick="window.open(href='shangping.do?type=rpg' )" type="button">角色扮演 </button> </td>									
									</tr>
							
								</table>
									</div>
								<div>
									
									<form action="xiangqin.jsp" method="post">
								      <input type="text" name="name"  />
									    <input type="submit" value="搜索" name="btnSubmit"/>
								</form>
									
								</div>
								
								


            </div>
       
          </div>
          <div class="templatemo-flex-row flex-content-row">
            <div class="col-1">              
              <div class="templatemo-content-widget white-bg">
                <i class="fa fa-times"></i>                
                <div class="media">
                  <div class="media-left">
                  </div>
                  <div class="media-body">
                    <h2 class="media-heading text-uppercase"><font color="black">人气列表</font></h2>
                   
                   
                   <div style="background:url(images/beijing.jpg);" >
                  <div class="Cooldog_container">
    <div class="Cooldog_content">
        <ul>
            <li class="p1">
                <a href="#">
                    <img src="images/new6.jpg" alt="">
                </a>
            </li>
            <li class="p2">
                <a href="#">
                    <img src="images/new1.jpg" alt="">
                </a>
            </li>
            <li class="p3">
                <a href="#">
                    <img src="images/new2.jpg" alt="">
                </a>
            </li>
            <li class="p4">
                <a href="#">
                    <img src="images/new3.jpg" alt="">
                </a>
            </li>
            <li class="p5">
                <a href="#">
                    <img src="images/new4.jpg" alt="">
                </a>
            </li>
            <li class="p5">
                <a href="#">
                    <img src="images/new5.jpg" alt="">
                </a>
            </li>
            <li class="p5">
                <a href="#">
                    <img src="images/new1.jpg" alt="">
                </a>
            </li>
        </ul>
    </div>
    <a href="javascript:;" class="btn_left">
        <i class="iconfont icon-zuoyoujiantou"></i>
    </a>
    <a href="javascript:;" class="btn_right">
        <i class="iconfont icon-zuoyoujiantou-copy-copy"></i>
    </a>
    <a href="javascript:;" class="btn_close">
        <i class="iconfont icon-icon-test"></i>
    </a>
    <div class="buttons clearfix">
        <a href="javascript:;" class="color"></a>
        <a href="javascript:;"></a>
        <a href="javascript:;"></a>
        <a href="javascript:;"></a>
        <a href="javascript:;"></a>
        <a href="javascript:;"></a>
        <a href="javascript:;"></a>
    </div>
</div>
                   
          </div>    
                   
                   
	
                   
                   
                   
                  </div>        
                </div>                
              </div>            
              
            
             <!-- Second row ends -->
          










    <!-- JS -->
     <script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/Cooldog.js"></script>
     


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

  </body>
</html>