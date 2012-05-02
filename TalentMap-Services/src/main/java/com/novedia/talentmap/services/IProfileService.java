package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.Profile;

public interface IProfileService {
	
	List<Profile> getAllProfile() throws Exception;
	Profile getProfile(int id) throws Exception;
}
