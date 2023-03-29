package com.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class stopwordsChat {
	/*public static boolean IsStopWord(String word){
		String pathname="E:\\trainfile\\dictionary.txt";
		File filename = new File(pathname);
		InputStreamReader isr=null;
		FileInputStream fis=null;
		BufferedReader br=null;
		String line=null;
		try {
			fis=new FileInputStream(filename);
			isr=new InputStreamReader(fis);
			br=new BufferedReader(isr);
			while((line=br.readLine())!=null){
				if(word.equalsIgnoreCase(line));
	    		return true;
			}
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
				     br.close();
				     fis.close();
				     isr.close();
				   } catch (IOException e) {
				    e.printStackTrace();
				   }
			}
		return false;
	}*/
	/*private static String stopWordsList[] ={"的", "我们","要","自己","之","将","“","”","（","）","后","应","到","某","后","个","是","位","新","一","两","在","中","或","有","更","好","，",""};//常用停用词
	public static boolean IsStopWord(String word)
	{
		//System.out.println("IsStopWord开始-------------------------------------");
		for(int i=0;i<stopWordsList.length;++i)
		{
			if(word.equalsIgnoreCase(stopWordsList[i]))
				return true;
		}
		return false;
	}*/
	public static boolean IsStopWord(String word) throws IOException
	{
		String pathname = "E:\\trainfile\\userdictionary.txt";
		File filename = new File(pathname);   
	    /*InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); 
	    BufferedReader br = new BufferedReader(reader); 
		String line = "";
	    while((line = br.readLine())!=null){
	    	if(word.equalsIgnoreCase(line));
	    		return true;
	    }
	    return false;*/
		BufferedReader br=new BufferedReader(new FileReader(filename));
	    ArrayList<String> stopWord=new ArrayList<String>();
	    String line=null;
	    while((line = br.readLine())!=null){
	    	stopWord.add(line);
	    	}
	    //System.out.println(stopWord);
	    String []terms=stopWord.toArray(new String[stopWord.size()]);
	    /*for(String e:terms){
	    	System.out.println(e);
	    }*/
	    for(int i=0;i<terms.length;i++){
	    	if(word.equalsIgnoreCase(terms[i]))
	    		return true;
	    }
	    return false;
	}
	/*public static void main(String []args) throws IOException{
		String word[]={"我们","国家","的","传统","文化","应该","被","保护"};
		stopwordsChat s=new stopwordsChat();
		for(int i=0;i<word.length;i++){
		boolean m=s.IsStopWord(word[i]);
		System.out.println(m);}
		/*if(s.IsStopWord(word)){
			 System.out.println(s.IsStopWord(word));
		}
	}*/
}
