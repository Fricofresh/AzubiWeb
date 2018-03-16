package de.dpma.azubiweb.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.model.User.Geschlecht;

public class BenutzerAnlage extends BenutzerVerwaltungsBasePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6527972472618497340L;
	
	public BenutzerAnlage() {
		
		super();
		initial();
	}
	
	public BenutzerAnlage(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	private void initial() {
		
		User user = new User();
		
		DropDownChoice<Geschlecht> geschlechtDropDownChoice = new DropDownChoice<>("geschlechtDropDownChoice",
				Model.ofList(Arrays.asList(Geschlecht.values())));
		TextField<String> vornameTextField = new TextField<>("vornameTextField");
		TextField<String> nachnameTextField = new TextField<>("nachnameTextField");
		PasswordTextField passwordPasswordField = new PasswordTextField("passwordPasswordField");
		EmailTextField emailEmailTextField = new EmailTextField("emailEmailTextField");
		NumberTextField<Integer> einstellungsjahrNumberTextField = new NumberTextField<>(
				"einstellungsjahrNumberTextField", Model.of(LocalDate.now().getYear()));
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
		CheckBox täglichesBerichtsheftCheckBox = new CheckBox("täglichesBerichtsheftCheckBox");
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
				session.getUserService().saveUser(user);
			}
		};
		
		userForm.add(geschlechtDropDownChoice, vornameTextField, nachnameTextField, passwordPasswordField,
				emailEmailTextField, einstellungsjahrNumberTextField, täglichesBerichtsheftCheckBox,
				ausbildungsartDropDownChoice);
		
		add(userForm);
	}
}
