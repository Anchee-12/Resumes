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

//�������� ����service  ��תjsp
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
		//ת����Ӣ������
		//����userdao��ʵ��
		GameDao gameDao=new GameDao();
		//�������鱣������ݿ���ѡ�������������͵�games
		ArrayList<shangpin> shangpins =gameDao.selectBytype(req.getParameter("type"));
		//setAttribute()����������һ��ָ�����ƺ�ֵ�������ԣ����߰�һ�����е������趨Ϊָ����ֵ
		req.setAttribute("sp", shangpins);
		//req.getRequestDispatcher("shangpin.jsp").forward(req, resp);
		System.out.println("�ɹ���ת");
	//	req.getRequestDispatcher("test.jsp").forward(req, resp);
		req.getRequestDispatcher("shangpin.jsp").forward(req, resp);
	}

}
