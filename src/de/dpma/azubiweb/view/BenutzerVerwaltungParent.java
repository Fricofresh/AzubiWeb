package de.dpma.azubiweb.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.Ansprechpartner;
import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.Referat;
import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.model.User.Geschlecht;

/**
 * Enthält alle Funktionen für {@link BenutzerAnlage} und
 * {@link BenutzerBearbeiten}.
 * 
 * @author Kenneth Böhmer
 *
 */
public class BenutzerVerwaltungParent extends BenutzerVerwaltungsBasePage {
	
	private static final long serialVersionUID = 9031648713788591454L;
	
	/**
	 * @see #initial(User)
	 */
	public BenutzerVerwaltungParent() {
		
		super();
		initial(new User());
	}
	
	/**
	 * NICHT VERWENDEN, zur vollständigkeitshalber hinzugefügt.
	 * 
	 * @param pageParameters
	 */
	@SuppressWarnings("Nicht verwendbar")
	public BenutzerVerwaltungParent(PageParameters pageParameters) {
		
		super(pageParameters);
		if (!pageParameters.get("User").isNull() || !pageParameters.get("User").isEmpty())
			initial(pageParameters.get("User").to(User.class));
	}
	
	/**
	 * 
	 * @see #initial(User)
	 * 
	 * @param user
	 */
	public BenutzerVerwaltungParent(User user) {
		
		super();
		initial(user);
	}
	
	/**
	 * Wird aufgerufen, wenn ein Objekt von einer der Klassen, die von
	 * {@link BenutzerVerwaltungParent} erben, erstellt wurde bzw. die View, von
	 * einer der Klassen {@link BenutzerVerwaltungParent} erben, aufgerufen
	 * wurde. <br>
	 * Belegt die {@link Component} mit Funktionen und entscheidet ob ein neuer
	 * Benutzer angelegt werden soll oder ein Benutzer bearbeitet wird.
	 * 
	 * @param oldUser
	 *            der zu bearbeitende <strong>{@link User Benutzer}</strong> |
	 *            bei einem leeren <strong>{@link User Benutzer}</strong> wird
	 *            ein neuer erstellt.<br>
	 *            Bei <strong>null</strong> wird ein NullPointerException
	 *            geworfen.
	 */
	protected void initial(User oldUser) {
		
		if (oldUser == null)
			throw new NullPointerException("Benutzer darf nicht null sein");
		// Injector.get().inject(this);
		User newUser = oldUser;
		
		DropDownChoice<Geschlecht> geschlechtDropDownChoice = initGeschlechtDropDownChoice();
		DropDownChoice<String> rolleDropDownChoice = initRolleDropDownChoice();
		DropDownChoice<String> referatDropDownChoice = initReferatDropDownChoice();
		DateTextField geburtstagDateTextField = initGeburtstagDateTextField();
		TextField<String> vornameTextField = initVornameTextField();
		TextField<String> nachnameTextField = initNachnameTextField();
		TextField<String> benutzernameTextField = initBenutzernameTextField();
		EmailTextField emailEmailTextField = initEmailEmailTextField();
		NumberTextField<Integer> einstellungsjahrNumberTextField = initEinstellungsjahrNumberTextField();
		DropDownChoice<String> ausbildungsartDropDownChoice = initAusbildungsartDropDownChoice();
		
		// Überprüfung ob ein Benutzer übergeben wurde
		boolean isNew = newUser.isEmpty();
		
		Button speichernUndZurückButton = initSpeichernUndZurückButton(newUser, isNew);
		
		// Setzen der Input-Felder, wenn ein Benutzer übergeben wurde
		if (!isNew) {
			geschlechtDropDownChoice.setModel(Model.of(newUser.getGeschlecht()));
			rolleDropDownChoice.setModel(Model.of(newUser.getRolle().getBeschreibung()));
			if (newUser.getRolle().getId() == Beschreibung.A.getRolleId()
					&& referatService.getReferatByAnsprechpartner(newUser) != null)
				referatDropDownChoice
						.setModel(Model.of(referatService.getReferatByAnsprechpartner(newUser).getReferat()));
			vornameTextField.setModel(Model.of(newUser.getVorname()));
			nachnameTextField.setModel(Model.of(newUser.getNachname()));
			benutzernameTextField.setModel(Model.of(newUser.getUsername()));
			emailEmailTextField.setModel(Model.of(newUser.getEmail()));
			// geburtstagDateTextField.setModel(Model.of(newUser.getGeburtsDatum()));
			if (newUser.getEinstiegsjahr() != null)
				einstellungsjahrNumberTextField.setModel(Model.of(newUser.getEinstiegsjahr()));
			if (!newUser.getAusbildungsart().isEmpty() && newUser.getAusbildungsart() != null)
				ausbildungsartDropDownChoice
						.setModel(Model.of(newUser.getAusbildungsart().get(0).getBerufsbildAbkürzung()));
		}
		
		WebMarkupContainer erfolgreicherAlertLabelParent = new WebMarkupContainer("erfolgreicherAlertLabelParent");
		Label erfolgreicherAlertLabel = new Label("erfolgreicherAlertLabel");
		erfolgreicherAlertLabelParent.setVisible(false);
		erfolgreicherAlertLabelParent.add(erfolgreicherAlertLabel);
		Button speichernButton = new Button("speichernButton", Model.of()) {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onAfterSubmit() {
				
				super.onAfterSubmit();
				// ermöglicht HTML Tags
				erfolgreicherAlertLabel.setEscapeModelStrings(false);
				// Setzen der Alertbox von Bootstrap
				erfolgreicherAlertLabel.setDefaultModel(
						Model.of("Der Benutzer <strong>" + newUser.getVorname() + " " + newUser.getNachname()
								+ "</strong> wurde erfolgreich " + (isNew ? "angelegt" : "bearbeitet") + "."));
				erfolgreicherAlertLabelParent.setVisible(true);
			}
		};
		
		Form<?> userForm = new Form<Void>("userForm") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit() {
				
				// Der Wert der Input-Felder werden in User gesetzt
				newUser.setGeschlecht(geschlechtDropDownChoice.getModelObject());
				newUser.setRolle(
						rolleService.getRolle(Beschreibung.valueOfString(rolleDropDownChoice.getModelObject())));
				if (!vornameTextField.getModelObject().trim().isEmpty())
					newUser.setVorname(vornameTextField.getModelObject().trim());
				if (!nachnameTextField.getModelObject().trim().isEmpty())
					newUser.setNachname(nachnameTextField.getModelObject().trim());
				newUser.setUsername(benutzernameTextField.getModelObject());
				newUser.setEmail(emailEmailTextField.getModelObject());
				if (newUser.getRolle().getId() == Beschreibung.AZUBI.getRolleId())
					newUser.setEinstiegsjahr(einstellungsjahrNumberTextField.getModelObject());
				newUser.setAusbildungsart(Arrays.asList(ausbildungsartService
						.getAusbildungsartByAbkürzung(ausbildungsartDropDownChoice.getModelObject())));
				// newUser.setGeburtsDatum(geburtstagDateTextField.getModelObject());
				
				// TODO Passwort generieren und per E-Mail senden
				if (isNew) {
					// Standart Passwort setzen
					newUser.setPassword("Anfang12");
					userService.saveUser(newUser);
				}
				else {
					userService.updateUser(newUser);
				}
				
				if (Beschreibung.A.getRolleId() == oldUser.getRolle().getId()) {
					Referat referat = referatService.getReferatByAnsprechpartner(oldUser);
					referatService.deleteAnsprechpartner(oldUser);
				}
				
				if (Beschreibung.A.getRolleId() == newUser.getRolle().getId()) {
					Referat referat = referatService.getReferatByReferat(referatDropDownChoice.getModelObject());
					referatService.addAnsprechpartner(new Ansprechpartner(referat, newUser));
				}
				
			}
		};
		
		userForm.add(geschlechtDropDownChoice, rolleDropDownChoice, referatDropDownChoice, vornameTextField,
				nachnameTextField, benutzernameTextField, emailEmailTextField, einstellungsjahrNumberTextField,
				ausbildungsartDropDownChoice, speichernUndZurückButton, speichernButton, geburtstagDateTextField);
		
		add(titelLabel, userForm, erfolgreicherAlertLabelParent);
	}
	
	private Button initSpeichernUndZurückButton(User user, boolean isNew) {
		
		Button speichernUndZurückButton = new Button("speichernUndZurückButton", Model.of()) {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onAfterSubmit() {
				
				setResponsePage(new BenutzerListe(user, isNew));
			}
		};
		return speichernUndZurückButton;
	}
	
	private NumberTextField<Integer> initEinstellungsjahrNumberTextField() {
		
		NumberTextField<Integer> einstellungsjahrNumberTextField = new NumberTextField<>(
				"einstellungsjahrNumberTextField", Model.of(LocalDate.now().getYear()));
		return einstellungsjahrNumberTextField;
	}
	
	private EmailTextField initEmailEmailTextField() {
		
		EmailTextField emailEmailTextField = new EmailTextField("emailEmailTextField", Model.of());
		return emailEmailTextField;
	}
	
	private TextField<String> initBenutzernameTextField() {
		
		TextField<String> benutzernameTextField = new TextField<>("benutzernameTextField", Model.of());
		return benutzernameTextField;
	}
	
	private TextField<String> initNachnameTextField() {
		
		TextField<String> nachnameTextField = new TextField<>("nachnameTextField", Model.of());
		return nachnameTextField;
	}
	
	private TextField<String> initVornameTextField() {
		
		return new TextField<>("vornameTextField", Model.of());
	}
	
	private DropDownChoice<String> initAusbildungsartDropDownChoice() {
		
		List<String> ausbildungsart = new ArrayList<>();
		for (Ausbildungsart ausbildungsarten : ausbildungsartService.getAllAusbildungsart()) {
			if (ausbildungsarten.getBerufsbildAbkürzung() == null
					|| ausbildungsarten.getBerufsbildAbkürzung().isEmpty())
				ausbildungsart.add(ausbildungsarten.getBerufsbildM());
			else
				ausbildungsart.add(ausbildungsarten.getBerufsbildAbkürzung());
		}
		DropDownChoice<String> ausbildungsartDropDownChoice = new DropDownChoice<>("ausbildungsartDropDownChoice",
				Model.ofList(ausbildungsart));
		ausbildungsartDropDownChoice.setDefaultModel(Model.of());
		return ausbildungsartDropDownChoice;
	}
	
	private DateTextField initGeburtstagDateTextField() {
		
		DateTextField geburtstagDateTextField = new DateTextField("birthdayDateTextField", Model.of());
		/*
		 * DatePicker datePicker = new DatePicker() { private static final long
		 * serialVersionUID = 6400867917526511761L;
		 * 
		 * @Override protected CharSequence getIconUrl() { // Icon verschwinden
		 * lassen return null; }
		 * 
		 * }; datePicker.setShowOnFieldClick(true);
		 * datePicker.setAutoHide(true);
		 * geburtstagDateTextField.add(datePicker);
		 */
		return geburtstagDateTextField;
	}
	
	private DropDownChoice<String> initReferatDropDownChoice() {
		
		List<String> referatData = new ArrayList<>();
		for (Referat referat : referatService.getAllReferat())
			referatData.add(referat.getReferat());
		DropDownChoice<String> referatDropDownChoice = new DropDownChoice<>("referatDropDownChoice", referatData);
		referatDropDownChoice.setDefaultModel(Model.of());
		return referatDropDownChoice;
	}
	
	private DropDownChoice<String> initRolleDropDownChoice() {
		
		List<String> rollenData = new ArrayList<>();
		for (Rolle rolle : rolleService.getAllRolles())
			rollenData.add(rolle.getBeschreibung());
		DropDownChoice<String> rolleDropDownChoice = new DropDownChoice<>("rolleDropDownChoice", rollenData);
		rolleDropDownChoice.setDefaultModel(Model.of());
		return rolleDropDownChoice;
	}
	
	private DropDownChoice<Geschlecht> initGeschlechtDropDownChoice() {
		
		DropDownChoice<Geschlecht> geschlechtDropDownChoice = new DropDownChoice<>("geschlechtDropDownChoice",
				Model.ofList(Arrays.asList(Geschlecht.values())));
		geschlechtDropDownChoice.setDefaultModel(Model.of());
		return geschlechtDropDownChoice;
	}
}
