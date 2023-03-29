package com.etc.servlet;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.service.GoodsService;
import com.etc.vo.Goods;


@MultipartConfig
public class UpdateGroupServlet extends HttpServlet{

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
		String production = req.getParameter("production");
		String description = req.getParameter("description");
		String category = req.getParameter("category");
		String price = req.getParameter("price");
		String discount=req.getParameter("discount");
		String publish=req.getParameter("publish");
		Goods good = new Goods();
		good.setName(goodsName);
		good.setKaifashang(production);
		good.setDescribe1(description);
		good.setType(category);
		good.setPrice(price);
		good.setDiscount(discount);
		good.setFaxingshang(publish);
		//System.out.println(good.toString());
		GoodsService goodsService = new GoodsService();
	 	goodsService.updategroup(good);
	 	//req.setAttribute("goodInfo",good);
	 	req.setAttribute("message","修改商品成功!");
	 	req.getRequestDispatcher("success.jsp").forward(req, resp);
	}
	
}
