package de.dpma.azubiweb.view;

import java.util.Arrays;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.model.User.Geschlecht;

public class BenutzerBearbeiten extends BenutzerVerwaltungsBasePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1996614134364296195L;
	
	@SuppressWarnings("Nicht verwendbar")
	public BenutzerBearbeiten(PageParameters params) {
		
		super(params);
		if (!params.get("User").isNull() || !params.get("User").isEmpty())
			initial(params.get("User").to(User.class));
	}
	
	public BenutzerBearbeiten(User user) {
		
		super();
		initial(user);
	}
	
	private void initial(User user) {
		
		if (user == null) {
			setResponsePage(StartPage.class);
			Session.get().fatal("Es wurde eine leerer User übergeben!");
			return;
		}
		DropDownChoice<Geschlecht> geschlechtDropDownChoice = new DropDownChoice<>("geschlechtDropDownChoice",
				Model.ofList(Arrays.asList(Geschlecht.values())));
		geschlechtDropDownChoice.setModel(Model.of(user.getGeschlecht()));
		TextField<String> vornameTextField = new TextField<>("vornameTextField", Model.of(user.getVorname()));
		TextField<String> nachnameTextField = new TextField<>("nachnameTextField", Model.of(user.getNachname()));
		PasswordTextField passwordPasswordField = new PasswordTextField("passwordPasswordField");
		EmailTextField emailEmailTextField = new EmailTextField("emailEmailTextField", Model.of(user.getEmail()));
		NumberTextField<Integer> einstellungsjahrNumberTextField = new NumberTextField<>(
				"einstellungsjahrNumberTextField");
		if (user.getEinstiegsjahr() != null)
			einstellungsjahrNumberTextField.setModel(Model.of(user.getEinstiegsjahr()));
		CheckBox täglichesBerichtsheftCheckBox = new CheckBox("täglichesBerichtsheftCheckBox");
		// BUG user.getAusbildungsart() ist immer leer oder null
		// if (user.getAusbildungsart() != null &&
		// !user.getAusbildungsart().isEmpty())
		täglichesBerichtsheftCheckBox.setModel(Model.of(user.getAusbildungsart().get(0).isTäglichesberichtsheft()));
		Form<?> userForm = new Form<Void>("userForm") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit() {
				
				if (!vornameTextField.getModelObject().trim().isEmpty())
					user.setVorname(vornameTextField.getModelObject().trim());
				if (!nachnameTextField.getModelObject().trim().isEmpty())
					user.setNachname(nachnameTextField.getModelObject().trim());
				if (!passwordPasswordField.getModelObject().trim().isEmpty())
					user.setPassword(passwordPasswordField.getModelObject().trim());
				if (!emailEmailTextField.getModelObject().trim().isEmpty())
					user.setEmail(emailEmailTextField.getModelObject().trim());
				if (!(einstellungsjahrNumberTextField.getModelObject().intValue() == 0))
					user.setEinstiegsjahr(einstellungsjahrNumberTextField.getModelObject());
				session.getUserService().updateUser(user);
			}
		};
		
		userForm.add(geschlechtDropDownChoice, vornameTextField, nachnameTextField, passwordPasswordField,
				emailEmailTextField, einstellungsjahrNumberTextField, täglichesBerichtsheftCheckBox);
		
		add(userForm);
	}
	
}
