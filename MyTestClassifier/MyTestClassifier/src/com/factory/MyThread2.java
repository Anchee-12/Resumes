package com.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.Callable;

import com.huaban.analysis.jieba.JiebaSegmenter;

public class MyThread2 implements Callable<String []>{
	public JiebaSegmenter js=new JiebaSegmenter();
	private String filepath;
	public MyThread2(String filepath){
		this.filepath=filepath;
	}
	public String[]  readFiletoString(String s) throws UnsupportedEncodingException, FileNotFoundException {
		InputStreamReader iReader =new InputStreamReader(new FileInputStream(s),"UTF-8");
		BufferedReader reader = new BufferedReader(iReader);
		String aline;
		StringBuilder sb = new StringBuilder();
		try {
			while ((aline = reader.readLine()) != null)
			{
				sb.append(aline + " ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				iReader.close();
				reader.close();
			    // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
			   } catch (IOException e) {
			    e.printStackTrace();
			   }
		}
		String str=sb.toString();
		List<String> Term = null;
	    Term= js.sentenceProcess(str);
	    String[]terms=Term.toArray(new String[Term.size()]); 
	    return terms;
	}
	@Override
	public String[] call() throws Exception {
		// TODO Auto-generated method stub
		String []content=readFiletoString(filepath);
		return content;
	}
}
