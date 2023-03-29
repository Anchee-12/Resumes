package com.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class classificationTest {
	private static NavieBayesClassifier nbc;
	private static TrainDataManagement tdm;
	private static TimeDifference tdf;
	public static void main(String []args) throws FileNotFoundException, IOException{
		String  pathname="E:\\TrainningSet\\环保.txt";
		String  pathname1="今年我们国家的汽车卖的很好";
		tdm=new TrainDataManagement();
		InputStreamReader iReader =new InputStreamReader(new FileInputStream(pathname),"UTF-8");
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
		String strall=sb.toString();
		tdf=new TimeDifference();
		String result=null;
		nbc=new NavieBayesClassifier();
		long starttime=System.currentTimeMillis();
		System.out.print("开始时间是：");
		tdf.calculateTime(starttime);
		result = nbc.classify(strall);//进行分类
		//System.out.println(strall);
		System.out.println("---------------分类结果如下---------------");
		System.out.println();
        System.out.println(pathname+"文本属于："+result);
        long endtime=System.currentTimeMillis();
        System.out.print("结束时间是：");
		tdf.calculateTime(endtime);
        long ms=endtime-starttime;
        System.out.print("本轮分类所花费的时间是：");
        tdf.calculateTime(ms);
       
	}
}
