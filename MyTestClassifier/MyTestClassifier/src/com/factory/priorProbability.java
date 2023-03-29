package com.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
* 先验概率计算
* <h3>先验概率计算</h3>

* P(c<sub>j</sub>)=N(C=c<sub>j</sub>)<b>/</b>N <br>

* 其中，N(C=c<sub>j</sub>)表示类别c<sub>j</sub>中的训练文本数量；

* N表示训练文本集总数量。
* 先验概率P(c)的计算

P(c)的意思是：在所有的文档中，类别为c的文档出现的概率有多大？
假设训练数据中一共有Ndoc篇文档，只要数一下类别c的文档有多少个就能计算p(c)了，
类别c的文档共有Nc篇

*/
public class priorProbability {
	private static TrainDataManagement tdm=new  TrainDataManagement();
	
	/*
	 * 伯努利先验概率
	 * c为分类
	 */
	public static double calculatepriProbofBNL(String c){
		float result=0f;
		float Nc=tdm.gettrainfilecountofClassification(c);//得到这个类别下训练文本的总数
		float Ndoc=tdm.gettrainfileCount();//得到全部的训练文本总数
		result=Nc/Ndoc;//返回每个类别的训练文本占全部文本数的概率
		return result;
	}
	
	/*
	 * 多项式先验概率
	 */
	public static double calculatepriProb(String c) throws FileNotFoundException, IOException{
		double result=0d;
		double Nc=tdm.gettotalwordofClassification(c);//得到类别c下的单词总数
		double Ndoc=tdm.getallwords();//得到全部的训练文本中单词总数
		result=Nc/Ndoc;//返回每个类别的训练文本占全部文本数的概率
		return result;
	}

	/*public static void main(String []args){
		priorProbability pp=new priorProbability();
		double Nc=calculatepriProb("XW5文化");
		System.out.println(Nc);
	}*/
}
