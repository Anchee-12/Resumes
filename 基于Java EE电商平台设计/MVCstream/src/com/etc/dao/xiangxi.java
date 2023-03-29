package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.lookup.ParameterizedGenericMethodBinding;

import com.etc.util.ConnectFactory;
import com.etc.vo.shangpin;

//�������ݿ�  ��ɾ���sql���
public class xiangxi {
	public int count=0;
	public shangpin selectName(String name) {
		shangpin game  =null;
		PreparedStatement pstmt = null;
		//�õ����ݿ�����
		Connection conn=ConnectFactory.getConn();
		//����sql��ѯ���
		
		String sql = "select * from goods where name =?";
		System.out.println("��ѯ���ݿ�����");
		try {
			//����Ԥ����Statement������
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			//pstmt.setString(1, type);
			//ִ�в�ѯ
			
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next()) {
				
				//shangpin game=new shangpin();
				//��ѯ�������sp
				game=new shangpin();
				game.setName(rs.getString("name"));
				game.setShiping(rs.getString("shiping"));
				game.setPrice(rs.getInt("price"));
				game.setKaifashang(rs.getString("kaifashang"));
				game.setFaxingshang(rs.getString("faxingshang"));
				System.out.println("�����ݿ�ȡֵ��"+game.getName());
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