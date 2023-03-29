package com.cauc.chat;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import com.cauc.chat.Server.UserHandler;
import java.awt.BorderLayout;


public class ManagerUser extends JFrame {

	private JPanel contentPane;
	private JTable table;
	final DefaultTableModel AllUsersDtm = new DefaultTableModel();
	//private final JTable tableAllUsers;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerUser frame = new ManagerUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManagerUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);	
		
		JPanel center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new GridLayout(1, 0, 0, 0));
	
		
		JScrollPane scrollPane = new JScrollPane();
		center.add(scrollPane);

		
								
		AllUsersDtm.addColumn("用户名");
		AllUsersDtm.addColumn("哈希口令值");
		AllUsersDtm.addColumn("登录时间");
		
		table = new JTable(AllUsersDtm);
		table.setVisible(true);
		scrollPane.setViewportView(table);
		
		JPanel south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout((LayoutManager) new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JButton btnDeleteUser = new JButton("\u5220\u9664");
		btnDeleteUser.addActionListener(new ActionListener() {     //表中删除用户
			public void actionPerformed(ActionEvent e) {
				 Derby userDatabase = new Derby();
				if (rootPaneCheckingEnabled) {
					int row = table.getSelectedRow();
					String userName = (String) AllUsersDtm.getValueAt(row, 0);
					AllUsersDtm.removeRow(row);
					userDatabase.delUser(userName);
				}
				
		
			}
		});
		south.add(btnDeleteUser);
		
		JButton btnAllUsers = new JButton("\u6240\u6709");
        btnAllUsers.addActionListener(new ActionListener() {  //显示所有用户			
			public void actionPerformed(ActionEvent e) {
				 Derby userDatebase = new Derby();
				
					try {						
						Statement s = userDatebase.getStatement();
						ResultSet users = s
								.executeQuery("select USERNAME, HASHEDPWD, REGISTERTIME from USERTABLE order by REGISTERTIME");
						// Loop through the ResultSet and print the data
						while (users.next()) {// 如果有下一行
							AllUsersDtm.addRow(new Object[]{
									 users.getString("USERNAME")
									, new HexBinaryAdapter().marshal(users
											.getBytes("HASHEDPWD")) ,
									 users.getTimestamp("REGISTERTIME")});


            }
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		});
		south.add(btnAllUsers);
		
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.addActionListener(new ActionListener() {			
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
		});
		south.add(btnCancel);
	}

}
