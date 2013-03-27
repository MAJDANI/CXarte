package com.novedia.talentmap.services.impl;


/**
 * 
 * @author b.tiomofofou
 *
 */

public class ScoreManageTest {
	
	/**
	 * 
	 * @throws Exception
	 */
//	@Test
//	public void averageSumOfToolReturnInteger() throws Exception {
//		
//		//Given
//		Tool tool1 = Tool.builder().id(1).name("tool_name1").build();
//		Tool tool2 = Tool.builder().id(2).name("tool_name2").build();
//		Integer note1 = 2;
//		Integer note2 = 3;
//		Skill skill1 = Skill.builder().averageScore(note1).build();
//		Skill skill2 = Skill.builder().averageScore(note2).build();
//		Map<Tool, Skill> mapTool = new HashMap<Tool, Skill>();
//		
//		mapTool.put(tool1, skill1);
//		mapTool.put(tool2, skill2);
//		
//		Integer sommeExpected = 5;
//		
//		//When
//		Integer resultSumAverageOfTool = ScoreManage.sumAverageToolConcept(mapTool);
//		
//		//Then
//		Assert.assertEquals(sommeExpected, resultSumAverageOfTool);
//	}
	
	/**
	 * 
	 * @throws Exception
	 */
//	@Test
//	public void scoreConceptReturnOneMap() throws Exception {
//		
//		//Given
//		Integer nbToolKnown = 5;
//		Integer nbAllTools = 10;
//		//Le calcul computeConceptScore() a été modifié (on ne tiens plus compte de nbAllTools)
//		//afin de pouvoir obtenir des notes de concept supérieures à 0
//		// cette modification fait qu'on n'attend plus 1.0 mais 2.0 pour concept 1 et
//		// au lieu de 3.0 on obtient 5.0 pour le concept 2
//		//A revoir quand le calcul de la note aura été revu
////		Double expectedScoreConcept1 = 1.0;
////		Double expectedScoreConcept2 = 3.0;
//		Double expectedScoreConcept1 = 2.0;
//		Double expectedScoreConcept2 = 5.0;
//		
//		Category category = Category.builder().id(1).name("JAVA").build();
//		//création concept 1
//		Concept concept1 = Concept.builder().id(1).name("IOC").score(0.0).category(category).build();
//		Tool tool1 = Tool.builder().id(1).name("Spring").concept(concept1).build();
//		Tool tool2 = Tool.builder().id(2).name("Castor").concept(concept1).build();
//		
//		//création du concept 2
//		Concept concept2 = Concept.builder().id(2).name("ORM").score(0.0).category(category).build();
//		Tool tool3 = Tool.builder().id(3).name("Hibernate").concept(concept2).build();
//		Tool tool4 = Tool.builder().id(4).name("Ibatis").concept(concept2).build();
//		// Création de la map des outils du concept 1
//		Map<Tool, Skill> mapTool1 = new HashMap<Tool, Skill>();
//		Integer note1 = 2;
//		Integer note2 = 3;
//		Skill skill1 = Skill.builder().averageScore(note1).build();
//		Skill skill2 = Skill.builder().averageScore(note2).build();
//		mapTool1.put(tool1, skill1);
//		mapTool1.put(tool2, skill2);
//		
//		// Création de la map des outils du concept 2
//		Map<Tool, Skill> mapTool2 = new HashMap<Tool, Skill>();
//		Integer note3 = 5;
//		Integer note4 = 5;
//		Skill skill3 = Skill.builder().averageScore(note3).build();
//		Skill skill4 = Skill.builder().averageScore(note4).build();
//		mapTool2.put(tool3, skill3);
//		mapTool2.put(tool4, skill4);
//		
//		//Création de la map des concepts
//		Map<Concept, Map> givenConceptMap = new HashMap<Concept, Map>();
//		givenConceptMap.put(concept1, mapTool1);
//		givenConceptMap.put(concept2, mapTool2);
//		
//		//Création de la map des catégories
//		Map<Category, Map> givenMapCateg = new HashMap<Category, Map>();
//		givenMapCateg.put(category, givenConceptMap);
//		
//		//When
//		givenMapCateg = ScoreManage.computeConceptScore(givenMapCateg, nbToolKnown, nbAllTools);
//		Map<Concept, Map> resultMapconcept =  givenMapCateg.get(category);
//		Set<Concept> setConcepts = resultMapconcept.keySet();
//		int count = 0;
//		for (Concept actualConcept : setConcepts) {
//			Double actualScore = actualConcept.getScore();
//			switch (count) {
//				case 0:
//				{
//					Assert.assertEquals(expectedScoreConcept2, actualScore);
//					break;
//				}
//				
//				case 1:
//				{
//					Assert.assertEquals(expectedScoreConcept1, actualScore);
//					break;
//				}
//				
//			}
//			count++;
//			
//		}
//		
//		//Then
//		//Assert.assertThat(givenmapCateg,);
//		
//	}
	

}
