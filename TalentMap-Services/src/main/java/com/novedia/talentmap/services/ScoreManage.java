package com.novedia.talentmap.services;


	/**
	 * Class abstract.
	 * @author v.dibi
	 */
	public abstract class ScoreManage {
	/**
	 * TOOL_PONDERATION = 3.0.
	 */
//	private static final Double TOOL_PONDERATION = 5.0;
	/**
	 * FREQUENCY_USE_PONDERATION = 1.0.
	 */
//	private static final Double FREQUENCY_USE_PONDERATION = 3.0;
	/**
	 * NO_USING_TIME_PONDERATION = 5.0.
	 */
//	private static final Double NO_USING_TIME_PONDERATION = 1.0;
	
	
//	private static Logger LOGGER = Logger.getLogger(ScoreManage.class); 
	
	
	/**
	 * TODO : revoir le calcul
	 * @param mapCategory : map of category 
	 * @param nbToolKnow : number of tools known
	 * @param nbAllTool : total number of tools
	 * @return mapCategory : map of category with score of each concept
	 */
//	public static Map<Category, Map> computeConceptScore(Map<Category, Map> mapCategory,Integer nbToolKnow,Integer nbAllTool){
//		
//		double scoreConcept = 0;
//		double toolKnow = nbToolKnow / (nbAllTool*1.0);
//		LOGGER.debug("Compute concept score for all catégories");
//		if(mapCategory != null){
//			for(Entry<Category, Map> category : mapCategory.entrySet()) {
//				Map<Concept, Map> allConcept = category.getValue(); // tous les concepts de la categorie
//				 for (Entry<Concept, Map> mapConcept : allConcept.entrySet()) {
//					 Map<Tool, Skill> mapTools = mapConcept.getValue();
//					 int id =mapConcept.getKey().getId();
//					 skillService.getAllToolsByConcept(id);
//					 Integer sum = sumAverageToolConcept(mapTools);
//					 scoreConcept = sum / mapTools.size();
//					 scoreConcept =  Math.round(scoreConcept);
//					 mapConcept.getKey().setScore(scoreConcept);
//					 allConcept.put(mapConcept.getKey(), mapConcept.getValue());
//				 }
//				 mapCategory.put(category.getKey(), allConcept);
//			}
//		}
//		
//		return mapCategory;
//	}
	
	
	/**
	 * 
	 * compute sum of average tool's concept
	 * @param mapTools map of tools and skill
	 * @return sum of average tool's concept
	 */
//	public static Integer sumAverageToolConcept(Map<Tool, Skill> mapTools){
//		Integer sum = 0;
//		if (mapTools != null) {
//			for (Entry<Tool, Skill> tool : mapTools.entrySet()) {
//				sum += tool.getValue().getAverageScore();
//			 }			
//		}
//		return sum;
//	}
	
	
	
	
	/**
	 * 
	 * compute average's tool
	 * @param toolNote score of tool
	 * @param usingFrequencyTool using frequency tool
	 * @param timeNotUsingTool time not using tool
	 * @return average's tool
	 */
//	public static double computeToolAverage(final double toolNote, final double usingFrequencyTool,final double timeNotUsingTool) {
	
//		int j = 4;
//		int noUsingTimeInverse = 0;
//		for (int i = 1; i < 5; i++) {
//			if (i == timeNotUsingTool) {
//				noUsingTimeInverse = j;
//			}
//			j--;
//		}
		//double noUsingTimeInverse = 5 - timeNotUsingTool;
//		double noUsingTimeValue = 0;
//		int intTimeNotUsingTool= (int) timeNotUsingTool;
//		switch(intTimeNotUsingTool)
//		{
//		case 1:
//			noUsingTimeValue = 5;
//			break;
//		case 2:
//			noUsingTimeValue = 3;
//			break;
//		case 3:
//			noUsingTimeValue = 1;
//			break;
//		case 4:
//			noUsingTimeValue = 0;
//			break;
//		}
//		
//		double usingFrequencyToolValue = 0;
//		int intUsingFrequencyTool= (int) usingFrequencyTool;
//		switch(intUsingFrequencyTool)
//		{
//		case 1:
//			usingFrequencyToolValue = 1;
//			break;
//		case 2:
//			usingFrequencyToolValue = 3;
//			break;
//		case 3:
//			usingFrequencyToolValue = 5;
//			break;
//		}
//		
//		return Math.round((TOOL_PONDERATION * toolNote
//		+ FREQUENCY_USE_PONDERATION * usingFrequencyToolValue
//		+ NO_USING_TIME_PONDERATION
//		* noUsingTimeValue)
//		/ (TOOL_PONDERATION + FREQUENCY_USE_PONDERATION + NO_USING_TIME_PONDERATION));
//		}
}