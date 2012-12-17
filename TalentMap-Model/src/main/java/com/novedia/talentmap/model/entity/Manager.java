package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This entity represents a manager.
 * 
 * @author e.moumbe
 *
 */
public class Manager extends Colleague implements Serializable {

	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = -5987763744641087415L;
	
	/**
	 * Lists collaborator
	 */
	private ArrayList<Colleague> colleagues = new ArrayList<Colleague>();

	/**
	 * Build an instance of manager
	 */
	public Manager() {
		super();
	}

	/**
	 * Add collaborator in the list collaborators
	 * @param mission
	 * @return
	 */
	public ArrayList<Colleague> addListCollaborators( Colleague collaborator){
		colleagues.add(collaborator);
		return colleagues;		
	}

	/**
	 * Get the manager's colleagues list
	 * 
	 * @return the collaborators
	 */
	public ArrayList<Colleague> getCollaborators() {
		return colleagues;
	}

	/**
	 * Set the manager's colleagues list
	 * 
	 * @param colleagues the colleagues to set
	 */
	public void setCollaborators(ArrayList<Colleague> colleagues) {
		this.colleagues = colleagues;
	}
	
}
