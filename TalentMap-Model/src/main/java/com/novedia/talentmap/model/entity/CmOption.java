package com.novedia.talentmap.model.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



/**
 * This entity represents options of a CM.
 * 
 * @author j.maquin
 * 
 */
public class CmOption {
	
	/**
	 * The email frequency
	 */
	private Frequency frequency;

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	
	/**
	 * Build an immutable CmOption entity.
	 * 
	 * @param builder
	 *            the builder inner class for this entity
	 */
	public CmOption(final Builder builder) {
		this.frequency = builder.frequency;
	}

	public CmOption() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hashBuilder = new HashCodeBuilder();
		hashBuilder.append(this.getFrequency());
		
		return hashBuilder.hashCode();
	}

	/**
	 * Override equals, because we are need to compare colleague by id
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (obj != null && !(obj instanceof CmOption)) {
			return false;
		}

		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		
		CmOption cmOption = (CmOption) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.getFrequency(), cmOption.getFrequency()).isEquals();
	}

	// -------------------------------------
	// ------------ BUILDER PART -----------
	// -------------------------------------
	/**
	 * Static constructor for this class.
	 * 
	 * @return a builder instance
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Inner builder class.
	 * 
	 * @author j.maquin
	 */
	public static class Builder {

		/**
		 * Frequency identifier
		 */
		private Frequency frequency;

		/**
		 * Set frequency
		 * 
		 * @param frequency
		 *            Email Frequency
		 * @return the builder
		 */
		public Builder frequency(final Frequency frequency) {
			this.frequency = frequency;
			return this;
		}

		/**
		 * Build an immutable instance of CmOption.
		 * 
		 * @return CmOption
		 */
		public CmOption build() {
			return new CmOption(this);
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
