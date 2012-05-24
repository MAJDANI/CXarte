package com.novedia.talentmap.web.util;

public interface IObservable {
	
	public void addObservateur(IProfileView pv);
	public void updateObservateur();
	public void delObservateur();
}
