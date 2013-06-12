/**
 * 
 */
package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
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

    /* (non-Javadoc)
     * @see com.novedia.talentmap.services.IEAEervice#getEAE(java.lang.Integer)
     */
    @Override
    public EAE getEAEById(Integer id) {
	return eaeDao.get(id);
    }

    /* (non-Javadoc)
     * @see com.novedia.talentmap.services.IEAEervice#addEAE(com.novedia.talentmap.model.entity.EAE)
     */
    @Override
    public int addEAE(EAE eae) {
	return eaeDao.add(eae);
    }

    /* (non-Javadoc)
     * @see com.novedia.talentmap.services.IEAEervice#saveEAE(com.novedia.talentmap.model.entity.EAE)
     */
    @Override
    public int saveEAE(EAE eae) {
	return eaeDao.save(eae);
    }

    /* (non-Javadoc)
     * @see com.novedia.talentmap.services.IEAEervice#deleteEAE(com.novedia.talentmap.model.entity.EAE)
     */
    @Override
    public int deleteEAE(EAE eae) {
	return eaeDao.delete(eae);
    }

    /* (non-Javadoc)
     * @see com.novedia.talentmap.services.IEAEervice#getOngoingEAEForCM(java.lang.Integer)
     */
    @Override
    public List<EAEForSynthesisDTO> getOngoingEAEForCM(Integer idManager) {
	EAEDao eaeDao = (EAEDao) this.eaeDao;
	return eaeDao.getOngoingEAEForCM(idManager);
    }

    @Override
    public List<Colleague> getCollabWithoutOngoingEAEForManager(
	    Integer idManager) {
	EAEDao eaeDao = (EAEDao) this.eaeDao;
	return eaeDao.getCollabWithoutOngoingEAEForManager(idManager);
    }

    @Override
    public List<EAEForSynthesisDTO> getHistoryEAEForCollab(Integer idCollab) {
	EAEDao eaeDao = (EAEDao) this.eaeDao;
	return eaeDao.getHistoryEAEForCollab(idCollab);
    }
 
    @Override
    public EAEGeneralityDTO getEAEGenerality(Integer idEAE) {
	EAEDao eaeDao = (EAEDao) this.eaeDao;
	return eaeDao.getEAEGenerality(idEAE);
    }


    /**
     * @return the eaeDao
     */
    public IDao<EAE> getEaeDao() {
        return eaeDao;
    }

    /**
     * @param eaeDao the eaeDao to set
     */
    public void setEaeDao(IDao<EAE> eaeDao) {
        this.eaeDao = eaeDao;
    }


}
