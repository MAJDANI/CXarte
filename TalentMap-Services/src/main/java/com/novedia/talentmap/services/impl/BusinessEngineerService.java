package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.BusinessEngineer;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.store.IDao;

/**
 * The BusinessEngineer Service implementation.
 * 
 * @author v.guillemain
 */
public class BusinessEngineerService implements IBusinessEngineerService {

    /**
     * The businessEngineer DAO.
     */
    private IDao<BusinessEngineer> businessEngineerDao;

    @Override
    public List<BusinessEngineer> getAllBusinessEngineer() {
	return businessEngineerDao.getAll();
    }

    public IDao<BusinessEngineer> getBusinessEngineerDao() {
	return businessEngineerDao;
    }

    public void setBusinessEngineerDao(
	    IDao<BusinessEngineer> businessEngineerDao) {
	this.businessEngineerDao = businessEngineerDao;
    }

}