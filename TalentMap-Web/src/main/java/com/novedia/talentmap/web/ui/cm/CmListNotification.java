package com.novedia.talentmap.web.ui.cm;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.entity.UserNotification;

@SuppressWarnings("serial")
public class CmListNotification extends PagedTable {

	protected static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm");
	public static final String NOTIFICATION = "Notifications";
	public static final String DATE = "date";
	
	/**
	 * Vaadin UI
	 */
	private CmNotificationContainer cmNotificationContainer;
	
	private Integer colleagueId;
	
	private static final int PAGE_SIZE = 5;
	
	/**
	 * Default constructor
	 */
	public CmListNotification(){
		super();
		addColumns();
		setSelectable(true);
		setNullSelectionAllowed(true);
	}
	
	/**
	 * Build cm's notification
	 * @return
	 */
	public CmListNotification buildAllCmNotification(){
		removeAllItems();
		buildMain();
		return this;
	}
	
	
	public void buildMain(){
		setWidth("900px");
		buildContainer();
	}
	
	/**
	 * Principal function filling ListMission Table
	 */
	public void buildContainer(){
		fillNotificationContainer();
		fillResultsTable();
	}
	
	public void fillNotificationContainer() {
		this.cmNotificationContainer.fillContainer(getColleagueId());
	}
	
	/**
	 * Builds Headers of the Table CmListNotification
	 */
	public void addColumns() {
		addContainerProperty(NOTIFICATION, String.class, null);
		addContainerProperty(DATE, String.class, null);
	
	}
	
	/**
	 * Gets each item mission in the MissionContainer given in parameter.
	 * With each item mission we fill the actual Table ListMission using addItem() method.  
	 * @param missionContainer
	 */
	public void fillResultsTable() {
		Collection<UserNotification> collectionNotification = this.cmNotificationContainer
				.getItemIds();


		for (UserNotification notification : collectionNotification) {
			String date = formatterDate(notification.getDate());

			addItem(new Object[] { notification.getNotes(),
					date},notification);
		}
	
	}

	
	/**
	 * Format the date with the template dateFormat "dd/MM/yyyy"
	 * @param date
	 * @return
	 */
	public static String formatterDate(Date date) {
		if (date != null) {
			return dateFormat.format(date);
		}
		else {
			return "";
		}
	}
	public CmNotificationContainer getCmNotificationContainer() {
		return cmNotificationContainer;
	}

	public void setCmNotificationContainer(CmNotificationContainer cmNotificationContainer) {
		this.cmNotificationContainer = cmNotificationContainer;
	}

	public Integer getColleagueId() {
		return colleagueId;
	}

	public void setColleagueId(Integer colleagueId) {
		this.colleagueId = colleagueId;
	}

}
