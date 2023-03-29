package com.etc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSpinnerUI;

import com.etc.service.UserService;
import com.etc.vo.User;

public class LoginServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		//创建Servive实例
		
		UserService userService = new UserService();
		//获得JSP页面的时间信息
		String timelength=req.getParameter("timelength");
		System.out.println(timelength);
		String username=req.getParameter("userName");
		String pwd=req.getParameter("password");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        System.out.println(userName + " " +password);
		if(userService.isValidUser(userName,password ))
		{
//			int days=0;
//			if(timelength!=null){//避免空指针异常，当直接跳时，表单下拉列表为空
//				days=Integer.parseInt(timelength);
//			}
//			if(days!=0){//避免空指针异常，否则每次进入此处均设置cookie，有时会带空值或默认是0
//			       //字符串类型变量 .用户名和密码分别用两个cookie保存，不可以存User对象
//			Cookie usernamecookie=new Cookie("username",username);
//			Cookie passwordcookie=new Cookie("password",pwd); 
//			//分别给每一个Cookie对象单独设置有效期
//			usernamecookie.setMaxAge(days*24*3600); //注意：若0*24*3600删除了cookie
//			passwordcookie.setMaxAge(days*24*3600);
//			resp.addCookie(usernamecookie);
//			resp.addCookie(passwordcookie);
//			}
//			HttpSession session = req.getSession();
//			session.setAttribute("userInfo", req.getParameter(username));
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
		else
		{
			resp.sendRedirect("login.jsp");
		}
	}
      
}
