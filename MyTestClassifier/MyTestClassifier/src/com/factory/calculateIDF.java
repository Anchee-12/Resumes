package com.factory;

public class calculateIDF {
	public TrainDataManagement tdm =new TrainDataManagement();
	public double calcIDF(String words){
		String []classes=tdm.getTraningClassifications();
		double Nc=tdm.gettrainfileCount();
		double Nac=0.0d;
		for(String classification:classes){
			Nac+=tdm.getcountcontainkeyofClassification(classification,words);}
		double result=0.0d;
		result=Math.log((Nc/(Nac+1)));
		return result;
	}
}
