/**
 * 
 */
package com.novedia.talentmap.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

import com.novedia.talentmap.model.dto.CategoryMapDTO;
import com.novedia.talentmap.model.dto.ConceptMapDTO;
import com.novedia.talentmap.model.dto.ToolSkillMap;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.model.entity.VSkillCollab;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.IVSkillCollabDao;
import com.novedia.talentmap.store.IVSkillDao;
import com.novedia.talentmap.store.impl.SkillDao;

/**
 * @author v.dibi
 *
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("rawtypes")
public class SkillServiceTest {
	
	/**
	 * Static Constant ORM
	 */
	private static final String ORM = "ORM";
	
	/**
	 * Static Constant JAVA
	 */
	private static final String JAVA = "JAVA";
	

	private SkillService service;
	
	@Mock
	private SkillDao skillDaoMock;
	
	@Mock
	private IDao<Tool> toolDaoMock;
	
	@Mock
	private IVSkillCollabDao vSkillCollabDaoMock;
	
	@Mock
	private IVSkillDao vSkillDaoMock;
	
	@Mock
	private IDao<Concept> conceptDaoMock;
	
	@Mock
	private IDao<Category> categoryDaoMock;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new SkillService();
		service.setSkillDao(skillDaoMock);	
		service.setvSkillCollabDao(vSkillCollabDaoMock);
		service.setToolDao(toolDaoMock);
		service.setvSkillDao(vSkillDaoMock);
		service.setConceptDao(conceptDaoMock);
		service.setCategoryDao(categoryDaoMock);
	}

	@Test
	public void getSkillByToolIdReturnOneSkill(){
		
		//Given	
		Integer collaboratorId = 1;
		Integer toolId = 1;	
		
		Skill skillExpected = Skill.builder().tool_id(1).colleagueId(2).score(2).build();
											
		//When
		Mockito.when(skillDaoMock.getCollaboratorSkillByTool(collaboratorId, toolId)).thenReturn(skillExpected);
		Skill skillActuel = service.getSkillByToolId(collaboratorId, toolId);
		//Then
		
		assertNotNull(skillActuel);
		assertEquals(skillExpected, skillActuel);
		
		
	}
	
	@Test(expected = DataAccessException.class)
	public void getSkillByToolIdDataAccessException() throws DataAccessException{
		
		//Given	
		Integer collaboratorId = 1;
		Integer toolId = 1;	
		
		//When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(skillDaoMock).getCollaboratorSkillByTool(collaboratorId, toolId);
		service.getSkillByToolId(collaboratorId, toolId);		
	}
	
	@Test
	public void getAllSkillCollabReturnALisOfVSkillCollab(){
		
		//Given
		Integer collabId = 1;
		
		VSkillCollab vSkillCollab = new VSkillCollab();
		vSkillCollab.setCollab_id(collabId);
		vSkillCollab.setCategory_name("java");
		vSkillCollab.setTool_name("tool_name");
			
	
		List<VSkillCollab> expetedvSkillCollabList = new ArrayList<VSkillCollab>();
		expetedvSkillCollabList.add(vSkillCollab);
		
		//When
		Mockito.when(vSkillCollabDaoMock.getAllSkillCollab(collabId)).thenReturn(expetedvSkillCollabList);
		List<VSkillCollab> vSkillCollabListActuel = service.getAllSkillCollab(collabId); 
		
		//Then
		
		assertNotNull(vSkillCollabListActuel);
		assertEquals(expetedvSkillCollabList, vSkillCollabListActuel);
	}
	
	@Test(expected = DataAccessException.class)
	public void getAllSkillCollabDataAccessException() throws DataAccessException{
		
		//Given
		Integer collaboratorId = 1;

		//When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(vSkillCollabDaoMock).getAllSkillCollab(collaboratorId);
		service.getAllSkillCollab(collaboratorId);	
	}
	
	
	@Test
	public void buildCategoryMapDto(){
		//Given
		Category expectedCategory = Category.builder().id(1).name("categoryName").build();
		Concept concept = Concept.builder().id(1).name("conceptName").category(expectedCategory).build();
		ConceptMapDTO givenConceptMapDto = new ConceptMapDTO();
		CategoryMapDTO GivencategoryMapDto = new CategoryMapDTO();
		
		ToolSkillMap toolSkillMap = new ToolSkillMap(); 
		Tool tool1 = Tool.builder().id(1).concept(concept).name("toolName").build();
		Skill skill1 = Skill.builder().colleagueId(1).tool_id(1).build();
		toolSkillMap.getMapTool().put(tool1, skill1);
		
		List<Category> categories = new ArrayList<Category>();
		categories.add(expectedCategory);
		
		givenConceptMapDto.getMapConcept().put(concept, toolSkillMap);
		
		//When
		Mockito.when(categoryDaoMock.getAll()).thenReturn(categories);
		service.buildCategoryMapDto(givenConceptMapDto, GivencategoryMapDto);
		
		//Then
		Assert.assertTrue(GivencategoryMapDto.getMapCategory().containsKey(expectedCategory));
		Assert.assertTrue(GivencategoryMapDto.getMapCategory().get(expectedCategory).getMapConcept().containsKey(concept));
	}
	
	
	@Test
	public void getAllToolsReturnAListOfTools(){
		
		// Given
		Category category = Category.builder().id(1).name(JAVA).build();
		Concept concept = Concept.builder().id(1).category(category).name(ORM).build();
		Tool tool = Tool.builder().id(1).concept(concept).name("Spring").build();

		List<Tool> expectedToolsList = new ArrayList<Tool>();
		expectedToolsList.add(tool);

		// When
		Mockito.when(toolDaoMock.getAll()).thenReturn(expectedToolsList);
		List<Tool> tools = service.getAllTools();

		// Then
		Assert.assertNotNull(tools);
		Assert.assertEquals(expectedToolsList, tools);		
	}
	

	@Test(expected = DataAccessException.class)
	public void getToolByConceptDataAccessException() throws DataAccessException{
		
		//Given
		Integer collaboratorId = 1;

		//When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(skillDaoMock).getAllCollaboratorSkill(collaboratorId);
		service.getAllCollaboratorSkill(collaboratorId);
		
	}
	
	@Test
	public void getSkillByToolReturnOneVSkill(){
		
		//Given
		String toolName = "toolName";
		
		VSkill vskillExpected = new VSkill();
		vskillExpected.setToolName("tool_name");
		
		//When
		Mockito.when(vSkillDaoMock.getSkillByTool(toolName)).thenReturn(vskillExpected);
		VSkill vskillActuel = service.getSkillByTool(toolName);
				
		//Then
		Assert.assertNotNull(vskillActuel);
		Mockito.verify(vSkillDaoMock, Mockito.times(1)).getSkillByTool(toolName);
		assertSame(vskillExpected, vskillActuel);	
	}	
	
	@Test(expected =  DataAccessException.class)
	public void getSkillByToolDataAccessException() throws DataAccessException {
		
		//Given
		String toolName = "toolName";

		//When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(vSkillDaoMock).getSkillByTool(toolName);
		service.getSkillByTool(toolName);	
	}
	
	@Test
	public void addSkillOneSkillToUpdate(){
		
		//Given
		Skill skill = new Skill();
		skill.setColleagueId(1);
		skill.setScore(2);
		skill.setTool_id(1);
		
		//When
		Mockito.when(skillDaoMock.add(Mockito.any(Skill.class))).thenReturn(1);
		service.addSkill(skill);
		
		//Then 
		Mockito.verify(skillDaoMock, Mockito.times(1)).add(Mockito.any(Skill.class));		
	}	
	
	@Test(expected =  DataAccessException.class)
	public void addSkillDataAccessException() throws DataAccessException {
		
		//When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(skillDaoMock).add(Mockito.any(Skill.class));
		service.addSkill(Mockito.any(Skill.class));
	}

	@Test
	public void saveSkillOneSkillToInsertet(){
		//Given
		Skill skill = new Skill();
		skill.setColleagueId(1);
		skill.setScore(2);
		skill.setTool_id(1);
		
		//When
		Mockito.when(skillDaoMock.add(Mockito.any(Skill.class))).thenReturn(1);
		service.addSkill(skill);
		
		//Then 
		Mockito.verify(skillDaoMock, Mockito.times(1)).add(Mockito.any(Skill.class));	
	}
	
	@Test(expected =  DataAccessException.class)
	public void saveSkillDataAccessException() throws DataAccessException {
		
		//When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(skillDaoMock).add(Mockito.any(Skill.class));
		service.addSkill(Mockito.any(Skill.class));
	}
	
	@Test
	public void getConceptByIdReturnAConcept(){
		
		//Given
		Integer conceptId = 1;
		
		Category category = Category.builder().id(1).name(JAVA).build();
		Concept conceptExpected = Concept.builder().id(conceptId).category(category).name(ORM).build();
		
		List<Concept> conceptListExpected = new ArrayList<Concept>();
		conceptListExpected.add(conceptExpected);
		
		//When
		Mockito.when(conceptDaoMock.getAll()).thenReturn(conceptListExpected);
		Concept conceptActuel = service.getConceptById(conceptId);
		
		//Then
		Mockito.verify(conceptDaoMock, Mockito.times(1)).getAll();
		Assert.assertNotNull(conceptActuel);
		assertSame(conceptExpected, conceptActuel);		
	}
	
	@Test(expected = DataAccessException.class)
	public void getConceptByIdDataAccessException() throws DataAccessException {	
		
		//Given
		Integer conceptId = 1;
		
		//When 
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(conceptDaoMock).getAll();
		service.getConceptById(conceptId);
	
	}

	@Test
	public void getCategoryByIdReturnACategory(){
		//Given
		Integer categoryId = 1;
		String categoryName = JAVA;
		
		
		Category categoryExpected = Category.builder().id(categoryId).name(categoryName).build();
		
		List<Category> categoryListExpected = new ArrayList<Category>();
		categoryListExpected.add(categoryExpected);
		
		//When
		Mockito.when(categoryDaoMock.getAll()).thenReturn(categoryListExpected);
		Category categoryActuel = service.getCategoryById(categoryId);
		
		//Then
		Mockito.verify(categoryDaoMock, Mockito.times(1)).getAll();
		Assert.assertNotNull(categoryActuel);
		assertSame(categoryExpected, categoryActuel);				
	}
	
	@Test(expected = DataAccessException.class)
	public void getCategoryByIdDataAccessException() throws DataAccessException {	
		
		//Given
		Integer categoryId = 1;
		
		//When 
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(categoryDaoMock).getAll();
		service.getCategoryById(categoryId);
	}	

	
	
	@Test 
	public void buildToolATool(){		
		//Given
		Tool tool = new Tool();
		Integer tooId = 1;
				
		//When
		Mockito.when(toolDaoMock.get(tooId)).thenReturn(tool);
		tool.getId();
					
		//Then
		Assert.assertNotNull(tool);
		//Mockito.verify(toolDaoMock, Mockito.times(1)).get(tooId);		
	}
		
	@Test 
	public void buildConceptReturnAConcept(){
		//Given
	
		Tool tool = new Tool();
		List<Tool> listToolExpected =  new ArrayList<Tool>();
		listToolExpected.add(tool);
	
		Map<Concept, Map> conceptMap = new HashMap<Concept, Map>();
				
		//When
		Mockito.when(toolDaoMock.getAll()).thenReturn(listToolExpected);
		List<Tool> toolActual = service.getAllTools();
		
		//Then
		Mockito.verify(toolDaoMock, Mockito.times(1)).getAll();
		assertSame(listToolExpected, toolActual);
		Assert.assertNotNull(conceptMap);
	}
	
	
	@Test
	public void getToolByNameReturnTool(){
		
		//Given
		Category categ = Category.builder().id(1).name("category_name").build();
		Concept c = Concept.builder().id(1).score(1.0).category(categ).build();
		Tool  expectedTool = Tool.builder().concept(c).id(1).name("tool_name").build();
		
		//when
		Mockito.when(toolDaoMock.getByName("name")).thenReturn(expectedTool);
		Tool toolactuel = service.getToolByName("name");
		
		//then
		
		assertNotNull(toolactuel);
		assertSame(expectedTool, toolactuel);
	
		
	}
	
}