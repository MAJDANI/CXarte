package com.novedia.talentmap.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;

	/**
	 * Class abstract.
	 * @author v.dibi
	 */
	public abstract class ScoreManage {
	/**
	 * TOOL_PONDERATION.
	 */
	private static final Double TOOL_PONDERATION = 3.0;
	/**
	 * FREQUENCY_USE_PONDERATION.
	 */
	private static final Double FREQUENCY_USE_PONDERATION = 1.0;
	/**
	 * NO_USING_TIME_PONDERATION.
	 */
	private static final Double NO_USING_TIME_PONDERATION = 5.0;
	
	
	//private static Logger logger = Logger.getLogger(ScoreManage.class); 
	
	/**
	 * This method make statistics.
	 * @param scoreT a list toolScore
	 * @param nbTool a number of tool
	 * @return scocre of concept
	 */
	
	/*
	public static double conceptScore(final List<Integer> scoreT,final double nbTool) {
	
		DecimalFormat df = new DecimalFormat();
		double resultToolScore = 0;
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		//df.setDecimalSeparatorAlwaysShown(true);
		
		double toolKnown = scoreT.size() / nbTool;
		
		for (double toolScore : scoreT) {
			resultToolScore += toolScore;
		}
		
		String strScore = df.format(resultToolScore * toolKnown);
		
		double result = Double.parseDouble(strScore.replace(",", "."));
		
		return  result / scoreT.size();
		
	}
	*/
	
	
	
	/**
	 * 
	 * @param mapCategory : map contenant toutes les compétences regroupé par categories d'un collaborateur 
	 * @param nbToolKnow : nombre d'outil connu par un collaborateur
	 * @param nbAllTool : le nombre total d'outils
	 * @return mapCategory : contenant la note de chaque concept
	 */
	public static Map<Category, Map> scoreConcept(Map<Category, Map> mapCategory,Integer nbToolKnow,Integer nbAllTool){
		
		double scoreConcept = 0;
		double toolKnow = nbToolKnow / (nbAllTool*1.0);
		if(mapCategory != null){
			for(Entry<Category, Map> category : mapCategory.entrySet()) {
				//logger.debug("categorie : " +category.getKey().getName());
				Map<Concept, Map> allConcept = category.getValue(); // tous les concepts de la categorie
				 for (Entry<Concept, Map> mapConcept : allConcept.entrySet()) {
					 Map<Tool, Skill> mapTools = mapConcept.getValue();
					 Integer sum = sumAverageToolOfConcept(mapTools);
					 scoreConcept = (sum * toolKnow) / mapTools.size();
					 scoreConcept =  Math.round(scoreConcept);
					 mapConcept.getKey().setScore(scoreConcept);
					 allConcept.put(mapConcept.getKey(), mapConcept.getValue());
					 //logger.debug("concept : " + mapConcept.getKey().getName() + "  note : "+(somme * toolKnow) / tools.size() +" arrondi : " +Math.round(scoreConcept));
				 }
				 mapCategory.put(category.getKey(), allConcept);
				 //logger.debug("**********************************");
			}
		}
		
		return mapCategory;
	}
	
	/**
	 * 
	 * @param tools
	 * @return
	 */
	public static Integer sumAverageToolOfConcept(Map<Tool, Skill> mapTools){
		Integer sum = 0;
		if (mapTools != null) {
			for (Entry<Tool, Skill> tool : mapTools.entrySet()) {
				sum += tool.getValue().getAverageScore();
			 }			
		}
		return sum;
	}
	
	
	
	
	/**
	 * @param toolS tool Score.
	 * @param fUScore a frequently use score
	 * @param noUsingTimeScore a noUsingTimeScore
	 * @return a score from a Tool
	 */
	public static double toolScore(final double toolS, final double fUScore,final double noUsingTimeScore) {
	
		int j = 4;
		int noUsingTimeInverse = 0;
		
		for (int i = 1; i < 5; i++) {
			if (i == noUsingTimeScore) {
				noUsingTimeInverse = j;
			}
			j--;
		}
		
		return Math.round((TOOL_PONDERATION * toolS
		+ FREQUENCY_USE_PONDERATION * fUScore 
		+ NO_USING_TIME_PONDERATION
		* noUsingTimeInverse)
		/ (TOOL_PONDERATION + FREQUENCY_USE_PONDERATION + NO_USING_TIME_PONDERATION));
		}
}