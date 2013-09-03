package com.novedia.talentmap.web.ui.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.ISkillService;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.ui.Tree;

/**
 * Manager service test {@link SearchTarget}
 * 
 * @author v.guillemain
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchTargetTest {

	private SearchTarget searchTarget;

	/**
	 * For services concerning Skills, SearchTarget calls an ISkillService. This
	 * object is mocked here.
	 */
	@Mock
	private ISkillService skillServiceMock;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		searchTarget = new SearchTarget();
		searchTarget.setSkillService(skillServiceMock);
	}

	@Test
	public void getListColleagueForTooIdCheckedReturnsAListEmpty()
			throws Exception {
		// --------------
		// Given
		// --------------
		// Données
		String categoryName = "CONCEPTION";
		List<Integer> expectedColleagueList = new ArrayList<Integer>();
		// Les id des tool appartenant à la catégorie CONCEPTION
		List<Integer> listToolIdForCategory = new ArrayList<Integer>();
		listToolIdForCategory.add(51);
		listToolIdForCategory.add(52);
		listToolIdForCategory.add(53);

		// Construire l'arbre des compétences
		Tree treeSkills = searchTarget.buildTreeSkills();
		searchTarget.setTreeSkills(treeSkills);

		// Faire une sélection sur l'arbre des compétences
		// "CONCEPTION" doit ne renvoyer aucun résultat
		Collection<Object> lesItemId = (Collection<Object>) treeSkills
				.getContainerDataSource().getItemIds();
		Boucle: for (Object item : lesItemId) {
			if (item instanceof Category) {
				Category cat = (Category) item;
				if (categoryName == cat.getName()) {
					// Ce select est inefficace, l'objet semble ne pas être
					// identifié, et donc aucune sélection n'est faite
					treeSkills.select(item);
					break Boucle;
				}
			}
		}

		// --------------
		// When
		// --------------
		Mockito.when(
				skillServiceMock
						.getAllColleagueIdByListToolId(listToolIdForCategory))
				.thenReturn(expectedColleagueList);

		List<Integer> listColleagueIdResult = new ArrayList<Integer>();
		Boolean atLeastOneIsSelected = searchTarget
				.parseList(listColleagueIdResult);
		// --------------
		// Then
		// --------------
		Assert.assertTrue(listColleagueIdResult.isEmpty());

	}

	@Ignore
	private List<Integer> buildListToolIdForConcept1() {
		List<Integer> listToolIdForConcept = new ArrayList<Integer>();
		listToolIdForConcept.add(24);
		listToolIdForConcept.add(25);
		listToolIdForConcept.add(26);
		listToolIdForConcept.add(27);
		listToolIdForConcept.add(28);
		listToolIdForConcept.add(29);
		listToolIdForConcept.add(30);
		return listToolIdForConcept;
	}

	@Ignore
	private List<Integer> buildExpectedColleagueList1() {
		List<Integer> expectedColleagueList = new ArrayList<Integer>();
		expectedColleagueList.add(15);
		expectedColleagueList.add(16);
		expectedColleagueList.add(17);
		expectedColleagueList.add(18);
		expectedColleagueList.add(19);
		expectedColleagueList.add(20);
		expectedColleagueList.add(23);
		expectedColleagueList.add(24);
		expectedColleagueList.add(27);
		expectedColleagueList.add(28);
		expectedColleagueList.add(29);
		expectedColleagueList.add(30);
		expectedColleagueList.add(35);
		expectedColleagueList.add(36);
		return expectedColleagueList;
	}

	@Ignore
	private List<VSkill> buildVSkills() {
		VSkill v1 = new VSkill(6, "CONCEPTION", 12, "CONCEPTION", 52, "UML");
		VSkill v2 = new VSkill(6, "CONCEPTION", 12, "CONCEPTION", 53, "Merise");
		VSkill v3 = new VSkill(6, "CONCEPTION", 12, "CONCEPTION", 51, "ALM");
		VSkill v4 = new VSkill(3, "PHP", 6, "IOC", 30, "WACT");
		VSkill v5 = new VSkill(3, "PHP", 6, "IOC", 29, "ClawPHP");
		VSkill v6 = new VSkill(3, "PHP", 6, "IOC", 28, "Seasar");
		VSkill v7 = new VSkill(3, "PHP", 6, "IOC", 27, "Phemto");
		VSkill v8 = new VSkill(3, "PHP", 6, "IOC", 26, "Solar");
		VSkill v9 = new VSkill(3, "PHP", 6, "IOC", 25, "Garden");
		VSkill v10 = new VSkill(3, "PHP", 6, "IOC", 24, "Drip");
		VSkill v11 = new VSkill(3, "PHP", 5, "ORM", 23, "JDAO");
		VSkill v12 = new VSkill(3, "PHP", 5, "ORM", 22, "PHPMyObject");
		VSkill v13 = new VSkill(3, "PHP", 5, "ORM", 21, "Propel");
		VSkill v14 = new VSkill(3, "PHP", 5, "ORM", 20, "Doctrine");
		List<VSkill> list = new ArrayList<VSkill>();
		list.add(v1);
		list.add(v2);
		list.add(v3);
		list.add(v4);
		list.add(v5);
		list.add(v6);
		list.add(v7);
		list.add(v8);
		list.add(v9);
		list.add(v10);
		list.add(v11);
		list.add(v12);
		list.add(v13);
		list.add(v14);
		return list;
	}

	@Ignore
	public void getListColleagueForTooIdCheckedReturnsAListNotEmpty()
			throws Exception {

		// --------------
		// Given
		// --------------
		// Données
		Integer conceptId = 6;
		// Les id des tool appartenant au concept "IOC" (idConcept = 6)(category
		// PHP)
		List<Integer> listToolIdForConcept = buildListToolIdForConcept1();

		// Construire l'arbre des compétences et le setter à notre searchTarget
		// qui va s'en servir
		List<VSkill> listExpected = buildVSkills();
		Mockito.when(skillServiceMock.getAllVSkillOrdered()).thenReturn(
				listExpected);
		Tree treeSkills = searchTarget.buildTreeSkills();

		// Faire une sélection du concept "IOC" sur l'arbre des compétences
		Collection<Object> lesItemId = (Collection<Object>) treeSkills
				.getContainerDataSource().getItemIds();

		System.out.println("lesItemId.isEmpty()=" + lesItemId.isEmpty());
		Boucle: for (Object item : lesItemId) {
			if (item instanceof Concept) {
				Concept concept = (Concept) item;
				if (conceptId == concept.getId()) {
					// Ce select est inefficace, l'objet semble ne pas être
					// identifié, et donc aucune sélection n'est faite
					treeSkills.select(concept);
					// ces façons de récupérer l'item renvoient null =>
					// inefficaces
					// Container c = treeSkills.getContainerDataSource();
					// Item i = c.getItem(conceptId);
					// Item i2 = c.getItem(concept);
					// Item i3 = c.getItem(item);
					// treeSkills.select(i2);
					// treeSkills.select(i3);

					break Boucle;
				}
			}
		}
		searchTarget.setTreeSkills(treeSkills);

		// Construire le résultat attendu
		List<Integer> expectedColleagueList = buildExpectedColleagueList1();
		List<Integer> listColleagueIdResult = new ArrayList<Integer>();

		// --------------
		// When
		// --------------
		Mockito.when(
				skillServiceMock
						.getAllColleagueIdByListToolId(listToolIdForConcept))
				.thenReturn(expectedColleagueList);
		Boolean atLeastOneIsSelected = searchTarget
				.parseList(listColleagueIdResult);
		System.out.println("atLeastOneIsSelected=" + atLeastOneIsSelected);
		// --------------
		// Then
		// --------------
		Assert.assertSame(expectedColleagueList, listColleagueIdResult);

	}

}
