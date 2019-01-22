package de.dpma.azubiweb.view.panel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.Referat;
import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.model.User.Geschlecht;
import de.dpma.azubiweb.service.AusbildungsartService;
import de.dpma.azubiweb.service.ReferatService;
import de.dpma.azubiweb.service.RolleService;

public class UserInformationPanel extends Panel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4767162493269744536L;
	
	@SpringBean
	private ReferatService referatService;
	
	@SpringBean
	private RolleService rolleService;
	
	@SpringBean
	private AusbildungsartService ausbildungsartService;
	
	private DropDownChoice<Geschlecht> geschlechtDropDownChoice;
	
	private DropDownChoice<String> rolleDropDownChoice;
	
	private DropDownChoice<String> referatDropDownChoice;
	
	private TextField<String> vornameTextField;
	
	private TextField<String> nachnameTextField;
	
	private TextField<String> benutzernameTextField;
	
	private EmailTextField emailEmailTextField;
	
	private NumberTextField<Integer> einstellungsjahrNumberTextField;
	
	private DropDownChoice<String> ausbildungsartDropDownChoice;
	
	private PasswordTextField passwortPasswordField;
	
	public UserInformationPanel(String id) {
		
		super(id);
		init(Model.of());
	}
	
	public UserInformationPanel(String id, IModel<User> model) {
		
		super(id, model);
		init(model);
	}
	
	public void init(IModel<User> model) {
		
		initAndAddAllCompnents();
		User user = model.getObject();
		if (user != null && !user.isEmpty()) {
			getGeschlechtDropDownChoice().setModel(Model.of(user.getGeschlecht()));
			getRolleDropDownChoice().setModel(Model.of(user.getRolle().getBeschreibung()));
			if (user.getRolle().getId() == Beschreibung.A.getRolleId()
					&& referatService.getReferatByAnsprechpartner(user) != null)
				getReferatDropDownChoice()
						.setModel(Model.of(referatService.getReferatByAnsprechpartner(user).getReferat()));
			getVornameTextField().setModel(Model.of(user.getVorname()));
			getNachnameTextField().setModel(Model.of(user.getNachname()));
			getBenutzernameTextField().setModel(Model.of(user.getUsername()));
			getEmailEmailTextField().setModel(Model.of(user.getEmail()));
			if (user.getEinstiegsjahr() != null)
				getEinstellungsjahrNumberTextField().setModel(Model.of(user.getEinstiegsjahr()));
			if (!user.getAusbildungsart().isEmpty() && user.getAusbildungsart() != null)
				getAusbildungsartDropDownChoice()
						.setModel(Model.of(user.getAusbildungsart().get(0).getBerufsbildAbkürzung()));
		}
		
	}
	
	public void handleToAll(Consumer<Component> function) {
		
		List<Component> components = Arrays.asList(getGeschlechtDropDownChoice(), getReferatDropDownChoice(),
				getRolleDropDownChoice(), getNachnameTextField(), getBenutzernameTextField(), getEmailEmailTextField(),
				getEinstellungsjahrNumberTextField(), getAusbildungsartDropDownChoice(), getVornameTextField(),
				getPasswortPasswordField());
		components.forEach(function::accept);
	}
	
	private void initAndAddAllCompnents() {
		
		geschlechtDropDownChoice = initGeschlechtDropDownChoice();
		rolleDropDownChoice = initRolleDropDownChoice();
		referatDropDownChoice = initReferatDropDownChoice();
		vornameTextField = initVornameTextField();
		nachnameTextField = initNachnameTextField();
		benutzernameTextField = initBenutzernameTextField();
		emailEmailTextField = initEmailEmailTextField();
		einstellungsjahrNumberTextField = initEinstellungsjahrNumberTextField();
		ausbildungsartDropDownChoice = initAusbildungsartDropDownChoice();
		passwortPasswordField = initPasswortPasswordTextField();
		add(geschlechtDropDownChoice, rolleDropDownChoice, referatDropDownChoice, vornameTextField, nachnameTextField,
				benutzernameTextField, emailEmailTextField, einstellungsjahrNumberTextField,
				ausbildungsartDropDownChoice, passwortPasswordField);
	}
	
	public DropDownChoice<Geschlecht> getGeschlechtDropDownChoice() {
		
		return geschlechtDropDownChoice;
	}
	
	public DropDownChoice<String> getRolleDropDownChoice() {
		
		return rolleDropDownChoice;
	}
	
	public DropDownChoice<String> getReferatDropDownChoice() {
		
		return referatDropDownChoice;
	}
	
	public TextField<String> getVornameTextField() {
		
		return vornameTextField;
	}
	
	public TextField<String> getNachnameTextField() {
		
		return nachnameTextField;
	}
	
	public TextField<String> getBenutzernameTextField() {
		
		return benutzernameTextField;
	}
	
	public EmailTextField getEmailEmailTextField() {
		
		return emailEmailTextField;
	}
	
	public NumberTextField<Integer> getEinstellungsjahrNumberTextField() {
		
		return einstellungsjahrNumberTextField;
	}
	
	public DropDownChoice<String> getAusbildungsartDropDownChoice() {
		
		return ausbildungsartDropDownChoice;
	}
	
	public PasswordTextField getPasswortPasswordField() {
		
		return passwortPasswordField;
	}
	
	private NumberTextField<Integer> initEinstellungsjahrNumberTextField() {
		
		NumberTextField<Integer> einstellungsjahrNumberTextField = new NumberTextField<>(
				"einstellungsjahrNumberTextField", Model.of(LocalDate.now().getYear()));
		return einstellungsjahrNumberTextField;
	}
	
	private EmailTextField initEmailEmailTextField() {
		
		EmailTextField emailEmailTextField = new EmailTextField("emailEmailTextField", Model.of());
		return emailEmailTextField;
	}
	
	private TextField<String> initBenutzernameTextField() {
		
		TextField<String> benutzernameTextField = new TextField<>("benutzernameTextField", Model.of());
		return benutzernameTextField;
	}
	
	private TextField<String> initNachnameTextField() {
		
		TextField<String> nachnameTextField = new TextField<>("nachnameTextField", Model.of());
		return nachnameTextField;
	}
	
	private TextField<String> initVornameTextField() {
		
		return new TextField<>("vornameTextField", Model.of());
	}
	
	private DropDownChoice<String> initAusbildungsartDropDownChoice() {
		
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
		return ausbildungsartDropDownChoice;
	}
	
	private DropDownChoice<String> initReferatDropDownChoice() {
		
		List<String> referatData = new ArrayList<>();
		for (Referat referat : referatService.getAllReferat())
			referatData.add(referat.getReferat());
		DropDownChoice<String> referatDropDownChoice = new DropDownChoice<>("referatDropDownChoice", referatData);
		referatDropDownChoice.setDefaultModel(Model.of());
		return referatDropDownChoice;
	}
	
	private DropDownChoice<String> initRolleDropDownChoice() {
		
		List<String> rollenData = new ArrayList<>();
		for (Rolle rolle : rolleService.getAllRolles())
			rollenData.add(rolle.getBeschreibung());
		DropDownChoice<String> rolleDropDownChoice = new DropDownChoice<>("rolleDropDownChoice", rollenData);
		rolleDropDownChoice.setDefaultModel(Model.of());
		return rolleDropDownChoice;
	}
	
	private DropDownChoice<Geschlecht> initGeschlechtDropDownChoice() {
		
		DropDownChoice<Geschlecht> geschlechtDropDownChoice = new DropDownChoice<>("geschlechtDropDownChoice",
				Model.ofList(Arrays.asList(Geschlecht.values())));
		geschlechtDropDownChoice.setDefaultModel(Model.of());
		return geschlechtDropDownChoice;
	}
	
	private PasswordTextField initPasswortPasswordTextField() {
		
		PasswordTextField passwordTextField = new PasswordTextField("passwortPasswortField", Model.of());
		passwordTextField.setRequired(false);
		return passwordTextField;
	}
}
