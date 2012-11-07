/**
 * 
 */
package com.novedia.talentmap.services;

import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.List;

public abstract class ScoreManage {

	private final static Double TOOL_PONDERATION = 3.0;
	private final static Double FREQUENCY_USE_PONDERATION = 1.0;
	private final static Double NO_USING_TIME_PONDERATION = 5.0;

	public static double ConceptScore(List<Integer> listToolScore, double nbTool) {
		
//		for (double toolScore : listToolScore) {
//			System.out.println("Tool Score : "+ toolScore);
//		}
//		
//		System.out.println("Nombre de tools connus : "+listToolScore.size());
//		System.out.println("Nombre de tools en tout : "+ nbTool);
		
		DecimalFormat df = new DecimalFormat();
		double resultToolScore = 0;
		
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setDecimalSeparatorAlwaysShown(true);
		
		double toolKnown = listToolScore.size() / nbTool;
		
		for (double toolScore : listToolScore) {
			resultToolScore += toolScore;
		}
		
		String strScore = df.format(resultToolScore * toolKnown);
		
		double result = Double.parseDouble(strScore.replace(",", "."));
		
		//On ramène sur 5
		
		return result/nbTool; 
	}

	public static double ToolScore(double toolScore, double frequencyUseScore, double noUsingTimeScore) {
		
		int j = 4;
		int noUsingTimeInverse = 0;
		
		for(int i = 1; i<5; i++){
			if(i == noUsingTimeScore){
				noUsingTimeInverse = j; 
			}
			
			j--;
		}
		
		return Math
				.round((TOOL_PONDERATION * toolScore
						+ FREQUENCY_USE_PONDERATION * frequencyUseScore + NO_USING_TIME_PONDERATION
						* noUsingTimeInverse)
						/ (TOOL_PONDERATION + FREQUENCY_USE_PONDERATION + NO_USING_TIME_PONDERATION));
	}
}
