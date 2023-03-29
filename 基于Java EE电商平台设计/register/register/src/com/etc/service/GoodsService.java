package com.etc.service;

import com.etc.dao.GoodsDao;
import com.etc.vo.Goods;

public class GoodsService {
	
	GoodsDao goodsDao = new GoodsDao();
	   /*
     * 判断用户名是否存在
     */
   public boolean isExistUser(String name)
   {
   	//创建Dao实例
   	//取得数据
	   Goods good = goodsDao.selectBygoodsName(name);
   	 //判断用户信息是否正确
   	     if(good==null)
   	     {
   		  return false;
   	     }
   	     return true;
   	 
   }
   
   
   /*
    * 注册
    */
   public int addgroup(Goods good)
   {
	   return goodsDao.insert(good);
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
   public int updategroup(Goods good)
   {
	   return goodsDao.update(good);
   }
   
   /*
    * 查询
    */
   public Goods selectgroup(String goodsname) {
	   return goodsDao.selectBygoodsName(goodsname);
   }
}
