package com.novedia.talentmap.rest.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.rest.exception.TalentMapRestHandlerException;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IRegistrationService;


/**
 * 
 * @author j.maquin
 *
 */

@Controller
public class ColleagueController extends TalentMapRestHandlerException {
	
	@Autowired
	IColleagueService colleagueService;
	
	@Autowired
	IRegistrationService registrationService;
	
	@Autowired
	IAdminService adminService;
	
	/**
	 * 
	 * @return businessEngineers
	 */
	@RequestMapping(value = "/businessengineers/")
	@ResponseBody
	public List<Colleague> getBusinessEngineers() {
		List<Colleague> businessEngineers = colleagueService.getAllBusinessEngineers();
		return businessEngineers;
	}
	
	/**
	 * 
	 * @return managers
	 */
	@RequestMapping(value = "/managers/")
	@ResponseBody
	public List<Colleague> getManagers() {
		List<Colleague> managers = colleagueService.getAllConsultantManager();
		return managers;
	}
	
	/**
	 * 
	 * @return profiles
	 */
	@RequestMapping(value = "/profiles/")
	@ResponseBody
	public List<Profile> getProfiles() {
		List<Profile> profiles = registrationService.getAllProfile();
		return profiles;
	}
	
	/**
	 * @param colleagueId
	 * @return colleague
	 */
	@RequestMapping(value = "/colleague/{colleagueId}/")
	@ResponseBody
	public Colleague getColleague(@PathVariable Integer colleagueId) {
		Colleague colleague = colleagueService.getColleague(colleagueId);
		return colleague;
	}
	
	/**
	 * @param colleagueId
	 * @return colleague
	 */
	@RequestMapping(value = "/colleague/{colleagueId}/", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteColleague(@PathVariable Integer colleagueId) {
		Colleague colleague = Colleague.builder().id(colleagueId).build();
		Set<Colleague> colleagues = new HashSet<Colleague>();
		colleagues.add(colleague);
		adminService.deleteColleague(colleagues);
	}

}
