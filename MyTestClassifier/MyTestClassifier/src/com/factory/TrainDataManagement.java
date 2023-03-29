package com.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.huaban.analysis.jieba.JiebaSegmenter;

public class TrainDataManagement {
	//public Map<String,Integer>wordsfrequent=new HashMap<String,Integer>();
	//public Map<String,Integer>everyclasswordsfrequent=new HashMap<String,Integer>();
	//public Map<String,Integer>wordFlag=new HashMap<String,Integer>();
	private String[] trainingFileClassifications;//训练集管理器
	private File traintextDir;//训练文本路径
	private static String defaultpath = "E:\\Train";
	public JiebaSegmenter js=new JiebaSegmenter();
	public TrainDataManagement(){
		traintextDir=new File(defaultpath);//训练文本存放位置
		if(!traintextDir.isDirectory()){
			throw new IllegalArgumentException("训练语料库搜索失败！ [" +defaultpath + "]");
		}
		this.trainingFileClassifications = traintextDir.list();
	}
	
	/*
	* 返回训练文本类别，这个类别就是目录名
	*/
	public String[] getTraningClassifications() 
	{
		return this.trainingFileClassifications;	
	}
	
	/*
	 *  根据训练文本类别返回这个类别下的所有训练本文路径
	 */
	public String[] getfilePath(String classification){
		File classDir=new File(traintextDir.getPath() +File.separator +classification);
		String[] ret = classDir.list();
		for (int i = 0; i < ret.length; i++) 
		{
			ret[i] = traintextDir.getPath() +File.separator +classification +File.separator +ret[i];
		}
		return ret;
	}
	
	/*
	 * 返回给定路径的文本文件内容
	 * filePath 给定的文本文件路径,返回指定内容
	 */
	public static String getContent(String filepath) throws IOException, FileNotFoundException{
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
	
	/*
	 * 返回训练文本集中在给定分类下的训练文本数目
	 */
	public int gettrainfilecountofClassification(String classification){
		File classDir = new File(traintextDir.getPath() +File.separator +classification);
		return classDir.list().length;
	}
	
	/*
	 * 返回训练文本集中所有的文本数目
	 */
	public int gettrainfileCount(){
		int count=0;
		for(int i=0;i<trainingFileClassifications.length;i++){
			count +=gettrainfilecountofClassification(trainingFileClassifications[i]);
		}
		return count;
	}
	
	/*
	 * 返回给定分类中包含关键字/词的训练文本的数目
	 */
	public int getcountcontainkeyofClassification(String classification,String key){
		int count=0;
		try {
		String []filepath=getfilePath(classification);//得到这个类别下所有的文档
		for(int i=0;i<filepath.length;i++){
			/*ExecutorService executorService=Executors.newFixedThreadPool(3);//.newSingleThreadExecutor();//.newFixedThreadPool(3);//.newCachedThreadPool();
			MyThread1 callable=new MyThread1(filepath[i]);
			Future<String> future = executorService.submit(callable);	
			String content;
			try {
				content = future.get();
				if (content.contains(key)) 
				{
					count++;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			executorService.shutdown();	*/
			String content=getContent(filepath[i]);//得到文档内容
			if (content.contains(key)) 
			{
				count++;
			}
			} 
		}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			Logger.getLogger(TrainDataManagement.class.getName()).log(Level.SEVERE, null,e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Logger.getLogger(TrainDataManagement.class.getName()).log(Level.SEVERE, null,e);
			}	
		return count;
	}
	
	/*
	 * 返回一个单词在一个类别的所有文件中出现的次数
	 */
	public int getcountwordofallClassification(String cj,String word) throws FileNotFoundException, IOException{
		File file=new File("E:\\Train\\"+cj);
		File []files=file.listFiles();
		List<String> fileofdoc=new ArrayList<String>();
		int i=0;
		int count=0;
		if(files!=null){
			for(File f1:files){
				fileofdoc.add(f1.getAbsolutePath());
				//sfile[i]=f1.getAbsolutePath();
				//i++;
			}	
			}
			String []sfile=fileofdoc.toArray(new String[fileofdoc.size()]);
			for(int j=0;j<sfile.length;j++){
				/*ExecutorService executorService=Executors.newFixedThreadPool(3);//.newSingleThreadExecutor();//.newFixedThreadPool(3);//.newCachedThreadPool();
				Callable<String []> callable=new MyThread2(sfile[j]);
				Future<String []> future = executorService.submit(callable);
				String[] terms;
				try {
					terms = future.get();
					for(String t:terms){
						if(t.equals(word)){
							count++;
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				executorService.shutdown();*/
				String []terms=readFiletoString(sfile[j]);	
				for(String t:terms){
					if(t.equals(word)){
						count++;
					}
				}
			}
			return count;
			}
	
	
	/*
	 * 返回给定类别包含的文档的总词数
	 */
	public int gettotalwordofClassification(String cj) throws FileNotFoundException, IOException{
		File file=new File("E:\\Train\\"+cj);
		File []files=file.listFiles();
		List<String> fileofdoc=new ArrayList<String>();
		int i=0;
		int count=0;
		//int Count[]=new int[files.length];
		if(files!=null){
			for(File f1:files){
				fileofdoc.add(f1.getAbsolutePath());
				//sfile[i]=f1.getAbsolutePath();
				//i++;
			}	
			}
			String []sfile=fileofdoc.toArray(new String[fileofdoc.size()]);
			for(int j=0;j<sfile.length;j++){
				/*ExecutorService executorService=Executors.newFixedThreadPool(3);//.newSingleThreadExecutor();//.newFixedThreadPool(3);//.newCachedThreadPool();
				Callable<String []> callable=new MyThread2(sfile[j]);
				Future<String []> future = executorService.submit(callable);
				String terms[];
				try {
					terms = future.get();
					for(String t:terms){
						count++;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				executorService.shutdown();*/
				String []terms=readFiletoString(sfile[j]);
				for(String t:terms){
					count++;
				}
			}
		return count;	
	}
	
	/*
	 * 返回全部类别的单词总数
	 */
	public int getallwords() throws FileNotFoundException, IOException{
		Map<String,Integer> words=getwordsfrequent();
		int count=0;
		Iterator<Entry<String,Integer>> iter = words.entrySet().iterator();
		while(iter.hasNext()){
		   Entry<String,Integer> entry = iter.next();
		   String key = entry.getKey();
		   Integer value = entry.getValue();
		   //System.out.println(key+" "+value);
		   count=count+value;
		  }
		return count;		
	}
	
	/*
	 * 返回一个类别的单词总数
	 */
	public int geteveryclasswords(String c) throws FileNotFoundException, IOException{
		Map<String,Integer> words=getfrequentofeveryClass(c);
		int count=0;
		Iterator<Entry<String,Integer>> iter = words.entrySet().iterator();
		while(iter.hasNext()){
		   Entry<String,Integer> entry = iter.next();
		   String key = entry.getKey();
		   Integer value = entry.getValue();
		   //System.out.println(key+" "+value);
		   count=count+value;
		  }
		return count;		
	}
	
	/*
	 * 返回训练集的单词种类数
	 */
	public int getkindsofWord() throws FileNotFoundException, IOException{
		Map<String,Integer> words=getwordsfrequent();
		int size=words.size();
		return size;
	}
	
	/*
	 * 建立类别的词频Map
	 */
	public Map<String,Integer> getwordsfrequent() throws FileNotFoundException, IOException{
		String []classes=getTraningClassifications();
		Map<String,Integer>wordsfrequent=new HashMap<String,Integer>();
		for(String c:classes){
			String []sfile=getfilePath(c);
			for(String s:sfile){
				/*ExecutorService executorService=Executors.newFixedThreadPool(3);//.newSingleThreadExecutor();//.newFixedThreadPool(3);//.newCachedThreadPool();
				Callable<String []> callable=new MyThread2(s);
				Future<String []> future = executorService.submit(callable);
				String[] terms;
				try {
					terms = future.get();
					for(String word:terms){
				    	 wordsfrequent.put(word, wordsfrequent.containsKey(word) ? wordsfrequent.get(word) + 1 : 1);
				     }
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				executorService.shutdown();*/
				String []terms=readFiletoString(s);
				for(String word:terms){
			    	 wordsfrequent.put(word, wordsfrequent.containsKey(word) ? wordsfrequent.get(word) + 1 : 1);
			     } 
			}			
		}
		//int size=wordsfrequent.size();
		return wordsfrequent;
	}
	
	/*
	 * 将Map转换成String数组
	 */
	public String[] convertmap() throws FileNotFoundException, IOException{
		Map<String,Integer>wordfre= getwordsfrequent();
		String words[]=new String[wordfre.size()];
		Iterator<Entry<String,Integer>> iter = wordfre.entrySet().iterator();
		int i=0;
		while(iter.hasNext()){
		   Entry<String,Integer> entry = iter.next();
		   String key = entry.getKey();
		   words[i]=key;
		   i++;
		}
		return words;
	}
	
	/*
	 * 判断一个单词是否存在于这个类别当中
	 */
	public String Isexist(String w,String word) throws FileNotFoundException, IOException{
			if(word.equals(w))
				return word;
			else
				return w;
	
	}
	
	/*
	 * 建立一个词是否存在于词频Map当中的新Map
	 */
	public Map<String,Integer> setnewMap(String X[]) throws FileNotFoundException, IOException{
		String allwords[]=convertmap();
		List list = new ArrayList(Arrays.asList(allwords));
		list.addAll(Arrays.asList(X));
		String[] str = new String[list.size()];
		list.toArray(str);  
		Map<String,Integer> map = new HashMap<>();    
		Map<String,Integer>wordFlag=new HashMap<String,Integer>();
	    for(String s:str){
	    	map.put(s,map.containsKey(s) ? map.get(s) + 1 : 1);
	    }
	    Iterator<Entry<String,Integer>> iter = map.entrySet().iterator();
	    while(iter.hasNext()){
	    	Entry<String,Integer> entry = iter.next();
		    String key = entry.getKey();
		    int flag=entry.getValue();
		    if(flag==1){
				wordFlag.put(key, 0);
			}
			else{
				wordFlag.put(key, 1);
			}
	    }
		/*for(String word:allwords){
			for(String x:X){
				if(x.equals(word)){
					wordFlag.put(word, 1);
				}
				else{
					wordFlag.put(word, 0);
				}
			}
		}*/
		return wordFlag ;
		
	}
	/*
	 * 建立每个类别的词频Map
	 */
	public Map<String,Integer>getfrequentofeveryClass(String Cj) throws FileNotFoundException, IOException{
		String []sfile=getfilePath(Cj);
		Map<String,Integer>everyclasswordsfrequent=new HashMap<String,Integer>();
		for(String s:sfile){
			/*ExecutorService executorService=Executors.newFixedThreadPool(3);//.newSingleThreadExecutor();//.newFixedThreadPool(3);//.newCachedThreadPool();
			Callable<String []> callable=new MyThread2(s);
			Future<String []> future = executorService.submit(callable);
			String terms[];
			try {
				terms = future.get();
				 for(String word:terms){
			    	 everyclasswordsfrequent.put(word, everyclasswordsfrequent.containsKey(word) ? everyclasswordsfrequent.get(word) + 1 : 1);
			     }
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			executorService.shutdown();*/
			String []terms=readFiletoString(s);
			for(String word:terms){
		    	 everyclasswordsfrequent.put(word, everyclasswordsfrequent.containsKey(word) ? everyclasswordsfrequent.get(word) + 1 : 1);
		     }
		}
		return everyclasswordsfrequent;
	}
	
	/*
	 * 读取文件并拼接成String
	 */
	/*public String[] readFiletoString(String s){
		File filename=new File(s);
		String []terms;
		InputStreamReader isr=null;
		FileInputStream fis=null;
		BufferedReader br=null;
	     String str=null;
	     String line = null; 
	     try {
			fis=new FileInputStream(filename);
			isr = new InputStreamReader(fis);
		     br = new BufferedReader(isr);
			 while ((line=br.readLine()) != null) {  
	            str+=line;
	        }  
			// System.out.println(str);
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
			    // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
			   } catch (IOException e) {
			    e.printStackTrace();
			   }
		}
	     List<String> Term = null;
	     Term= js.sentenceProcess(str);
	     terms=Term.toArray(new String[Term.size()]); 
	     return terms;
	}*/
	
	
	public String[] readFiletoString(String s) throws IOException, FileNotFoundException{
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
	
	/*
	 * 计算一个词在一个文件中出现的次数
	 */
	public int countofeveryDoc(String docname,String word) throws FileNotFoundException, IOException{
		int count=0;
		/*ExecutorService executorService=Executors.newFixedThreadPool(3);//.newSingleThreadExecutor();//.newFixedThreadPool(3);//.newCachedThreadPool();
		Callable<String []> callable=new MyThread2(docname);
		Future<String []> future = executorService.submit(callable);
		String terms[];
		try {
			terms = future.get();
			for(String s:terms){
				if(word.equals(s)){
					count++;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();*/
		String terms[]=readFiletoString(docname);
		for(String s:terms){
			if(word.equals(s)){
				count++;
			}
		}
		return count;
	}
	
	/*
	 * 计算一个文件当中所有词出现的次数之和
	 */
	public int allwordsofDoc(String docname) throws FileNotFoundException, IOException{
		int count=0;
		/*ExecutorService executorService=Executors.newFixedThreadPool(3);//.newSingleThreadExecutor();//.newFixedThreadPool(3);//.newCachedThreadPool();
		Callable<String []> callable=new MyThread2(docname);
		Future<String []> future = executorService.submit(callable);
		String[] terms;
		try {
			terms = future.get();
			for(String s:terms){
				count++;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();*/
		String terms[]=readFiletoString(docname);
		for(String s:terms){
			count++;
		}
		return count;
		
	}
	
	  public static void main(String []args) throws FileNotFoundException, IOException{
		String pathname="E:\\trainfile\\tdmdata.txt";
		TrainDataManagement tdm=new TrainDataManagement();
		Map<String,Integer>fre=new HashMap<String,Integer>();
		int allwordscount=tdm.getallwords();//训练集的单词总数
		int allkindsword=tdm.getkindsofWord();//训练集的单词种类数
		int count1=tdm.gettrainfileCount();//训练集的类别数
		File filename=new File(pathname);
		Writer out=null;
		try {
			out=new FileWriter(filename,true);
			out.write("训练集的单词总数："+allwordscount+"\r\n\r\n"+"训练集的单词种类数："+allkindsword+
					"\r\n\r\n"+"训练集的类别数："+count1+"\r\n\r\n");
			out.close();		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String []classes=tdm.getTraningClassifications();
		for(String c:classes){
			int wordsofeveryclass=tdm.geteveryclasswords(c);//一个类别的单词总数
			int numberofdoc=tdm.gettrainfilecountofClassification(c);//一个类别的文档数
			try {
				out=new FileWriter(filename,true);
				out.write("\r\n\r\n"+c+"类别的单词总数："+wordsofeveryclass+"\r\n\r\n"+c+"类别的文档数："+numberofdoc+
						"\r\n\r\n");
				out.close();		
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String []spath=tdm.getfilePath(c);
			for(String s:spath){
				int wordsofeverydoc=tdm.allwordsofDoc(s);
				try {
				out=new FileWriter(filename,true);
				out.write(s+"文档的单词数："+wordsofeverydoc+"\r\n\r\n");
				out.close();		
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		}
			for(String c:classes){
		  System.out.println(tdm.gettrainfilecountofClassification(c));
			}
			String words[]=tdm.convertmap();//convertmap
			System.out.println(words.length);
			for(String w:words){
				System.out.println(w);
			}
		}
}
