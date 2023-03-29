<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
	     <meta name="description" content="">
        <meta name="author" content="templatemo">
        
	    <link href='http://fonts.useso.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
	    <link href="css/font-awesome.min.css" rel="stylesheet">
	    <link href="css/bootstrap.min.css" rel="stylesheet">
	    <link href="css/templatemo-style.css" rel="stylesheet">
		<title>Stream | Register</title>
		
	    <script type="text/javascript" src="js/jquery-1.11.3.js" ></script>
	    <script type="text/javascript"></script>
	    <script>
	    	function checkUser()
	    	{
	    		$.ajax({
	    			type:"post",
	    			url:"register.do?action=checkUser ",
	    			data:{"userName":$("#userName").val()}, 
	    			success:function(data) 
	    			{
	    				$("#message").text(data);
	    			}
	    			});
	
	    	}	    	
       </script>
	</head>
	<body class="light-gray-bg">
		<div class="templatemo-content-widget1 templatemo-login-widget white-bg">
			<header class="text-center">
	          <div class="square"></div>
	          <h1>Stream</h1>
	        </header>
	        <form action="register.do?action=register" class="templatemo-login-form" enctype="multipart/form-data" method="post">
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-user fa-fw"></i>用户名</div>	    
		              	<input type="text" class="form-control" placeholder="字母+数字" name="userName" id="userName" onblur="checkUser()">
		          	</div>	
		           <span id="message">请输入用户名</span>    				              	           
		          	
	        	</div>
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-user fa-fw"></i>邮箱</div>	        		
		              	<input type="text" class="form-control" placeholder="js@dashboard.com" name="email">           
		          	</div>	
	        	</div>
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-key fa-fw"></i>密码</div>	        		
		              	<input type="password" class="form-control" placeholder="******" name="password">           
		          	</div>	
	        	</div>

	          	<div class="form-group">
				    <div class="checkbox squaredTwo">
				    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            &nbsp;&nbsp;	
				        <input type="checkbox" id="c1" name="cc" />
						<label for="c1"><span></span>确认条款</label>
				    </div>				    
				</div>
				<div class="form-group">
					<button type="submit" class="templatemo-blue-button width-100" onclick="confirmed()"><a href="index1.jsp">提交</button>
				</div>
	        </form>
		</div>
    <script>
    	function confirmed()
    	{
    		alert("注册成功！")
    	}
    </script>
	</body>
</html>
