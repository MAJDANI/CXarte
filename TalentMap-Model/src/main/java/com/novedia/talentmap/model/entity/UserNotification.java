package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author j.maquin
 * 
 */
public class UserNotification implements Serializable {

	/**
	 * serial number
	 */
	private static final long serialVersionUID = 3527985794687884954L;

	/**
	 * The colleague id.
	 */
	private Integer colleagueId;

	/**
	 * Information on this notification
	 */
	private String notes;

	/**
	 * Date of the notification
	 */
	private Date date;

	/**
	 * Build the class Notification.java
	 */
	public UserNotification() {
	}

	public Integer getColleagueId() {
		return colleagueId;
	}

	public void setColleagueId(Integer colleagueId) {
		this.colleagueId = colleagueId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// --------------------------------------
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
	 * Build an immutable mission entity.
	 * 
	 * @param builder
	 *            the builder inner class for this entity
	 */
	private UserNotification(final Builder builder) {

		this.colleagueId = builder.colleagueId;
		this.notes = builder.notes;
		this.date = builder.date;

	}

	/**
	 * Inner builder class.
	 * 
	 * @author j.maquin
	 */
	public static final class Builder {

		/**
		 * Colleague associated the notification
		 */
		private Integer colleagueId;

		/**
		 * Information on this notification
		 */
		private String notes;

		/**
		 * date of the notification
		 */
		private Date date;

		/**
		 * List the colleague
		 * 
		 * @param colleague
		 *            the linked colleague
		 * 
		 * @return the builder
		 */
		public Builder colleagueId(final Integer colleagueId) {
			this.colleagueId = colleagueId;
			return this;
		}

		/**
		 * List the notes about the notification
		 * 
		 * @param notes
		 *            the comments about the notification
		 * @return the builder
		 */
		public Builder notes(final String notes) {
			this.notes = notes;
			return this;
		}

		/**
		 * List the date
		 * 
		 * @param date
		 *            the notification's date
		 * @return the builder
		 */
		public Builder date(final Date date) {
			this.date = date;
			return this;
		}

		/**
		 * Build an immutable instance of notification.
		 * 
		 * @return notification
		 */
		public UserNotification build() {
			return new UserNotification(this);
		}
	}
}
