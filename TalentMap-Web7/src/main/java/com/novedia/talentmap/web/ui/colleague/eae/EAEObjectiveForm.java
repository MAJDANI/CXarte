package com.novedia.talentmap.web.ui.colleague.eae;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.entity.Objective;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

/**
 * Formulaire permettant d'afficher les données d'un Objectif d'Entretien
 * Annuel. Utilisé dans l'onglet des Résultats (du Bilan) et dans l'onglet de
 * définition des Objectifs.
 * 
 * @author v.guillemain
 * 
 */
public class EAEObjectiveForm extends FormLayout implements BlurListener {

	/**
	 * Titre de l'objectif
	 */
	@PropertyId(ComponentsId.OBJECTIVE_TITLE_ID)
	private Label title;

	/**
	 * But de l'objectif
	 */
	@PropertyId(ComponentsId.OBJECTIVE_GOAL_ID)
	private TextField goal;

	/**
	 * Date d'échéance de l'objectif
	 */
	@PropertyId(ComponentsId.OBJECTIVE_TARGET_DATE_ID)
	private TextField targetDate;

	/**
	 * Indicateurs permattant de mesure l'atteinte de l'objectif
	 */
	@PropertyId(ComponentsId.OBJECTIVE_INDICATORS_ID)
	private TextField indicators;

	/**
	 * Moyens envisagés par le manager pour aider le collaborateur à atteidre
	 * son objectifs
	 */
	@PropertyId(ComponentsId.OBJECTIVE_MEANS_ID)
	private TextField means;

	/**
	 * Formulaire parent qui sera chargé de la sauvegarde des modifications.
	 */
	private EAEResultsForm myFormParent;

	// ---------------------------------
	// NON INJECTED ATTRIBUTS
	// ---------------------------------
	/**
	 * Le layout
	 */
	private GridLayout eaeObjectivesFormLayout = new GridLayout();
	/**
	 * L'objectif à afficher
	 */
	private Objective currentObjective;
	/**
	 * Le mode de consultation en cours : permet de savoir si une donnée doit
	 * être visible, modifiable, ou en consultation seule.
	 */
	private EAEConsultationMode currentMode;
	/**
	 * A positionner par le formulaire qui englobe l'EAEObjectiveForm ici.
	 * L'affichage d'uhn objectif dans l'onglet "Bilan" est différent de
	 * l'afichage d'un objectif dans l'onglet "Objectifs"
	 */
	private Boolean objectifForBilan;
	/**
	 * Binder des données dans le formulaire
	 */
	private BeanFieldGroup<Objective> binder;

	/**
	 * Largeur de chaque élément du formulaire
	 */
	private final String ELEMENT_WIDTH = "450px";
	/**
	 * Hauteur de chaque élément du formulaire
	 */
	private final String ELEMENT_HEIGHT = "30px";

	private ResourceBundle resourceBundle;

	private void initResourceBundle() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES, locale);
	}

	/**
	 * En cas de Blur on lance une sauvegarde des données du formulaire
	 */
	@Override
	public void blur(BlurEvent event) {
		myFormParent.saveObjective(currentObjective);
	}

	/**
	 * Construit le formulaire à partir des données de l'Objectif passé en
	 * paramètre, en gérant l'accès aux champs du formulaire en fonction des
	 * deux autres paramètres : le currentMode et le booléen forBilan.
	 * 
	 * @param currentObjective
	 *            : Objectif dont les données sont à afficher dans le formulaire
	 * @param currentMode
	 *            : mode de consultation actuel du formulaire
	 * @param forBilan
	 *            : à true si le formulaire appelant est l'onglet "Bilan", false
	 *            si l'onglet appelant est l'onglet "Objectifs". L'accès aux
	 *            données du formulaire diffère enn fonction de ce paramètre
	 * @return le formulaire construit
	 */
	public EAEObjectiveForm buildEAEObjectiveFormView(
			Objective currentObjective, EAEConsultationMode currentMode,
			Boolean forBilan) {
		initResourceBundle();
		this.currentObjective = currentObjective;
		this.currentMode = currentMode;
		this.objectifForBilan = forBilan;
		removeAllComponents();
		buildMain();

		return this;
	}

	private void buildMain() {
		try {
			buildLayout();
			buildEAEObjectiveForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildLayout() {
		eaeObjectivesFormLayout.removeAllComponents();
		this.eaeObjectivesFormLayout.setColumns(1);
		this.eaeObjectivesFormLayout.setRows(7);
	}

	private void buildEAEObjectiveForm() {
		removeAllComponents();

		// -----------------------------
		// Instanciations
		// -----------------------------
		title = new Label();
		goal = new TextField();
		targetDate = new TextField();
		indicators = new TextField();
		means = new TextField();

		// -----------------------------
		// GOAL
		// -----------------------------
		goal.setCaption(resourceBundle.getString("goal.caption"));
		goal.setStyleName("TODO");
		goal.setNullRepresentation("");
		goal.setId(ComponentsId.OBJECTIVE_GOAL_ID);
		goal.setHeight(ELEMENT_HEIGHT);
		goal.setWidth(ELEMENT_WIDTH);
		goal.setMaxLength(200);
		if (currentMode == EAEConsultationMode.VALIDATED_MANAGER
				&& objectifForBilan == true) {
			goal.setImmediate(true);
			goal.addBlurListener(this);
//			goal.setWordwrap(true); //passage à la ligne automatique TODO
		}
		// -----------------------------
		// targetDate
		// -----------------------------
		targetDate.setCaption(resourceBundle.getString("targetDate.caption"));
		targetDate.setStyleName("TODO");
		targetDate.setNullRepresentation("");
		targetDate.setId(ComponentsId.OBJECTIVE_TARGET_DATE_ID);
		targetDate.setHeight(ELEMENT_HEIGHT);
		targetDate.setWidth(ELEMENT_WIDTH);
		targetDate.setMaxLength(200);
		if (currentMode == EAEConsultationMode.VALIDATED_MANAGER
				&& objectifForBilan == true) {
			targetDate.setImmediate(true);
			targetDate.addBlurListener(this);
		}
		// -----------------------------
		// indicators
		// -----------------------------
		indicators.setCaption(resourceBundle.getString("indicators.caption"));
		indicators.setStyleName("TODO");
		indicators.setNullRepresentation("");
		indicators.setId(ComponentsId.OBJECTIVE_INDICATORS_ID);
		indicators.setHeight(ELEMENT_HEIGHT);
		indicators.setWidth(ELEMENT_WIDTH);
		indicators.setMaxLength(200);
		if (currentMode == EAEConsultationMode.VALIDATED_MANAGER
				&& objectifForBilan == true) {
			indicators.setImmediate(true);
			indicators.addBlurListener(this);
		}

		// -----------------------------
		// means
		// -----------------------------
		means.setCaption(resourceBundle.getString("means.caption"));
		means.setStyleName("TODO");
		means.setNullRepresentation("");
		means.setId(ComponentsId.OBJECTIVE_MEANS_ID);
		means.setHeight(ELEMENT_HEIGHT);
		means.setWidth(ELEMENT_WIDTH);
		means.setMaxLength(200);
		if (currentMode == EAEConsultationMode.VALIDATED_MANAGER
				&& objectifForBilan == true) {
			means.setImmediate(true);
			means.addBlurListener(this);
		}

		// -----------------------------------
		// Binding des éléments du formulaire
		// -----------------------------------
		binder = new BeanFieldGroup<Objective>(Objective.class);
		binder.setItemDataSource(currentObjective);
		binder.setBuffered(false);
		binder.bindMemberFields(this);

		// ---------------------------------
		// Intégration
		// ---------------------------------
		eaeObjectivesFormLayout.addComponent(goal, 0, 0);
		eaeObjectivesFormLayout.addComponent(targetDate, 0, 1);
		eaeObjectivesFormLayout.addComponent(indicators, 0, 2);
		eaeObjectivesFormLayout.addComponent(means, 0, 3);

		// ---------------------------------
		// Désactivation si nécessaire
		// ---------------------------------
		if (!(currentMode == EAEConsultationMode.VALIDATED_MANAGER && objectifForBilan == true)) {
			for (int col = 0; col < 4; col++) {
				Component theComponent = eaeObjectivesFormLayout.getComponent(
						0, col);
				theComponent.setReadOnly(true);
			}
		}

		addComponent(eaeObjectivesFormLayout);

	}

	public Label getTitle() {
		return title;
	}

	public void setTitle(Label title) {
		this.title = title;
	}

	public TextField getGoal() {
		return goal;
	}

	public void setGoal(TextField goal) {
		this.goal = goal;
	}

	public TextField getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(TextField targetDate) {
		this.targetDate = targetDate;
	}

	public TextField getIndicators() {
		return indicators;
	}

	public void setIndicators(TextField indicators) {
		this.indicators = indicators;
	}

	public TextField getMeans() {
		return means;
	}

	public void setMeans(TextField means) {
		this.means = means;
	}

	public EAEResultsForm getMyFormParent() {
		return myFormParent;
	}

	public void setMyFormParent(EAEResultsForm myFormParent) {
		this.myFormParent = myFormParent;
	}

}
