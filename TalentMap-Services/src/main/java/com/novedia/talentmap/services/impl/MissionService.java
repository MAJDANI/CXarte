package com.novedia.talentmap.services.impl;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IMissionService;
import com.novedia.talentmap.store.IDao;

/**
 * The mission service.
 * 
 * @author y.rohr
 */
public class MissionService implements IMissionService {

    /**
     * mission DAO.
     */
    private IDao<Mission> missionDao;

    /**
     * This method allows to make the spring injection. Set the manager DAO.
     * 
     * @param managerDao
     */
    public void setMissionDao(IDao<Mission> missionDao) {
	this.missionDao = missionDao;
    }
}