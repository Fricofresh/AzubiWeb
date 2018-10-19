package de.dpma.azubiweb.view;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.User;

/**
 * Controller für die Anmelde Seite. <br>
 * Die Klasse ruft {@link StartPage} auf, bei erfolgreicher Anmeldung.
 * 
 * @author Kenneth Böhmer
 *
 */
public class LoginPage extends RootPage {
	
	private static final long serialVersionUID = 3863219283612212097L;
	
	/**
	 * @see #initial()
	 */
	public LoginPage() {
		
		super();
		initial();
	}
	
	/**
	 * @see #initial()
	 * @param pageParameters
	 */
	public LoginPage(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	/**
	 * Wird aufgerufen, wenn ein Objekt von {@link LoginPage} erstellt wurde
	 * bzw. die View von {@link LoginPage} aufgerufen wurde. <br>
	 * Setzt die Überschrift und belegt die {@link Component} mit Funktionen.
	 */
	private void initial() {
		
		titelLabel.setDefaultModelObject("Anmelden");
		CheckBox rememberMeCheckBox = new CheckBox("rememberMe", new Model<>());
		Form<User> anmeldung = new Form<>("form");
		TextField<String> usernameTextField = new TextField<>("username", new PropertyModel<>(new User(), "username"));
		PasswordTextField passwordTextfield = new PasswordTextField("password",
				new PropertyModel<>(new User(), "password"));
		// Button passwortVergessenButton = new Button("user", Model.of()) {
		TextField<String> userLabel = new TextField<>("userLabel", Model.of());
		Form<String> passwortVergessen = new Form<String>("passwortVergessen") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
				
				User user = session.getUserService().getUserByName(userLabel.getModelObject());
				
				if (user != null) {
					// Simple Mail ... hinzufügen und eine E-Mail Senden lassen
					// (user.getEmail());
					// LoginPage.this.success("Es wurde eine E-Mail an den
					// Benutzer gesendet");
					LoginPage.this.success("Bitte wenden Sie sich an einen Ausbildungsleiter.\n"
							+ "Derzeit ist die Funktion noch nicht implementiert.");
				}
				else
					LoginPage.this.error("Es wurde kein Benutzer mit den Benutzernamen gefunden");
			}
		};
		passwortVergessen.add(userLabel);
		add(passwortVergessen);
		anmeldung.add(usernameTextField, passwordTextfield, rememberMeCheckBox);
		anmeldung.add(new Button("loginButton") {
			
			private static final long serialVersionUID = 1196988521149423209L;
			
			@Override
			public void onSubmit() {
				
				String username = usernameTextField.getModelObject();
				String password = passwordTextfield.getModelObject();
				if (session.signIn(username, password))
					setResponsePage(StartPage.class);
			}
		});
		add(anmeldung/* , fp */);
	}
}
