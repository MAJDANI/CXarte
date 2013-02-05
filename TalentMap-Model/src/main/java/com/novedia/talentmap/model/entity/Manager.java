package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * This entity represents a manager.
 * 
 * @author j.marie-sainte
 *
 */
public class Manager extends Colleague implements Serializable {

	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = -5987763744641087415L;
	
	/**
	 * Colleagues list
	 */
	private List<Colleague> colleagues = new ArrayList<Colleague>();

	// TODO : A supprimer car constructeur par défaut
	//NON, ne pas supprimer, iBatis a besoin du constructeur par défaut 
	//pour instancier l'objet ManagerResult dans sqlmap-colleague 
	//(cf http://0guzhan.blogspot.fr/2011/12/ibatis-with-spring-javabeansdataexchang.html)
	/**
	 * Build an instance of manager
	 */
	public Manager() {
		super();
	}

	/**
	 * Get the manager's colleagues list
	 * 
	 * @return the colleagues
	 */
	public List<Colleague> getColleagues() {
		return colleagues;
	}

	/**
	 * Set the manager's colleagues list
	 * 
	 * @param colleagues the colleagues to set
	 */
	public void setColleagues(List<Colleague> colleagues) {
		this.colleagues = colleagues;
	}
	
	// ------------------------------------------
	// ------------ OVERRIDEN METHODS -----------
	// ------------------------------------------
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append(super.toString());
		strBld.append("->[Colleagues List").append(getColleagues()).append("] ");

		return strBld.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hashBuilder = new HashCodeBuilder();
		hashBuilder.append(this.getId());
		hashBuilder.append(this.getFirstName());
		hashBuilder.append(this.getLastName());

		return hashBuilder.hashCode();
	}
		
	// -------------------------------------
	// ------------ BUILDER PART -----------
	// -------------------------------------
		
	/**
	 * Static constructor for this class.
	 * 
	 * @return a builder instance
	 */
	public static Builder builder(){
		return new Builder() ;
	} 
	
	/**
	 * 
	 * @param builder
	 */
	private Manager(final Builder builder){
		super(builder);
		this.colleagues = builder.colleagues;
	}
	
	
	/**
	 * Inner builder class.
	 * 
	 * @author j.marie-sainte
	 */
	public static final class Builder extends Colleague.Builder{
		
		/**
		 * Colleagues list bind to this manager
		 */
		private List<Colleague> colleagues = Lists.newArrayList();
		
		/**
		 * Set the colleagues list
		 * 
		 * @param colleagues
		 * @return the builder
		 */
		public Builder colleagues(Colleague... colleagues){
			this.colleagues.addAll(Arrays.asList(colleagues));
			return this;
		}
		
		/**
		 * Build an immutable instance of tool.
		 * 
		 * @return a manager
		 */
		public Manager build() {
			this.colleagues = ImmutableList.copyOf(this.colleagues);
			return new Manager(this);
		}
				
	}
	
}		
