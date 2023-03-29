package com.etc.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.etc.service.GoodsService;
import com.etc.service.UserService;
import com.etc.vo.Goods;
import com.etc.vo.User;

public class EditorServlet extends HttpServlet{
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String message="";
		User user = new User();
		String name = req.getParameter("userName");

		if (!ServletFileUpload.isMultipartContent(req)) {
			// 如果不是返回页面提示错误
			message = "表单必须包含 enctype=multipart/form-data";
			req.setAttribute("message", message);
			req.getRequestDispatcher("editor.jsp").forward(req, resp);
		}
		// 创建DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 创建ServletFileUpload 实例
		ServletFileUpload userupload = new ServletFileUpload(factory);
		// 设置最大文件上传值
		userupload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		userupload.setSizeMax(MAX_REQUEST_SIZE);

		// 中文处理
		userupload.setHeaderEncoding("UTF-8");
		String uploadPath = req.getServletContext().getRealPath("userupload");
		// 如果目录不存在则创建
		File useruploadDir = new File(uploadPath);
		if (!useruploadDir.exists()) {
			useruploadDir.mkdir();
		}
       try {
		List<FileItem> formItems = userupload.parseRequest(req);
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
                     message = "用户图片文件上传成功!";
                     req.setAttribute("message","用户图片文件上传成功!");
                     user.setPhoto(saveFileName);
                 }
                 else
                 {
                	 String fieldName = item.getFieldName();
                	 if("userName".equals(fieldName))
                	 {
                		 user.setUserName(item.getString("utf-8"));
                	 }
                	 else if("gender".equals(fieldName))
                	 {
                		 user.setGender(item.getString("utf-8"));
                	 }
                	 else if("age".equals(fieldName))
                	 {
                		 user.setAge(item.getString("utf-8"));
                	 }
                	 else if("email".equals(fieldName)) {
                		 user.setEmail(item.getString("utf-8"));
                	 }
                	 else if("region".equals(fieldName)) {
                		 user.setRegion(item.getString("utf-8"));
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
        UserService userService = new UserService();
	 	userService.updateuser(user);
	 	String displayPath = req.getContextPath()+File.separator +"userupload";
		String displayFileName = user.getPhoto();
		String url = displayPath +File.separator +displayFileName;
		//System.out.println("url:"+url);
	 	req.setAttribute("userInfo",user);
	 	req.setAttribute("url", url);
		//request.getRequestDispatcher("download.jsp").forward(request, response);
	    req.getRequestDispatcher("personalcenter.jsp").forward(req, resp);
	}
	
}
