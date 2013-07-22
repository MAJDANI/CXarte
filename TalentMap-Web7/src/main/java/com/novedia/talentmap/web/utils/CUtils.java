package com.novedia.talentmap.web.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public abstract class CUtils {

   
    /**
     * Method used to encode password
     */
    public static String encodePassword(String password) {
		Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();
		String encodedPassword = md5Encoder.encodePassword(password, null);
	
		return encodedPassword;
    }

}
