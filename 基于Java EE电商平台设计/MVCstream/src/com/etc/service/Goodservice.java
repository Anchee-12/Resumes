package com.etc.service;


import com.etc.dao.Goodsdao;
import com.etc.vo.shangpin;

public class Goodservice {
	
	Goodsdao goodsDao = new Goodsdao();
	   /*
     * �ж��û����Ƿ����
     */
   public boolean isExistUser(String name)
   {
   	//����Daoʵ��
   	//ȡ������
	   shangpin game = goodsDao.selectBygoodsName(name);
   	 //�ж��û���Ϣ�Ƿ���ȷ
   	     if(game==null)
   	     {
   		  return false;
   	     }
   	     return true;
   	 
   }
   
   
   /*
    * ����
    */
   public int addgroup(shangpin game)
   {
	   return goodsDao.insert(game);
   }
   
   /*
    * ɾ��
    */
   public int deletegroup(String name) {
	   return goodsDao.deletegroup(name);
   }
   
   /*
    * �޸�
     */
   public int updategroup(shangpin good)
   {
	   return goodsDao.update(good);
   }
  
}
