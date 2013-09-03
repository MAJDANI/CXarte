package com.novedia.talentmap.model.entity;

/**
 * The authorization contains permission on view
 * 
 * @author e.moumbe
 * 
 */
public class Authorization {

    /**
     * The role
     */
    public Role role;

    /**
     * The id of role
     */
    private Integer roleId;

    /**
     * The label of role
     */
    private String label;

    /**
     * Enum thats contains all roles
     * 
     * @author e.moumbe
     * 
     */
    public static enum Role {

	RH(1, "Ressources Humaines"), CM(2, "Consultant Manager"), IA(3,
		"Ingenieur d'Affaires"), CL(4, "Consultant"), AD(5,
		"Administrateur");

	/**
	 * The constructor
	 * 
	 * @param name
	 *            the name of role
	 */
	Role(Integer id, String name) {
	    this.name = name;
	    this.id = id;
	}

	/**
	 * The id of role
	 */
	private Integer id;

	/**
	 * The name of role
	 */
	private String name;

	/**
	 * @return the id
	 */
	public Integer getId() {
	    return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
	    this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
	    return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
	    this.name = name;
	}
    }

    /**
     * @return the label
     */
    public String getLabel() {
	return label;
    }

    /**
     * @param label
     *            the label to set
     */
    public void setLabel(String label) {
	this.label = label;
    }

    /**
     * @return the roleId
     */
    public Integer getRoleId() {
	return roleId;
    }

    /**
     * @param roleId
     *            the roleId to set
     */
    public void setRoleId(Integer roleId) {
	this.roleId = roleId;
    }

    /**
     * @return the role
     */
    public Role getRole() {
	return role;
    }

}
