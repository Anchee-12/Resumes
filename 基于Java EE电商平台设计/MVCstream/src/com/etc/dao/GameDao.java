package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.etc.util.ConnectFactory;
import com.etc.vo.shangpin;

//�������ݿ�  ��ɾ���sql���
public class GameDao {

	
	public int count=0;
	public ArrayList<shangpin> selectBytype(String type) {
		ArrayList<shangpin> shangpins =new ArrayList<shangpin>();

		PreparedStatement pstmt = null;
		//�õ����ݿ�����
		Connection conn=ConnectFactory.getConn();
		//����sql��ѯ���
		
		String sql = "select * from goods where type = '"+type+"'";
		System.out.println("��ѯ���ݿ�����");
		try {
			//����Ԥ����Statement������
			pstmt=conn.prepareStatement(sql);
			
			//pstmt.setString(1, type);
			//ִ�в�ѯ
			ResultSet rs =pstmt.executeQuery();
			while(rs.next()) {
				
				shangpin sp=new shangpin();
				//��ѯ�������sp
				sp.setName(rs.getString("name"));
				sp.setPrice(rs.getInt("price"));
				sp.setDescribe1(rs.getString("describe1"));
				sp.setPhoto(rs.getString("photo"));
				sp.setDiscount(rs.getString("discount"));
				shangpins.add(sp);
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
return shangpins;
	}
	
}
