package com.novedia.talentmap.web.utils;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Embedded;

public class Images {

	private static final Resource resImgFeuBleu = new ThemeResource(
			"./img/FeuBleu.jpg");
	private static final Resource resImgFeuJaune = new ThemeResource(
			"./img/FeuJaune.jpg");
	private static final Resource resImgFeuOrange = new ThemeResource(
			"./img/FeuOrange.jpg");
	private static final Resource resImgFeuVert = new ThemeResource(
			"./img/FeuVert.jpg");
	private static final Resource resImgFeuRouge = new ThemeResource(
			"./img/FeuRouge.jpg");
	private static final Resource resImgButtonGreen = new ThemeResource(
			"./img/buttonGreen.png");
	private static final Resource resImgButtonRed = new ThemeResource(
			"./img/buttonRed.png");
	private static final Resource resImgButtonOrange = new ThemeResource(
			"./img/buttonOrange.png");

	public static Embedded getImgFeuVert() {
		return new Embedded("", resImgFeuVert);
	}

	public static Embedded getImgFeuOrange() {
		return new Embedded("", resImgFeuOrange);
	}

	public static Embedded getImgFeuRouge() {
		return new Embedded("", resImgFeuRouge);
	}

	public static Embedded getImgFeuBleu() {
		return new Embedded("", resImgFeuBleu);
	}

	public static Embedded getImgFeuJaune() {
		return new Embedded("", resImgFeuJaune);
	}
	public static Embedded getImgButtonGreen() {
		return new Embedded("", resImgButtonGreen);
	}
	public static Embedded getImgButtonRed() {
		return new Embedded("", resImgButtonRed);
	}
	public static Embedded getImgButtonOrange() {
		return new Embedded("", resImgButtonOrange);
	}

}
