package com.novedia.talentmap.services.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.store.impl.ProfileDao;

public class ProfileServiceTest {
	
	private ProfileService profileService;
	
	@Before
	public void setUp() throws Exception {
		this.profileService = new ProfileService();
		this.profileService.setProfileDao(new ProfileDao());
	}

	@Test
	public void testGetAllProfile() throws Exception {
		assertNotNull(this.profileService.getAllProfile());
		assertFalse(this.profileService.getAllProfile().size() == 0);
	}

	@Test
	public void testGetProfileInt() throws Exception {
		assertNotNull(this.profileService.getProfile(1));
	}
//
//	@Test
//	public void testGetProfileString() throws Exception {
//		assertNotNull(this.profileService.getProfile("technique"));
//	}
//
//	@Test
//	public void testSetProfileDao() {
//		
//	}
	
	@Test
	public void testGetProfileDao(){
		assertNotNull(this.profileService.getProfileDao());
	}

}
