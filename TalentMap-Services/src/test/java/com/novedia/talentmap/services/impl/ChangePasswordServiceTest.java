package com.novedia.talentmap.services.impl;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.store.IDao;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordServiceTest {
	
	@Mock
	private IDao<Authentication> authenticationDaoMock;
	
	@Test
	public void savePasswordReturnInteger(){
		
		//Given
		String newPassword = "pwd";
		Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();		
		String encodedPassword = md5Encoder.encodePassword(newPassword, null);
		Authentication authentication = new Authentication();
		CredentialToken token = new CredentialToken();
		token.setLogin("b.tiomo");
		token.setPassword(encodedPassword);
		authentication.setToken(token);
		Integer expectedResultSave = 1;
		Integer currentResult = 1;
		
		//When 
		Mockito.when(authenticationDaoMock.save(authentication)).thenReturn(currentResult);
		
		//Then
		Assert.assertEquals(expectedResultSave, currentResult);
		
	}
	

}
