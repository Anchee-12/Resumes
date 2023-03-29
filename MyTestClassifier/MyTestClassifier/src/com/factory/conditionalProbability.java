package com.factory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.huaban.analysis.jieba.JiebaSegmenter;

import java.util.Map.Entry;
/**
 * 伯努利
 */
/**
* <b>类</b>条件概率计算
*
* <h3>类条件概率</h3>
* P(x<sub>j</sub>|c<sub>j</sub>)=( N(X=x<sub>i</sub>, C=c<sub>j
* </sub>)+1 ) <b>/</b> ( N(C=c<sub>j</sub>)+M+V ) <br>
* 其中，N(X=x<sub>i</sub>, C=c<sub>j</sub>）表示类别c<sub>j</sub>中包含属性x<sub>
* i</sub>的训练文本数量；N(C=c<sub>j</sub>)表示类别c<sub>j</sub>中的训练文本数量；M值用于避免
* N(X=x<sub>i</sub>, C=c<sub>j</sub>）过小所引发的问题；V表示类别的总数。
*
* <h3>条件概率</h3>
* <b>定义</b> 设A, B是两个事件，且P(A)>0 称<br>
* <tt>P(B∣A)=P(AB)/P(A)</tt><br>
* 为在条件A下发生的条件事件B发生的条件概率。

*/
public class conditionalProbability {
	private static TrainDataManagement tdm=new TrainDataManagement();
	public static double zoomfactor=100.0f;
	//private static final float A=0f;
	
	/*
	 * 计算类条件概率，X为关键字，C为分类类别，返回给定条件下的类条件概率
	 */
	/*public static float calculateconditionProb(String X,String c){
		float result=0f;
		float Nxc=tdm.getcountcontainkeyofClassification(c, X);//返回给定分类中包含关键字/词的训练文本的数目
		float Nc=tdm.gettrainfilecountofClassification(c);//返回给定分类下的训练文本数目
		float v=tdm.getTraningClassifications().length;//类别总数
		//result=(Nxc+1)/(Nc+A+v);//包含这个词的文档数/类别总数
		result=(Nxc+1)/(Nc+A+v);
		return result;
	}*/
	
	/*
	 * 计算类条件概率
	 */
	public double calculateProb(String []X,String c) throws FileNotFoundException, IOException{
		conditionalProbability ccp=new conditionalProbability();
		int d=ccp.calculatefenmu(X);//得到文本的长度
		Map<String,Integer>wordflag= tdm.getwordsfrequent();
		Map<String,Double>probability=new HashMap<String,Double>();
		List<Double>resultyes=new ArrayList<Double>();
		List<Double>resultno=new ArrayList<Double>();
		//double result1[]=new double[wordfre.size()];
		//double result2[]=new double[wordfre.size()];
		double result=0.0f;
		double middleret=1.0f;
		double ret=0.0f;
		String words[]=tdm.convertmap();
		wordflag=tdm.setnewMap(X);
		float Nc=tdm.gettrainfilecountofClassification(c);//返回训练文本集中在给定分类下的训练文本数目
		//float v=tdm.getTraningClassifications().length;//类别总数
		Iterator<Entry<String,Integer>> iter = wordflag.entrySet().iterator();
		while(iter.hasNext()){
		   Entry<String,Integer> entry = iter.next();
		   String key = entry.getKey();
		   float Nxc=tdm.getcountcontainkeyofClassification(c, key);
		   int flag=entry.getValue();
		   if(flag==1){//这个特征词出现过
			   result=(Nxc+1)/(Nc+2);
			   resultyes.add(result);
			   //System.out.println(result);
		   }
		   else{
			   result=1-((Nxc+1)/(Nc+2));	
			   resultyes.add(result);
			   //System.out.println(result);
		   }
		}
		Double []result1=(Double[])resultyes.toArray(new Double[resultyes.size()]);
		for(int j=0;j<result1.length;j++){
			//System.out.println(result1[j]);
			ret=ret+Math.log(result1[j]);
			//System.out.println("相乘结果："+middleret);
		}
		/*for(int i=0;i<X.length;i++){
			String Xi=X[i];
			for(String w:words){
				String word=tdm.Isexist(w, Xi);
				float Nxc=tdm.getcountcontainkeyofClassification(c, word);
				if(Xi.equals(word)){
					result=(Nxc+1)/(Nc+2);
					//System.out.println("有词："+result);
					resultyes.add(result);
				}
				else{
					result=1-((Nxc+1)/(Nc+2));
					//System.out.println("没词："+result);	
					resultno.add(result);
				}
			}
			String word=tdm.Isexist(Xi);
			float Nxc=tdm.getcountcontainkeyofClassification(c, word);//返回给定分类中包含关键字的训练文本的数目
			if(word.equals(Xi)){
				result=(Nxc+1)/(Nc+2);
				System.out.println("有词："+result);
				resultyes.add(result);
				/*result=(Nxc+1)/(Nc+2);
				System.out.println("有词："+result);
				probability.put(word, result);
			}
			else{
				result=1-((Nxc+1)/(Nc+2));
				System.out.println("没词："+result);	
				resultno.add(result);
				/*result=1-((Nxc+1)/(Nc+2));
				probability.put(word, result);
				System.out.println("没词："+result);
			}
		   
		}*/
		//System.out.println(resultyes.size());
		//System.out.println(resultno.size());
		/*for(int j=0;j<resultyes.size();j++){
			middleret*=resultyes.get(j);
			//System.out.println(result);
		}
		for(int j=0;j<resultno.size();j++){
			middleret*=resultno.get(j);
			//System.out.println(middleret);
		}*/
		double allresult1=ret+Math.log(priorProbability.calculatepriProbofBNL(c));
		//double allresult1=middleret*priorProbability.calculatepriProbofBNL(c);
		//System.out.println("最终结果："+allresult1);
		return (allresult1*0.001);
	}
	/*public static float calculateconditionProb(String X,String c){
		nbc=new NavieBayesClassifier();
		float result=0f;
		float Nxc=tdm.getcountcontainkeyofClassification(c, X);//返回给定分类中包含关键字/词的训练文本的数目
		float Nc=tdm.gettrainfilecountofClassification(c);//返回给定分类下的训练文本数目
		float v=tdm.getTraningClassifications().length;//类别总数
		result=(Nxc+1)/(Nc+v+A);
		return result;
		
	}*/
	public int calculatefenmu(String X[]){
		return X.length;
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
	public static void main(String []args) throws FileNotFoundException, IOException{
		NavieBayesClassifier nbc=new NavieBayesClassifier();
		conditionalProbability ccp=new conditionalProbability();
		String  text="从需求到供给,触角不断延伸";
		String result=null;
		String classes[]=tdm.getTraningClassifications();
		List<String>firstTerm=null;
		JiebaSegmenter js=new JiebaSegmenter();
		firstTerm=js.sentenceProcess(text);
		String []lastTerm=firstTerm.toArray(new String[firstTerm.size()]);
		lastTerm=ccp.deletestopWords(lastTerm);
		//for(String c:classes){
			ccp.calculateProb(lastTerm, "A1Q");
		//}
	}
}
