package com.novedia.talentmap.rest.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.dto.Response;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.BusinessEngineer;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.model.entity.JsonException;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.rest.exception.IBadRequestException;
import com.novedia.talentmap.rest.exception.TalentMapRestHandlerException;
import com.novedia.talentmap.rest.utiils.DateFormat;
import com.novedia.talentmap.rest.utiils.ConstantsValue;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.services.impl.AuthenticationService;
import com.novedia.talentmap.services.utils.Constants;


/**
 * 
 * @author j.maquin
 * @author b.tiomofofou
 *
 */

@Controller
public class ColleagueController extends TalentMapRestHandlerException implements IBadRequestException {
	
	@Autowired
	IColleagueService colleagueService;
	
	@Autowired
	IRegistrationService registrationService;
	
	@Autowired
	IAdminService adminService;
	
	@Autowired 
	private AuthenticationService authenticationService;
	
	/**
	 * 
	 * @return businessEngineers
	 */
	@RequestMapping(value = "/businessengineers/" , method=RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getBusinessEngineers() {
		List<Colleague> businessEngineers = colleagueService.getAllBusinessEngineers();
		return businessEngineers;
	}
	
	/**
	 * 
	 * @return managers
	 */
	@RequestMapping(value = "/managers/" , method=RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getManagers() {
		List<Colleague> managers = colleagueService.getAllConsultantManager();
		return managers;
	}
	
	/**
	 * 
	 * @return profiles
	 */
	@RequestMapping(value = "/profiles/" , method=RequestMethod.GET )
	@ResponseBody
	public List<Profile> getProfiles() {
		List<Profile> profiles = registrationService.getAllProfile();
		return profiles;
	}
	
	/**
	 * @param colleagueId
	 * @return colleague
	 */
	@RequestMapping(value = "/colleague/{colleagueId}/" , method=RequestMethod.GET )
	@ResponseBody
	public Colleague getColleague(@PathVariable Integer colleagueId) {
		Colleague colleague = colleagueService.getColleague(colleagueId);
		if(colleague == null){
			colleague = Colleague.builder().build();
		}
		return colleague;
	}
	
	/**
	 * @param colleagueId
	 * @return colleague
	 */
	@RequestMapping(value = "/colleague/{colleagueId}/", method = RequestMethod.DELETE)
	@ResponseBody
	public Response deleteColleague(@PathVariable Integer colleagueId) {
		Response response = new Response();
		response.setMessage(ConstantsValue.SUCCESSFUL_DELETE_MSG);
		
		Colleague colleague = Colleague.builder().id(colleagueId).build();
		Set<Colleague> colleagues = new HashSet<Colleague>();
		colleagues.add(colleague);
		Map<String, Object> result = adminService.deleteColleague(colleagues);
		
		if(result.get(Constants.MSG).equals((String)Constants.UNSUCCESS)){
			response.setMessage(ConstantsValue.UNSUCCESSFUL_DELETE_MSG);
		}
		return response;
	}
	
	
	
	/**
	 * Add colleague resource
	 * @param name name of colleague
	 * @param firstName first name of colleague
	 * @param title sex of colleague
	 * @param login login of colleague
	 * @param password password of colleague
	 * @param email email of colleague
	 * @param employemtDate date of hiring of colleague
	 * @param profileId profile id  of colleague
	 * @param experience experience year number of colleague
	 * @param phone phone number of colleague
	 * @param bEngineerID business engineer of colleague
	 * @param managerId manager of colleague
	 * @return Authentication 
	 * @throws ParseException
	 * @throws DataAccessException
	 */
	
	@RequestMapping(value = "/colleague/{name}/{firstName}/{title}/{login}/{password}/{email}/{employemtDate}"+
			"/{profileId}/{experience}/{phone}/{bEngineerID}/{managerId}/", 
			method = RequestMethod.POST)
	@ResponseBody
	public Authentication addColleague(@PathVariable final String name, @PathVariable final String firstName,@PathVariable final String title,
			@PathVariable final String login, @PathVariable final String password,@PathVariable final String email,@PathVariable final String employemtDate,
			@PathVariable final Integer profileId, @PathVariable final Integer experience,@PathVariable final String phone,
			@PathVariable final Integer bEngineerID, @PathVariable final Integer managerId) throws ParseException, DataAccessException{
		
		Authentication authentication = null;
		Registration registration = null;
		
		BusinessEngineer businessEngineer = null;
		
		Integer manager = managerId;
		if(managerId.equals(ConstantsValue.DEFAULT_NUMERIC_VALUE)){
			manager = null;
		}
		
		if(!bEngineerID.equals(ConstantsValue.DEFAULT_NUMERIC_VALUE)){
			businessEngineer = BusinessEngineer.builder().id(bEngineerID).build();
		}
		
		String phoneNumber = phone;
		if(phone.equalsIgnoreCase(ConstantsValue.DEFAULT_STRING_VALUE)){
			phoneNumber = null;
		}
		
		Date employement = DateFormat.parseStringToDate(employemtDate);
		
		Integer checkLogin = registrationService.countLogin(login);
		if(!checkLogin.equals(0)){
			return new Authentication();
		}
		
		Md5PasswordEncoder md5encoder = new Md5PasswordEncoder();
		String encodePassword = md5encoder.encodePassword(password, null);
		
		registration = Registration.Builder.builder().lastName(name).firstName(firstName).title(title).login(login).password(encodePassword).email(email).employmentDate(employement).
				profileId(profileId).experience(experience).phone(phoneNumber).managerId(manager).businessEngineer(businessEngineer).build();
		registration.setRole(Authorization.Role.CL);
		
		Integer result = registrationService.addColleagueFromRegistration(registration);
		
		if(!result.equals(0)){
			CredentialToken token = new CredentialToken();
			token.setLogin(login);
			token.setPassword(encodePassword);
			authentication = authenticationService.checkUser(token);
		}
		
		return authentication;
		
	}
	
	
	/**
	 * Update information about a colleague
	 * @param colleagueId the colleague Id
	 * @param name name of colleague
	 * @param firstName first name of colleague
	 * @param email email of colleague
	 * @param employemtDate date of hiring of colleague
	 * @param profileId profile id of colleague
	 * @param experience experience year number of colleague
	 * @param phone phone number of colleague
	 * @param bEngineerID business engineer of colleague
	 * @param managerId manager of colleague
	 * @return Colleague
	 * @throws ParseException
	 * @throws DataAccessException
	 */
	@RequestMapping(value = "/colleague/{colleagueId}/{name}/{firstName}/{email}/{employemtDate}"+
			"/{profileId}/{experience}/{phone}/{bEngineerID}/{managerId}/", 
			method = RequestMethod.PUT)
	@ResponseBody
	public Colleague updateColleague(@PathVariable final Integer colleagueId,@PathVariable final String name, @PathVariable final String firstName,
			@PathVariable final String email,@PathVariable final String employemtDate,@PathVariable final Integer profileId,
			@PathVariable final Integer experience,@PathVariable final String phone,@PathVariable final Integer bEngineerID,
			@PathVariable final Integer managerId) throws ParseException, DataAccessException{
		
		Colleague colleague = colleagueService.getColleague(colleagueId);
		
		if(colleague == null){
			return new Colleague();
		}
		
		Date employement = DateFormat.parseStringToDate(employemtDate);
	
		BusinessEngineer businessEngineer = null;
		
		Integer manager = managerId;
		if(managerId.equals(ConstantsValue.DEFAULT_NUMERIC_VALUE)){
			manager = null;
		}
		
		if(!bEngineerID.equals(ConstantsValue.DEFAULT_NUMERIC_VALUE)){
			businessEngineer = BusinessEngineer.builder().id(bEngineerID).build();
		}
		
		String phoneNumber = phone;
		if(phone.equalsIgnoreCase(ConstantsValue.DEFAULT_STRING_VALUE)){
			phoneNumber = null;
		}
		
		colleague = Colleague.builder().id(colleagueId).firstName(firstName).lastName(name).email(email).profileId(profileId).employmentDate(employement)
				.businessEngineer(businessEngineer).managerId(manager).experience(experience).phone(phoneNumber).build();
		
		colleagueService.saveColleague(colleague);
		
		return colleague;
	}

	
	@Override
	@ExceptionHandler({TypeMismatchException.class, ParseException.class})
	public JsonException handlerBadRequestException(Exception ex) {
		JsonException jsonError = new JsonException();
		jsonError.setCode(HttpStatus.BAD_REQUEST.value());
		if (ex instanceof ParseException) {
			jsonError.setMessage(ConstantsValue.INCORRECT_DATE_FORMAT_MSG);
		} else {
			jsonError.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}
		
		return jsonError;
	}


}
