package com.etc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.dao.GameDao;
import com.etc.service.GameService;
import com.etc.vo.shangpin;
import com.sun.glass.ui.CommonDialogs.Type;

//接收数据 调用service  跳转jsp
public class Gameservlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		System.out.println(req.getParameter("type"));
		//转换中英文类型
		//创建userdao的实例
		GameDao gameDao=new GameDao();
		//利用数组保存从数据库挑选出符合条件类型的games
		ArrayList<shangpin> shangpins =gameDao.selectBytype(req.getParameter("type"));
		//setAttribute()方法：增加一个指定名称和值的新属性，或者把一个现有的属性设定为指定的值
		req.setAttribute("sp", shangpins);
		//req.getRequestDispatcher("shangpin.jsp").forward(req, resp);
		System.out.println("成功跳转");
	//	req.getRequestDispatcher("test.jsp").forward(req, resp);
		req.getRequestDispatcher("shangpin.jsp").forward(req, resp);
	}

}
