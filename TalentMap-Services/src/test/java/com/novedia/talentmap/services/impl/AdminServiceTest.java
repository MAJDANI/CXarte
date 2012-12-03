package com.novedia.talentmap.services.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.runners.VerboseMockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.IVSkillDao;
import com.novedia.talentmap.store.impl.ConceptDao;

/**
 * Test class for administration service.
 * @author v.dibi
 * @author v.guillemain
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
/**
 * class AdminService.
 */
private AdminService service;
/**
 *  IVSkillDao.
 */
@Mock
private IVSkillDao vSkillDaoMock;
/**
 * toolDaoMock.
 */
@Mock
private IDao<Tool> toolDaoMock;

/**
 * conceptDaoMock.
 */
@Mock
private IDao<Concept> conceptDaoMock;

/**
 * categoryDaoMock.
 */
@Mock
private IDao<Category> categoryDaoMock;

/**
 * This method allow initialize the object.
 * @throws Exception
 */
@Before
public void setUp() throws Exception {
service = new AdminService();
service.setToolDao(toolDaoMock);
service.setConceptDao(conceptDaoMock);
service.setCategoryDao(categoryDaoMock);
Concept concept = Concept.Builder.builder().build();
concept.setConcept_id(1);
service.setConcept(concept );
service.setvSkillDao(vSkillDaoMock);
}
/**
 * This method allow to get all tool and return a list of tools.
 */
	@Test
	public void getAllToolsReturnAListOfTools() {

		// Given
		Tool tool1 = new Tool();
		Concept concept = Concept.Builder.builder().build();
		concept.setConcept_id(1);
		tool1.setConcept(concept);
		tool1.setId(1);
		tool1.setName("Spring");

		List<Tool> expectedToolsList = new ArrayList<Tool>();
		expectedToolsList.add(tool1);

		// When
		Mockito.when(toolDaoMock.getAll()).thenReturn(expectedToolsList);
		List<Tool> tools = service.getAllTools();

		// Then
		Assert.assertNotNull(tools);
		Assert.assertEquals(expectedToolsList, tools);
	}

	@Test(expected = DataAccessException.class)
	public void getAllToolsThrowsDataAccessException()
			throws DataAccessException {

		// When
		Mockito.doThrow(
				new DataAccessResourceFailureException("Resource failure"))
				.when(toolDaoMock).getAll();
		service.getAllTools();

	}

	@Test
	public void getAllToolsCallToolDaoOneTime() {

		// Given
		Tool tool1 = Tool.Builder.builder().build();
		
		Concept concept = Concept.Builder.builder().build();
		concept.setConcept_id(1);
		
		tool1.setConcept(concept);
		tool1.setId(1);
		tool1.setName("Spring");

		List<Tool> expectedToolsList = new ArrayList<Tool>();
		expectedToolsList.add(tool1);

		// When
		Mockito.when(toolDaoMock.getAll()).thenReturn(expectedToolsList);
		List<Tool> tools = service.getAllTools();

		// Then
		Mockito.verify(toolDaoMock, Mockito.times(1)).getAll();
	}

	@Test
	public void getAllConceptsReturnAListOfConcepts()
			throws DataAccessException {

		// Given
		Category category = Category.Builder.builder().build();
		category.setId(1);

		Concept concept = Concept.Builder.builder().build();
		concept.setConcept_id(1);
		concept.setCategory(category);
		concept.setName("ORM");
		
		List<Concept> conceptListRequested = new ArrayList<Concept>();
		conceptListRequested.add(concept);

		// When
		Mockito.when(conceptDaoMock.getAll()).thenReturn(conceptListRequested);
		List<Concept> conceptsListActuel = service.getAllConcepts();

		// Then
		assertNotNull(conceptsListActuel);
		assertSame(conceptListRequested, conceptsListActuel);
	}

	@Test(expected = DataAccessException.class)
	public void getAllConceptsThrowsDataAccessException() throws DataAccessException{

		// when
		Mockito.when(conceptDaoMock.getAll()).thenThrow(
				new DataAccessResourceFailureException("Resource failure"));
		service.getAllConcepts();

	}

	@Test
	public void getAllConceptsCallConceptDaoOneTime()
			throws DataAccessException {

		// Given
		Category category = Category.Builder.builder().build();
		category.setId(1);

		Concept concept = Concept.Builder.builder().build();
		concept.setConcept_id(1);
		concept.setCategory(category);
		concept.setName("ORM");

		List<Concept> conceptListRequested = new ArrayList<Concept>();
		conceptListRequested.add(concept);

		// When
		Mockito.when(conceptDaoMock.getAll()).thenReturn(conceptListRequested);
		List<Concept> conceptsListActuel = service.getAllConcepts();

		// Then
		Mockito.verify(conceptDaoMock, Mockito.times(1)).getAll();
	}

	@Test
	public void getAllCategoriesReturnAListOfCategories()
			throws DataAccessException {

		// Given
		Category category = Category.Builder.builder().build();
		category.setId(1);
		category.setName("JAVA");

		List<Category> categoryListExpected = new ArrayList<Category>();
		categoryListExpected.add(category);

		// When
		Mockito.when(categoryDaoMock.getAll()).thenReturn(categoryListExpected);
		List<Category> categoryListActual = service.getAllCategories();

		// Then
		assertNotNull(categoryListActual);
		assertEquals(categoryListExpected, categoryListActual);
	}

	@Test(expected = DataAccessException.class)
	public void getAllCategoriesThrowsDataAccessException()
			throws DataAccessException {

		// When
		Mockito.doThrow(
				new DataAccessResourceFailureException("Resource failure"))
				.when(categoryDaoMock).getAll();
		service.getAllCategories();
	}

	@Test
	public void getAllCategoriesCallCategoryDaoOneTime(){
		// Given
		Category category = Category.Builder.builder().build();
		category.setId(1);
		category.setName("JAVA");

		List<Category> categoryListExpected = new ArrayList<Category>();
		categoryListExpected.add(category);

		// When
		Mockito.when(categoryDaoMock.getAll()).thenReturn(categoryListExpected);
		List<Category> categoryListActual = service.getAllCategories();

		// Then
		Mockito.verify(categoryDaoMock, Mockito.times(1)).getAll();
	}
	
	@Test(expected = DataAccessException.class)
	public void saveCategoryThrowsDataAccessException() throws DataAccessException {
		VSkill vSkill =new VSkill("category_name", "concept_name",	"tool_name");
		//When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(categoryDaoMock).save(Mockito.any(Category.class));
		service.saveCategory(vSkill);
	}

	@Test
	public void saveCategoryWhenCategoryIsNull(){

		// Given
		Integer categoryId = 1;

		VSkill vSkill = new VSkill("category_name", "concept_name",
				"tool_name");

		// When
		Mockito.when(categoryDaoMock.check(Mockito.anyString())).thenReturn(
				null);
		Mockito.when(categoryDaoMock.save(Mockito.any(Category.class)))
				.thenReturn(categoryId);
		service.saveCategory(vSkill);

		// Then
		Mockito.verify(categoryDaoMock, Mockito.times(1)).save(
				Mockito.any(Category.class));
	}

	@Ignore
	public void saveCategoryWhenCategoryNotNull(){

		// Given
		Category category = Category.Builder.builder().build();
		category.setId(1);
		category.setName("JAVA");

		// When
		Mockito.when(categoryDaoMock.check(Mockito.anyString())).thenReturn(category);
		// Then
	}
	
	@Test(expected = DataAccessException.class)
	public void saveToolThrowsDataAccessException()throws DataAccessException{
		VSkill vSkill = new VSkill();
		//When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(toolDaoMock).save(Mockito.any(Tool.class));
		service.saveTool(vSkill);
	}
	
	@Test
	public void saveToolCreateNewToolWhenToolIsNull(){

		// Given
		Integer toolId = 1;
		VSkill vSkill = new VSkill("category_name", "concept_name",	"tool_name");

		// When
		Mockito.when(toolDaoMock.save(Mockito.any(Tool.class))).thenReturn(toolId);
		Tool tool = service.saveTool(vSkill);

		// Then
		assertNotNull(tool);
		assertEquals(toolId, tool.getId());
		
		Mockito.verify(toolDaoMock, Mockito.times(1)).save(Mockito.any(Tool.class));
	}
	
	@Test
	public void saveToolCreateNewToolWhenToolNotNull() {
		
		//Given
		VSkill vSkill = new VSkill("category_name", "concept_name",	"tool_name");
		
		//When
		Mockito.when(vSkillDaoMock.getSkillByTool(Mockito.anyString())).thenReturn(vSkill);
		Tool tool = service.saveTool(vSkill);
		
		//Then
		assertNotNull(tool);		
	}
	
	@Test
	public void saveConceptCallsConceptDaoWhenCategoryNotNullAndConceptNull() {
		//Given
		Integer conceptId = 1;
		VSkill vSkill = new VSkill("category_name", "concept_name",	"tool_name");
		
		Category category = Category.Builder.builder().build();
		category.setId(1);

		Concept concept = Concept.Builder.builder().build();
		concept.setConcept_id(1);
		concept.setCategory(category);
		concept.setName("ORM");

		service.setCategory(new Category());
		
		//When
		Mockito.when(conceptDaoMock.check(Mockito.anyString())).thenReturn(null);
		Mockito.when(conceptDaoMock.save(Mockito.any(Concept.class))).thenReturn(conceptId);
		service.saveConcept(vSkill);
		
		//Then
		Mockito.verify(conceptDaoMock, Mockito.times(1)).save(Mockito.any(Concept.class));

	}
	
	@Test
	public void saveConceptCallsConceptDaoWhenCategoryNotNullAndConceptNotNull() {
		//Given
		Integer conceptId = 1;
		VSkill vSkill = new VSkill("category_name", "concept_name",	"tool_name");
		
		Category category = Category.Builder.builder().build();
		category.setId(1);

		Concept concept = Concept.Builder.builder().build();
		concept.setConcept_id(1);
		concept.setCategory(category);
		concept.setName("ORM");

		service.setCategory(new Category());
		
		//When
		Mockito.when(conceptDaoMock.check(Mockito.anyString())).thenReturn(concept);
		Mockito.when(conceptDaoMock.save(Mockito.any(Concept.class))).thenReturn(conceptId);
		service.saveConcept(vSkill);
		
		//Then
		Mockito.verify(conceptDaoMock, Mockito.times(0)).save(Mockito.any(Concept.class));
	}
		
	@Test(expected = DataAccessException.class)
	public void saveConceptThrowsDataAccessException() throws DataAccessException{
		//Given
		VSkill vSkill = new VSkill();
		service.setCategory(new Category());
		
		//When
		Mockito.when(conceptDaoMock.check(Mockito.anyString())).thenReturn(null);
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(conceptDaoMock).save(Mockito.any(Concept.class));
		service.saveConcept(vSkill);
	}
	
	@Test
	public void updateOneSkillDoSaveIfCategoryNotNull(){
		
		//Given
		Category category = Category.Builder.builder().build();
		category.setId(1);
		category.setName("JAVA");
		service.setMapNotification(new HashMap<String, Object>());

		//When
		Mockito.when(categoryDaoMock.save(Mockito.any(Category.class))).thenReturn(0);	
		service.updateASkill(category, null, null);
		
		//Then		
		Mockito.verify(categoryDaoMock, Mockito.times(1)).save(category);
	}
	
	@Test
	public void updateOneSkillIfConceptNotNull(){
		//Given
		Category category = Category.Builder.builder().build();
		category.setId(1);
		category.setName("JAVA");

		Concept concept = Concept.Builder.builder().build();
		concept.setConcept_id(1);
		concept.setCategory(category);
		concept.setName("ORM");

		service.setMapNotification(new HashMap<String, Object>());
		
		//When
		Mockito.when(categoryDaoMock.save(Mockito.any(Category.class))).thenReturn(0);	
		Mockito.when(conceptDaoMock.save(Mockito.any(Concept.class))).thenReturn(0);	
		service.updateASkill(category, concept, null);
		
		//Then		
		//Mockito.verify(conceptDaoMock, Mockito.times(1)).save(concept);
		Mockito.verify(conceptDaoMock, Mockito.times(1)).save(Mockito.any(Concept.class));
			
	}
	
	@Test
	public void updateOneSkillIfToolNotNull(){
		//Given
		Category category = Category.Builder.builder().build();
		category.setId(1);
		category.setName("JAVA");

		Concept concept = Concept.Builder.builder().build();
		concept.setConcept_id(1);
		concept.setCategory(category);
		concept.setName("ORM");

		Tool tool = Tool.Builder.builder().build();
		tool.setId(1);
		tool.setConcept(concept);
		tool.setName("Spring");
		
		service.setMapNotification(new HashMap<String, Object>());
		
		//When
		Mockito.when(categoryDaoMock.save(Mockito.any(Category.class))).thenReturn(0);	
		Mockito.when(conceptDaoMock.save(Mockito.any(Concept.class))).thenReturn(0);	
		Mockito.when(toolDaoMock.save(Mockito.any(Tool.class))).thenReturn(0);	
		service.updateASkill(category, concept, tool);
				
		//Then		
		Mockito.verify(toolDaoMock, Mockito.times(1)).save(Mockito.any(Tool.class));
	}
	
	@Test(expected=DataAccessException.class)
	public void updateOneSkillThrowsDataAccessExceptionForCategory() throws DataAccessException{
		//Given
		Category category = Category.Builder.builder().build();
		category.setId(1);
		category.setName("JAVA");

		Concept concept = Concept.Builder.builder().build();
		concept.setConcept_id(1);
		concept.setCategory(category);
		concept.setName("ORM");

		Tool tool = Tool.Builder.builder().build();
		tool.setId(1);
		tool.setConcept(concept);
		tool.setName("Spring");
		
		//When
		Mockito.when(categoryDaoMock.save(Mockito.any(Category.class))).thenThrow(new DataAccessResourceFailureException("Access Failure"));
		
		//Then
		service.updateASkill(category, concept, tool);
		
	}

	@Test(expected=DataAccessException.class)
	public void updateOneSkillThrowsDataAccessExceptionForConcept() throws DataAccessException {
		//Given
		Category category = Category.Builder.builder().build();
		category.setId(1);
		category.setName("JAVA");

		Concept concept = Concept.Builder.builder().build();
		concept.setConcept_id(1);
		concept.setCategory(category);
		concept.setName("ORM");

		Tool tool = Tool.Builder.builder().build();
		tool.setId(1);
		tool.setConcept(concept);
		tool.setName("Spring");
		
		//When
		Mockito.when(conceptDaoMock.save(Mockito.any(Concept.class))).thenThrow(new DataAccessResourceFailureException("Access Failure"));
		
		//Then
		service.updateASkill(category, concept, tool);
		
	}
	
	@Test(expected=DataAccessException.class)
	public void updateOneSkillThrowsDataAccessExceptionForTool() throws DataAccessException {
		//Given
		Category category = Category.Builder.builder().build();
		category.setId(1);
		category.setName("JAVA");

		Concept concept = Concept.Builder.builder().build();
		concept.setConcept_id(1);
		concept.setCategory(category);
		concept.setName("ORM");

		Tool tool = Tool.Builder.builder().build();
		tool.setId(1);
		tool.setConcept(concept);
		tool.setName("Spring");
		
			
		//When
		Mockito.when(toolDaoMock.save(Mockito.any(Tool.class))).thenThrow(new DataAccessResourceFailureException("Access Failure"));
		
		//Then
		service.updateASkill(category, concept, tool);
		
	}
	
	@Test
	public void deleteCategoryCallsDelete() {
		//Given
		Integer categoryId = 1;
		service.setMapNotification(new HashMap<String, Object>());
		
		//When
		Mockito.when(categoryDaoMock.delete(Mockito.any(Category.class))).thenReturn(1);
		service.deleteCategory(categoryId);
		
		//Then
		Mockito.verify(categoryDaoMock, Mockito.times(1)).delete(Mockito.any(Category.class));
	}
	
	@Test(expected=DataAccessException.class)
	public void deleteCategoryThrowsDataAccessException() throws DataAccessException {
		//Given
		Integer categoryId = 1;
		
		//When
		Mockito.when(categoryDaoMock.delete(Mockito.any(Category.class))).thenThrow(new DataAccessResourceFailureException("Access Failure"));
		//Then
		service.deleteCategory(categoryId);		
	}
		
	@Test
	public void deleteConceptCallsDelete() {
		//Given
		Integer conceptId = 1;
		service.setMapNotification(new HashMap<String, Object>());
		
		//When
		Mockito.when(conceptDaoMock.delete(Mockito.any(Concept.class))).thenReturn(1);
		service.deleteConcept(conceptId);
		
		//Then
		Mockito.verify(conceptDaoMock, Mockito.times(1)).delete(Mockito.any(Concept.class));
	}
	
	@Test(expected=DataAccessException.class)
	public void deleteConceptThrowsDataAccessException() throws DataAccessException {
		//Given
		Integer conceptId = 1;
		
		//When
		Mockito.when(conceptDaoMock.delete(Mockito.any(Concept.class))).thenThrow(new DataAccessResourceFailureException("Access Failure"));
		//Then
		service.deleteConcept(conceptId);		
	}
	
	@Test
	public void deleteToolCallsDelete() {
		//Given
		Integer toolId = 1;
		service.setMapNotification(new HashMap<String, Object>());
		
		//When
		Mockito.when(toolDaoMock.delete(Mockito.any(Tool.class))).thenReturn(1);
		service.deleteTool(toolId);
		
		//Then
		Mockito.verify(toolDaoMock, Mockito.times(1)).delete(Mockito.any(Tool.class));
	}
	
	@Test(expected=DataAccessException.class)
	public void deleteToolThrowsDataAccessException() throws DataAccessException {
		//Given
		Integer toolId = 1;		
		//When
		Mockito.when(toolDaoMock.delete(Mockito.any(Tool.class))).thenThrow(new DataAccessResourceFailureException("Access Failure"));
		//Then
		service.deleteTool(toolId);	
	}
	
//	@Test
//	public void buildCategory(){
//		//Given
//		Category category = new Category();
//		category.setId(1);
//		category.setName("categoryName");
//		
//		//When
//		
//		//Then
//		
//	}
}