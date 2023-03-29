package com.etc.service;

import com.etc.dao.UserDao;
import com.etc.vo.Goods;
import com.etc.vo.User;

public class UserService {
	//创建DAO实例
	UserDao userDao = new UserDao();
	/*
	 * 判断登录用户是否存在
	 */
	public boolean isValidUser(String name, String password) {
		//创建DAO实例
		UserDao userDao = new UserDao();
		//取得数据
		User user = userDao.selectByNameAndPwd(name, password);
		//判断用户信息是否正确
		if(user==null) {
			return false;
		}
		return true;
	}
	
	 /*
     * 判断用户名是否存在
     */
   public boolean isExistUser(String name)
   {
   	//创建Dao实例
   	//取得数据
   	User user = userDao.selectByName(name);
   	 //判断用户信息是否正确
   	     if(user==null)
   	     {
   		  return false;
   	     }
   	     return true;
   	 
   }
	/*
	 * 判断注册
	 */
	public int register(User user) {
		//insert数据
		return userDao.insert(user);
	}
	
	/*
	 * 修改用户信息
	 */
	 public int updateuser(User user)
 {
		   return userDao.update(user);
	   }
	 
	 /*
	  * 删除用户
	  */
	 public int deleteUser(String name) {
		 if(userDao.selectByName(name)!=null) {
		   return userDao.deleteuser(name);
	   }
		 return 0;
	 }
}
