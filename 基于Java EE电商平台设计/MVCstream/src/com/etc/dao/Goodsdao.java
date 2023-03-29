package com.etc.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.etc.util.ConnectFactory;
import com.etc.vo.shangpin;

public class Goodsdao {
	/*
	 * 添加新商品
	 */
	public  int insert(shangpin game) {
		PreparedStatement pstmt= null;
		int result=0;
		//得到数据库的连接
		Connection conn=ConnectFactory.getConn();
		System.out.println("进入insert函数");
		//定义sql
		String sql = "insert into goods(name,price,describe1,photo,discount,type,kaifashang,faxingshang,shiping,typec)values(?,?,?,?,?,?,?,?,?,?)";
		try {
			//生成预编译Statement语句对象
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, game.getName());
			pstmt.setInt(2, game.getPrice()); 
			pstmt.setString(3, game.getDescribe1());
			pstmt.setString(4, game.getPhoto());
			pstmt.setString(5, game.getDiscount());
			System.out.println("已经成功赋值至折扣"+game.getDiscount());
			pstmt.setString(6, game.getType());
			pstmt.setString(7, game.getKaifashang());
			pstmt.setString(8, game.getFaxingshang());
			pstmt.setString(9, game.getShiping());
			pstmt.setString(10, game.getTypec());
			//执行insert
			result=pstmt.executeUpdate();
			System.out.println("执行结果"+result);
			//取得查询结果
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
}
	/*
	 * 查询商品
	 */
	public shangpin selectBygoodsName(String name) {
		shangpin game = null;
		PreparedStatement pstmt= null;
		//得到数据库的连接
		Connection conn = ConnectFactory.getConn();
		//定义sql
		String sql = "select name,price,describe1,photo,discount,type,kaifashang,faxingshang,shiping,typec from goods where name =? ";
		try {
			//生成预编译Statement语句对象
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			//执行查询
			ResultSet rs = pstmt.executeQuery();
			//取得查询结果
			while (rs.next()) {
				game = new shangpin();//把查询结果赋给返回的User
				game.setName(rs.getString(1));
				game.setPrice(rs.getInt(2));
				game.setDescribe1(rs.getString(3));
				game.setPhoto(rs.getString(4));
				game.setDiscount(rs.getString(5));
				game.setType(rs.getString(6));
				game.setKaifashang(rs.getString(7));
				game.setFaxingshang(rs.getString(8));
				game.setTypec(rs.getString(9));
				game.setName(rs.getString(10));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(good.toString());
		return game;
	}
	
	/*
	 * 删除商品
	 */
	public int deletegroup(String name) {
		PreparedStatement pstmt= null;
		//得到数据库的连接
		int result=0;
		Connection conn = ConnectFactory.getConn();
		String sql="delete  from goods where name=? ";
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
	
	
	 
	public int update(shangpin game) {
		PreparedStatement pstmt= null;
		int result=0;
		//得到数据库的连接
		Connection conn = ConnectFactory.getConn();
		shangpin g1= selectBygoodsName(game.getName());
//		System.out.println(g1.getDescription()+" "+g1.getCategory());
//		System.out.println(g1.getGoodsName()+" "+g1.getPrice()+" "+g1.getCategory());
		//定义sql
//		String sql = "update tb_goods set production=?,description=?,category=?,price=?,photo=? where goodsName=?";
		System.out.println(g1.toString());
		String[] sql= new String[5];
		sql[0]="update goods set kaifashang=? where name=?";
		sql[1]="update goods set descript1=? where name=?";
		sql[2]="update goods set type=? where name=?";
		sql[3]="update goods set price=? where name=?";
		sql[4]="update goods set photo=? where name=?";
		System.out.println(g1.toString());
		try {
			//生成预编译Statement语句对象
			pstmt = conn.prepareStatement(sql[0]);
			pstmt.setString(2, game.getName());
			if(!game.getKaifashang().equals("")) {
				pstmt.setString(1, game.getKaifashang());
			}
			else {
				System.out.println(g1.getKaifashang());
				pstmt.setString(1, g1.getKaifashang());
			}
			result=pstmt.executeUpdate();
			//pstmt.close();
			
			pstmt = conn.prepareStatement(sql[1]);
			pstmt.setString(2, game.getName());
			if(!game.getDescribe1().equals("")) {
				System.out.println("1");
				pstmt.setString(1, game.getDescribe1());
			}
			else {
				pstmt.setString(1, g1.getDescribe1());
			}
			result=pstmt.executeUpdate();
			//pstmt.close();
			
			pstmt = conn.prepareStatement(sql[2]);
			pstmt.setString(2, game.getName());
			if(!game.getType().equals("")) {
				System.out.println("1");
				pstmt.setString(1, game.getType());
			}
			else {
				pstmt.setString(1, g1.getType());
			}
			result=pstmt.executeUpdate();
			//pstmt.close();
			
			pstmt = conn.prepareStatement(sql[3]);
			pstmt.setString(2, game.getName());
			if(!String.valueOf(game.getPrice()).equals("")) {
				System.out.println("1");
				pstmt.setInt(1, game.getPrice());
			}
			else {
				System.out.println("2");
				pstmt.setInt(1, g1.getPrice());
			}
			result=pstmt.executeUpdate();
			//pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(pstmt.toString());
		System.out.println(result);
		return result;
}
	
}
