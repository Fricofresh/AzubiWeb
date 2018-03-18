package de.dpma.azubiweb.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.Rolle.Beschreibung;
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
		List<String> rollenData = new ArrayList<>();
		for (Rolle rolle : rolleService.getAllRolles())
			rollenData.add(rolle.getBeschreibung());
		DropDownChoice<String> rolleDropDownChoice = new DropDownChoice<>("rolleDropDownChoice", rollenData);
		rolleDropDownChoice.setModel(Model.of(user.getRolle().getBeschreibung()));
		TextField<String> vornameTextField = new TextField<>("vornameTextField", Model.of(user.getVorname()));
		TextField<String> nachnameTextField = new TextField<>("nachnameTextField", Model.of(user.getNachname()));
		TextField<String> benutzernameTextField = new TextField<>("benutzernameTextField",
				Model.of(user.getUsername()));
		EmailTextField emailEmailTextField = new EmailTextField("emailEmailTextField", Model.of(user.getEmail()));
		NumberTextField<Integer> einstellungsjahrNumberTextField = new NumberTextField<>(
				"einstellungsjahrNumberTextField");
		if (user.getEinstiegsjahr() != null)
			einstellungsjahrNumberTextField.setModel(Model.of(user.getEinstiegsjahr()));
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
		ausbildungsartDropDownChoice.setModel(Model.of(user.getAusbildungsart().get(0).getBerufsbildAbkürzung()));
		Form<?> userForm = new Form<Void>("userForm") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit() {
				
				user.setGeschlecht(geschlechtDropDownChoice.getModelObject());
				user.setRolle(
						rolleService.getRolle(Beschreibung.valueOfString(rolleDropDownChoice.getModelObject())));
				if (!vornameTextField.getModelObject().trim().isEmpty())
					user.setVorname(vornameTextField.getModelObject().trim());
				if (!nachnameTextField.getModelObject().trim().isEmpty())
					user.setNachname(nachnameTextField.getModelObject().trim());
				if (!benutzernameTextField.getModelObject().trim().isEmpty())
					user.setUsername(benutzernameTextField.getModelObject().trim());
				if (!emailEmailTextField.getModelObject().trim().isEmpty())
					user.setEmail(emailEmailTextField.getModelObject().trim());
				user.setEinstiegsjahr(einstellungsjahrNumberTextField.getModelObject());
				user.setAusbildungsart(Arrays.asList(ausbildungsartService
						.getAusbildungsartByAbkürzung(ausbildungsartDropDownChoice.getModelObject())));
				session.getUserService().updateUser(user);
			}
		};
		
		userForm.add(geschlechtDropDownChoice, rolleDropDownChoice, vornameTextField, nachnameTextField,
				benutzernameTextField, emailEmailTextField, einstellungsjahrNumberTextField,
				ausbildungsartDropDownChoice);
		
		add(userForm);
	}
	
}
