package com.novedia.talentmap.web.ui.colleague.eae;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.entity.Objective;
import com.novedia.talentmap.model.entity.ObjectiveScoreEnum;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.EAEConsultationMode;
import com.novedia.talentmap.web.utils.EAETabEnum;
import com.novedia.talentmap.web.utils.ObjUtils;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

/**
 * Formulaire permettant d'afficher les données d'UN Objectif d'Entretien
 * Annuel. Utilisé dans l'onglet des Résultats (du Bilan) et dans l'onglet de
 * définition des Objectifs.
 * 
 * @author v.guillemain
 * 
 */
public class EAEObjectiveForm extends FormLayout implements BlurListener,
		Property.ValueChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4394760795881720951L;

	/**
	 * Titre de l'objectif
	 */
	@PropertyId(ComponentsId.OBJECTIVE_TITLE_ID)
	private Label title;
	@PropertyId(ComponentsId.OBJECTIVE_TITLE_ID)
	private TextField titleField;

	/**
	 * But de l'objectif
	 */
	@PropertyId(ComponentsId.OBJECTIVE_GOAL_ID)
	private TextField goal;

	/**
	 * Date d'échéance de l'objectif
	 */
	@PropertyId(ComponentsId.OBJECTIVE_TARGET_DATE_ID)
	private PopupDateField targetDate;

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
	 * Note que se donne le collaborateur sur l'atteinte de son objectif
	 */
	@PropertyId(ComponentsId.OBJECTIVE_COLL_SCORE_ID)
	private OptionGroup colleagueScore;

	/**
	 * Note que donne le manager au collaboarteur collaborateur sur l'atteinte
	 * de son objectif
	 */
	@PropertyId(ComponentsId.OBJECTIVE_MAN_SCORE_ID)
	private OptionGroup managerScore;

	/**
	 * The Colleague may indicate the elements during the year that either
	 * motived or retrained the achievement of the Objective.
	 */
	@PropertyId(ComponentsId.OBJECTIVE_MOTIVES_OR_RESTRAINTS_ID)
	private TextField motivesOrRestraints;

	/**
	 * The Colleague may indicate comments about the achievement of the
	 * Objective.
	 */
	@PropertyId(ComponentsId.OBJECTIVE_COMMENT_ID)
	private TextField comments;

	/**
	 * Formulaire parent qui sera chargé de la sauvegarde des modifications.
	 */
	private EAESaveObjectiveForm myFormParent;

	// ---------------------------------
	// NON INJECTED ATTRIBUTS
	// ---------------------------------
	/**
	 * Le layout
	 */
	private GridLayout eaeObjectiveFormLayout = new GridLayout();
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
	 * Pour permettre de savoir qi on affiche les objectifs dans l'onglet
	 * Results ou Objectives
	 */
	private EAETabEnum currentTab;
	/**
	 * Binder des données dans le formulaire
	 */
	private BeanFieldGroup<Objective> binder;

	/**
	 * Largeur de chaque élément du formulaire
	 */
	private final String ELEMENT_WIDTH = "500px";//580
	/**
	 * Hauteur de chaque élément du formulaire
	 */
	private final String ELEMENT_HEIGHT = "30px";

	private ResourceBundle resourceBundle;

	private void initResourceBundle() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(
				PropertiesFile.TALENT_MAP_PROPERTIES, locale);
	}

	/**
	 * En cas de Blur on lance une sauvegarde des données du formulaire
	 */
	@Override
	public void blur(BlurEvent event) {
		if (!validateEAEObjectiveForm()) {
			Notification.show(
					resourceBundle.getString("missing.or.invalid.field.msg"),
					Notification.Type.WARNING_MESSAGE);
		}
		myFormParent.saveObjective(currentObjective);

	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		myFormParent.saveObjective(currentObjective);
	}

	/**
	 * Test the EAEObjectiveForm validity
	 * 
	 * @return boolean
	 */
	private boolean validateEAEObjectiveForm() {
		boolean isValidGenerality = true;
		if (!this.binder.isValid()) {
			isValidGenerality = false;
		}
		return isValidGenerality;
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
			EAETabEnum currentTab, EAESaveObjectiveForm formParent) {
		initResourceBundle();
		this.currentObjective = currentObjective;
		this.currentMode = currentMode;
		this.currentTab = currentTab;
		this.myFormParent = formParent;
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
		eaeObjectiveFormLayout.removeAllComponents();
		eaeObjectiveFormLayout
				.setId(ComponentsId.EAE_OBJECTIVES_FORM_LAYOUT_ID);
		this.eaeObjectiveFormLayout.setColumns(1);
		this.eaeObjectiveFormLayout.setRows(9);
		this.eaeObjectiveFormLayout.setMargin(true);
		this.eaeObjectiveFormLayout.addStyleName("styleDeTest");
	}

	private void buildEAEObjectiveForm() {
		removeAllComponents();
		ObjUtils objUtils = ObjUtils.getInstance();

		// -----------------------------
		// Instanciations
		// -----------------------------
		title = new Label();
		titleField = new TextField();
		goal = new TextField();
		targetDate = new PopupDateField();
		indicators = new TextField();
		means = new TextField();
		motivesOrRestraints = new TextField();
		comments = new TextField();

		// -----------------------------
		// TITLE
		// -----------------------------
		String titleDisplayMode = objUtils.getMode(
				ComponentsId.OBJECTIVE_TITLE_ID, currentTab, currentMode);
		if (titleDisplayMode != ObjUtils.HIDDEN) {
			titleField.setStyleName("TODO");
			titleField.setNullRepresentation("");
			titleField.setId(ComponentsId.OBJECTIVE_TITLE_ID);
			titleField.setHeight(ELEMENT_HEIGHT);
			titleField.setWidth(ELEMENT_WIDTH);
			titleField.setMaxLength(200);
			titleField.setCaption(resourceBundle
					.getString("objective.title.caption"));
			if (titleDisplayMode == ObjUtils.UPDATE) {
				titleField.setImmediate(true);
				titleField.addBlurListener(this);
			} else {
				titleField.addStyleName("monStyleBorderNone");
			}
			eaeObjectiveFormLayout.addComponent(titleField, 0, 0);
		}

		// -----------------------------
		// GOAL
		// -----------------------------
		String goalDisplayMode = objUtils.getMode(
				ComponentsId.OBJECTIVE_GOAL_ID, currentTab, currentMode);
		if (goalDisplayMode != ObjUtils.HIDDEN) {
			goal.setStyleName("TODO");
			goal.setNullRepresentation("");
			goal.setId(ComponentsId.OBJECTIVE_GOAL_ID);
			goal.setHeight(ELEMENT_HEIGHT);
			goal.setWidth(ELEMENT_WIDTH);
			goal.setMaxLength(200);
			goal.setCaption(resourceBundle.getString("objective.goal.caption"));
			if (goalDisplayMode == ObjUtils.UPDATE) {
				goal.setImmediate(true);
				goal.addBlurListener(this);
			} else {
				goal.addStyleName("monStyleBorderNone");
			}
			eaeObjectiveFormLayout.addComponent(goal, 0, 1);
		}
		// -----------------------------
		// targetDate
		// -----------------------------
		String targetDisplayMode = objUtils.getMode(
				ComponentsId.OBJECTIVE_TARGET_DATE_ID, currentTab, currentMode);
		if (targetDisplayMode != ObjUtils.HIDDEN) {
			targetDate.setStyleName("TODO");
			targetDate.setId(ComponentsId.OBJECTIVE_TARGET_DATE_ID);
			targetDate.setHeight(ELEMENT_HEIGHT);
			targetDate.setWidth(ELEMENT_WIDTH);
			targetDate.setCaption(resourceBundle
					.getString("objective.targetDate.caption"));
			if (targetDisplayMode == ObjUtils.UPDATE) {
				targetDate.setImmediate(true);
				targetDate.addBlurListener(this);
			} else {
				targetDate.addStyleName("monStyleBorderNone");
			}
			eaeObjectiveFormLayout.addComponent(targetDate, 0, 2);
		}
		// -----------------------------
		// indicators
		// -----------------------------
		String indicDisplayMode = objUtils.getMode(
				ComponentsId.OBJECTIVE_INDICATORS_ID, currentTab, currentMode);
		if (indicDisplayMode != ObjUtils.HIDDEN) {
			indicators.setStyleName("TODO");
			indicators.setNullRepresentation("");
			indicators.setId(ComponentsId.OBJECTIVE_INDICATORS_ID);
			indicators.setHeight(ELEMENT_HEIGHT);
			indicators.setWidth(ELEMENT_WIDTH);
			indicators.setMaxLength(200);
			indicators.setCaption(resourceBundle
					.getString("objective.indicators.caption"));
			if (indicDisplayMode == ObjUtils.UPDATE) {
				indicators.setImmediate(true);
				indicators.addBlurListener(this);
			} else {
				indicators.addStyleName("monStyleBorderNone");
			}
			eaeObjectiveFormLayout.addComponent(indicators, 0, 3);
		}
		// -----------------------------
		// means
		// -----------------------------
		String meansDisplayMode = objUtils.getMode(
				ComponentsId.OBJECTIVE_MEANS_ID, currentTab, currentMode);
		if (meansDisplayMode != ObjUtils.HIDDEN) {
			means.setStyleName("TODO");
			means.setNullRepresentation("");
			means.setId(ComponentsId.OBJECTIVE_MEANS_ID);
			means.setHeight(ELEMENT_HEIGHT);
			means.setWidth(ELEMENT_WIDTH);
			means.setMaxLength(200);
			means.setCaption(resourceBundle
					.getString("objective.means.caption"));
			if (meansDisplayMode == ObjUtils.UPDATE) {
				means.setImmediate(true);
				means.addBlurListener(this);
			} else {
				means.addStyleName("monStyleBorderNone");
			}
			eaeObjectiveFormLayout.addComponent(means, 0, 4);
		}
		// -----------------------------
		// colleagueScore
		// -----------------------------
		// N'est affiché que pour l'onglet BILAN/RESULTS
		String colleagueScoreDisplayMode = objUtils.getMode(
				ComponentsId.OBJECTIVE_COLL_SCORE_ID, currentTab, currentMode);
		if (colleagueScoreDisplayMode != ObjUtils.HIDDEN) {
			colleagueScore = new OptionGroup(resourceBundle.getString("objective.score.colleague.caption"));
			colleagueScore.addItem(ObjectiveScoreEnum.NOT_ACHIEVED.getId());
			colleagueScore.setItemCaption(ObjectiveScoreEnum.NOT_ACHIEVED
					.getId(), resourceBundle
					.getString(ObjectiveScoreEnum.NOT_ACHIEVED.getLabel()));

			colleagueScore.addItem(ObjectiveScoreEnum.PARTLY_ACHIEVED.getId());
			colleagueScore.setItemCaption(ObjectiveScoreEnum.PARTLY_ACHIEVED
					.getId(), resourceBundle
					.getString(ObjectiveScoreEnum.PARTLY_ACHIEVED.getLabel()));

			colleagueScore.addItem(ObjectiveScoreEnum.ACHIEVED.getId());
			colleagueScore.setItemCaption(ObjectiveScoreEnum.ACHIEVED.getId(),
					resourceBundle.getString(ObjectiveScoreEnum.ACHIEVED
							.getLabel()));

			colleagueScore.addItem(ObjectiveScoreEnum.EXCEEDED.getId());
			colleagueScore.setItemCaption(ObjectiveScoreEnum.EXCEEDED.getId(),
					resourceBundle.getString(ObjectiveScoreEnum.EXCEEDED
							.getLabel()));

			colleagueScore.addStyleName("horizontal");

			colleagueScore.addValueChangeListener(this);
			if (colleagueScoreDisplayMode == ObjUtils.UPDATE) {
				colleagueScore.setRequired(true);
				colleagueScore.setRequiredError(resourceBundle
						.getString("objective.colleaguescore.error.message"));
				colleagueScore.setImmediate(true);
				colleagueScore.addValueChangeListener(this);
			}
			eaeObjectiveFormLayout.addComponent(colleagueScore, 0, 5);
		}
		// -----------------------------
		// managerScore
		// -----------------------------
		// N'est affiché que pour l'onglet BILAN/RESULTS
		String managerScoreDisplayMode = objUtils.getMode(
				ComponentsId.OBJECTIVE_MAN_SCORE_ID, currentTab, currentMode);
		if (managerScoreDisplayMode != ObjUtils.HIDDEN) {
			managerScore = new OptionGroup(resourceBundle.getString("objective.score.manager.caption"));
			managerScore.addItem(ObjectiveScoreEnum.NOT_ACHIEVED.getId());
			managerScore.setItemCaption(
					ObjectiveScoreEnum.NOT_ACHIEVED.getId(), resourceBundle
							.getString(ObjectiveScoreEnum.NOT_ACHIEVED
									.getLabel()));

			managerScore.addItem(ObjectiveScoreEnum.PARTLY_ACHIEVED.getId());
			managerScore.setItemCaption(ObjectiveScoreEnum.PARTLY_ACHIEVED
					.getId(), resourceBundle
					.getString(ObjectiveScoreEnum.PARTLY_ACHIEVED.getLabel()));

			managerScore.addItem(ObjectiveScoreEnum.ACHIEVED.getId());
			managerScore.setItemCaption(ObjectiveScoreEnum.ACHIEVED.getId(),
					resourceBundle.getString(ObjectiveScoreEnum.ACHIEVED
							.getLabel()));

			managerScore.addItem(ObjectiveScoreEnum.EXCEEDED.getId());
			managerScore.setItemCaption(ObjectiveScoreEnum.EXCEEDED.getId(),
					resourceBundle.getString(ObjectiveScoreEnum.EXCEEDED
							.getLabel()));

			managerScore.addStyleName("horizontal");

			managerScore.addValueChangeListener(this);
			if (colleagueScoreDisplayMode == ObjUtils.UPDATE) {
				managerScore.setImmediate(true);
				managerScore.addValueChangeListener(this);
			}
			eaeObjectiveFormLayout.addComponent(managerScore, 0, 6);
		}

		// -----------------------------
		// motivesOrRestraints
		// -----------------------------
		String motivesOrRestraintsDisplayMode = objUtils.getMode(
				ComponentsId.OBJECTIVE_MOTIVES_OR_RESTRAINTS_ID, currentTab,
				currentMode);
		if (motivesOrRestraintsDisplayMode != ObjUtils.HIDDEN) {
			motivesOrRestraints.setStyleName("TODO");
			motivesOrRestraints.setNullRepresentation("");
			motivesOrRestraints
					.setId(ComponentsId.OBJECTIVE_MOTIVES_OR_RESTRAINTS_ID);
			motivesOrRestraints.setHeight(ELEMENT_HEIGHT);
			motivesOrRestraints.setWidth(ELEMENT_WIDTH);
			motivesOrRestraints.setMaxLength(200);
			motivesOrRestraints.setCaption(resourceBundle
					.getString("objective.motivesOrRestraints.caption"));
			if (motivesOrRestraintsDisplayMode == ObjUtils.UPDATE) {
				motivesOrRestraints.setRequired(true);
				motivesOrRestraints.setRequiredError(resourceBundle
						.getString("objective.motives.error.message"));
				motivesOrRestraints.setImmediate(true);
				motivesOrRestraints.addBlurListener(this);
			} else {
				motivesOrRestraints.addStyleName("monStyleBorderNone");
			}
			eaeObjectiveFormLayout.addComponent(motivesOrRestraints, 0, 7);
		}

		// -----------------------------
		// comments
		// -----------------------------
		String commentsDisplayMode = objUtils.getMode(
				ComponentsId.OBJECTIVE_COMMENT_ID, currentTab, currentMode);
		if (commentsDisplayMode != ObjUtils.HIDDEN) {
			comments.addStyleName("spacerBottom");
			comments.setStyleName("TODO");
			comments.setNullRepresentation("");
			comments.setId(ComponentsId.OBJECTIVE_COMMENT_ID);
			comments.setHeight(ELEMENT_HEIGHT);
			comments.setWidth(ELEMENT_WIDTH);
			comments.setMaxLength(200);
			comments.setCaption(resourceBundle
					.getString("objective.comments.caption"));
			if (motivesOrRestraintsDisplayMode == ObjUtils.UPDATE) {
				comments.setImmediate(true);
				comments.addBlurListener(this);
			} else {
				comments.addStyleName("monStyleBorderNone");
			}
			eaeObjectiveFormLayout.addComponent(comments, 0, 8);
		}

		// -----------------------------------
		// Binding des éléments du formulaire
		// -----------------------------------
		binder = new BeanFieldGroup<Objective>(Objective.class);
		binder.setItemDataSource(currentObjective);
		binder.setBuffered(false);
		binder.bindMemberFields(this);

		// ----------------------------------------------------------
		// ReadOnly si nécessaire : goal, target, indicators et means
		// ----------------------------------------------------------
		if (titleDisplayMode == ObjUtils.READONLY) {
			eaeObjectiveFormLayout.getComponent(0, 0).setReadOnly(true);
		}
		if (goalDisplayMode == ObjUtils.READONLY) {
			eaeObjectiveFormLayout.getComponent(0, 1).setReadOnly(true);
		}
		if (targetDisplayMode == ObjUtils.READONLY) {
			eaeObjectiveFormLayout.getComponent(0, 2).setReadOnly(true);
		}
		if (indicDisplayMode == ObjUtils.READONLY) {
			eaeObjectiveFormLayout.getComponent(0, 3).setReadOnly(true);
		}
		if (meansDisplayMode == ObjUtils.READONLY) {
			eaeObjectiveFormLayout.getComponent(0, 4).setReadOnly(true);
		}
		if (colleagueScoreDisplayMode == ObjUtils.READONLY) {
			eaeObjectiveFormLayout.getComponent(0, 5).setReadOnly(true);
		}
		if (managerScoreDisplayMode == ObjUtils.READONLY) {
			eaeObjectiveFormLayout.getComponent(0, 6).setReadOnly(true);
		}
		if (motivesOrRestraintsDisplayMode == ObjUtils.READONLY) {
			eaeObjectiveFormLayout.getComponent(0, 7).setReadOnly(true);
		}
		if (commentsDisplayMode == ObjUtils.READONLY) {
			eaeObjectiveFormLayout.getComponent(0, 8).setReadOnly(true);
		}

		addComponent(eaeObjectiveFormLayout);

	}

	public EAESaveObjectiveForm getMyFormParent() {
		return myFormParent;
	}

	public void setMyFormParent(EAESaveObjectiveForm myFormParent) {
		this.myFormParent = myFormParent;
	}

	/**
	 * @return the colleagueScore
	 */
	public OptionGroup getColleagueScore() {
		return colleagueScore;
	}

	/**
	 * @param colleagueScore
	 *            the colleagueScore to set
	 */
	public void setColleagueScore(OptionGroup colleagueScore) {
		this.colleagueScore = colleagueScore;
	}

	/**
	 * @return the managerScore
	 */
	public OptionGroup getManagerScore() {
		return managerScore;
	}

	/**
	 * @param managerScore
	 *            the managerScore to set
	 */
	public void setManagerScore(OptionGroup managerScore) {
		this.managerScore = managerScore;
	}

	/**
	 * @return the motivesOrRestraints
	 */
	public TextField getMotivesOrRestraints() {
		return motivesOrRestraints;
	}

	/**
	 * @param motivesOrRestraints
	 *            the motivesOrRestraints to set
	 */
	public void setMotivesOrRestraints(TextField motivesOrRestraints) {
		this.motivesOrRestraints = motivesOrRestraints;
	}

	/**
	 * @return the comments
	 */
	public TextField getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(TextField comments) {
		this.comments = comments;
	}

}
