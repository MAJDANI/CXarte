package com.novedia.talentmap.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.services.impl.AuthenticationService;
import com.novedia.talentmap.services.utils.Constants;



/**
 * @author j.maquin
 * @author b.tiomofofou
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, 
locations = {"classpath:test-mockito-services-context.xml","classpath:test-api-rest-context.xml"})
public class ColleagueControllerTest {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Autowired
	IColleagueService colleagueService;
	
	@Autowired
	IRegistrationService registrationService;
	
	@Autowired
	IAdminService adminService;
	
	@Autowired 
	private AuthenticationService authenticationService;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void getBusinessEngineersTest() throws Exception{
		//GIVEN
		List<Colleague> colleaguesList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleaguesList.add(colleague);
		
		//WHEN
		Mockito.when(colleagueService.getAllBusinessEngineers()).thenReturn(colleaguesList);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/businessengineers/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void getManagersTest() throws Exception{
		//GIVEN
		List<Colleague> colleaguesList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleaguesList.add(colleague);
		
		//WHEN
		Mockito.when(colleagueService.getAllConsultantManager()).thenReturn(colleaguesList);
		
		// THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/managers/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void getProfilesTest() throws Exception{
		//GIVEN
		List<Profile> profiles = new ArrayList<Profile>();
		Profile profile = Profile.builder().id(1).type("test").build();
		Profile profile2 = Profile.builder().id(2).type("test2").build();
		profiles.add(profile);
		profiles.add(profile2);
		
		//WHEN 
		Mockito.when(registrationService.getAllProfile()).thenReturn(profiles);
		
		// THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/profiles/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void getColleagueTest() throws Exception{
		//GIVEN
		Colleague expectedColleague = Colleague.builder().id(1).firstName("test").build();
		
		//WHEN 
		Mockito.when(colleagueService.getColleague(1)).thenReturn(expectedColleague);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/colleague/{colleagueId}/",1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	@Test
	public void deleteColleagueTest() throws Exception{
		//GIVEN
		Map<String, Object> map = new HashMap<String, Object>();
		Colleague colleague = Colleague.builder().id(1).build();
		Set<Colleague> colleagues = new HashSet<Colleague>();
		colleagues.add(colleague);
		map.put(Constants.MSG_ERROR, Constants.SUCCESSFUL_DELETE);
				
		//WHEN
		Mockito.when(adminService.deleteColleague(Mockito.anySetOf(Colleague.class))).thenReturn(map);
		
		// THEN
		mockMvc.perform(MockMvcRequestBuilders.delete("/colleague/{colleagueId}/",1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testAddColleague() throws Exception {
		
		//GIVEN
		Integer expectedColleagueId = 1;
		String name ="testName";
		String firstName = "testFirstName";
		String title ="M";
		String login = "t.login";
		String password ="t.password";
		String email = "t.eamil";
		String employemtDate = "12-04-2012";
		Integer prfileId = 1;
		Integer experience = 1;
		String phone ="-1";
		Integer bEngineerID = -1;
		Integer managerId = -1;

		CredentialToken token = new CredentialToken();
		token.setLogin(login);
		token.setPassword(password);
		
		Authentication authentication = new Authentication();
		authentication.setColleagueId(expectedColleagueId);
		authentication.setToken(token);
		
		//WHEN
		Mockito.when(registrationService.countLogin(Mockito.anyString())).thenReturn(0);
		Mockito.when(registrationService.addColleagueFromRegistration(Mockito.any(Registration.class))).thenReturn(expectedColleagueId);
		Mockito.when(authenticationService.checkUser(Mockito.any(CredentialToken.class))).thenReturn(authentication);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.post("/colleague/{name}/{firstName}/{title}/{login}/{password}/{email}/{employemtDate}"+
			"/{profileId}/{experience}/{phone}/{bEngineerID}/{managerId}/",name,firstName,title,login,password,email,employemtDate,
			prfileId,experience,phone,bEngineerID,managerId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testUpdateColleague() throws Exception {
		
		//GIVEN
		Integer expectedColleagueId = 1;
		Integer colleagueId = 1;
		String name ="testName";
		String firstName = "testFirstName";
		String email = "t.eamil";
		String employemtDate = "12-04-2012";
		Integer prfileId = 1;
		Integer experience = 1;
		String phone ="-1";
		Integer bEngineerID = -1;
		Integer managerId = -1;
		
		Colleague colleague = Colleague.builder().id(colleagueId).build();
		
		//WHEN
		Mockito.when(colleagueService.getColleague(Mockito.anyInt())).thenReturn(colleague);
		Mockito.when(colleagueService.saveColleague(Mockito.any(Colleague.class))).thenReturn(expectedColleagueId);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.put("/colleague/{colleagueId}/{name}/{firstName}/{email}/{employemtDate}"+
			"/{profileId}/{experience}/{phone}/{bEngineerID}/{managerId}/",colleagueId,name,firstName,email,employemtDate,
			prfileId,experience,phone,bEngineerID,managerId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

}
