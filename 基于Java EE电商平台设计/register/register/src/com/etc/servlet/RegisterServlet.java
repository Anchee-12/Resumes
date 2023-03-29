//package com.etc.servlet;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//
//import com.etc.dao.UserDao;
//import com.etc.service.UserService;
//import com.etc.vo.User;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//
//public class RegisterServlet extends HttpServlet {
//	// 上传配置
//    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
//    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
//    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doPost(request,response);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String message="";
//		User user=new User();
//		if(!ServletFileUpload.isMultipartContent(request)) {
//			//如果不是返回页面提示错误
//			message="Error:表单必须包含enctype=\"multipart/form-data";
//			request.setAttribute("message", message);
//			request.getRequestDispatcher("register.jsp").forward(request, response);
//		}
//		//创建DiskFileItemFactory
//		DiskFileItemFactory factory=new DiskFileItemFactory();
//		//创建ServletFileUpload实例
//		ServletFileUpload upload=new ServletFileUpload(factory);
//		//设置最大文件上传值
//		upload.setFileSizeMax(MAX_FILE_SIZE);
//		factory.setSizeThreshold(MEMORY_THRESHOLD);
//		//设置最大请求值，包含表单和文件
//		upload.setSizeMax(MAX_REQUEST_SIZE);
//		upload.setHeaderEncoding("UTF_8");
//		//文件的真实路径
//		String uploadPath=request.getServletContext().getRealPath("upload");
//		//如果路径不存在则创建
//		File uploadDir=new File(uploadPath);
//		if(!uploadDir.exists()) {
//			uploadDir.mkdir();
//		}
//		//解析请求文件的数据
//		 try {
//	            // 解析请求的内容提取文件数据
//	            @SuppressWarnings("unchecked")
//	            List<FileItem> formItems = upload.parseRequest(request);
//	 
//	            if (formItems != null && formItems.size() > 0) {
//	                // 迭代表单数据
//	                for (FileItem item : formItems) {
//	                    // 处理不在表单中的字段
//	                    if (!item.isFormField()) {//为false表示上传的是文件
//	                    	//创建了文件，即上传的文件转换成File型并取到文件名字
//	                        String fileName = new File(item.getName()).getName();
//	                        String saveFileName = ""+System.currentTimeMillis();
//							String type = fileName.split("\\.")[1];
//							saveFileName = saveFileName+"."+type;
//	                        // File.separator表示斜杠，filepath为当前获取的文件的绝对路径
//	                        String filePath = uploadPath + File.separator + fileName;
//	                        File storeFile = new File(filePath);
//	                        // 在控制台输出文件的上传路径
//	                        System.out.println(filePath);
//	                        // 保存文件到硬盘
//	                        try {
//	                        item.write(storeFile);
//	                        }catch(Exception e) {
//	                        	e.printStackTrace();
//	                        }
//	                        
//	                        //user.setPhoto(fileName);
//	                        user.setPhoto(saveFileName);
//	                        request.setAttribute("message","文件上传成功!");
//	                    }else {
//	                    	String fieldName=item.getFieldName();
//	                    	if("userName".equals(fieldName)) {
//	                    	    user.setUserName(item.getString("utf-8"));
//	                    	}else if("password".equals(fieldName)) {
//	                    		user.setPassword(item.getString("utf-8"));
//	                    	}
//	                    	else if("age".equals(fieldName)) {
//	                    		user.setAge(item.getString("utf-8"));
//	                    	}
//	                    	else if("gender".equals(fieldName)) {
//	                    		user.setGender(item.getString("utf-8"));
//	                    	}
//	                    	else if("email".equals(fieldName)) {
//	                    		user.setEmail(item.getString("utf-8"));
//	                    	}
//	                    }
//	                }
//	            }
//	        } catch (FileUploadException e) {
//	        	e.printStackTrace();
//	           
//	        } 
//		 	UserService userService = new UserService();
//		 	userService.register(user);
//		 	String displayPath = request.getContextPath()+File.separator +"upload";
//			String displayFileName = user.getPhoto();
//			String url = displayPath +File.separator +displayFileName;
//			System.out.println("url:"+url);
//		 	request.setAttribute("userInfo",user);
//		 	request.setAttribute("url", url);
//			//request.getRequestDispatcher("download.jsp").forward(request, response);
//		 	UserDao userDao=new UserDao();
//		    request.getRequestDispatcher("success.jsp").forward(request, response);
//	}
//
//}











package com.etc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.etc.service.UserService;
import com.etc.vo.User;

public class RegisterServlet  extends HttpServlet
{
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub    
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if("register".equals(action))
		{
			doRegister(request,response);
		}
		else if("checkUser".equals(action))
		{
			doCheckUser(request,response);
		}

}
	/*
	 * 注册处理
	 */
	private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 

	{
		String message = "";
		User user = new User();
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是返回页面提示错误
			message = "表单必须包含 enctype=multipart/form-data";
			request.setAttribute("message", message);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		// 创建DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 创建ServletFileUpload 实例
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 中文处理
		upload.setHeaderEncoding("UTF-8");
		String uploadPath = request.getServletContext().getRealPath("upload");
		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
       try {
		List<FileItem> formItems = upload.parseRequest(request);
		 if (formItems != null && formItems.size() > 0) {
             // 迭代表单数据
             for (FileItem item : formItems) {
                 // 处理不在表单中的字段
                 if (!item.isFormField()) {
                     String fileName = new File(item.getName()).getName();

                     String saveFileName = ""+System.currentTimeMillis();
                     String type = fileName.split("\\.")[1];
                     saveFileName = saveFileName+"."+type;
                     String filePath = uploadPath + File.separator + saveFileName;
                     File storeFile = new File(filePath);
                     // 在控制台输出文件的上传路径
                     System.out.println(filePath);
                     // 保存文件到硬盘
                     item.write(storeFile);
                     request.setAttribute("message",
                         "文件上传成功!");
                     user.setPhoto(saveFileName);
                 }
                 else
                 {
                	 String fieldName = item.getFieldName();
                	 if("userName".equals(fieldName))
                	 {
                		 user.setUserName(item.getString("utf-8"));
                	 }
                	 else if("password".equals(fieldName))
                	 {
                		 user.setPassword(item.getString("utf-8"));
                	 }
                	 else if("email".equals(fieldName))
                	 {
                		 user.setEmail(item.getString("utf-8"));
                	 }
                 }
             }
         }

	} catch (FileUploadException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        //request.setAttribute("userInfo", user);
		UserService userservice = new UserService();
		userservice.register(user);
		request.getRequestDispatcher("index1.jsp").forward(request, response);
		 request.setAttribute("userInfo", user);
		 request.getRequestDispatcher("editor.jsp").forward(request, response);

	}
	/*
	 * 登录处理
	 */
	private void doCheckUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		UserService  userService = new UserService();
		boolean isExist = userService.isExistUser(userName);
		String message="";
		System.out.println(userName+"1");
		if(isExist)
		{
			message = "用户名已经存在,请重新输入";
		}
		else
		{
			message = "用户名可用";
		}
		if(userName=="")
		{
			message = "用户名不能为空";
		}
		System.out.println(message);
		String jsonStr = JSON.toJSONString(message);
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json; charset=utf-8");
		//resoponse响应结果
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		out.flush();
		out.close();


	}
}
