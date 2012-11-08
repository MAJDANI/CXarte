package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Manager extends Collaborator implements Serializable {

	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = -5987763744641087415L;
	
	private ArrayList<Collaborator> collaborator;

	/**
	 * Build the class Manager.java 
	 */
	public Manager() {
		super();
	}

	public ArrayList<Collaborator> getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(ArrayList<Collaborator> collaborator) {
		this.collaborator = collaborator;
	}
	
	/**
	 * create a List of Tool for Collaborator
	 * @param mission
	 * @return
	 */
	@SuppressWarnings("null")
	public ArrayList<Collaborator> addCollaborator( ArrayList<Collaborator> collaborator){
		if(collaborator == null){
			Collaborator c =  new Collaborator();
			collaborator.add(c);
		}
		return collaborator;		
	}
	
	
}
