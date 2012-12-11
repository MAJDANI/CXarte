/**
 * 
 */
package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author v.dibi
 * @author v.guillemain
 * 
 */
public class Authentification implements Serializable {

	/**
	 * serial number
	 */
	private static final long serialVersionUID = 6615977875597428826L;

	/**
	 * id of table
	 */
	private Integer id;
	/**
	 * id of collaborator
	 */
	private Integer idCollab;
	/**
	 * login of collaborator who is connected
	 */
	private String login;
	/**
	 * password of collaborator who is connected
	 */
	private String password;
	

	/**
	 * Build an immutable Authentification entity. the builder inner class for this
	 * entity
	 */
	public Authentification() {
	}


	/**
	 * Build an immutable Authentification entity.
	 * @param builder
	 * the builder inner class for this entity
	 */
	public Authentification(final Builder builder) {
		this.id = builder.id;
		this.login = builder.login;
		this.password =  builder.password;
		this.idCollab = builder.idCollab;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCollab() {
		return idCollab;
	}

	public void setIdCollab(Integer idCollab) {
		this.idCollab = idCollab;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// ------------------------------------------
	// ------------ OVERRIDEN METHODS -----------
	// ------------------------------------------
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append("[idCollab=").append(getIdCollab()).append(", ");
		strBld.append("login").append(getLogin()).append("]");
		return strBld.toString();
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hashBuilder = new HashCodeBuilder();
		hashBuilder.append(this.getId());
		return hashBuilder.hashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Authentification)) {
			return false;
		}

		Authentification comparedObj = (Authentification) obj;
		EqualsBuilder ebuilder = new EqualsBuilder();
		ebuilder.append(this.getId(), comparedObj.getId());
		return ebuilder.isEquals();
	}
	
	/**
	 * BUILDER PART.
	 * 
	 */
	public static class Builder {

		/**
		 * id of table
		 */
		private Integer id;
		/**
		 * id of collaborator
		 */
		private Integer idCollab;
		/**
		 * login of collaborator who is connected
		 */
		private String login;
		/**
		 * password of collaborator who is connected
		 */
		private String password;

		/**
		 * Constructor with parameter id.
		 * @param id
		 * @return
		 */
		public Builder id(final Integer id) {
			this.id = id;
			return this;
		}

		/**
		 * Constructor with parameter idCollab
		 * 
		 * @param name
		 * @return
		 */
		public Builder idCollab(final Integer idCollab) {
			this.idCollab = idCollab;
			return this;
		}
		
		/**
		 * 
		 * @param idCollab
		 * @return
		 */
		public Builder login(final String login) {
			this.login = login;
			return this;
		}
		/**
		 * 
		 * @param password
		 * @return
		 */
		public Builder password(final String password) {
			this.password = password;
			return this;
		}
		/**
		 * Build Authentification
		 * 
		 * @return
		 */
		public Authentification build() {
			return new Authentification(this);
		}

		/**
		 * Static constructor for this class.
		 * 
		 * @return a builder instance
		 */
		public static Builder builder() {
			return new Builder();
		}
	}
}
