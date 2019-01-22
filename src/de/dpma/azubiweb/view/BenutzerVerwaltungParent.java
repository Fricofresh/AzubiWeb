package de.dpma.azubiweb.view;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.Ansprechpartner;
import de.dpma.azubiweb.model.Referat;
import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.model.User.Geschlecht;
import de.dpma.azubiweb.view.panel.UserInformationPanel;

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
	protected void initial(final User oldUser) {
		
		if (oldUser == null)
			throw new NullPointerException("Benutzer darf nicht null sein");
		
		User newUser = (User) oldUser.clone();
		
		boolean isNew = oldUser.isEmpty();
		
		UserInformationPanel userInformationPanel = new UserInformationPanel("userInformationPanel",
				isNew ? Model.of() : Model.of(oldUser));
		
		DropDownChoice<Geschlecht> geschlechtDropDownChoice = userInformationPanel.getGeschlechtDropDownChoice();
		DropDownChoice<String> rolleDropDownChoice = userInformationPanel.getRolleDropDownChoice();
		DropDownChoice<String> referatDropDownChoice = userInformationPanel.getReferatDropDownChoice();
		TextField<String> vornameTextField = userInformationPanel.getVornameTextField();
		TextField<String> nachnameTextField = userInformationPanel.getNachnameTextField();
		TextField<String> benutzernameTextField = userInformationPanel.getBenutzernameTextField();
		EmailTextField emailEmailTextField = userInformationPanel.getEmailEmailTextField();
		NumberTextField<Integer> einstellungsjahrNumberTextField = userInformationPanel
				.getEinstellungsjahrNumberTextField();
		DropDownChoice<String> ausbildungsartDropDownChoice = userInformationPanel.getAusbildungsartDropDownChoice();
		PasswordTextField passwortPasswordField = userInformationPanel.getPasswortPasswordField();
		WebMarkupContainer erfolgreicherAlertLabelParent = getErfolgreicherAlertLabelParent();
		Label erfolgreicherAlertLabel = getErfolgreicherAlertLabel(erfolgreicherAlertLabelParent);
		Button speichernUndZurückButton = initSpeichernUndZurückButton(newUser, isNew);
		Button speichernButton = initSpeichernButton(newUser, erfolgreicherAlertLabelParent, erfolgreicherAlertLabel,
				isNew);
		
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
				if (passwortPasswordField.getValue() == null && passwortPasswordField.getValue().isEmpty()) {
					if (oldUser.getPassword().isEmpty()) {
						newUser.setPassword("Anfang12");
					}
				}
				else {
					newUser.setPassword(passwortPasswordField.getValue());
				}
				
				// TODO Passwort generieren und per E-Mail senden
				if (isNew) {
					userService.saveUser(newUser);
				}
				else {
					userService.updateUser(newUser);
				}
				
				if (!isNew && Beschreibung.A.getRolleId() == oldUser.getRolle().getId()) {
					referatService.deleteAnsprechpartner(oldUser);
				}
				
				if (Beschreibung.A.getRolleId() == newUser.getRolle().getId()) {
					Referat referat = referatService.getReferatByReferat(referatDropDownChoice.getModelObject());
					referatService.addAnsprechpartner(new Ansprechpartner(referat, newUser));
				}
				
			}
		};
		
		userForm.add(userInformationPanel, speichernUndZurückButton, speichernButton);
		
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
	
	private Button initSpeichernButton(User newUser, WebMarkupContainer erfolgreicherAlertLabelParent,
			Label erfolgreicherAlertLabel, boolean isNew) {
		
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
		return speichernButton;
	}
	
	public Label getErfolgreicherAlertLabel(WebMarkupContainer erfolgreicherAlertLabelParent) {
		
		Label erfolgreicherAlertLabel = new Label("erfolgreicherAlertLabel");
		erfolgreicherAlertLabelParent.setVisible(false);
		erfolgreicherAlertLabelParent.add(erfolgreicherAlertLabel);
		return erfolgreicherAlertLabel;
	}
	
	public WebMarkupContainer getErfolgreicherAlertLabelParent() {
		
		return new WebMarkupContainer("erfolgreicherAlertLabelParent");
	}
}
