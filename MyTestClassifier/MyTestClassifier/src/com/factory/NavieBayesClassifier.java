package com.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.Set;

import com.huaban.analysis.jieba.JiebaSegmenter;

public class NavieBayesClassifier {
	private TrainDataManagement tdm;//训练集管理器
	private static double zoomfactor=100.0f;//缩放因子
	public JiebaSegmenter js=new JiebaSegmenter();
	public  calculateTFIDF cti=new calculateTFIDF();
	/*
	 * 默认构造器，初始化训练集
	 */
	public NavieBayesClassifier(){
		System.out.println("开始构造分类器！（此时贝叶斯分类器已经开始分类）");
		tdm=new TrainDataManagement();
	}
	
	
	/*
	 * 计算文本属性向量X在分类c中的后验概率
	 */
	/*public float calcProd(String []X,String c){
		conditionalProbability ccp=new conditionalProbability();
		TrainDataManagement tdm=new TrainDataManagement();
		float result[]=new float[X.length];
		///float result=1.0f;
		float result1=0.0f;
		//float allresult=1.0f;
		int d=ccp.calculatefenmu(X);
		for(int i=0;i<X.length;i++){
			String Xi=X[i];
			//结果过小，在连乘之前放大十倍
			float Nxc=tdm.getcountcontainkeyofClassification(c, X[i]);//返回给定分类中包含关键字/词的训练文本的数目
			float Nc=tdm.gettrainfilecountofClassification(c);//返回给定分类下的训练文本数目
			float v=tdm.getTraningClassifications().length;
			result[i]=(Nxc+1)/(Nc+v);
			//result*=conditionalProbability.calculateconditionProb(Xi, c)*zoomfactor;//每个词在这个类别的类条件概率
			//result*=conditionalProbability.calculateconditionProb(Xi, c);
		}
		for(int j=0;j<result.length;j++){
			result1=(float) (result1+Math.log(result[j]));
		}
		//float ret1=(float) Math.log(result);
		float allresult=(float) (result1*priorProbability.calculatepriProb(c));
		//float allresult=(float) Math.log(result);
		return allresult;
	}*/
	
	/*
	 * 去掉停用词
	 */
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
		return newwords;
	}
	
	public int getnewwordsLength(String []oldwords){
		return deletestopWords(oldwords).length;
	}
	/*
	 * 进行文本分类
	 */
	@SuppressWarnings("unchecked")
	public String classify(String text) throws FileNotFoundException, IOException{
		File filename=new File("E:\\trainfile\\classification.txt");
		int i=0;
		calculateProbability cp=new calculateProbability();
		conditionalProbability ccp=new conditionalProbability();
		double TFIDF=0.0d;
		List<String>firstTerm=null;
		Map<String,Double>secondTerm=new HashMap<String,Double>();
		Map<String,Map<String,Double>>secondTerm2=new HashMap<String,Map<String,Double>>();
		Map<String,Integer>secondTerm3=new HashMap<String,Integer>();
		firstTerm=js.sentenceProcess(text);
		String []lastTerm=firstTerm.toArray(new String[firstTerm.size()]);
		lastTerm=deletestopWords(lastTerm);
		String []classes=tdm.getTraningClassifications();//获得已有的分类名
		for(String c:classes){
			String []doclist=tdm.getfilePath(c);//获得类别中的文档
			for(String doc:doclist){
				for(String word:lastTerm){
					TFIDF=cti.calcTFIDF(doc, word);
					if(TFIDF>0){
						secondTerm.put(word, TFIDF);
						//System.out.println(word+":"+TFIDF);
					}
				}
				secondTerm2.put(doc, secondTerm);
			}
		}
		Set<String>document=secondTerm2.keySet();
		for(String d:document){
			Map<String,Double>m=secondTerm2.get(d);
			Set<String>Word=m.keySet();
			for(String w:Word){
				secondTerm3.put(w, secondTerm3.containsKey(w) ? secondTerm3.get(w) + 1 : 1);
			}
		}
		Iterator<Entry<String, Integer>> iter = secondTerm3.entrySet().iterator();
		while(iter.hasNext()){
		   Entry<String, Integer> entry = iter.next();
		   String key = entry.getKey();
		   lastTerm[i]=key;
		   i++;
		}
		double probability=0.0d;
		List<classificationResult>cr=new ArrayList<classificationResult>();//分类结果存储集合
		for(int j=0;j<classes.length;j++){
			String cj=classes[j];
			//计算给定的文本属性向量lastTerm在给定的分类Cj中的分类中的概率
			probability=cp.calcProd(lastTerm, cj);//多项式
			//probability=ccp.calculateProb(lastTerm, cj);//伯努利
			classificationResult CR=new classificationResult();//保存分类结果
			CR.classification=cj;
			CR.probability=probability;
			System.out.println("---------------分类进行中---------------");
			System.out.println();
			String cl="";
			if(cj.equals("XW0IT")){cl="IT新闻";}
			else if(cj.equals("XW1体育")){cl="体育新闻";}
			else if(cj.equals("XW2健康")){cl="健康新闻";}
			else if(cj.equals("XW3教育")){cl="教育新闻";}
			else if(cj.equals("XW4文化")){cl="文化新闻";}
			else if(cj.equals("XW5旅游")){cl="旅游新闻";}
			else if(cj.equals("XW6汽车")){cl="汽车新闻";}
			else if(cj.equals("XW7财经")){cl="财经新闻";}
			else if(cj.equals("XW8军事")){cl="军事新闻";}
			else if(cj.equals("XW9环保")){cl="环保新闻";}
			else{cl="党建新闻";}
			System.out.println("要分类文本在"+"["+cl+"（"+cj + "）"+"]"+"中的准确率为"+"：" + probability);
			
			Writer out=null;
			try {
				out=new FileWriter(filename,true);
				//out.write("\r\n\r\n"+cl+"\r\n\r\n");
				out.write("\r\n"+"要分类文本在"+"["+cl+"（"+cj + "）"+"]"+"中的准确率为"+"：" + probability+"\r\n");
				out.close();		
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
			cr.add(CR);
		}
		java.util.Collections.sort(cr,new Comparator(){
			public int compare(final Object o1,final Object o2) 
			{
				final classificationResult m1 = (classificationResult) o1;
				final classificationResult m2 = (classificationResult) o2;
				final double ret = m1.probability - m2.probability;
				if (ret < 0) 
				{
					return 1;
				} 
				else 
				{
					return -1;
				}
			}
		});
		return cr.get(0).classification;//返回概率的最大值
	}
	/*public static void main(String[] args) 
	{
		//String text = "市电教馆陈平馆长做了开班讲话。他首先与大家一起回顾了南京市智慧校园建设的背景，他表示这次培训的目的是为了拓宽视野、增进交流，增强大家的紧迫感，更好地进行智慧校园的规划和设计工作。第一，他认为智慧校园建设重在教育应用，而不是重装备建设。建设的终极目标是为应用服务的，是为提升教师和学生的信息素养，提高学校的办学效益而服务的。他希望每一所学校，更多地在谋划如何充分利用现有的条件，开展技术与教育教学、与教学管理相融合的研究和实践。无论是省里的标准，还是市里的标准，都是侧重应用，侧重在全面提升信息化应用水平，提升教师发展、学生发展和学校发展的这个层面上。希望每个试点学校都能充分理解，智慧校园建设的终极目标是为人的发展提供服务的。第二，这次培训共有5天，前3天是大家共同交流，分享建设的经验和成果，是一次思维的碰撞，希望通过这样的碰撞，能激发我们的建设与应用的大智慧。每一个校长和主任都要汲取其他学校教育信息化应用和智慧校园创建中的一些思想火花，回到学校以后开展切实可行的研究与实践。后2天将组织大家到外地参观考察，希望在学习其他地";
		//System.out.println("构造贝叶斯分类器开始------------------------------------------------------------");
		String text="我们国家的传统文化应该被保护";
		NavieBayesClassifier classifier = new NavieBayesClassifier();//构造Bayes分类器
		String result=classifier.classify(text);//进行分类
		System.out.println("此项属于["+result+"]");
		/*String pathname = "E:\\TrainningSet\\1.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
        File filename = new File(pathname); // 要读取以上路径的input。txt文件  
        InputStreamReader reader=null;
        FileInputStream fis = null;
        BufferedReader br = null;
        String str=null;
        String line = null; 
		try {
			fis=new FileInputStream(filename); 
			reader = new InputStreamReader(fis);
		     br = new BufferedReader(reader);
			while ((line=br.readLine()) != null) {  
	            str+=line;
	        }  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 建立一个输入流对象reader  
         // 建立一个对象，它把文件内容转成计算机能读懂的语言  
     catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
			     br.close();
			     fis.close();
			     reader.close();
			    // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
			   } catch (IOException e) {
			    e.printStackTrace();
			   }
		} 
        String result=null;
  
        
        BayesClassifier classifier = new BayesClassifier();//构造Bayes分类器
		result = classifier.classify(str);//进行分类
		System.out.println(str);
		System.out.println("准备分类...");
        System.out.println("需要分类的文本属于："+result);
	}*/
}
