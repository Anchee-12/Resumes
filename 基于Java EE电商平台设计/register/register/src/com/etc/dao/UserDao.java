package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.etc.util.ConnectionFactory;
import com.etc.vo.Goods;
import com.etc.vo.User;

public class UserDao {
/*
 * 登录
 */
	public User selectByNameAndPwd(String name, String password) {
		User user = null;
		PreparedStatement pstmt= null;
		//得到数据库的连接
		Connection conn = ConnectionFactory.getConn();
		//定义sql
		String sql = "select name, password, gender, age, email from tb_user where name =? and password=?";
		try {
			//生成预编译Statement语句对象
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			//执行查询
			ResultSet rs = pstmt.executeQuery();
			//取得查询结果
			while (rs.next()) {
				user = new User();//把查询结果赋给返回的User
				user.setUserName(rs.getString(1));
				user.setAge(rs.getString(4));
				user.setGender(rs.getString(3));
				user.setPassword(rs.getString(2));
				user.setEmail(rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	 public User selectByName(String name) 
     {
   	  User user = null;
   	  PreparedStatement pstmt = null;
   	  //得到数据库的连接
   	  Connection conn = ConnectionFactory.getConn();
   	  //定义sql
   	  String sql = "select name,password,gender,age,email,photo,id from tb_user where name = ?";
   	  try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
           ResultSet rs = pstmt.executeQuery();
           while(rs.next())
           {
           	user = new User();
           	user.setUserName(rs.getString(1));
           	user.setAge(rs.getString(4));
           	user.setGender(rs.getString(3));
           	user.setPassword(rs.getString(2));
           	user.setEmail(rs.getString(5));
           	user.setPhoto(rs.getString(6));
           	user.setId(rs.getInt(7));
           }
           
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   	
        return user;
     }
	 
	/*
	 * 注册
	 */
	public int insert(User user) {
		PreparedStatement pstmt= null;
		int result=0;
		//得到数据库的连接
		Connection conn = ConnectionFactory.getConn();
		//定义sql
		String sql = "insert into tb_user(name,password,photo)values(?,?,?)";
		try {
			//生成预编译Statement语句对象
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getPhoto());
			//执行insert
			result=pstmt.executeUpdate();
			//取得查询结果
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
	}
	
	/*
	 * 修改信息
	 */
	public  int update(User user) {
		PreparedStatement pstmt= null;
		int result=0;
		//得到数据库的连接
		Connection conn = ConnectionFactory.getConn();
		User u=selectByName(user.getUserName());
		String[] sql= new String[6];
		sql[0]="update tb_user set name=? where name=?";
		sql[1]="update tb_user set gender=? where name=?";
		sql[2]="update tb_user set age=? where name=?";
		sql[3]="update tb_user set email=? where name=?";
		sql[4]="update tb_user set photo=? where name=?";
		sql[5]="update tb_user set region=? where name=?";
		System.out.println(u.toString());
		System.out.println(user.toString());
		try {
			//生成预编译Statement语句对象
			pstmt = conn.prepareStatement(sql[0]);
			pstmt.setString(2, user.getUserName());
			if(!user.getUserName().equals("")) {
				pstmt.setString(1, user.getUserName());
			}
			else {
				pstmt.setString(1, u.getUserName());
			}
			result=pstmt.executeUpdate();

			pstmt = conn.prepareStatement(sql[1]);
			pstmt.setString(2, user.getUserName());
			if(!user.getGender().equals("")) {
				pstmt.setString(1, user.getGender());
			}
			else {
				pstmt.setString(1, u.getGender());
			}
			result=pstmt.executeUpdate();
            
			pstmt = conn.prepareStatement(sql[2]);
			pstmt.setString(2, user.getUserName());
			if(!user.getAge().equals("")) {
				pstmt.setString(1, user.getAge());
			}
			else {
				pstmt.setString(1, u.getAge());
			}
			result=pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql[3]);
			pstmt.setString(2, user.getUserName());
			if(!user.getEmail().equals("")) {
				pstmt.setString(1, user.getEmail());
			}
			else {
				pstmt.setString(1, u.getEmail());
			}
			result=pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql[4]);
			pstmt.setString(2, user.getUserName());
			if(!user.getPhoto().equals("")) {
				pstmt.setString(1, user.getPhoto());
			}
			else {
				pstmt.setString(1, u.getPhoto());
			}
			result=pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql[5]);
			pstmt.setString(2, user.getUserName());
			if(!user.getRegion().equals("")) {
				pstmt.setString(1, user.getRegion());
			}
			else {
				pstmt.setString(1, u.getRegion());
			}
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
}
	
	/*
	 * 删除用户
	 */
	public int deleteuser(String name) {
		PreparedStatement pstmt= null;
		//得到数据库的连接
		int result=0;
		Connection conn = ConnectionFactory.getConn();
		String sql="delete  from tb_user where name=? ";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
