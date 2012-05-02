package com.novedia.talentmap.services.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.novedia.talentmap.model.entity.Profile;

public class AppMain {

	/**
	 * @class AppMain.java
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("services-spring-context.xml");
		ProfileService profileService = (ProfileService) context.getBean("profileService");
		
		try {
			
			for(Profile p : profileService.getAllProfile()){
				System.out.println(p.getProfileType());
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
