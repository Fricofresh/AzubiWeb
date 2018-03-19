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
import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.model.User.Geschlecht;

public class BenutzerVerwaltungParent extends BenutzerVerwaltungsBasePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9031648713788591454L;
	
	public BenutzerVerwaltungParent() {
		
		super();
	}
	
	@SuppressWarnings("Nicht verwendbar")
	public BenutzerVerwaltungParent(PageParameters pageParameters) {
		
		super(pageParameters);
		if (!pageParameters.get("User").isNull() || !pageParameters.get("User").isEmpty())
			initial(pageParameters.get("User").to(User.class));
	}
	
	public BenutzerVerwaltungParent(User user) {
		
		super();
		initial(user);
	}
	
	protected void initial(User user) {
		
		boolean isNew = user == null || user.isEmpty();
		DropDownChoice<Geschlecht> geschlechtDropDownChoice = new DropDownChoice<>("geschlechtDropDownChoice",
				Model.ofList(Arrays.asList(Geschlecht.values())));
		geschlechtDropDownChoice.setDefaultModel(Model.of());
		List<String> rollenData = new ArrayList<>();
		for (Rolle rolle : rolleService.getAllRolles())
			rollenData.add(rolle.getBeschreibung());
		DropDownChoice<String> rolleDropDownChoice = new DropDownChoice<>("rolleDropDownChoice", rollenData);
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
		if (!isNew) {
			geschlechtDropDownChoice.setModel(Model.of(user.getGeschlecht()));
			rolleDropDownChoice.setModel(Model.of(user.getRolle().getBeschreibung()));
			vornameTextField.setModel(Model.of(user.getVorname()));
			nachnameTextField.setModel(Model.of(user.getNachname()));
			benutzernameTextField.setModel(Model.of(user.getUsername()));
			emailEmailTextField.setModel(Model.of(user.getEmail()));
			if (user.getEinstiegsjahr() != null)
				einstellungsjahrNumberTextField.setModel(Model.of(user.getEinstiegsjahr()));
			if (!user.getAusbildungsart().isEmpty() && user.getAusbildungsart() != null)
				ausbildungsartDropDownChoice
						.setModel(Model.of(user.getAusbildungsart().get(0).getBerufsbildAbkürzung()));
		}
		
		Form<?> userForm = new Form<Void>("userForm") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit() {
				
				user.setGeschlecht(geschlechtDropDownChoice.getModelObject());
				user.setRolle(rolleService.getRolle(Beschreibung.valueOfString(rolleDropDownChoice.getModelObject())));
				if (!vornameTextField.getModelObject().trim().isEmpty())
					user.setVorname(vornameTextField.getModelObject().trim());
				if (!nachnameTextField.getModelObject().trim().isEmpty())
					user.setNachname(nachnameTextField.getModelObject().trim());
				if (benutzernameTextField.getModelObject() != null
						&& !benutzernameTextField.getModelObject().trim().isEmpty())
					user.setUsername(benutzernameTextField.getModelObject().trim());
				if (emailEmailTextField.getModelObject() != null
						&& !emailEmailTextField.getModelObject().trim().isEmpty())
					user.setEmail(emailEmailTextField.getModelObject().trim());
				user.setEinstiegsjahr(einstellungsjahrNumberTextField.getModelObject());
				if (user.getRolle().getId() == Beschreibung.AZUBI.getRolleId())
					user.setAusbildungsart(Arrays.asList(ausbildungsartService
							.getAusbildungsartByAbkürzung(ausbildungsartDropDownChoice.getModelObject())));
				
				// TODO Passwort generieren und per E-Mail senden
				if (!isNew)
					userService.updateUser(user);
				else {
					user.setPassword("Anfang12");
					userService.saveUser(user);
				}
			}
		};
		
		userForm.add(geschlechtDropDownChoice, rolleDropDownChoice, vornameTextField, nachnameTextField,
				benutzernameTextField, emailEmailTextField, einstellungsjahrNumberTextField,
				ausbildungsartDropDownChoice);
		
		add(userForm);
	}
	
}
