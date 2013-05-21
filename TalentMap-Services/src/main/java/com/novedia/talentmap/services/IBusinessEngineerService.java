package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.BusinessEngineer;

/**
 * BusinessEngineer Service Interface.
 * 
 * @author v.guillemain TODO : Must create a data referential interface
 */
public interface IBusinessEngineerService {

	/**
	 * Get all BusinessEngineer.
	 * 
	 * @return List<BusinessEngineer>
	 */
	List<BusinessEngineer> getAllBusinessEngineer();

}