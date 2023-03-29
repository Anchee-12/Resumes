package com.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class calculateTFIDF {
	public calculateTF ct=new calculateTF();
	public calculateIDF ci=new calculateIDF();
	public double calcTFIDF(String docname,String word) throws FileNotFoundException, IOException{
		double Nc=ct.calcTF(docname, word);
		double Nac=ci.calcIDF(word);
		double result=0.0d;
		result=Nc*Nac;
		return result;
	}
}
