package com.etc.service;


import com.etc.dao.Goodsdao;
import com.etc.vo.shangpin;

public class Goodservice {
	
	Goodsdao goodsDao = new Goodsdao();
	   /*
     * 判断用户名是否存在
     */
   public boolean isExistUser(String name)
   {
   	//创建Dao实例
   	//取得数据
	   shangpin game = goodsDao.selectBygoodsName(name);
   	 //判断用户信息是否正确
   	     if(game==null)
   	     {
   		  return false;
   	     }
   	     return true;
   	 
   }
   
   
   /*
    * 增加
    */
   public int addgroup(shangpin game)
   {
	   return goodsDao.insert(game);
   }
   
   /*
    * 删除
    */
   public int deletegroup(String name) {
	   return goodsDao.deletegroup(name);
   }
   
   /*
    * 修改
     */
   public int updategroup(shangpin good)
   {
	   return goodsDao.update(good);
   }
  
}
