package de.dpma.azubiweb.view;

import org.apache.wicket.Component;
import org.apache.wicket.PageReference;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.dpma.azubiweb.service.UserService;
import de.dpma.azubiweb.util.AlertUtil.AlertType;
import de.dpma.azubiweb.view.panel.UserInformationPanel;

/**
 * Klasse für die Einstellungen für die einzelnen Benutzer
 * 
 * @author Kenneth Böhmer
 *
 */
@AuthorizeInstantiation({"Auszubildende", "Ausbildungsleiter", "Ausbilder"})
public class Einstellungen extends BenutzerVerwaltungsBasePage {
	
	private static final long serialVersionUID = -3177439622281163213L;
	
	@SpringBean
	private UserService userService;
	
	/**
	 * @see #initial()
	 */
	public Einstellungen() {
		
		super();
		initial();
	}
	
	/**
	 * @see #initial()
	 * @param pageParameters
	 */
	public Einstellungen(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	/**
	 * Wird aufgerufen, wenn ein Objekt von {@link Einstellungen} erstellt wurde
	 * bzw. die View von {@link Einstellungen} aufgerufen wurde. <br>
	 * Belegt die {@link Component} mit Funktionen.
	 */
	private void initial() {
		
		user = userService.getUserByID(user.getId());
		
		UserInformationPanel userInformationPanel = new UserInformationPanel("userInformationPanel", Model.of(user));
		userInformationPanel.handleToAll(e -> e.setEnabled(false));
		userInformationPanel.getPasswortPasswordField().setVisible(false);
		
		PasswordTextField neuesPasswortPasswordTextField = new PasswordTextField("neuesPasswortPasswordTextField",
				Model.of(""));
		Button bestätigenButton = new Button("bestätigenButton");
		Button abbrechenButton = new Button("abbrechenButton") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
				
				// Die vorherige eingabe wird ignoriert.
				// TODO Richtiges zurückgehen
				setResponsePage(new PageReference(getPageId() - 1).getPage().getClass());
			}
		};
		abbrechenButton.setDefaultFormProcessing(false);
		// einstellungenForm ist für die Passwort Änderung.
		Form<?> einstellungenForm = new Form<Void>("einstellungenForm") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
				
				String passwort = neuesPasswortPasswordTextField.getModel().getObject();
				if (!passwort.isEmpty() && passwort.length() >= 6) {
					user.setPassword(passwort);
					// Aktuallisiert das Passwort in der Datenbank
					if (session.getUserService().updateUserPasswort(user)) {
						// TODO Richtiges zurückgehen
						setAlert(AlertType.SUCCESS, "Passwort erfolgreich geändert.");
					}
					else // TODO zur vorherigen Seite weiterleiten
						Session.get().error("Ein Fehler ist aufgetreten");
				}
				else
					Session.get().error("Bitte geben Sie ein Passwort ein, dass mindestens 6 Zeichen lang ist.");
				
			}
		};
		einstellungenForm.add(neuesPasswortPasswordTextField, bestätigenButton, abbrechenButton);
		add(einstellungenForm, userInformationPanel);
	}
	
}
