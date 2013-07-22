package com.novedia.talentmap.web.commons;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;

public class Images {

    private static final Resource resImgFeuBleu = new ThemeResource("./images/FeuBleu.jpg");
    private static final Resource resImgFeuJaune = new ThemeResource("./images/FeuJaune.jpg");
    private static final Resource resImgFeuOrange = new ThemeResource("./images/FeuOrange.jpg");
    private static final Resource resImgFeuVert = new ThemeResource("./images/FeuVert.jpg");
    private static final Resource resImgFeuRouge = new ThemeResource("./images/FeuRouge.jpg");

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
}
