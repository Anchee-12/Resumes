package com.factory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import com.huaban.analysis.jieba.JiebaSegmenter;
/**
 * 多项式模型
 * @author 黄安琦
 *
 */
public class calculateProbability {
	public static double zoomfactor=100.0f;
	public static JiebaSegmenter js;
	public static TrainDataManagement tdm;
	public static String pathname="E:\\trainfile\\data.txt";
	/*
	 * 将概率写入文件
	 */
	public void writeData(String []X,String Cj) throws FileNotFoundException, IOException  {
		File filename=new File(pathname);
		Writer out=null;
		System.out.println(Cj);
		try {
			out=new FileWriter(filename,true);
			out.write("\r\n"+Cj+"\r\n\r\n");
			out.close();		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tdm=new TrainDataManagement();
		double Ntk=0.0d;
		double result=0.0d;
		int count=tdm.geteveryclasswords(Cj);//一个类别的单词总数
		//Map<String,Integer>wordFrequent=tdm.getfrequentofeveryClass(Cj);
		//String str=null;
		for(int i=0;i<X.length;i++){
			String Xi=X[i];
			Ntk=tdm.getcountwordofallClassification(Cj, Xi);//一个单词在一个类别中出现的次数
			System.out.println(Ntk);
			if(Ntk!=0){
						try{
							out=new FileWriter(filename,true);
							result=Ntk/count; 
							System.out.println(result);
							out.write(Xi+":"+result+"\r\n");
							out.close();
							}catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
							}		
						}
			else{
				try{
					out=new FileWriter(filename,true);
					out.write(Xi+":"+0.0+"\r\n");
					out.close();
					}catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}		
				}
			}
		}
	
	/*
	 * 计算概率
	 */
	public double calcProd(String[] X, String Cj) throws FileNotFoundException, IOException 
	{
		//conditionalProbability ccp=new conditionalProbability();
		tdm=new TrainDataManagement();
		//计算每个词在每个条件中的概率
		//System.out.println("calcProd开始计算给定的文本属性向量X在给定的分类Cj中的类条件概率-------------------------------------");
		double result[]=new double[X.length];
		double result1=0.0f;
		double ret=0.0f;
		//double result11=1.0d;
		float Ntk=0;//一个单词在这个类别中出现的次数
		float Nk=tdm.gettotalwordofClassification(Cj);//得到这个类别的总单词数
		float v=tdm.getkindsofWord();//得到单词种类数
		for (int i = 0; i <X.length; ++i)
		{
			String Xi = X[i];
			Ntk=tdm.getcountwordofallClassification(Cj, Xi);//得到这个类别中单词的次数
			//因为结果过小，因此在连乘之前放大10倍，这对最终结果并无影响，因为我们只是比较概率大小而已
			//ret *=ClassConditionalProbability.calculatePxc(Xi, Cj)*zoomFactor;
			ret=(Ntk+1)/(Nk+v);
			result[i]=ret*zoomfactor;
			//result[i]=(Ntk+1)/(Nk+v);
		}
		//单词的类条件概率连乘取log
		for(int j=0;j<result.length;j++){
			result1=result1+Math.log(result[j]);			
		}
		
		double allresult1=(result1+Math.log(priorProbability.calculatepriProb(Cj)));
		return (allresult1*0.001);
	}
	
	public String[] deletestopWords(String []oldwords){
		Vector<String> vs=new Vector<String>();
		for(int i=0;i<oldwords.length;++i){
			try {
				if(stopwordsChat.IsStopWord(oldwords[i])==false){
					vs.add(oldwords[i]);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String []newwords=new String[vs.size()];
		vs.toArray(newwords);
		for(String i:newwords){
			System.out.println(i);
		}
		return newwords;
	}
	/*public static void main(String[]args ){
		calculateProbability cp=new calculateProbability();
		conditionalProbability ccp=new conditionalProbability();
		TrainDataManagement tdm=new TrainDataManagement();
		String text="我们国家的传统文化应该被保护";
		List<String>firstTerm=null;
		js=new JiebaSegmenter();
		firstTerm=js.sentenceProcess(text);
		String []lastTerm=firstTerm.toArray(new String[firstTerm.size()]);
		
		lastTerm=cp.deletestopWords(lastTerm);
		//for(String s:lastTerm){
			//System.out.println(s);
		    // }
		String []classes=tdm.getTraningClassifications();//获得已有的分类名
		//int count1=tdm.getallwords(classes);
		for(String c:classes){System.out.println(c);}
		for(int j=0;j<classes.length;j++){
			double p=cp.calcProd(lastTerm,classes[j]);
			//calcProd(lastTerm,classes[j]);//
			//System.out.println(p);
		}
	}*/
	public static void main(String[]args ) throws FileNotFoundException, IOException{
		String  pathname1="E:\\TrainningSet\\4.txt";
		String  text="我们国家的传统文化应该被保护";
		File filename=new File(pathname1);
		InputStreamReader isr=null;
		FileInputStream fis=null;
		BufferedReader br=null;
		String strall=null;
		String line=null;
		try {
			fis=new FileInputStream(filename);
			isr=new InputStreamReader(fis);
			br=new BufferedReader(isr);
			while((line=br.readLine())!=null){
				strall+=line;
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
			    // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
			   } catch (IOException e) {
			    e.printStackTrace();
			   }
		}
		tdm=new TrainDataManagement();
		int i=0;
		calculateTFIDF cti=new calculateTFIDF();
		calculateProbability cp=new calculateProbability();
		String []classes=tdm.getTraningClassifications();
		//String text="我们国家的传统文化应该被保护";
		List<String>firstTerm=null;
		js=new JiebaSegmenter();
		firstTerm=js.sentenceProcess(strall);
		String []lastTerm=firstTerm.toArray(new String[firstTerm.size()]);
		double TFIDF=0.0d;
		Map<String,Double>secondTerm=new HashMap<String,Double>();
		lastTerm=cp.deletestopWords(lastTerm);
		
		Iterator<Entry<String, Double>> iter = secondTerm.entrySet().iterator();
		while(iter.hasNext()){
		   Entry<String, Double> entry = iter.next();
		   String key = entry.getKey();
		   lastTerm[i]=key;
		   i++;
		}
		for(int j=0;j<classes.length;j++){
			cp.writeData(lastTerm, classes[j]);	
		}
	}
}
