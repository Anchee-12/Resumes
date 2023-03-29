package com.cauc.chat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.JOptionPane;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
//两个主要的类：connection、statement（SQL执行器）
//PreparedStatement：能带参数，statement不能带参数
public class Derby {
	// ## DEFINE VARIABLES SECTION ##
		// define the driver to use
		//数据库驱动器
	String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	// the database name
	String dbName = "USERDB";
	// define the Derby connection URL to use
	//"jdbc:derby:"为协议，如果是两台机器，在前面加IP地址
	String connectionURL = "jdbc:derby:" + dbName + ";create=true";
	Connection conn;//跟驱动建立连接
	//以下为构造方法

	public Derby() {
		// ## LOAD DRIVER SECTION ##
		try {
			/*
			 * * Load the Derby driver.* When the embedded Driver is used this
			 * action start the Derby engine.* Catch an error and suggest a
			 * CLASSPATH problem
			 */
			Class.forName(driver);//加载数据库驱动器，返回与带有给定字符串名的类或接口相关联的类或对象
			System.out.println(driver + " loaded. ");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
			System.out.println("\n    >>> Please check your CLASSPATH variable   <<<\n");
		}
		//表的定义
		String createString = "create table USERTABLE " // 表名
				+ "(USERNAME varchar(20) primary key not null, " // 用户名
				+ "HASHEDPWD char(20) for bit data, " // 口令的HASH值
				+ "REGISTERTIME timestamp default CURRENT_TIMESTAMP)"; // 注册时间

		try {
			// Create (if needed) and connect to the database
			conn = DriverManager.getConnection(connectionURL);
			// Create a statement to issue simple commands.
			Statement s = conn.createStatement();
			// Call utility method to check if table exists.
			// Create the table if needed检查是否有usertable表
			if (!checkTable(conn)) {
				System.out.println(" . . . . creating table USERTABLE");
				s.execute(createString); //如果表不存在，则创建一张表
			}
			s.close();
			System.out.println("Database openned normally");
		} catch (SQLException e) {
			errorPrint(e);
		}
	}

	// Insert a new user into the USERTABLE table
	//实际上是用户注册的功能，执行insert语句，两个参数，用户名和口令
		//执行select语句，看用户是否存在
	public boolean insertUser(String userName, String userPwd) {
		try {
			if (!userName.isEmpty() && !userPwd.isEmpty()) {
				//如果某个值是待定的时候，用PreparedStatement，参数：select语句，ResultSet结果集，TYPE_SCROLL_INSENSITIVE和CONCUR_READ_ONLY是结果集查看方式
				PreparedStatement psTest = conn.prepareStatement(
						"select * from USERTABLE where USERNAME=?",
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				psTest.setString(1, userName);//setString给prepareStatement中的问号赋值，1代表prepareStatement中第一个问号赋值
				ResultSet rs = psTest.executeQuery();
				rs.last();
				int n = rs.getRow(); //n表示：得到ResultSet的行数，即检索到的个数
				psTest.close();
				//如果n=0，就把该用户插进去
				if (n == 0) {
					PreparedStatement psInsert = conn
							.prepareStatement("insert into USERTABLE values (?,?,?)");
					MessageDigest digest = MessageDigest.getInstance("SHA-1");//信息摘要算法
					digest.update(userPwd.getBytes());
					byte[] hashedPwd = digest.digest();//用digest方法对获取的密码加密
					//为问号赋值，为什么要使用PreparedStatement：因为他可以屏蔽普通的statment（使用字符串拼接出来的）
					//对前面insert语句中的三个问号分别赋值
					psInsert.setString(1, userName);
					psInsert.setBytes(2, hashedPwd);
					psInsert.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
					psInsert.executeUpdate();//更新数据
					psInsert.close();
					System.out.println("成功注册新用户" + userName);
					return true;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			errorPrint(e);
		}
		System.out.println("用户" + userName + "已经存在");
		return false;
	}
	//删除用户
	public boolean deleteUser(String userName, String userPwd) {
		if (checkUserPassword(userName, userPwd) == true) {
			try {
				PreparedStatement psDelete = conn
						.prepareStatement("delete from USERTABLE where USERNAME=?");
				psDelete.setString(1, userName);
				int n = psDelete.executeUpdate();
				psDelete.close();
				if (n > 0) {
					System.out.println("成功删除用户" + userName);
					return true;
				} else {
					System.out.println("删除用户" + userName + "失败");
					return false;
				}
			} catch (SQLException e) {
				errorPrint(e);
			}
		}
		return false;
	}
	//不需要密码就可以在服务器端删除用户
	public void delUser(String userName) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement psDelete = conn
					.prepareStatement("delete from USERTABLE where USERNAME=?");
			psDelete.setString(1, userName);
			int n = psDelete.executeUpdate();
			psDelete.close();
			if (n > 0) {
				JOptionPane.showMessageDialog(null, "删除用户" + userName + "成功！");
			} else {
				JOptionPane.showMessageDialog(null, "删除用户:" + userName + "失败！");
			}
		} catch (SQLException e) {
			errorPrint(e);
		}

	}

	public Statement getStatement() throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = conn.createStatement();
		return statement;
	}
	
	// check if userName with password userPwd can logon
	public boolean checkUserPassword(String userName, String userPwd) {
		try {
			if (!userName.isEmpty() && !userPwd.isEmpty()) {
				PreparedStatement psTest = conn.prepareStatement(
						"select * from USERTABLE where USERNAME=? and HASHEDPWD=?",
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				digest.update(userPwd.getBytes());
				byte[] hashedPwd = digest.digest();
				psTest.setString(1, userName);
				psTest.setBytes(2, hashedPwd);
				ResultSet rs = psTest.executeQuery();
				rs.last();
				int n = rs.getRow();
				psTest.close();
				return n > 0 ? true : false;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			errorPrint(e);
		}
		return false;
	}
	
	
	// show the information of all users in table USERTABLE, should be called
	// before the program exited
	public void showAllUsers() {
		String printLine = "  ______________当前所有注册用户______________";
		try {
			Statement s = conn.createStatement();
			// Select all records in the USERTABLE table
			ResultSet users = s
					.executeQuery("select USERNAME, HASHEDPWD, REGISTERTIME from USERTABLE order by REGISTERTIME");

			// Loop through the ResultSet and print the data
			System.out.println(printLine);
			while (users.next()) {
				System.out.println("User-Name: " + users.getString("USERNAME")
						+ " Hashed-Pasword: "
						+ new HexBinaryAdapter().marshal(users.getBytes("HASHEDPWD"))
						+ " Regiester-Time " + users.getTimestamp("REGISTERTIME"));
			}
			System.out.println(printLine);
			// Close the resultSet
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 关闭数据库
	public void shutdownDatabase() {
		/***
		 * In embedded mode, an application should shut down Derby. Shutdown
		 * throws the XJ015 exception to confirm success.
		 ***/
		if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
			boolean gotSQLExc = false;
			try {
				conn.close();
				DriverManager.getConnection("jdbc:derby:;shutdown=true");
			} catch (SQLException se) {
				if (se.getSQLState().equals("XJ015")) {
					gotSQLExc = true;
				}
			}
			if (!gotSQLExc) {
				System.out.println("Database did not shut down normally");
			} else {
				System.out.println("Database shut down normally");
			}
		}
	}

	/*** Check for USER table ****/
	public boolean checkTable(Connection conTst) throws SQLException {
		try {
			Statement s = conTst.createStatement();
			s.execute("update USERTABLE set USERNAME= 'TEST', REGISTERTIME = CURRENT_TIMESTAMP where 1=3");
		} catch (SQLException sqle) {
			String theError = (sqle).getSQLState();
			// System.out.println("  Utils GOT:  " + theError);
			/** If table exists will get - WARNING 02000: No row was found **/
			if (theError.equals("42X05")) // Table does not exist
			{
				return false;
			} else if (theError.equals("42X14") || theError.equals("42821")) {
				System.out
						.println("checkTable: Incorrect table definition. Drop table USERTABLE and rerun this program");
				throw sqle;
			} else {
				System.out.println("checkTable: Unhandled SQLException");
				throw sqle;
			}
		}
		return true;
	}

	// Exception reporting methods with special handling of SQLExceptions
	static void errorPrint(Throwable e) {
		if (e instanceof SQLException) {
			SQLExceptionPrint((SQLException) e);
		} else {
			System.out.println("A non SQL error occured.");
			e.printStackTrace();
		}
	}

	// Iterates through a stack of SQLExceptions
	static void SQLExceptionPrint(SQLException sqle) {
		while (sqle != null) {
			System.out.println("\n---SQLException Caught---\n");
			System.out.println("SQLState:   " + (sqle).getSQLState());
			System.out.println("Severity: " + (sqle).getErrorCode());
			System.out.println("Message:  " + (sqle).getMessage());
			sqle.printStackTrace();
			sqle = sqle.getNextException();
		}
	}
}
