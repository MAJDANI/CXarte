package com.novedia.talentmap.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.IVSkillDao;
import com.novedia.talentmap.store.impl.ToolDao;

/**
 * Test class for administration service.
 * 
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
	 * IVSkillDao.
	 */
	@Mock
	private IVSkillDao vSkillDaoMock;
	/**
	 * toolDaoMock.
	 */
	@Mock
	private ToolDao toolDaoMock;

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
	 * Message constant
	 */
	private final String DATA_ACCESS_ERROR_MESSAGE = "Data Access Failure";

	/**
	 * This method allow initialize the object.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new AdminService();
		service.setToolDao(toolDaoMock);
		service.setConceptDao(conceptDaoMock);
		service.setCategoryDao(categoryDaoMock);
		service.setvSkillDao(vSkillDaoMock);
	}

	/**
	 * This method allow to get all tool and return a list of tools.
	 */
	@Test
	public void getAllToolsReturnAListOfTools() {

		// Given
		Category category = Category.builder().id(1).name("JAVA").build();
		Concept concept = Concept.builder().id(1).category(category)
				.name("ORM").build();
		Tool tool = Tool.builder().id(1).name("Spring").concept(concept)
				.build();

		List<Tool> expectedToolsList = new ArrayList<Tool>();
		expectedToolsList.add(tool);

		// When
		Mockito.when(toolDaoMock.getAll()).thenReturn(expectedToolsList);
		List<Tool> toolsListActual = service.getAllTools();

		// Then
		Assert.assertNotNull(toolsListActual);
		Assert.assertEquals(expectedToolsList, toolsListActual);
	}

	@Test(expected = DataAccessException.class)
	public void getAllToolsThrowsDataAccessException()
			throws DataAccessException {

		// When
		Mockito.doThrow(
				new DataAccessResourceFailureException(
						DATA_ACCESS_ERROR_MESSAGE)).when(toolDaoMock).getAll();
		service.getAllTools();

	}

	@Test
	public void getAllToolsCallToolDaoOneTime() {

		// Given
		List<Tool> toolsListExpected = new ArrayList<Tool>();

		// When
		Mockito.when(toolDaoMock.getAll()).thenReturn(toolsListExpected);
		List<Tool> toolsListActual = service.getAllTools();

		// Then
		assertNotNull(toolsListActual);
		Mockito.verify(toolDaoMock, Mockito.times(1)).getAll();
	}

	@Test
	public void getAllConceptsReturnAListOfConcepts()
			throws DataAccessException {

		// Given
		Category category = Category.builder().id(1).name("JAVA").build();
		Concept concept = Concept.builder().id(1).category(category)
				.name("ORM").build();

		List<Concept> conceptListRequested = new ArrayList<Concept>();
		conceptListRequested.add(concept);

		// When
		Mockito.when(conceptDaoMock.getAll()).thenReturn(conceptListRequested);
		List<Concept> conceptsListActuel = service.getAllConcepts();

		// Then
		assertNotNull(conceptsListActuel);
		assertEquals(conceptListRequested, conceptsListActuel);
	}

	@Test(expected = DataAccessException.class)
	public void getAllConceptsThrowsDataAccessException()
			throws DataAccessException {

		// when
		Mockito.when(conceptDaoMock.getAll()).thenThrow(
				new DataAccessResourceFailureException(
						DATA_ACCESS_ERROR_MESSAGE));
		service.getAllConcepts();
	}

	@Test
	public void getAllConceptsCallConceptDaoOneTime()
			throws DataAccessException {

		// Given

		List<Concept> conceptListExpected = new ArrayList<Concept>();

		// When
		Mockito.when(conceptDaoMock.getAll()).thenReturn(conceptListExpected);
		List<Concept> conceptsListActual = service.getAllConcepts();

		// Then
		Mockito.verify(conceptDaoMock, Mockito.times(1)).getAll();
	}

	@Test
	public void getAllCategoriesReturnAListOfCategories()
			throws DataAccessException {

		// Given
		Category category = Category.builder().id(1).name("JAVA").build();
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
				new DataAccessResourceFailureException(
						DATA_ACCESS_ERROR_MESSAGE)).when(categoryDaoMock)
				.getAll();
		service.getAllCategories();
	}

	@Test
	public void getAllCategoriesCallCategoryDaoOneTime() {

		// Given
		List<Category> categoryListExpected = new ArrayList<Category>();

		// When
		Mockito.when(categoryDaoMock.getAll()).thenReturn(categoryListExpected);
		List<Category> categoryListActual = service.getAllCategories();

		// Then
		Mockito.verify(categoryDaoMock, Mockito.times(1)).getAll();
	}

	@Test
	public void saveCategoryWhenCategoryNotNull() {

		// Given
		Category category = Category.builder().id(1).name("JAVA").build();

		// When
		Mockito.when(categoryDaoMock.save(category)).thenReturn(1);
		// Then

	}

	@Test(expected = DataAccessException.class)
	public void saveToolThrowsDataAccessException() throws DataAccessException {
		VSkill vSkill = new VSkill();
		// When
		Mockito.doThrow(
				new DataAccessResourceFailureException(
						DATA_ACCESS_ERROR_MESSAGE)).when(toolDaoMock)
				.save(Mockito.any(Tool.class));
		service.saveTool(vSkill);
	}

	@Test
	public void saveToolCreateNewToolWhenToolIsNull() {

		// Given
		Integer toolId = 1;
		VSkill vSkill = VSkill.builder().categoryName("JAVA")
				.conceptName("ORM").toolName("HIBERNATE").build();

		// When
		Mockito.when(toolDaoMock.save(Mockito.any(Tool.class))).thenReturn(
				toolId);
		Tool tool = service.saveTool(vSkill);

		// Then
		assertNotNull(tool);
		Mockito.verify(toolDaoMock, Mockito.times(1)).save(
				Mockito.any(Tool.class));
	}

	@Test
	public void saveToolCreateNewToolWhenToolNotNull() {

		// Given
		VSkill vSkill = VSkill.builder().categoryName("JAVA")
				.conceptName("ORM").toolName("HIBERNATE").build();
		// When
		Mockito.when(vSkillDaoMock.getSkillByTool(Mockito.anyString()))
				.thenReturn(vSkill);
		Tool toolActual = service.saveTool(vSkill);

		// Then
		assertNotNull(toolActual);
	}

	@Test
	public void updateOneSkillDoaSaveIfCategoryNotNull() {

		// Given
		Category category = Category.builder().id(1).name("JAVA").build();
		service.setMapNotification(new HashMap<String, Object>());

		// When
		Mockito.when(categoryDaoMock.save(Mockito.any(Category.class)))
				.thenReturn(0);
		service.updateASkill(category, null, null);

		// Then
		Mockito.verify(categoryDaoMock, Mockito.times(1)).save(category);
	}

	@Test
	public void updateOneSkillIfConceptNotNull() {
		// Given
		Category category = Category.builder().id(1).name("JAVA").build();
		Concept concept = Concept.builder().id(1).category(category)
				.name("ORM").build();
		Tool tool = Tool.builder().id(1).name("Spring").concept(concept)
				.build();

		service.setMapNotification(new HashMap<String, Object>());

		// When
		Mockito.when(categoryDaoMock.save(Mockito.any(Category.class)))
				.thenReturn(0);
		Mockito.when(conceptDaoMock.save(Mockito.any(Concept.class)))
				.thenReturn(0);
		service.updateASkill(category, concept, tool);

		// Then
		// Mockito.verify(conceptDaoMock, Mockito.times(1)).save(concept);
		Mockito.verify(conceptDaoMock, Mockito.times(1)).save(
				Mockito.any(Concept.class));

	}

	@Test
	public void updateOneSkillIfToolNotNull() {

		// Given
		Category category = Category.builder().id(1).name("JAVA").build();
		Concept concept = Concept.builder().id(1).category(category)
				.name("ORM").build();
		Tool tool = Tool.builder().id(1).concept(concept).name("Spring")
				.build();

		service.setMapNotification(new HashMap<String, Object>());

		// When
		Mockito.when(categoryDaoMock.save(Mockito.any(Category.class)))
				.thenReturn(0);
		Mockito.when(conceptDaoMock.save(Mockito.any(Concept.class)))
				.thenReturn(0);
		Mockito.when(toolDaoMock.save(Mockito.any(Tool.class))).thenReturn(0);
		service.updateASkill(category, concept, tool);

		// Then
		Mockito.verify(toolDaoMock, Mockito.times(1)).save(
				Mockito.any(Tool.class));
	}

	@Test(expected = DataAccessException.class)
	public void updateOneSkillThrowsDataAccessExceptionForCategory()
			throws DataAccessException {

		// Given
		Category category = Category.builder().id(1).name("JAVA").build();
		Concept concept = Concept.builder().id(1).category(category)
				.name("ORM").build();
		Tool tool = Tool.builder().id(1).concept(concept).name("Spring")
				.build();

		// When
		Mockito.when(categoryDaoMock.save(Mockito.any(Category.class)))
				.thenThrow(
						new DataAccessResourceFailureException(
								DATA_ACCESS_ERROR_MESSAGE));

		// Then
		service.updateASkill(category, concept, tool);

	}

	@Test(expected = DataAccessException.class)
	public void updateOneSkillThrowsDataAccessExceptionForConcept()
			throws DataAccessException {
		// Given
		Category category = Category.builder().id(1).name("JAVA").build();
		Concept concept = Concept.builder().id(1).category(category)
				.name("ORM").build();
		Tool tool = Tool.builder().id(1).concept(concept).name("Spring")
				.build();
		// When
		Mockito.when(conceptDaoMock.save(Mockito.any(Concept.class)))
				.thenThrow(
						new DataAccessResourceFailureException(
								DATA_ACCESS_ERROR_MESSAGE));

		// Then
		service.updateASkill(category, concept, tool);

	}

	@Test(expected = DataAccessException.class)
	public void updateOneSkillThrowsDataAccessExceptionForTool()
			throws DataAccessException {
		// Given
		Category category = Category.builder().id(1).name("JAVA").build();
		Concept concept = Concept.builder().id(1).category(category)
				.name("ORM").build();
		Tool tool = Tool.builder().id(1).concept(concept).name("Spring")
				.build();

		// When
		Mockito.when(toolDaoMock.save(Mockito.any(Tool.class))).thenThrow(
				new DataAccessResourceFailureException(
						DATA_ACCESS_ERROR_MESSAGE));

		// Then
		service.updateASkill(category, concept, tool);
	}

	@Test
	public void deleteCategoryCallsDelete() {
		// Given
		Integer categoryId = 1;
		service.setMapNotification(new HashMap<String, Object>());

		// When
		Mockito.when(categoryDaoMock.delete(Mockito.any(Category.class)))
				.thenReturn(1);
		service.deleteCategory(categoryId);

		// Then
		Mockito.verify(categoryDaoMock, Mockito.times(1)).delete(
				Mockito.any(Category.class));
	}

	@Test(expected = DataAccessException.class)
	public void deleteCategoryThrowsDataAccessException()
			throws DataAccessException {
		// Given
		Integer categoryId = 1;

		// When
		Mockito.when(categoryDaoMock.delete(Mockito.any(Category.class)))
				.thenThrow(
						new DataAccessResourceFailureException(
								DATA_ACCESS_ERROR_MESSAGE));
		// Then
		service.deleteCategory(categoryId);
	}

	@Test
	public void deleteConceptCallsDelete() {
		// Given
		Integer conceptId = 1;
		service.setMapNotification(new HashMap<String, Object>());

		// When
		Mockito.when(conceptDaoMock.delete(Mockito.any(Concept.class)))
				.thenReturn(1);
		service.deleteConcept(conceptId);

		// Then
		Mockito.verify(conceptDaoMock, Mockito.times(1)).delete(
				Mockito.any(Concept.class));
	}

	@Test(expected = DataAccessException.class)
	public void deleteConceptThrowsDataAccessException()
			throws DataAccessException {
		// Given
		Integer conceptId = 1;

		// When
		Mockito.when(conceptDaoMock.delete(Mockito.any(Concept.class)))
				.thenThrow(
						new DataAccessResourceFailureException(
								DATA_ACCESS_ERROR_MESSAGE));
		// Then
		service.deleteConcept(conceptId);
	}

	@Test
	public void deleteToolCallsDelete() {
		// Given
		Integer toolId = 1;
		service.setMapNotification(new HashMap<String, Object>());

		// When
		Mockito.when(toolDaoMock.delete(Mockito.any(Tool.class))).thenReturn(1);
		service.deleteTool(toolId);

		// Then
		Mockito.verify(toolDaoMock, Mockito.times(1)).delete(
				Mockito.any(Tool.class));
	}

	@Test(expected = DataAccessException.class)
	public void deleteToolThrowsDataAccessException()
			throws DataAccessException {
		// Given
		Integer toolId = 1;
		// When
		Mockito.when(toolDaoMock.delete(Mockito.any(Tool.class))).thenThrow(
				new DataAccessResourceFailureException(
						DATA_ACCESS_ERROR_MESSAGE));
		// Then
		service.deleteTool(toolId);
	}
}