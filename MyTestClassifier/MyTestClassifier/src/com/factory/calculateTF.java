package com.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class calculateTF {
	public TrainDataManagement tdm =new TrainDataManagement();
	public double calcTF(String docname,String word) throws FileNotFoundException, IOException{
		double Nc=tdm.countofeveryDoc(docname, word);
		double Nac=tdm.allwordsofDoc(docname);
		double result=0.0d;
		result=Nc/Nac;
		return result;
	}
}
