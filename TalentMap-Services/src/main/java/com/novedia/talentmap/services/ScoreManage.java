package com.novedia.talentmap.services;

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
	
	
	private static Logger LOGGER = Logger.getLogger(ScoreManage.class); 
	
		
	/**
	 * 
	 * @param mapCategory : map of category 
	 * @param nbToolKnow : number of tools known
	 * @param nbAllTool : total number of tools
	 * @return mapCategory : map of category with score of each concept
	 */
	public static Map<Category, Map> computeConceptScore(Map<Category, Map> mapCategory,Integer nbToolKnow,Integer nbAllTool){
		
		double scoreConcept = 0;
		double toolKnow = nbToolKnow / (nbAllTool*1.0);
		LOGGER.debug("Compute concept score for all catégories");
		if(mapCategory != null){
			for(Entry<Category, Map> category : mapCategory.entrySet()) {
				Map<Concept, Map> allConcept = category.getValue(); // tous les concepts de la categorie
				 for (Entry<Concept, Map> mapConcept : allConcept.entrySet()) {
					 Map<Tool, Skill> mapTools = mapConcept.getValue();
					 Integer sum = sumAverageToolConcept(mapTools);
					 scoreConcept = (sum * toolKnow) / mapTools.size();
					 scoreConcept =  Math.round(scoreConcept);
					 mapConcept.getKey().setScore(scoreConcept);
					 allConcept.put(mapConcept.getKey(), mapConcept.getValue());
				 }
				 mapCategory.put(category.getKey(), allConcept);
			}
		}
		
		return mapCategory;
	}
	
	
	/**
	 * 
	 * compute sum of average tool's concept
	 * @param mapTools map of tools and skill
	 * @return sum of average tool's concept
	 */
	public static Integer sumAverageToolConcept(Map<Tool, Skill> mapTools){
		Integer sum = 0;
		if (mapTools != null) {
			for (Entry<Tool, Skill> tool : mapTools.entrySet()) {
				sum += tool.getValue().getAverageScore();
			 }			
		}
		return sum;
	}
	
	
	
	
	/**
	 * 
	 * compute average's tool
	 * @param toolNote score of tool
	 * @param usingFrequencyTool using frequency tool
	 * @param timeNotUsingTool time not using tool
	 * @return average's tool
	 */
	public static double computeToolAverage(final double toolNote, final double usingFrequencyTool,final double timeNotUsingTool) {
	
		int j = 4;
		int noUsingTimeInverse = 0;
		for (int i = 1; i < 5; i++) {
			if (i == timeNotUsingTool) {
				noUsingTimeInverse = j;
			}
			j--;
		}
		
		return Math.round((TOOL_PONDERATION * toolNote
		+ FREQUENCY_USE_PONDERATION * usingFrequencyTool 
		+ NO_USING_TIME_PONDERATION
		* noUsingTimeInverse)
		/ (TOOL_PONDERATION + FREQUENCY_USE_PONDERATION + NO_USING_TIME_PONDERATION));
		}
}