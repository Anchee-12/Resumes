package com.etc.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



//工具类 共同调用
public class ConnectFactory {
	public static Connection getConn() {
		Connection conn=null;
		
				try {
					//1.加载驱动类
					Class.forName("com.mysql.jdbc.Driver");
					String url="jdbc:mysql://localhost:3306/shangpin?useUnicode=true&characterEncoding=UTF-8";
					//数据库url地址 头部：//数据库地址：端口号/数据库名
					String username="root";
					String password="root";
					//2.获得连接加载驱动类后自动调用regiseterDriver
					//注册
		 conn=	DriverManager.getConnection(url,username,password);
			System.out.println("成功连接数据库");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
				return conn;//返回连接
		
	}
}
