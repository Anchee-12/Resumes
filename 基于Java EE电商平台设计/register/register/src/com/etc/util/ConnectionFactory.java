package com.etc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	/*
	 * 商品连接
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			//1.加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/test123?useUnicode=true&characterEncoding=UTF-8";
			String username ="root";
			String password=null;
			//2.获得连接
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
