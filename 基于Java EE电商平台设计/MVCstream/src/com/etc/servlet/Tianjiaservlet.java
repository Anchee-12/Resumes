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

import com.etc.service.Goodservice;
import com.etc.vo.shangpin;

public class Tianjiaservlet extends HttpServlet{
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 200; // 200MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 210; // 210MB
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String message="";
		shangpin game = new shangpin();
		String name = request.getParameter("goodsName");

		if (!ServletFileUpload.isMultipartContent(request)) 
		{
			// ������Ƿ���ҳ����ʾ����
			message = "��������� enctype=multipart/form-data";
			request.setAttribute("message", message);
			request.getRequestDispatcher("addgroup.jsp").forward(request, response);
		}
		// ����DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// ����ServletFileUpload ʵ��
		ServletFileUpload goodsupload = new ServletFileUpload(factory);
		// ��������ļ��ϴ�ֵ
		goodsupload.setFileSizeMax(MAX_FILE_SIZE);

		// �����������ֵ (�����ļ��ͱ�����)
		goodsupload.setSizeMax(MAX_REQUEST_SIZE);

		// ���Ĵ���
		goodsupload.setHeaderEncoding("UTF-8");
		String uploadPath = request.getServletContext().getRealPath("goodsupload");
		// ���Ŀ¼�������򴴽�
		File goodsuploadDir = new File(uploadPath);
		if (!goodsuploadDir.exists()) {
			goodsuploadDir.mkdir();
		}
       try {
		List<FileItem> formItems = goodsupload.parseRequest(request);
		 if (formItems != null && formItems.size() > 0) {
             // ����������
             for (FileItem item : formItems) {
                 // �����ڱ��е��ֶ�
                 if (!item.isFormField()) {
                     String fileName = new File(item.getName()).getName();

                     String saveFileName = ""+System.currentTimeMillis();
                     String type = fileName.split("\\.")[1];
                     saveFileName = saveFileName+"."+type;//����ļ���+����
                     String filePath = uploadPath + File.separator + saveFileName;//�ϴ�����ļ���+ 
                     File storeFile = new File(filePath);
                     // �ڿ���̨����ļ����ϴ�·��
                     System.out.println(filePath);
                     // �����ļ���Ӳ��
                     item.write(storeFile);
                     request.setAttribute("message","��ƷͼƬ�ļ��ϴ��ɹ�!");
                     //bmp,jpg,png,tiff,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF,webp�ȡ�
                     //ȡ��ַ
                     String displayPath = request.getContextPath()+File.separator +"goodsupload";
                    
                     String url = displayPath +File.separator +saveFileName;
                     String url1 = displayPath +File.separator +saveFileName;
                     
                     if(type.equalsIgnoreCase("jpg")||type.equalsIgnoreCase("png")){
                    	 game.setPhoto(url);
                     }else {
						game.setShiping(url1);
					}
                     
                  
                 }
                 else
                 {
                	 String fieldName = item.getFieldName();
                	 if("goodsName".equals(fieldName))
                	 {
                		 game.setName(item.getString("utf-8"));
                	 }
                	 else if("production".equals(fieldName))
                	 {
                		 game.setKaifashang(item.getString("utf-8"));
                	 }
                	 else if("type".equals(fieldName))
                	 {
                		 game.setType(item.getString("utf-8"));
                	 }
                 	 else if("typec".equals(fieldName))
                	 {
                		 game.setTypec(item.getString("utf-8"));
                	 }
                 	 else if("discount".equals(fieldName))
                	 {
                		 game.setDiscount(item.getString("utf-8"));
                	 }
                 	 else if("faxing".equals(fieldName))
                	 {
                		 game.setFaxingshang(item.getString("utf-8"));
                	 }
                	 else if("price".equals(fieldName)) {
                		 game.setPrice(Integer.parseInt(item.getString("utf-8")));
                	 }
                	 else if("description".equals(fieldName)) {
                		game.setDescribe1(item.getString("utf-8"));
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
       
        Goodservice goodsService = new Goodservice();
	 	goodsService.addgroup(game);
	 	//String displayPath = request.getContextPath()+File.separator +"goodsupload";
		//String displayFileName = game.getPhoto();
		//String url = displayPath +File.separator +displayFileName;
		//System.out.println("url:"+url);
		//System.out.println("�ɹ��ϴ�");
	 	//request.setAttribute("goodInfo",good);
	 	//request.setAttribute("url", url);
		//request.getRequestDispatcher("download.jsp").forward(request, response);
	    request.getRequestDispatcher("success.jsp").forward(request, response);


	}

}
