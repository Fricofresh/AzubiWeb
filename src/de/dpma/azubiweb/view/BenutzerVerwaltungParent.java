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
	 * einer der Klassen {@link BenutzerVerwaltungParent} erben, aufgerufen wurde.
	 * <br>
	 * Belegt die {@link Component} mit Funktionen und entscheidet ob ein neuer
	 * Benutzer angelegt werden soll oder ein Benutzer bearbeitet wird.
	 * 
	 * @param user
	 *            der zu bearbeitende <strong>{@link User Benutzer}</strong> | bei
	 *            einem leeren <strong>{@link User Benutzer}</strong> wird ein neuer
	 *            erstellt.<br>
	 *            Bei <strong>null</strong> wird ein NullPointerException geworfen.
	 */
	protected void initial(User user) {

		if (user == null)
			throw new NullPointerException("Benutzer darf nicht null sein");
		// Components deklarieren und Model setzen
		DropDownChoice<Geschlecht> geschlechtDropDownChoice = new DropDownChoice<>("geschlechtDropDownChoice",
				Model.ofList(Arrays.asList(Geschlecht.values())));
		geschlechtDropDownChoice.setDefaultModel(Model.of());
		// Alle Rollen als Auswahlmöglichkeiten anbieten
		List<String> rollenData = new ArrayList<>();
		for (Rolle rolle : rolleService.getAllRolles())
			rollenData.add(rolle.getBeschreibung());
		DropDownChoice<String> rolleDropDownChoice = new DropDownChoice<>("rolleDropDownChoice", rollenData);
		rolleDropDownChoice.setDefaultModel(Model.of());
		// Alle Referate als Auswahlmöglichkeiten anbieten
		List<String> referatData = new ArrayList<>();
		for (Referat referat : referatService.getAllReferat())
			referatData.add(referat.getReferat());
		DropDownChoice<String> referatDropDownChoice = new DropDownChoice<>("referatDropDownChoice", referatData);
		referatDropDownChoice.setDefaultModel(Model.of());

		DateTextField geburtstagDateTextField = new DateTextField("birthdayDateTextField", Model.of());

		/*DatePicker datePicker = new DatePicker() {
			private static final long serialVersionUID = 6400867917526511761L;

			@Override
			protected CharSequence getIconUrl() {
				// Icon verschwinden lassen
				return null;
			}

		};
		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		geburtstagDateTextField.add(datePicker);*/

		TextField<String> vornameTextField = new TextField<>("vornameTextField", Model.of());
		TextField<String> nachnameTextField = new TextField<>("nachnameTextField", Model.of());
		TextField<String> benutzernameTextField = new TextField<>("benutzernameTextField", Model.of());
		EmailTextField emailEmailTextField = new EmailTextField("emailEmailTextField", Model.of());
		NumberTextField<Integer> einstellungsjahrNumberTextField = new NumberTextField<>(
				"einstellungsjahrNumberTextField", Model.of(LocalDate.now().getYear()));
		// Alle Ausbildungsarten als Auswahlmöglichkeiten anbieten
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

		// Überprüfung ob ein Benutzer übergeben wurde
		boolean isNew = user.isEmpty();

		Button speichernUndZurückButton = new Button("speichernUndZurückButton", Model.of()) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onAfterSubmit() {

				setResponsePage(BenutzerListe.class, new PageParameters().add("isNew", isNew).add("user",
						user.getVorname() + " " + user.getNachname()));
			}
		};
		// Setzen der Input-Felder, wenn ein Benutzer übergeben wurde
		if (!isNew) {
			geschlechtDropDownChoice.setModel(Model.of(user.getGeschlecht()));
			rolleDropDownChoice.setModel(Model.of(user.getRolle().getBeschreibung()));
			if (user.getRolle().getId() == Beschreibung.A.getRolleId()
					&& referatService.getReferatByAnsprechpartner(user) != null)
				referatDropDownChoice.setModel(Model.of(referatService.getReferatByAnsprechpartner(user).getReferat()));
			vornameTextField.setModel(Model.of(user.getVorname()));
			nachnameTextField.setModel(Model.of(user.getNachname()));
			benutzernameTextField.setModel(Model.of(user.getUsername()));
			emailEmailTextField.setModel(Model.of(user.getEmail()));
			geburtstagDateTextField.setModel(Model.of(user.getGeburtsDatum()));
			if (user.getEinstiegsjahr() != null)
				einstellungsjahrNumberTextField.setModel(Model.of(user.getEinstiegsjahr()));
			if (!user.getAusbildungsart().isEmpty() && user.getAusbildungsart() != null)
				ausbildungsartDropDownChoice
						.setModel(Model.of(user.getAusbildungsart().get(0).getBerufsbildAbkürzung()));
		}
		WebMarkupContainer erfolgreicherAlertLabelParent = new WebMarkupContainer("erfolgreicherAlertLabelParent");
		Label erfolgreicherAlertLabel = new Label("erfolgreicherAlertLabel");
		erfolgreicherAlertLabelParent.setVisible(false);
		erfolgreicherAlertLabelParent.add(erfolgreicherAlertLabel);
		Button speichernButton = new Button("speichernButton", Model.of()) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onAfterSubmit() {

				// ermöglicht HTML Tags
				erfolgreicherAlertLabel.setEscapeModelStrings(false);
				// Setzen der Alertbox von Bootstrap
				erfolgreicherAlertLabel
						.setDefaultModel(Model.of("Der Benutzer <strong>" + user.getVorname() + " " + user.getNachname()
								+ "</strong> wurde erfolgreich " + (isNew ? "angelegt" : "bearbeitet") + "."));
				erfolgreicherAlertLabelParent.setVisible(true);
			}
		};
		Form<?> userForm = new Form<Void>("userForm") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {

				// Der Wert der Input-Felder werden in User gesetzt
				user.setGeschlecht(geschlechtDropDownChoice.getModelObject());
				user.setRolle(rolleService.getRolle(Beschreibung.valueOfString(rolleDropDownChoice.getModelObject())));
				if (!vornameTextField.getModelObject().trim().isEmpty())
					user.setVorname(vornameTextField.getModelObject().trim());
				if (!nachnameTextField.getModelObject().trim().isEmpty())
					user.setNachname(nachnameTextField.getModelObject().trim());
				user.setUsername(benutzernameTextField.getModelObject());
				user.setEmail(emailEmailTextField.getModelObject());
				if (user.getRolle().getId() == Beschreibung.AZUBI.getRolleId())
					user.setEinstiegsjahr(einstellungsjahrNumberTextField.getModelObject());
				user.setAusbildungsart(Arrays.asList(ausbildungsartService
						.getAusbildungsartByAbkürzung(ausbildungsartDropDownChoice.getModelObject())));
				user.setGeburtsDatum(geburtstagDateTextField.getModelObject());

				// TODO Passwort generieren und per E-Mail senden
				if (!isNew)
					userService.updateUser(user);
				else {
					// Standart Passwort setzen
					user.setPassword("Anfang12");
					userService.saveUser(user);
				}
				if (user.getRolle().getId() == Beschreibung.A.getRolleId()) {
					Referat referat = referatService.getReferatByReferat(referatDropDownChoice.getModelObject());
					referat.addAnsprechpartner(user);
					referatService.updateAnsprechpartner(referat);
				}
			}
		};

		userForm.add(geschlechtDropDownChoice, rolleDropDownChoice, referatDropDownChoice, vornameTextField,
				nachnameTextField, benutzernameTextField, emailEmailTextField, einstellungsjahrNumberTextField,
				ausbildungsartDropDownChoice, speichernUndZurückButton, speichernButton, geburtstagDateTextField);

		add(titelLabel, userForm, erfolgreicherAlertLabelParent);
	}
}
