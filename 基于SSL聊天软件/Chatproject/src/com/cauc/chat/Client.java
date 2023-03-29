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
	
	// �������û��б�ListModel��,����ά���������û��б�����ʾ������
	private final DefaultListModel<String> onlinUserDlm = new DefaultListModel<String>();
	// ���ڿ���ʱ����Ϣ��ʾ��ʽ
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
		// ��������˽���Socket���ӣ�����׳��쳣���򵯳��Ի���֪ͨ�û������˳�
		try {
			//String keyStoreFile = "test.keys";
			String passphrase = "123456";
			char[] password = passphrase.toCharArray();//String.ToCharArray: ���ַ������Ϊ�ַ�������
			String trustStoreFile = "test.keys";    
			KeyStore ts = KeyStore.getInstance("JKS");
			ts.load(new FileInputStream(trustStoreFile), password);//�����ļ�
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			tmf.init(ts);
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, tmf.getTrustManagers(), null);//��Ҫ������֤��
			SSLSocketFactory factory=sslContext.getSocketFactory();//����sslContext���Ϳ��ԡ�����
			socket=(SSLSocket)factory.createSocket("localhost",port);//�������������ȫ�ķ�������
			String[] supported=socket.getSupportedCipherSuites();
			socket.setEnabledCipherSuites(supported); 
			System.out.println(socket.getUseClientMode()? "�ͻ�ģʽ":"������ģʽ");
			//socket = new Socket("localhost", port);
			// ��socket����������������ֱ��װ�ɶ����������Ͷ��������
			oos = new ObjectOutputStream(socket
					.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(null, "�Ҳ�������������");
			e1.printStackTrace();
			System.exit(0);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,
					"������I/O���󣬷�����δ������");
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

		btnLogon = new JButton("\u767B\u5F55"); // ����¼����ť
		btnLogon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnLogon.getText().equals("��¼")) {
					localUserName = textFieldUserName.getText().trim();
					passwd=passwordFieldPwd.getText();
					if (localUserName.length() > 0) {
						
						
						// ������������û�������Ϣ�����Լ����û��������뷢�͸�������
						LoginMessage loginMessage = new LoginMessage(
								localUserName,"",passwd);
						try {
							oos.writeObject(loginMessage);
							oos.flush();
						} catch (IOException e1) {
							e1.printStackTrace();
						}						
					}
				} else if (btnLogon.getText().equals("�˳�")) {
					if (JOptionPane.showConfirmDialog(null, "�Ƿ��˳�?", "�˳�ȷ��",
							JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
						// ������������û�������Ϣ
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
		
		btnRegister = new JButton("\u6CE8\u518C");//ע�ᰴť
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				Register register=new Register();
				register.setVisible(true);
				String msgRecord = dateFormat.format(new Date())
						+ " ���������������ע������\r\n";
				addMsgRecord(msgRecord, Color.red, 12, false, false);
				//ע����Ϣ���ֶ���Ϊ��ɫ����
				
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

		btnSendPublicMsg = new JButton("\u53D1\u516C\u804A\u6D88\u606F"); // ����������Ϣ����ť
		btnSendPublicMsg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msgContent = textFieldMsgToSend.getText();
				if (msgContent.length() > 0) {
					// ����Ϣ�ı����е�������Ϊ������Ϣ���͸�������
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
					// �ڡ���Ϣ��¼���ı���������ɫ��ʾ���͵���Ϣ������ʱ��
					String msgRecord = dateFormat.format(new Date()) + "������˵:"
							+ msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.blue, 12, false, false);
				}
			}
		});
		panelSouth.add(btnSendPublicMsg);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panelSouth.add(horizontalStrut_3);
		
		btnSendPrivateMsg = new JButton("\u53D1\u79C1\u804A\u6D88\u606F");// ����˽����Ϣ����ť
		btnSendPrivateMsg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msgContent = textFieldMsgToSend.getText();
				//dstUserName=listOnlineUsers.getSelectedValue();
				if (msgContent.length() > 0) {
					// ����Ϣ�ı����е�������Ϊ˽����Ϣ���͸�������
					ChatMessage chatMessage = new ChatMessage(localUserName,
					   listOnlineUsers.getSelectedValue(), msgContent);//dstUserName
					try {
						synchronized (oos) {	//synchronized�ؼ��֣���������
							oos.writeObject(chatMessage);
							oos.flush();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					// �ڡ���Ϣ��¼���ı���������ɫ��ʾ���͵���Ϣ������ʱ��
					String msgRecord = dateFormat.format(new Date()) + "����"
					+listOnlineUsers.getSelectedValue()+"˵:"+ msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.blue, 12, false, false);
				}
			}
		});
		panelSouth.add(btnSendPrivateMsg);

		Component horizontalStrut_4= Box.createHorizontalStrut(20);
		panelSouth.add(horizontalStrut_4);
		
		

		btnSendFile = new JButton("\u53D1\u9001\u6587\u4EF6");//���������ļ�����ť
		btnSendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				//JFileChooser jFileChooser=new JFileChooser();
				JFileChooser chooser = new JFileChooser();			   
			    int returnVal = chooser.showOpenDialog(chooser);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getName());
			       filePath = chooser.getSelectedFile().getAbsolutePath();//�ļ�·��
			        fileName = chooser.getSelectedFile().getName();//�ļ���
			        fileSize = chooser.getSelectedFile().length();//�ļ���С
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
			 // �ڡ���Ϣ��¼���ı���������ɫ��ʾ���͵���Ϣ������ʱ��
				String msgRecord = dateFormat.format(new Date()) + "����"
				+listOnlineUsers.getSelectedValue()+"�����ļ���������"+ 
						fileName+ "\r\n";
				addMsgRecord(msgRecord, Color.blue, 12, false, false);
				
			}
		});
		panelSouth.add(btnSendFile);//���ӷ����ļ��ؼ�

		// �������ļ���ť��Ϊ����״̬
		btnSendFile.setEnabled(true);
		// ��������Ϣ��ť��Ϊ������״̬
		btnSendPublicMsg.setEnabled(true);
		// ��������Ϣ��ť��Ϊ������״̬
		btnSendPrivateMsg.setEnabled(true);
	}
	
	
	
	

	// ����Ϣ��¼�ı��������һ����Ϣ��¼
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

	// ��̨�����߳�
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
						//System.out.println("�޾���"+"ִ���˴���״̬����");
						// �����û�״̬��Ϣ
						processUserStateMessage((UserStateMessage) msg);
					} else if (msg instanceof ChatMessage) {
						//System.out.println(""+"ִ���˴����������");
						// ����������Ϣ
						processChatMessage((ChatMessage) msg);
					} else if (msg instanceof RegisterMessage) {//msg instanceof RegisterMessage
						// �����û�ע����Ϣ
						processRegisterMessage((RegisterMessage) msg);
						//System.out.println("jj"+"ִ���˴���ע�����");
					} else if (msg instanceof LoginMessage) {//msg instanceof RegisterMessage
						// �����û���½��Ϣ
						processLoginMessage((LoginMessage) msg);
						//System.out.println("jj"+"ִ���˴����½����");
					}else if(msg instanceof FileSendMessage){
						// �����û��ļ�����������Ϣ
						processFileSendMessage((FileSendMessage) msg);
					}else if(msg instanceof FileResponseMessage){
						//�����ļ����������Ӧ��Ϣ
						processFileResponseMessage((FileResponseMessage) msg);
					}else{
						// ���������Ӧ���û���������Ϣ��ʽ ����Ӧ�÷���Ϣ��ʾ�û����������
						System.err.println("�û���������Ϣ��ʽ����!");
					}
				}
			} catch (IOException e) {
				if (e.toString().endsWith("Connection reset")) {
					System.out.println("���������˳�");
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
			private FileOutputStream fos;//����д������ͼ������֮���ԭʼ�ֽڵ���
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
						System.out.println("�����ļ��Ĵ�СΪ"+fileSize+"b");
						fos = new FileOutputStream(fileout);
						dis = new DataInputStream(socket.getInputStream());
						byte[] buff = new byte[2048];
					    progressBar.setValue(0);
						int readSize;
						int reclen = 0;
						//�Ӱ������������ж�ȡһ���������ֽڣ��������Ǵ洢������������ buff ��
						while ((readSize = dis.read(buff)) != -1) {//��λ���ļ�ĩβ��û���ֽڿ��ã��򷵻�ֵ -1
							//��buff �����д�0��ʼ�� readSize���ֽ�д����ļ������
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
						System.out.println("----------���ļ�---------");
						}
						if(dis!= null)
						{	dis.close();}
						if(fileSocket!=null)
							fileSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					System.out.println("----------�����ļ�����---------");		
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
				this.fport = fport;//�����ļ��Ķ˿�
				this.fileSize = fileSize;
				this.progressBar =progressBar;
			}

			
			public void run() {
				try {
					
					socket = new Socket("localhost", fport);//�����ͻ��˽�������
					File sendfile = new File(filePath);//Ҫ���͵��ļ�
					System.out.println("�����ļ��ĳ���Ϊ"+fileSize+"b");
					FileInputStream fis = new FileInputStream(sendfile);//�õ�һ���ļ�������
					DataOutputStream dos = new DataOutputStream(
							socket.getOutputStream());//
					byte[] buff = new byte[2048];// һ�ο��������2048���ֽ�
					progressBar.setValue(0);
					int readSize;
					int sendlenth = 0;
					while ((readSize = fis.read(buff)) != -1) {
						dos.write(buff, 0, readSize);
						dos.flush();//ˢ��
						sendlenth = readSize + sendlenth;
						progressBar.setValue((int)((double)sendlenth/fileSize *100));//���ͽ��ȣ��Ѿ�����/�ܳ���
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

		// ����������������û�ע����Ϣ
		private void processRegisterMessage(RegisterMessage msg) {
			String srcUser = msg.getSrcUser();
			String passwd = msg.getpasswd();			
			if(msg.getDstUser().equals("")==true){
		    final String msgRecord = dateFormat.format(new Date())
		    		+"" + "ע��ɹ������¼\r\n";
			addMsgRecord(msgRecord, Color.red, 12, false, false);
			}else if(msg.getDstUser().equals("a")){
			     final String msgRecord1 = dateFormat.format(new Date())
					    + "�û����Ѵ��ڣ�������ע��\r\n";
					addMsgRecord(msgRecord1, Color.red, 12, false, false);
			}
			}
		
		// ����������������û���½��Ϣ
		private void processLoginMessage(LoginMessage msg) {				
			if(msg.getDstUser().equals("")==true){
				// ������������û�������Ϣ�����Լ����û�����IP��ַ���͸�������
				UserStateMessage userStateMessage = new UserStateMessage(
						localUserName, "", true);
				try {
					oos.writeObject(userStateMessage);
					oos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// �ڡ���Ϣ��¼���ı������ú�ɫ��ӡ�XXʱ���¼�ɹ�������Ϣ
				String msgRecord = dateFormat.format(new Date())
						+ " ��¼�ɹ�\r\n";
				addMsgRecord(msgRecord, Color.red, 12, false, false);
				// ��������������̨�����̡߳�,�����������������������Ϣ
				//new Thread(new ListeningHandler()).start();
				// ������¼����ť��Ϊ���˳�����ť
				btnLogon.setText("�˳�");
				// �������ļ���ť��Ϊ����״̬
				btnSendFile.setEnabled(true);
				// �����͹�����Ϣ��ť��Ϊ����״̬
				btnSendPublicMsg.setEnabled(true);
				// ������˽����Ϣ��ť��Ϊ����״̬
				btnSendPrivateMsg.setEnabled(true);
			}else if(msg.getDstUser().equals("a")){
			     final String msgRecord1 = dateFormat.format(new Date())
					    + "�û�����������������µ�¼\r\n";
					addMsgRecord(msgRecord1, Color.red, 12, false, false);
			}
			}
		
		// ����������������û��ļ�����������Ϣ
				private void processFileSendMessage(FileSendMessage msg) {				
					if(msg.getDstUser().equals(localUserName)==true){
						 String srcUser = msg.getSrcUser();
							String dstUser = msg.getDstUser();
							final String fileName = msg.getFileName();
							String filePath = msg.getFilePath();
							long fileSize = msg.getFileSize();
							// �ú�ɫ���ֽ��յ���Ϣ��ʱ�䡢������Ϣ���û�������Ϣ������ӵ�����Ϣ��¼���ı�����
							final String msgRecord = dateFormat.format(new Date()) + " " + srcUser
									+ "�������ļ�:" + fileName + "\r\n" + "  �ļ���С:" + fileSize + "b"
									+ "\r\n";
							addMsgRecord(msgRecord, Color.black, 12, false, false);
							//�����Ի����Ƿ�����ļ�������YES���ܾ�NO
							int response = JOptionPane.showConfirmDialog(null,
									"�Է������ļ����Ƿ����?", "�ļ���������ȷ��",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if (response == JOptionPane.NO_OPTION) {//ѡ��ܾ�
								System.out.println("�ܾ������ļ�");
								// ����һ���ܾ���Ϣ���͸�������
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
								// ����һ��������Ϣ��������
								FileResponseMessage frsp = new FileResponseMessage(dstUser, srcUser,true,  fileSize, filePath);
								System.out.println("ͬ������ļ�");
								
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
									fileChooser.showSaveDialog(null);//�������洰��
									File fileout = new File(fileChooser.getCurrentDirectory()
											+ "\\" + fileName);
									JProgressBar progressBar = new JProgressBar();
									progressBar.setStringPainted(true);
									progressBar.setPreferredSize(new Dimension(300, 25));
									progressBar.setBackground(Color.green);
									textPaneMsgRecord.insertComponent(progressBar);
									// �����ļ����߳�
									ReceiveFile recFile = new ReceiveFile(fileout, fileSize, fileSocket, progressBar);
									new Thread(recFile).start();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}																							
							}
							}
					
					}
				
				
				//�����ļ����������Ӧ��Ϣ
				private void processFileResponseMessage(FileResponseMessage msg) {
					// TODO Auto-generated method stub
					final String srcUser = msg.getSrcUser();
					final String dstUser = msg.getDstUser();
					if (msg.isAgreeAcceptFile()==true) {
						final String msgRecord = dateFormat.format(new Date()) + " "
								+ srcUser + "ͬ������ļ� "  + "\r\n";
						addMsgRecord(msgRecord, Color.black, 12, false, false);
						String filePath = msg.getFilePath();
						long fileSize = msg.getFileSize();
						JProgressBar progressBar = new JProgressBar();
						progressBar.setStringPainted(true);
						progressBar.setPreferredSize(new Dimension(300, 25));
						progressBar.setBackground(Color.green);
						textPaneMsgRecord.insertComponent(progressBar);
						// �����ļ�
						SendFile sendFile = new SendFile(filePath, fileSize, fport, progressBar);
						new Thread(sendFile).start();
					}else if (msg.isAgreeAcceptFile()==false) {
						final String msgRecord = dateFormat.format(new Date()) + " "
								+ srcUser + "�ܾ������ļ�" + "\r\n";
						addMsgRecord(msgRecord, Color.green, 12, false, false);
					}
				}
								
		
		// �����û�״̬��Ϣ
		private void processUserStateMessage(UserStateMessage msg) {
			String srcUser = msg.getSrcUser();
			String dstUser = msg.getDstUser();
			if (msg.isUserOnline()) {
				// ���û�������Ϣ:�����û�����ʱ��ʼ��UserStateMessageʱ��DstUser=""
				if (msg.isPubUserStateMessage()) { 
					// ����ɫ���ֽ��û������û�����ʱ����ӵ�����Ϣ��¼���ı�����
					final String msgRecord = dateFormat.format(new Date())
							+ " " + srcUser + "������!\r\n";
					addMsgRecord(msgRecord, Color.green, 12, false, false);
					// �ڡ������û����б������������ߵ��û���
					onlinUserDlm.addElement(srcUser);
				}
				if (dstUser.equals(localUserName)) { // �û�������Ϣ���������û����������û��б�
					onlinUserDlm.addElement(srcUser);
				}
			} else if (msg.isUserOffline()) { // �û�������Ϣ
				if (onlinUserDlm.contains(srcUser)) {
					// ����ɫ���ֽ��û������û�����ʱ����ӵ�����Ϣ��¼���ı�����
					final String msgRecord = dateFormat.format(new Date())
							+ " " + srcUser + "������!\r\n";
					addMsgRecord(msgRecord, Color.green, 12, false, false);
					// �ڡ������û����б���ɾ�����ߵ��û���
					onlinUserDlm.removeElement(srcUser);
				}
			}
		}

		// ���������ת������������Ϣ
		private void processChatMessage(ChatMessage msg) {
			String srcUser = msg.getSrcUser();
			String dstUser = msg.getDstUser();
			String msgContent = msg.getMsgContent();
			System.out.println("�޾���"+"1����");
			if (onlinUserDlm.contains(srcUser)) {//
				if (msg.isPubChatMessage()) {//������Ϣmsg.isPubChatMessage()
					// �ú�ɫ���ֽ��յ���Ϣ��ʱ�䡢������Ϣ���û�������Ϣ������ӵ�����Ϣ��¼���ı�����
					final String msgRecord = dateFormat.format(new Date())
							+ " " + srcUser + "����˵: " + msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.black, 12, false, false);
					System.out.println("�޾���"+"����");
				}else if(dstUser.equals(localUserName)){//˽����Ϣif(dstUser.equals(localUserName))
					final String msgRecord = dateFormat.format(new Date())
							+ " " + srcUser + "����˵: " + msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.black, 12, false, false);
				}
				}
			}
		}
	//ע�����
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
			
			JLabel lblNewLabel = new JLabel("�û���:");
			lblNewLabel.setBounds(92, 54, 54, 15);
			contentPane.add(lblNewLabel);
			
			JLabel lblPassword = new JLabel("����:");
			lblPassword.setBounds(92, 104, 54, 15);
			contentPane.add(lblPassword);
			
			textField = new JTextField();
			textField.setBounds(156, 51, 115, 21);
			contentPane.add(textField);
			textField.setColumns(10);
			
			JButton btnRegister = new JButton("ע��");
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
			
			JButton btnReset = new JButton("��λ���");
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField.setText("");
					passwordField.setText("");
			}
			});
			btnReset.setBounds(166, 146, 93, 23);
			contentPane.add(btnReset);
			
			JButton btnCancel = new JButton("ȡ��");
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


