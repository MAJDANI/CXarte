package com.novedia.talentmap.store.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.novedia.talentmap.model.entity.Profile;

public class AppMain {

	/**
	 * @class AppMain.java
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("store-spring-context.xml");
		
		ProfileDao profileDao = (ProfileDao) context.getBean("ProfileDao");
		//CollaboratorDao collaboratorDao = new CollaboratorDao();
		
		try {
		//	System.out.println(collaboratorDao.getCollaborator(1).toString());
			for(Profile p : profileDao.selectAll()){
				System.out.println(p.getType());
			}
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
