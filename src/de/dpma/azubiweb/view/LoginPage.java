package de.dpma.azubiweb.view;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class LoginPage extends RootPage {
	
	private static final long serialVersionUID = 3863219283612212097L;
	
	public LoginPage(PageParameters parameters) {
		
		super(parameters);
		setVisibleNav(true);
		// new InitialInsert();
		// if (((UserSession) getSession()).isSignedIn()) {
		// continueToOriginalDestination();
		// }
		// FeedbackPanel fp = new FeedbackPanel("feedback");
		// Label fp = new Label("feedback");
		// if (!parameters.isEmpty() &&
		// parameters.get("auth").toString().equalsIgnoreCase("failed")) {
		// fp = new Label("feedback", "Bitte melden Sie sich an!");
		// fp.add(new AttributeModifier("style", "color=red"));
		// fp.setEnabled(true);
		// fp.setVisible(true);
		//
		// }
		add(new SignInPanel("signInPanel", true));
		// FeedbackPanel fp = new FeedbackPanel("feedback");
		// CheckBox rememberMeCheckBox = new CheckBox("rememberMe", new
		// Model<>());
		// // LogInForm anmeldung = new LogInForm("form", true);
		// // anmeldung.add(fp, new Button("loginButton"), rememberMeCheckBox);
		// Form<User> anmeldung = new Form<>("form");
		// TextField<String> usernameTextField = new TextField<>("username", new
		// PropertyModel<>(new User(), "username"));
		// PasswordTextField passwordTextfield = new
		// PasswordTextField("password",
		// new PropertyModel<>(new User(), "password"));
		// anmeldung.add(usernameTextField, passwordTextfield, fp,
		// rememberMeCheckBox);
		// anmeldung.add(new Button("loginButton") {
		//
		// private static final long serialVersionUID = 1196988521149423209L;
		//
		// @Override
		// public void onSubmit() {
		//
		// // TODO Validate
		// PageParameters pageParameters = new PageParameters();
		// String username = usernameTextField.getModelObject();
		// String password = passwordTextfield.getModelObject();
		// UserSession us = (UserSession) Session.get();
		// if (us.signIn(username, password))
		// System.out.println("success");
		//
		// setResponsePage(StartPage.class, pageParameters);
		// }
		// });
		//
		// add(anmeldung);
	}
	
}
