package de.dpma.azubiweb.view;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.User;

public class LoginPage extends RootPage {
	
	private static final long serialVersionUID = 3863219283612212097L;
	
	public LoginPage() {
		
		super();
		initial();
	}
	
	public LoginPage(PageParameters parameters) {
		
		super(parameters);
		initial();
	}
	
	private void initial() {
		
		setVisibleNav(true);
		
		FeedbackPanel fp = new FeedbackPanel("feedback");
		
		CheckBox rememberMeCheckBox = new CheckBox("rememberMe", new Model<>());
		Form<User> anmeldung = new Form<>("form");
		TextField<String> usernameTextField = new TextField<>("username", new PropertyModel<>(new User(), "username"));
		PasswordTextField passwordTextfield = new PasswordTextField("password",
				new PropertyModel<>(new User(), "password"));
		Button passwortVergessenButton = new Button("user", Model.of()) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
				
				System.out.println(this.getModelObject());
				User user = session.getUserService().getUserByName(getModelObject());
				
				if (user != null) {
					// Simple Mail ... hinzufügen und eine E-Mail Senden lassen
					// (user.getEmail());
					LoginPage.this.success("Es wurde eine E-Mail an den Benutzer gesendet");
				}
				else
					LoginPage.this.error("Es wurde kein Benutzer mit den Benutzernamen gefunden");
				// setResponsePage(getPage());
			}
		};
		passwortVergessenButton.setDefaultFormProcessing(false);
		anmeldung.add(usernameTextField, passwordTextfield, fp, rememberMeCheckBox, passwortVergessenButton);
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
		// anmeldung.add(new InputPannel<String>("passwortVergessenLink", "Bitte
		// geben Sie Ihren Benutzernamen ein.") {
		//
		// /**
		// *
		// */
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void onClick(AjaxRequestTarget arg0) {
		//
		// User user = session.getUserService().getUserByName(getModelObject());
		//
		// if (user != null) {
		// // Simple Mail ... hinzufügen und eine E-Mail Senden lassen
		// // (user.getEmail());
		// LoginPage.this.success("Es wurde eine E-Mail an den Benutzer
		// gesendet");
		// }
		// else
		// LoginPage.this.error("Es wurde kein Benutzer mit den Benutzernamen
		// gefunden");
		// setResponsePage(getPage());
		// }
		//
		// });
		
		add(anmeldung);
	}
}
