/**
 * 
 */
package com.novedia.talentmap.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

import com.novedia.talentmap.model.dto.EAEForSynthesis;
import com.novedia.talentmap.model.dto.EntityUtil;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.EAE;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.store.impl.EAEDao;

/**
 * EAE service test {@link EAEService}
 * 
 * @author v.guillemain
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class EAEServiceTest {

    private EAEService service;

    @Mock
    private EAEDao eaeDaoMock;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	service = new EAEService();
	service.setEaeDao(eaeDaoMock);
    }

    @Test
    public void getEAEByIdReturnsAnEae() {
	// Given
	Integer eaeId = 4;
	EAE eaeRequested = EntityUtil.createEAE(4, 65, 62, "2012-05-25", 1);

	// When
	Mockito.when(eaeDaoMock.get(eaeId)).thenReturn(eaeRequested);
	EAE eaeActual = service.getEAEById(eaeId);

	// Then
	Mockito.verify(eaeDaoMock, Mockito.times(1)).get(eaeId);
	Assert.assertNotNull(eaeActual);
	Assert.assertSame(eaeRequested, eaeActual);

    }

    @Test
    public void addEAECallsDao() {
	// Given
	EAE eaeAdded = EntityUtil.createEAE(4, 65, 62, "2012-05-25", 1);

	// When
	Mockito.when(eaeDaoMock.add(Mockito.any(EAE.class))).thenReturn(1);
	service.addEAE(eaeAdded);

	// Then
	Mockito.verify(eaeDaoMock, Mockito.times(1))
		.add(Mockito.any(EAE.class));
    }

    @Test
    public void saveEAECallsDao() {
	// Given
	EAE eaeSaved = EntityUtil.createEAE(4, 65, 62, "2012-05-25", 1);

	// When
	Mockito.when(eaeDaoMock.save(Mockito.any(EAE.class))).thenReturn(1);
	service.saveEAE(eaeSaved);

	// Then
	Mockito.verify(eaeDaoMock, Mockito.times(1)).save(
		Mockito.any(EAE.class));
    }

    @Test
    public void deleteEAECallsDao() {
	// Given
	EAE eaeDeleted = EntityUtil.createEAE(4, 65, 62, "2012-05-25", 1);

	// When
	Mockito.when(eaeDaoMock.delete(Mockito.any(EAE.class))).thenReturn(1);
	service.deleteEAE(eaeDeleted);

	// Then
	Mockito.verify(eaeDaoMock, Mockito.times(1)).delete(
		Mockito.any(EAE.class));
    }

    @Test
    public void getOngoingEAEForCM() {
	// Given
	List<EAEForSynthesis> onGoingEaeExpected = new ArrayList<EAEForSynthesis>();
	EAEForSynthesis ef1 = EntityUtil.createEAEForSynthesis(1, "2012-05-25",
		"DUPOND", "Michel", 1, "OPEN");
	EAEForSynthesis ef2 = EntityUtil.createEAEForSynthesis(2, "2010-09-25",
		"DUROC", "Sophie", 1, "OPEN");
	onGoingEaeExpected.add(ef1);
	onGoingEaeExpected.add(ef2);
	Integer idManager = 62;

	// When
	Mockito.when(eaeDaoMock.getOngoingEAEForCM(idManager)).thenReturn(
		onGoingEaeExpected);
	service.getOngoingEAEForCM(idManager);

	// Then
	Mockito.verify(eaeDaoMock, Mockito.times(1)).getOngoingEAEForCM(
		Mockito.any(Integer.class));
    }

    @Test
    public void getCollabWithoutOngoingEAEForManager() {
	// Given
	List<Colleague> NotOnGoingCollabExpected = new ArrayList<Colleague>();
	
	Integer managerId = 62;
	Colleague collaborator1 = Colleague.builder().managerId(managerId)
		.build();
	Colleague collaborator2 = Colleague.builder().managerId(managerId)
		.build();
	
	NotOnGoingCollabExpected.add(collaborator1);
	NotOnGoingCollabExpected.add(collaborator2);

	// When
	Mockito.when(eaeDaoMock.getCollabWithoutOngoingEAEForManager(managerId)).thenReturn(
		NotOnGoingCollabExpected);
	service.getCollabWithoutOngoingEAEForManager(managerId);

	// Then
	Mockito.verify(eaeDaoMock, Mockito.times(1)).getCollabWithoutOngoingEAEForManager(
		Mockito.any(Integer.class));
    }

    @Test
    public void getHistoryEAEForCollab() {
	// Given
	List<EAEForSynthesis> historyEaeExpected = new ArrayList<EAEForSynthesis>();
	EAEForSynthesis ef1 = EntityUtil.createEAEForSynthesis(1, "2012-05-25",
		"DUPOND", "Michel", 1, "OPEN");
	EAEForSynthesis ef2 = EntityUtil.createEAEForSynthesis(2, "2010-09-25",
		"DUROC", "Sophie", 1, "OPEN");
	historyEaeExpected.add(ef1);
	historyEaeExpected.add(ef2);
	Integer idCollab = 63;

	// When
	Mockito.when(eaeDaoMock.getHistoryEAEForCollab(idCollab)).thenReturn(
		historyEaeExpected);
	service.getHistoryEAEForCollab(idCollab);

	// Then
	Mockito.verify(eaeDaoMock, Mockito.times(1)).getHistoryEAEForCollab(
		Mockito.any(Integer.class));
	
    }

}
