package com.novedia.talentmap.web.util;

public interface IObservable {

	public void addObservateur(Object observateur, Class<?> cl);

	public void updateObservateur();

	public void delObservateur();
}
