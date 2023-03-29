package com.etc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.etc.service.UserService;

public class DeleteUserServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String message="";
		String userName = req.getParameter("userName");
		UserService userService = new UserService();
		userService.deleteUser(userName);
		req.setAttribute("message","删除用户成功!");
	    req.getRequestDispatcher("success.jsp").forward(req, resp);
	}
	
}
