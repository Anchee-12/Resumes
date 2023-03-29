package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.lookup.ParameterizedGenericMethodBinding;

import com.etc.util.ConnectFactory;
import com.etc.vo.shangpin;

//处理数据库  增删查改sql语句
public class xiangxi {
	public int count=0;
	public shangpin selectName(String name) {
		shangpin game  =null;
		PreparedStatement pstmt = null;
		//得到数据库连接
		Connection conn=ConnectFactory.getConn();
		//定义sql查询语句
		
		String sql = "select * from goods where name =?";
		System.out.println("查询数据库数据");
		try {
			//生成预编译Statement语句对象
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			//pstmt.setString(1, type);
			//执行查询
			
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next()) {
				
				//shangpin game=new shangpin();
				//查询结果赋给sp
				game=new shangpin();
				game.setName(rs.getString("name"));
				game.setShiping(rs.getString("shiping"));
				game.setPrice(rs.getInt("price"));
				game.setKaifashang(rs.getString("kaifashang"));
				game.setFaxingshang(rs.getString("faxingshang"));
				System.out.println("从数据库取值："+game.getName());
				/*System.out.println(rs.getString(1));
				System.out.println(rs.getInt(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));*/		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return game;

	}
	
}