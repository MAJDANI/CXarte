package com.novedia.talentmap.store.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.store.ICollaboratorDao;

public class AppMain {

	/**
	 * @class AppMain.java
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		/*ICollaboratorDao collabDao = new CollaboratorDao();
		
		Collaborator c = collabDao.getCollaborator(1);
		
		c.toString();*/
		
		ApplicationContext context = new ClassPathXmlApplicationContext("store-spring-context.xml");
		CollaboratorDao collab = (CollaboratorDao)context.getBean("Collaborator");
		System.out.println(collab.getCollaborator(1));
	}

}
