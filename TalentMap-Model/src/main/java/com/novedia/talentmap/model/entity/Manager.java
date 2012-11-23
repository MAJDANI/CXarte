package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class contains data from table Manager
 * @author moumbe
 *
 */
public class Manager extends Collaborator implements Serializable {

	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = -5987763744641087415L;
	
	/**
	 * Lists collaborator
	 */
	private ArrayList<Collaborator> collaborators = new ArrayList<Collaborator>();

	/**
	 * Build the class Manager.java 
	 */
	public Manager() {
		super();
	}

	/**
	 * Add collaborator in the list collaborators
	 * @param mission
	 * @return
	 */
	public ArrayList<Collaborator> addListCollaborators( Collaborator collaborator){
		collaborators.add(collaborator);
		return collaborators;		
	}

	/**
	 * @return the collaborators
	 */
	public ArrayList<Collaborator> getCollaborators() {
		return collaborators;
	}

	/**
	 * @param collaborators the collaborators to set
	 */
	public void setCollaborators(ArrayList<Collaborator> collaborators) {
		this.collaborators = collaborators;
	}
	
	
}
