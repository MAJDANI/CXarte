package com.novedia.talentmap.model.entity;

import java.io.Serializable;




/**
 * Entity Client
 * @author y.rohr
 *
 */
public class Client implements Serializable, Comparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 257698099636559191L;
	
	/**
	 * entity identify
	 */
	protected Integer id;
	
	/**
	 * Colleague name
	 */
	protected String name;
	
	
	/**
	 * Default Constructor
	 */
	public Client() {
		
	}
	
	/**
	 * Build an immutable category entity.
	 * 
	 * @param builder
	 *            the builder inner class for this entity
	 */
	public Client(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
	}
	
	/**
	 * Inner builder class.
	 * 
	 * @author y.rohr
	 */
	public static class Builder {
		
		/**
		 * Client identifier
		 */
		private Integer id;

		/**
		 * Client Name
		 */
		private String name;

		
		/**
		 * Set id
		 * 
		 * @param id the coworker's identifier
		 * 
		 * @return the builder
		 */
		public Builder id(final Integer id) {
			this.id = id;
			return this;
		}

		/**
		 * Set name
		 * 
		 * @param name the coworker's first name
		 * @return the builder
		 */
		public Builder name(final String name) {
			this.name = name;
			return this;
		}
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Object o) {
		return this.getName().compareTo(((Client)o).getName());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Client)){
			return false;
		} else {
			Client client = (Client) obj;
			return (this.id.equals(client.getId()) && this.name.equals(client.getName()));
		}
	}
		
	    
}