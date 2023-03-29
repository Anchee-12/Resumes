package com.factory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class MyThread1 implements Callable<String>{
	private String filepath;
	public MyThread1(String filepath){
		this.filepath=filepath;	
	}
	

	public String getContent(String filepath) throws IOException, FileNotFoundException {
		InputStreamReader iReader =new InputStreamReader(new FileInputStream(filepath),"UTF-8");
		BufferedReader reader = new BufferedReader(iReader);
		String aline;
		StringBuilder sb = new StringBuilder();
		while ((aline = reader.readLine()) != null)
		{
			sb.append(aline + " ");
		}
		iReader.close();
		reader.close();
		return sb.toString();
	}


	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		String content=getContent(filepath);
		return content;
	}
}
