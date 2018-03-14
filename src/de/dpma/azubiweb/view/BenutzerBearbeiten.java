package de.dpma.azubiweb.view;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.User.Geschlecht;

public class BenutzerBearbeiten extends BenutzerVerwaltungsBasePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1996614134364296195L;
	
	public BenutzerBearbeiten() {
		
		initial();
	}
	
	public BenutzerBearbeiten(PageParameters params) {
		
		super(params);
		initial();
	}
	
	private void initial() {
		
		user = session.getUser();
		
		DropDownChoice<Geschlecht> geschlechtDropDownChoice = new DropDownChoice<>("geschlechtDropDownChoice",
				Model.ofList(Arrays.asList(Geschlecht.values())));
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
		if (user.getAusbildungsart() != null && !user.getAusbildungsart().isEmpty())
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
