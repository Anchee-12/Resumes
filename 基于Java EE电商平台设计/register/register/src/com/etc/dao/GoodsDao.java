package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.etc.util.ConnectionFactory;
import com.etc.vo.Goods;
import com.etc.vo.User;

public class GoodsDao {
	/*
	 * 添加新商品
	 */
	public  int insert(Goods good) {
		PreparedStatement pstmt= null;
		int result=0;
		//得到数据库的连接
		Connection conn = ConnectionFactory.getConn();
		//定义sql
		String sql = "insert into goods(name,kaifashang,describe1,type,price,photo,shiping,discount,faxingshang)values(?,?,?,?,?,?,?,?,?)";
		try {
			//生成预编译Statement语句对象
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, good.getName());
			pstmt.setString(2, good.getKaifashang());
			pstmt.setString(3, good.getDescribe1());
			pstmt.setString(4, good.getType());
			pstmt.setString(5, good.getPrice());
			pstmt.setString(6, good.getPhoto());
			pstmt.setString(7, good.getShiping());
			pstmt.setString(8, good.getDiscount());
			pstmt.setString(9, good.getFaxingshang());
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
	 * 查询商品
	 */
	public Goods selectBygoodsName(String goodsname) {
		Goods good = null;
		PreparedStatement pstmt= null;
		//得到数据库的连接
		Connection conn = ConnectionFactory.getConn();
		//定义sql
		String sql = "select kaifashang,describe1,type,price,photo,shiping,discount,faxingshang from goods where name =? ";
		try {
			//生成预编译Statement语句对象
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goodsname);
			
			//执行查询
			ResultSet rs = pstmt.executeQuery();
			//取得查询结果
			while (rs.next()) {
				good = new Goods();//把查询结果赋给返回的User
				good.setName(goodsname);
				good.setKaifashang(rs.getString(1));
				good.setDescribe1(rs.getString(2));
				good.setType(rs.getString(3));
				good.setPrice(rs.getString(4));
				good.setPhoto(rs.getString(5));
				good.setDiscount(rs.getString(7));
				good.setFaxingshang(rs.getString(8));
				good.setShiping(rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(good.toString());
		return good;
	}
	
	/*
	 * 删除商品
	 */
	public int deletegroup(String name) {
		PreparedStatement pstmt= null;
		//得到数据库的连接
		int result=0;
		Connection conn = ConnectionFactory.getConn();
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
	
	/*
	 * 修改商品
	 */
	public int update(Goods good) {
		PreparedStatement pstmt= null;
		int result=0;
		//得到数据库的连接
		Connection conn = ConnectionFactory.getConn();
		Goods g1= selectBygoodsName(good.getName());
//		System.out.println(g1.getDescription()+" "+g1.getCategory());
//		System.out.println(g1.getGoodsName()+" "+g1.getPrice()+" "+g1.getCategory());
		//定义sql
//		String sql = "update tb_goods set production=?,description=?,category=?,price=?,photo=? where goodsName=?";
		//System.out.println(g1.toString());
		String[] sql= new String[6];
		sql[0]="update goods set kaifashang=? where name=?";
		sql[1]="update goods set describe1=? where name=?";
		sql[2]="update goods set type=? where name=?";
		sql[3]="update goods set price=? where name=?";
		sql[4]="update goods set discount=? where name=?";
		sql[5]="update goods set faxingshang=? where name=?";
		System.out.println(g1.toString());
		try {
			//生成预编译Statement语句对象
			pstmt = conn.prepareStatement(sql[0]);
			pstmt.setString(2, good.getName());
			if(!good.getKaifashang().equals("")) {
				pstmt.setString(1, good.getKaifashang());
			}
			else {
				pstmt.setString(1, g1.getKaifashang());
			}
			result=pstmt.executeUpdate();
			//pstmt.close();
			
			pstmt = conn.prepareStatement(sql[1]);
			pstmt.setString(2, good.getName());
			if(!good.getDescribe1().equals("")) {
				pstmt.setString(1, good.getDescribe1());
			}
			else {
				pstmt.setString(1, g1.getDescribe1());
			}
			result=pstmt.executeUpdate();
			//pstmt.close();
			
			pstmt = conn.prepareStatement(sql[2]);
			pstmt.setString(2, good.getName());
			if(!good.getType().equals("")) {
				pstmt.setString(1, good.getType());
			}
			else {
				pstmt.setString(1, g1.getType());
			}
			result=pstmt.executeUpdate();
			//pstmt.close();
			
			pstmt = conn.prepareStatement(sql[3]);
			pstmt.setString(2, good.getName());
			if(!good.getPrice().equals("")) {
				pstmt.setString(1, good.getPrice());
			}
			else {				
				pstmt.setString(1, g1.getPrice());
			}
			result=pstmt.executeUpdate();
			//pstmt.close();
			
			
			pstmt = conn.prepareStatement(sql[4]);
			pstmt.setString(2, good.getName());
			if(!good.getDiscount().equals("")) {
				pstmt.setString(1, good.getDiscount());
			}
			else {
				pstmt.setString(1, g1.getDiscount());
			}
			result=pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql[5]);
			pstmt.setString(2, good.getName());
			if(!good.getFaxingshang().equals("")) {
				pstmt.setString(1, good.getFaxingshang());
			}
			else {
				pstmt.setString(1, g1.getFaxingshang());
			}
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(pstmt.toString());
		System.out.println(result);
		return result;
}
	
}
