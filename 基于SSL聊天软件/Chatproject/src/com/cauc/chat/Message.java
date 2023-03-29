package com.cauc.chat;

import java.io.File;
import java.io.Serializable;

public class Message implements Serializable {
	private String srcUser;
	private String dstUser;

	public Message(String srcUser, String dstUser) {
		this.srcUser = srcUser;
		this.dstUser = dstUser;
	}

	public String getSrcUser() {
		return srcUser;
	}

	public void setSrcUser(String srcUser) {
		this.srcUser = srcUser;
	}

	public String getDstUser() {
		return dstUser;
	}

	public void setDstUser(String dstUser) {
		this.dstUser = dstUser;
	}
}

class FileSendMessage extends Message{
	private String fileName;//文件名
	private long fileSize;//文件大小
	private String filePath;//文件路径
	private boolean isSendSuccess;
	public FileSendMessage(String srcUser, String dstUser, String fileName,
			String filePath, long fileSize, boolean isSendSuccess) {
		super(srcUser, dstUser);
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.filePath = filePath;
		this.isSendSuccess = isSendSuccess;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public boolean isSendSuccess() {
		return isSendSuccess;
	}
	public void setSendSuccess(boolean isSendSuccess) {
		this.isSendSuccess = isSendSuccess;
	}
	
}

class FileResponseMessage extends Message{
	private boolean isAgreeAcceptFile;
	private long fileSize;
	private String filePath;
	public FileResponseMessage(String srcUser, String dstUser,
	boolean isAgreeAcceptFile, long fileSize, String filePath) {
		super(srcUser, dstUser);
		this.isAgreeAcceptFile = isAgreeAcceptFile;
		this.fileSize = fileSize;
		this.filePath = filePath;
	}
	public boolean isAgreeAcceptFile() {
		return isAgreeAcceptFile;
	}
	public void setAgreeAcceptFile(boolean isAgreeAcceptFile) {
		this.isAgreeAcceptFile = isAgreeAcceptFile;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}

class ChatMessage extends Message {
	private String msgContent;

	public ChatMessage(String srcUser, String dstUser, String msgContent) {
		super(srcUser, dstUser);
		this.msgContent = msgContent;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public boolean isPubChatMessage() {
		return getDstUser().equals("");
	}
}

class UserStateMessage extends Message {
	private boolean userOnline;

	public UserStateMessage(String srcUser, String dstUser, boolean userOnline) {
		super(srcUser, dstUser);
		this.userOnline = userOnline;
	}

	public boolean isUserOnline() {
		return userOnline;
	}

	public boolean isUserOffline() {
		return !userOnline;
	}

	public void setUserOnline(boolean userOnline) {
		this.userOnline = userOnline;
	}

	public boolean isPubUserStateMessage() {//
		return getDstUser().equals("");
	}
}

class RegisterMessage extends Message {
	private String passwd;

	public RegisterMessage(String srcUser, String dstUser, String passwd) {
		super(srcUser, dstUser);
		this.passwd = passwd;
	}
	public String getpasswd() {
		return passwd;
	}
	public void setpasswd(String passwd) {
		this.passwd = passwd;
	}
	}


	class LoginMessage extends Message {
		private String passwd;

		public LoginMessage(String srcUser, String dstUser, String passwd) {
			super(srcUser, dstUser);
			this.passwd = passwd;
		}
		public String getpasswd() {
			return passwd;
		}
		public void setpasswd(String passwd) {
			this.passwd = passwd;
		}

}