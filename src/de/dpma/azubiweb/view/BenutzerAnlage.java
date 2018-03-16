package de.dpma.azubiweb.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.Rolle;
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
		geschlechtDropDownChoice.setDefaultModel(Model.of());
		DropDownChoice<Rolle> rolleDropDownChoice = new DropDownChoice<>("rolleDropDownChoice",
				Model.ofList(rolleService.getAllRolles()));
		rolleDropDownChoice.setDefaultModel(Model.of());
		TextField<String> vornameTextField = new TextField<>("vornameTextField", Model.of());
		TextField<String> nachnameTextField = new TextField<>("nachnameTextField", Model.of());
		TextField<String> benutzernameTextField = new TextField<>("benutzernameTextField", Model.of());
		EmailTextField emailEmailTextField = new EmailTextField("emailEmailTextField", Model.of());
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
		ausbildungsartDropDownChoice.setDefaultModel(Model.of());
		Form<?> userForm = new Form<Void>("userForm") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit() {
				
				user.setGeschlecht(geschlechtDropDownChoice.getModelObject());
				user.setRolle(rolleDropDownChoice.getModelObject());
				if (!vornameTextField.getModelObject().trim().isEmpty())
					user.setVorname(vornameTextField.getModelObject().trim());
				if (!nachnameTextField.getModelObject().trim().isEmpty())
					user.setNachname(nachnameTextField.getModelObject().trim());
				if (!benutzernameTextField.getModelObject().trim().isEmpty())
					user.setUsername(benutzernameTextField.getModelObject().trim());
				if (!emailEmailTextField.getModelObject().trim().isEmpty())
					user.setEmail(emailEmailTextField.getModelObject().trim());
				if (!(einstellungsjahrNumberTextField.getModelObject().intValue() == 0))
					user.setEinstiegsjahr(einstellungsjahrNumberTextField.getModelObject());
				user.setAusbildungsart(Arrays.asList(ausbildungsartService
						.getAusbildungsartByAbkürzung(ausbildungsartDropDownChoice.getModelObject())));
				// TODO Passwort generieren und per E-Mail senden
				user.setPassword("Anfang12");
				session.getUserService().saveUser(user);
			}
		};
		
		userForm.add(geschlechtDropDownChoice, rolleDropDownChoice, vornameTextField, nachnameTextField,
				benutzernameTextField, emailEmailTextField, einstellungsjahrNumberTextField,
				ausbildungsartDropDownChoice);
		
		add(userForm);
	}
}
