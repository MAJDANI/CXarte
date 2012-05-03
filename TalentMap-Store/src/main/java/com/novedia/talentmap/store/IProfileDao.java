package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Profile;


public interface IProfileDao {
	
	Profile getById(int id) throws Exception;
	
	List<Profile> selectAll() throws Exception;
}
