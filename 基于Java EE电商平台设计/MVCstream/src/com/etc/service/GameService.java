package com.etc.service;

import java.util.ArrayList;

import com.etc.dao.GameDao;
import com.etc.vo.shangpin;


//�����߼�����
public class GameService {
	public void displaysp(String type)
	{
		//����DAOʵ��
		GameDao userDao=new GameDao();
		ArrayList<shangpin> a=(ArrayList<shangpin>) userDao.selectBytype(type).clone();
		//ArrayList<shangpin> b =(ArrayList<shangpin>) a.clone();//
	   //shangpins=userDao.selectBytype("������");
	    System.out.println("2");
	   //for (int i = 0; i < 1; i++) {
			System.out.println(a.get(0).getName());
			System.out.println(a.get(0).getPrice());
			System.out.println(a.get(0).getPhoto());
			System.out.println(a.get(0).getDiscount());
			System.out.println(a.get(0).getDescribe1());
			System.out.println("�ɹ���ȡ����");

		//}
	   System.out.println("2");
	    
	    
		}	
}

	

