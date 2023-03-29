package com.etc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.service.Goodservice;



public class DeleteGroupServlet extends HttpServlet{

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
		String goodsName = req.getParameter("goodsName");
		Goodservice goodsservice = new Goodservice();
		goodsservice.deletegroup(goodsName);
		req.setAttribute("message","删除商品成功!");
	    req.getRequestDispatcher("success.jsp").forward(req, resp);
	}
	
}
