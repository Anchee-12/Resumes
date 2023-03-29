package com.etc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.service.GoodsService;
import com.etc.vo.Goods;

public class SelectGroupServlet extends HttpServlet{

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
		GoodsService goodsService = new GoodsService();
		Goods good= goodsService.selectgroup(goodsName);
		req.setAttribute("message","查询商品成功!");
		req.setAttribute("goodInfo", good);
	    req.getRequestDispatcher("selectsuccess.jsp").forward(req, resp);
	}
	
}
