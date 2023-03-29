package com.etc.service;

import java.util.ArrayList;

import com.etc.dao.GameDao;
import com.etc.vo.shangpin;


//处理逻辑问题
public class GameService {
	public void displaysp(String type)
	{
		//创建DAO实例
		GameDao userDao=new GameDao();
		ArrayList<shangpin> a=(ArrayList<shangpin>) userDao.selectBytype(type).clone();
		//ArrayList<shangpin> b =(ArrayList<shangpin>) a.clone();//
	   //shangpins=userDao.selectBytype("休闲类");
	    System.out.println("2");
	   //for (int i = 0; i < 1; i++) {
			System.out.println(a.get(0).getName());
			System.out.println(a.get(0).getPrice());
			System.out.println(a.get(0).getPhoto());
			System.out.println(a.get(0).getDiscount());
			System.out.println(a.get(0).getDescribe1());
			System.out.println("成功获取数据");

		//}
	   System.out.println("2");
	    
	    
		}	
}

	

