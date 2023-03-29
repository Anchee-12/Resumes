package com.cauc.chat;
import java.io.BufferedInputStream;
import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

//import org.apache.derby.vti.IFastPath;
public class Client extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int port = 9999;
	public  final int fport =8888;
	
	private SSLSocket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	private String localUserName;
	private String passwd;
	//private String dstUserName;
	
	// “在线用户列表ListModel”,用于维护“在线用户列表”中显示的内容
	private final DefaultListModel<String> onlinUserDlm = new DefaultListModel<String>();
	// 用于控制时间信息显示格式
	// private final SimpleDateFormat dateFormat = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private final JPanel contentPane;
	private final JTextField textFieldUserName;
	private final JPasswordField passwordFieldPwd;
	private final JTextField textFieldMsgToSend;
	private final JTextPane textPaneMsgRecord;
	private final JList<String> listOnlineUsers;
	private final JButton btnLogon;
	private final JButton btnRegister;
	private final JButton btnSendPublicMsg;
	private final JButton btnSendPrivateMsg;
	private final JButton btnSendFile;
	private String filePath;
	private long fileSize;
	private String fileName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws KeyStoreException 
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public Client() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException{
		// 与服务器端建立Socket连接，如果抛出异常，则弹出对话框通知用户，并退出
		try {
			//String keyStoreFile = "test.keys";
			String passphrase = "123456";
			char[] password = passphrase.toCharArray();//String.ToCharArray: 将字符串拆分为字符到数组
			String trustStoreFile = "test.keys";    
			KeyStore ts = KeyStore.getInstance("JKS");
			ts.load(new FileInputStream(trustStoreFile), password);//加载文件
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			tmf.init(ts);
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, tmf.getTrustManagers(), null);//不要给别人证书
			SSLSocketFactory factory=sslContext.getSocketFactory();//有了sslContext，就可以。。。
			socket=(SSLSocket)factory.createSocket("localhost",port);//向服务器建立安全的服务连接
			String[] supported=socket.getSupportedCipherSuites();
			socket.setEnabledCipherSuites(supported); 
			System.out.println(socket.getUseClientMode()? "客户模式":"服务器模式");
			//socket = new Socket("localhost", port);
			// 将socket的输入流和输出流分别封装成对象输入流和对象输出流
			oos = new ObjectOutputStream(socket
					.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(null, "找不到服务器主机");
			e1.printStackTrace();
			System.exit(0);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,
					"服务器I/O错误，服务器未启动？");
			e1.printStackTrace();
			System.exit(0);
		}
		new Thread(new ListeningHandler()).start();
		
		setTitle("\u5BA2\u6237\u7AEF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new EmptyBorder(0, 0, 5, 0));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.X_AXIS));

		JLabel lblUserName = new JLabel("\u7528\u6237\u540D\uFF1A");
		panelNorth.add(lblUserName);

		textFieldUserName = new JTextField();
		panelNorth.add(textFieldUserName);
		textFieldUserName.setColumns(10);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelNorth.add(horizontalStrut);

		JLabel lblPwd = new JLabel("\u53E3\u4EE4\uFF1A");
		panelNorth.add(lblPwd);

		passwordFieldPwd = new JPasswordField();
		passwordFieldPwd.setColumns(10);
		panelNorth.add(passwordFieldPwd);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panelNorth.add(horizontalStrut_1);

		btnLogon = new JButton("\u767B\u5F55"); // “登录”按钮
		btnLogon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnLogon.getText().equals("登录")) {
					localUserName = textFieldUserName.getText().trim();
					passwd=passwordFieldPwd.getText();
					if (localUserName.length() > 0) {
						
						
						// 向服务器发送用户上线信息，将自己的用户名和密码发送给服务器
						LoginMessage loginMessage = new LoginMessage(
								localUserName,"",passwd);
						try {
							oos.writeObject(loginMessage);
							oos.flush();
						} catch (IOException e1) {
							e1.printStackTrace();
						}						
					}
				} else if (btnLogon.getText().equals("退出")) {
					if (JOptionPane.showConfirmDialog(null, "是否退出?", "退出确认",
							JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
						// 向服务器发送用户下线消息
						UserStateMessage userStateMessage = new UserStateMessage(
								localUserName, "", false);
						try {
							synchronized (oos) {
								oos.writeObject(userStateMessage);
								oos.flush();
							}
							System.exit(0);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}

			}
		});
		panelNorth.add(btnLogon);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		panelNorth.add(horizontalStrut_5);
		
		btnRegister = new JButton("\u6CE8\u518C");//注册按钮
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				Register register=new Register();
				register.setVisible(true);
				String msgRecord = dateFormat.format(new Date())
						+ " 正在向服务器发送注册请求\r\n";
				addMsgRecord(msgRecord, Color.red, 12, false, false);
				//注册信息文字定义为红色字体
				
				}				
        });
		panelNorth.add(btnRegister);
	

		JSplitPane splitPaneCenter = new JSplitPane();
		splitPaneCenter.setResizeWeight(1.0);
		contentPane.add(splitPaneCenter, BorderLayout.CENTER);

		JScrollPane scrollPaneMsgRecord = new JScrollPane();
		scrollPaneMsgRecord.setViewportBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "\u6D88\u606F\u8BB0\u5F55",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPaneCenter.setLeftComponent(scrollPaneMsgRecord);

		textPaneMsgRecord = new JTextPane();
		scrollPaneMsgRecord.setViewportView(textPaneMsgRecord);

		JScrollPane scrollPaneOnlineUsers = new JScrollPane();
		scrollPaneOnlineUsers.setViewportBorder(new TitledBorder(null,
				"\u5728\u7EBF\u7528\u6237", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		splitPaneCenter.setRightComponent(scrollPaneOnlineUsers);

		listOnlineUsers = new JList<String>(onlinUserDlm);
		scrollPaneOnlineUsers.setViewportView(listOnlineUsers);

		JPanel panelSouth = new JPanel();
		panelSouth.setBorder(new EmptyBorder(5, 0, 0, 0));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.X_AXIS));

		textFieldMsgToSend = new JTextField();
		panelSouth.add(textFieldMsgToSend);
		textFieldMsgToSend.setColumns(10);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panelSouth.add(horizontalStrut_2);

		btnSendPublicMsg = new JButton("\u53D1\u516C\u804A\u6D88\u606F"); // “发公聊消息”按钮
		btnSendPublicMsg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msgContent = textFieldMsgToSend.getText();
				if (msgContent.length() > 0) {
					// 将消息文本框中的内容作为公聊消息发送给服务器
					ChatMessage chatMessage = new ChatMessage(localUserName,
							"", msgContent);
					try {
						synchronized (oos) {
							oos.writeObject(chatMessage);
							oos.flush();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					// 在“消息记录”文本框中用蓝色显示发送的消息及发送时间
					String msgRecord = dateFormat.format(new Date()) + "我向大家说:"
							+ msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.blue, 12, false, false);
				}
			}
		});
		panelSouth.add(btnSendPublicMsg);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panelSouth.add(horizontalStrut_3);
		
		btnSendPrivateMsg = new JButton("\u53D1\u79C1\u804A\u6D88\u606F");// “发私聊消息”按钮
		btnSendPrivateMsg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msgContent = textFieldMsgToSend.getText();
				//dstUserName=listOnlineUsers.getSelectedValue();
				if (msgContent.length() > 0) {
					// 将消息文本框中的内容作为私聊消息发送给服务器
					ChatMessage chatMessage = new ChatMessage(localUserName,
					   listOnlineUsers.getSelectedValue(), msgContent);//dstUserName
					try {
						synchronized (oos) {	//synchronized关键字：方法加锁
							oos.writeObject(chatMessage);
							oos.flush();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					// 在“消息记录”文本框中用蓝色显示发送的消息及发送时间
					String msgRecord = dateFormat.format(new Date()) + "我向"
					+listOnlineUsers.getSelectedValue()+"说:"+ msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.blue, 12, false, false);
				}
			}
		});
		panelSouth.add(btnSendPrivateMsg);

		Component horizontalStrut_4= Box.createHorizontalStrut(20);
		panelSouth.add(horizontalStrut_4);
		
		

		btnSendFile = new JButton("\u53D1\u9001\u6587\u4EF6");//处理“发送文件”按钮
		btnSendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				//JFileChooser jFileChooser=new JFileChooser();
				JFileChooser chooser = new JFileChooser();			   
			    int returnVal = chooser.showOpenDialog(chooser);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getName());
			       filePath = chooser.getSelectedFile().getAbsolutePath();//文件路径
			        fileName = chooser.getSelectedFile().getName();//文件名
			        fileSize = chooser.getSelectedFile().length();//文件大小
			        FileSendMessage sendMessage= new FileSendMessage(localUserName, listOnlineUsers.getSelectedValue(), fileName,filePath, fileSize, true);			      
			        try {
						synchronized (oos) {
							oos.writeObject(sendMessage);
							oos.flush();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			 // 在“消息记录”文本框中用蓝色显示发送的消息及发送时间
				String msgRecord = dateFormat.format(new Date()) + "我向"
				+listOnlineUsers.getSelectedValue()+"发送文件发送请求："+ 
						fileName+ "\r\n";
				addMsgRecord(msgRecord, Color.blue, 12, false, false);
				
			}
		});
		panelSouth.add(btnSendFile);//增加发送文件控件

		// 将发送文件按钮设为可用状态
		btnSendFile.setEnabled(true);
		// 将发送消息按钮设为不可用状态
		btnSendPublicMsg.setEnabled(true);
		// 将发送消息按钮设为不可用状态
		btnSendPrivateMsg.setEnabled(true);
	}
	
	
	
	

	// 向消息记录文本框中添加一条消息记录
	private void addMsgRecord(final String msgRecord, Color msgColor,
			int fontSize, boolean isItalic, boolean isUnderline) {
		final SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, msgColor);
		StyleConstants.setFontSize(attrset, fontSize);
		StyleConstants.setUnderline(attrset, isUnderline);
		StyleConstants.setItalic(attrset, isItalic);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Document docs = textPaneMsgRecord.getDocument();
				try {
					docs.insertString(docs.getLength(), msgRecord, attrset);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 后台监听线程
	class ListeningHandler implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					Message msg = null;
					synchronized (ois) {
						msg = (Message) ois.readObject();
					}
					if (msg instanceof UserStateMessage) {
						//System.out.println("邹景春"+"执行了处理状态程序");
						// 处理用户状态消息
						processUserStateMessage((UserStateMessage) msg);
					} else if (msg instanceof ChatMessage) {
						//System.out.println(""+"执行了处理聊天程序");
						// 处理聊天消息
						processChatMessage((ChatMessage) msg);
					} else if (msg instanceof RegisterMessage) {//msg instanceof RegisterMessage
						// 处理用户注册消息
						processRegisterMessage((RegisterMessage) msg);
						//System.out.println("jj"+"执行了处理注册程序");
					} else if (msg instanceof LoginMessage) {//msg instanceof RegisterMessage
						// 处理用户登陆消息
						processLoginMessage((LoginMessage) msg);
						//System.out.println("jj"+"执行了处理登陆程序");
					}else if(msg instanceof FileSendMessage){
						// 处理用户文件发送请求消息
						processFileSendMessage((FileSendMessage) msg);
					}else if(msg instanceof FileResponseMessage){
						//处理文件发送请求回应消息
						processFileResponseMessage((FileResponseMessage) msg);
					}else{
						// 这种情况对应着用户发来的消息格式 错误，应该发消息提示用户，这里从略
						System.err.println("用户发来的消息格式错误!");
					}
				}
			} catch (IOException e) {
				if (e.toString().endsWith("Connection reset")) {
					System.out.println("服务器端退出");
				} else {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		public class ReceiveFile implements Runnable {
			private File fileout;
			private long fileSize;
			private ServerSocket fileSocket;
			Socket socket;
			private FileOutputStream fos;//用于写入诸如图像数据之类的原始字节的流
			private DataInputStream dis;
			JProgressBar progressBar;	
			public ReceiveFile(File fileout, long fileSize, ServerSocket fileSocket,
					JProgressBar progressBar) {
				super();
				this.fileout = fileout;
				this.fileSize = fileSize;
				this.fileSocket = fileSocket;
				this.progressBar = progressBar;
			}



			public void run() {
				try {
						socket = fileSocket.accept();
						System.out.println("接收文件的大小为"+fileSize+"b");
						fos = new FileOutputStream(fileout);
						dis = new DataInputStream(socket.getInputStream());
						byte[] buff = new byte[2048];
					    progressBar.setValue(0);
						int readSize;
						int reclen = 0;
						//从包含的输入流中读取一定数量的字节，并将它们存储到缓冲区数组 buff 中
						while ((readSize = dis.read(buff)) != -1) {//流位于文件末尾而没有字节可用，则返回值 -1
							//将buff 数组中从0开始的 readSize个字节写入此文件输出流
							fos.write(buff, 0, readSize);
							fos.flush();
							reclen = readSize + reclen;
							//fos.write(buff, 0, readSize);
							progressBar.setValue((int)((double)reclen/fileSize *100));

						}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					
					try {
						if (socket != null) 
								socket.close();
						if(fos!=null)
						{fos.close();
						fos=null;
						System.out.println("----------打开文件---------");
						}
						if(dis!= null)
						{	dis.close();}
						if(fileSocket!=null)
							fileSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					System.out.println("----------接收文件结束---------");		
				}
			}
		}
		
		public class SendFile implements Runnable {
			private String filePath;
			private int fport;
			Socket socket;
			long fileSize;
			FileInputStream fis = null;
			DataOutputStream dos = null;
			JProgressBar progressBar;

			public SendFile(String filePath,long fileSize, int fport,JProgressBar progressBar) {
				this.filePath = filePath;
				this.fport = fport;//传输文件的端口
				this.fileSize = fileSize;
				this.progressBar =progressBar;
			}

			
			public void run() {
				try {
					
					socket = new Socket("localhost", fport);//与两客户端建立连接
					File sendfile = new File(filePath);//要发送的文件
					System.out.println("发送文件的长度为"+fileSize+"b");
					FileInputStream fis = new FileInputStream(sendfile);//得到一个文件输入流
					DataOutputStream dos = new DataOutputStream(
							socket.getOutputStream());//
					byte[] buff = new byte[2048];// 一次可允许最大发2048个字节
					progressBar.setValue(0);
					int readSize;
					int sendlenth = 0;
					while ((readSize = fis.read(buff)) != -1) {
						dos.write(buff, 0, readSize);
						dos.flush();//刷新
						sendlenth = readSize + sendlenth;
						progressBar.setValue((int)((double)sendlenth/fileSize *100));//发送进度：已经发送/总长度
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (socket != null)
							socket.close();
						if (fis != null)
							fis.close();
						if (dos != null)
							dos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		// 处理服务器发来的用户注册消息
		private void processRegisterMessage(RegisterMessage msg) {
			String srcUser = msg.getSrcUser();
			String passwd = msg.getpasswd();			
			if(msg.getDstUser().equals("")==true){
		    final String msgRecord = dateFormat.format(new Date())
		    		+"" + "注册成功，请登录\r\n";
			addMsgRecord(msgRecord, Color.red, 12, false, false);
			}else if(msg.getDstUser().equals("a")){
			     final String msgRecord1 = dateFormat.format(new Date())
					    + "用户名已存在，请重新注册\r\n";
					addMsgRecord(msgRecord1, Color.red, 12, false, false);
			}
			}
		
		// 处理服务器发来的用户登陆消息
		private void processLoginMessage(LoginMessage msg) {				
			if(msg.getDstUser().equals("")==true){
				// 向服务器发送用户上线信息，将自己的用户名和IP地址发送给服务器
				UserStateMessage userStateMessage = new UserStateMessage(
						localUserName, "", true);
				try {
					oos.writeObject(userStateMessage);
					oos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// 在“消息记录”文本框中用红色添加“XX时间登录成功”的信息
				String msgRecord = dateFormat.format(new Date())
						+ " 登录成功\r\n";
				addMsgRecord(msgRecord, Color.red, 12, false, false);
				// 创建并启动“后台监听线程”,监听并处理服务器传来的信息
				//new Thread(new ListeningHandler()).start();
				// 将“登录”按钮设为“退出”按钮
				btnLogon.setText("退出");
				// 将发送文件按钮设为可用状态
				btnSendFile.setEnabled(true);
				// 将发送公聊消息按钮设为可用状态
				btnSendPublicMsg.setEnabled(true);
				// 将发送私聊消息按钮设为可用状态
				btnSendPrivateMsg.setEnabled(true);
			}else if(msg.getDstUser().equals("a")){
			     final String msgRecord1 = dateFormat.format(new Date())
					    + "用户名或密码错误，请重新登录\r\n";
					addMsgRecord(msgRecord1, Color.red, 12, false, false);
			}
			}
		
		// 处理服务器发来的用户文件发送请求消息
				private void processFileSendMessage(FileSendMessage msg) {				
					if(msg.getDstUser().equals(localUserName)==true){
						 String srcUser = msg.getSrcUser();
							String dstUser = msg.getDstUser();
							final String fileName = msg.getFileName();
							String filePath = msg.getFilePath();
							long fileSize = msg.getFileSize();
							// 用黑色文字将收到消息的时间、发送消息的用户名和消息内容添加到“消息记录”文本框中
							final String msgRecord = dateFormat.format(new Date()) + " " + srcUser
									+ "请求发送文件:" + fileName + "\r\n" + "  文件大小:" + fileSize + "b"
									+ "\r\n";
							addMsgRecord(msgRecord, Color.black, 12, false, false);
							//弹出对话框是否接受文件，接收YES，拒绝NO
							int response = JOptionPane.showConfirmDialog(null,
									"对方发送文件，是否接受?", "文件发送请求确认",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if (response == JOptionPane.NO_OPTION) {//选择拒绝
								System.out.println("拒绝接收文件");
								// 定义一个拒绝消息发送给服务器
								FileResponseMessage frsp = new FileResponseMessage(dstUser, srcUser, false,  fileSize, filePath);
								try {
									synchronized (oos) {
										oos.writeObject(frsp);
										oos.flush();
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (response == JOptionPane.YES_OPTION) {
								// 定义一个接受消息给服务器
								FileResponseMessage frsp = new FileResponseMessage(dstUser, srcUser,true,  fileSize, filePath);
								System.out.println("同意接收文件");
								
								try {
									synchronized (oos) {
										oos.writeObject(frsp);
										oos.flush();
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								try {
									ServerSocket fileSocket = new ServerSocket();
									fileSocket.setReuseAddress(true);
									fileSocket.bind(new InetSocketAddress(fport));
									JFileChooser fileChooser = new JFileChooser();
									String pathname = fileChooser.getCurrentDirectory() + fileName;
									File file = new File(pathname);
									fileChooser.setSelectedFile(file);
									fileChooser.showSaveDialog(null);//弹出保存窗口
									File fileout = new File(fileChooser.getCurrentDirectory()
											+ "\\" + fileName);
									JProgressBar progressBar = new JProgressBar();
									progressBar.setStringPainted(true);
									progressBar.setPreferredSize(new Dimension(300, 25));
									progressBar.setBackground(Color.green);
									textPaneMsgRecord.insertComponent(progressBar);
									// 接收文件的线程
									ReceiveFile recFile = new ReceiveFile(fileout, fileSize, fileSocket, progressBar);
									new Thread(recFile).start();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}																							
							}
							}
					
					}
				
				
				//处理文件发送请求回应消息
				private void processFileResponseMessage(FileResponseMessage msg) {
					// TODO Auto-generated method stub
					final String srcUser = msg.getSrcUser();
					final String dstUser = msg.getDstUser();
					if (msg.isAgreeAcceptFile()==true) {
						final String msgRecord = dateFormat.format(new Date()) + " "
								+ srcUser + "同意接受文件 "  + "\r\n";
						addMsgRecord(msgRecord, Color.black, 12, false, false);
						String filePath = msg.getFilePath();
						long fileSize = msg.getFileSize();
						JProgressBar progressBar = new JProgressBar();
						progressBar.setStringPainted(true);
						progressBar.setPreferredSize(new Dimension(300, 25));
						progressBar.setBackground(Color.green);
						textPaneMsgRecord.insertComponent(progressBar);
						// 发送文件
						SendFile sendFile = new SendFile(filePath, fileSize, fport, progressBar);
						new Thread(sendFile).start();
					}else if (msg.isAgreeAcceptFile()==false) {
						final String msgRecord = dateFormat.format(new Date()) + " "
								+ srcUser + "拒绝接收文件" + "\r\n";
						addMsgRecord(msgRecord, Color.green, 12, false, false);
					}
				}
								
		
		// 处理用户状态消息
		private void processUserStateMessage(UserStateMessage msg) {
			String srcUser = msg.getSrcUser();
			String dstUser = msg.getDstUser();
			if (msg.isUserOnline()) {
				// 新用户上线消息:在新用户上线时初始化UserStateMessage时，DstUser=""
				if (msg.isPubUserStateMessage()) { 
					// 用绿色文字将用户名和用户上线时间添加到“消息记录”文本框中
					final String msgRecord = dateFormat.format(new Date())
							+ " " + srcUser + "上线了!\r\n";
					addMsgRecord(msgRecord, Color.green, 12, false, false);
					// 在“在线用户”列表中增加新上线的用户名
					onlinUserDlm.addElement(srcUser);
				}
				if (dstUser.equals(localUserName)) { // 用户在线消息：新上线用户接受在线用户列表
					onlinUserDlm.addElement(srcUser);
				}
			} else if (msg.isUserOffline()) { // 用户下线消息
				if (onlinUserDlm.contains(srcUser)) {
					// 用绿色文字将用户名和用户下线时间添加到“消息记录”文本框中
					final String msgRecord = dateFormat.format(new Date())
							+ " " + srcUser + "下线了!\r\n";
					addMsgRecord(msgRecord, Color.green, 12, false, false);
					// 在“在线用户”列表中删除下线的用户名
					onlinUserDlm.removeElement(srcUser);
				}
			}
		}

		// 处理服务器转发来的聊天消息
		private void processChatMessage(ChatMessage msg) {
			String srcUser = msg.getSrcUser();
			String dstUser = msg.getDstUser();
			String msgContent = msg.getMsgContent();
			System.out.println("邹景春"+"1公聊");
			if (onlinUserDlm.contains(srcUser)) {//
				if (msg.isPubChatMessage()) {//公聊消息msg.isPubChatMessage()
					// 用黑色文字将收到消息的时间、发送消息的用户名和消息内容添加到“消息记录”文本框中
					final String msgRecord = dateFormat.format(new Date())
							+ " " + srcUser + "向大家说: " + msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.black, 12, false, false);
					System.out.println("邹景春"+"公聊");
				}else if(dstUser.equals(localUserName)){//私聊消息if(dstUser.equals(localUserName))
					final String msgRecord = dateFormat.format(new Date())
							+ " " + srcUser + "向我说: " + msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.black, 12, false, false);
				}
				}
			}
		}
	//注册界面
	 class Register extends JFrame{
		private JPanel contentPane;
		private final JTextField textField;
		private final JPasswordField passwordField;
		//private String username;
		//private String password;
		private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
		public Register() {
			setTitle("Register");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("用户名:");
			lblNewLabel.setBounds(92, 54, 54, 15);
			contentPane.add(lblNewLabel);
			
			JLabel lblPassword = new JLabel("密码:");
			lblPassword.setBounds(92, 104, 54, 15);
			contentPane.add(lblPassword);
			
			textField = new JTextField();
			textField.setBounds(156, 51, 115, 21);
			contentPane.add(textField);
			textField.setColumns(10);
			
			JButton btnRegister = new JButton("注册");
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Login login=new Login();
					//setVisible(false);				
					String username=textField.getText();//
					//System.out.println("username: " + username);
					String password=passwordField.getText();
					//System.out.println("1"+textField.getText());
					//System.out.println("2"+password);		
					if (username.length() > 0&&password.length()>0) {
						 RegisterMessage registerMessage = new RegisterMessage(
									username, "",password);
						 
							try {
								oos.writeObject(registerMessage);
								oos.flush();
								//ois.readObject();								
								setVisible(false);
								
								
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						
					}	
				}
			});
			
			btnRegister.setBounds(56, 146, 90, 23);
			contentPane.add(btnRegister);
			
			JButton btnReset = new JButton("复位清空");
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField.setText("");
					passwordField.setText("");
			}
			});
			btnReset.setBounds(166, 146, 93, 23);
			contentPane.add(btnReset);
			
			JButton btnCancel = new JButton("取消");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					setVisible(false);
			}
			});
			btnCancel.setBounds(280, 146, 93, 23);
			contentPane.add(btnCancel);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(156, 101, 115, 21);
			contentPane.add(passwordField);}}
	}


