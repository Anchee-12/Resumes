package com.etc.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



//������ ��ͬ����
public class ConnectFactory {
	public static Connection getConn() {
		Connection conn=null;
		
				try {
					//1.����������
					Class.forName("com.mysql.jdbc.Driver");
					String url="jdbc:mysql://localhost:3306/shangpin?useUnicode=true&characterEncoding=UTF-8";
					//���ݿ�url��ַ ͷ����//���ݿ��ַ���˿ں�/���ݿ���
					String username="root";
					String password="root";
					//2.������Ӽ�����������Զ�����regiseterDriver
					//ע��
		 conn=	DriverManager.getConnection(url,username,password);
			System.out.println("�ɹ��������ݿ�");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
				return conn;//��������
		
	}
}
