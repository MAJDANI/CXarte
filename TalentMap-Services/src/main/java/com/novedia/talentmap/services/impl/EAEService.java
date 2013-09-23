<<<<<<< HEAD
/**
 * 
 */
package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.dto.EAEColleagueResumeForCMDTO;
import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
import com.novedia.talentmap.model.dto.EAEResultsDTO;
import com.novedia.talentmap.model.dto.EAESynthesisDTO;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.EAE;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.EAEDao;

/**
 * @author v.guillemain
 * 
 */
public class EAEService implements IEAEService {

	/**
	 * The EAE DAO.
	 */
	private IDao<EAE> eaeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#getEAE(java.lang.Integer)
	 */
	@Override
	public EAE getEAEById(Integer id) {
		return eaeDao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novedia.talentmap.services.IEAEervice#addEAE(com.novedia.talentmap
	 * .model.entity.EAE)
	 */
	@Override
	public int addEAE(EAE eae) {
		return eaeDao.add(eae);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novedia.talentmap.services.IEAEervice#saveEAE(com.novedia.talentmap
	 * .model.entity.EAE)
	 */
	@Override
	public int saveEAE(EAE eae) {
		return eaeDao.save(eae);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novedia.talentmap.services.IEAEervice#deleteEAE(com.novedia.talentmap
	 * .model.entity.EAE)
	 */
	@Override
	public int deleteEAE(EAE eae) {
		return eaeDao.delete(eae);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novedia.talentmap.services.IEAEervice#getOngoingEAEForCM(java.lang
	 * .Integer)
	 */
	@Override
	@Deprecated
	public List<EAEForSynthesisDTO> getOngoingEAEForCM(Integer idManager) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		return eaeDao.getOngoingEAEForCM(idManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#
	 * getCollabWithoutOngoingEAEForManager(java.lang .Integer)
	 */
	@Override
	@Deprecated
	public List<Colleague> getCollabWithoutOngoingEAEForManager(
			Integer idManager) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		return eaeDao.getCollabWithoutOngoingEAEForManager(idManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novedia.talentmap.services.IEAEervice#getHistoryEAEForCollab(java
	 * .lang .Integer)
	 */
	@Override
	public List<EAEForSynthesisDTO> getHistoryEAEForCollab(Integer idCollab) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		return eaeDao.getHistoryEAEForCollab(idCollab);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#getEAEGenerality(java.lang
	 * .Integer)
	 */
	@Override
	public EAEGeneralityDTO getEAEGenerality(Integer idEAE) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		EAEGeneralityDTO e = eaeDao.getEAEGenerality(idEAE);
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#getEAEResults(java.lang
	 * .Integer)
	 */
	@Override
	public EAEResultsDTO getEAEResults(Integer idEAE) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		EAEResultsDTO e = eaeDao.getEAEResults(idEAE);
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#getEAESynthesis(java.lang
	 * .Integer)
	 */
	@Override
	public EAESynthesisDTO getEAESynthesis(Integer idEAE) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		EAESynthesisDTO e = eaeDao.getEAESynthesis(idEAE);
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public Integer getOpenEAEIdForColleague(Integer idCollab) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		Integer eaeId = eaeDao.getOpenEAEIdForColleague(idCollab);
		return eaeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public List<EAEColleagueResumeForCMDTO> getEAEColleagueResumeForCM(Integer idManager) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		List<EAEColleagueResumeForCMDTO> list = eaeDao.getEAEColleagueResumeForCM(idManager);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public int saveEAESalary(EAEGeneralityDTO eae) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		return eaeDao.saveEAESalary(eae);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#saveEAEResults(com.novedia.talentmap
	 * .model.dto.EAEGeneralityDTO)
	 */
	@Override
	public int saveEAEResults(EAEResultsDTO eae) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		return eaeDao.saveEAEResults(eae);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#saveEAESynthesis(com.novedia.talentmap
	 * .model.dto.EAESynthesisDTO)
	 */
	@Override
	public int saveEAESynthesis(EAESynthesisDTO eae) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		return eaeDao.saveEAESynthesis(eae);
	}

	/**
	 * @return the eaeDao
	 */
	public IDao<EAE> getEaeDao() {
		return eaeDao;
	}

	/**
	 * @param eaeDao
	 *            the eaeDao to set
	 */
	public void setEaeDao(IDao<EAE> eaeDao) {
		this.eaeDao = eaeDao;
	}

}
=======
/**
 * 
 */
package com.novedia.talentmap.services.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
import com.novedia.talentmap.model.dto.EAEResultsDTO;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.EAE;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.EAEDao;

/**
 * @author v.guillemain
 * 
 */
public class EAEService implements IEAEService {

	/**
	 * The EAE DAO.
	 */
	private IDao<EAE> eaeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#getEAE(java.lang.Integer)
	 */
	@Override
	public EAE getEAEById(Integer id) {
		return eaeDao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novedia.talentmap.services.IEAEervice#addEAE(com.novedia.talentmap
	 * .model.entity.EAE)
	 */
	@Override
	public int addEAE(EAE eae) {
		return eaeDao.add(eae);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novedia.talentmap.services.IEAEervice#saveEAE(com.novedia.talentmap
	 * .model.entity.EAE)
	 */
	@Override
	public int saveEAE(EAE eae) {
		return eaeDao.save(eae);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novedia.talentmap.services.IEAEervice#deleteEAE(com.novedia.talentmap
	 * .model.entity.EAE)
	 */
	@Override
	public int deleteEAE(EAE eae) {
		return eaeDao.delete(eae);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novedia.talentmap.services.IEAEervice#getOngoingEAEForCM(java.lang
	 * .Integer)
	 */
	@Override
	@Deprecated
	public List<EAEForSynthesisDTO> getOngoingEAEForCM(Integer idManager) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		return eaeDao.getOngoingEAEForCM(idManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#
	 * getCollabWithoutOngoingEAEForManager(java.lang .Integer)
	 */
	@Override
	@Deprecated
	public List<Colleague> getCollabWithoutOngoingEAEForManager(
			Integer idManager) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		return eaeDao.getCollabWithoutOngoingEAEForManager(idManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novedia.talentmap.services.IEAEervice#getHistoryEAEForCollab(java
	 * .lang .Integer)
	 */
	@Override
	public List<EAEForSynthesisDTO> getHistoryEAEForCollab(Integer idCollab) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		return eaeDao.getHistoryEAEForCollab(idCollab);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#getEAEGenerality(java.lang
	 * .Integer)
	 */
	@Override
	public EAEGeneralityDTO getEAEGenerality(Integer idEAE) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		EAEGeneralityDTO e = eaeDao.getEAEGenerality(idEAE);
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#getEAEResults(java.lang
	 * .Integer)
	 */
	@Override
	public EAEResultsDTO getEAEResults(Integer idEAE) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		EAEResultsDTO e = eaeDao.getEAEResults(idEAE);
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public Integer getOpenEAEIdForColleague(Integer idCollab) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		Integer eaeId = eaeDao.getOpenEAEIdForColleague(idCollab);
		return eaeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public int saveEAESalary(EAEGeneralityDTO eae) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		return eaeDao.saveEAESalary(eae);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.services.IEAEervice#saveEAEResults(com.novedia.talentmap
	 * .model.dto.EAEGeneralityDTO)
	 */
	@Override
	public int saveEAEResults(EAEResultsDTO eae) {
		EAEDao eaeDao = (EAEDao) this.eaeDao;
		return eaeDao.saveEAEResults(eae);
	}

	/**
	 * @return the eaeDao
	 */
	public IDao<EAE> getEaeDao() {
		return eaeDao;
	}

	/**
	 * @param eaeDao
	 *            the eaeDao to set
	 */
	public void setEaeDao(IDao<EAE> eaeDao) {
		this.eaeDao = eaeDao;
	}

}
>>>>>>> branch 'master' of https://github.com/Jean-Max/NovTalentMap.git
