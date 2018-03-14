package de.dpma.azubiweb.view;

import org.apache.wicket.PageReference;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Klasse für die Einstellungen für die einzelnen Benutzer
 * 
 * @author Kenneth BÃ¶hmer
 *
 */
@AuthorizeInstantiation({"Auszubildende", "Ausbildungsleiter", "Ausbilder"})
public class Einstellungen extends RootPage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3177439622281163213L;
	
	public Einstellungen() {
		
		super();
		initial();
	}
	
	public Einstellungen(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	private void initial() {
		
		PasswordTextField neuesPasswortPasswordTextField = new PasswordTextField("neuesPasswortPasswordTextField",
				Model.of(""));
		Button bestätigenButton = new Button("bestätigenButton");
		Button abbrechenButton = new Button("abbrechenButton") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
				
				System.out.println("Ich breche ab");
				// TODO Richtiges zurückgehen
				setResponsePage(new PageReference(getPageId() - 1).getPage().getClass());
			}
		};
		abbrechenButton.setDefaultFormProcessing(false);
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		Form<?> einstellungenForm = new Form<Void>("einstellungenForm") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
				
				String passwort = neuesPasswortPasswordTextField.getModel().getObject();
				if (!passwort.isEmpty() && passwort.length() >= 6) {
					user.setPassword(passwort);
					if (session.getUserService().updateUserPasswort(user)) // TODO
																			// Richtiges
																			// zurückgehen
						setResponsePage(new PageReference(getPageId() - 2).getPage().getClass());
					else // TODO zur vorherigen Seite weiterleiten
						Session.get().error("Ein Fehler ist aufgetreten");
				}
				else {
					Session.get().error("Bitte geben Sie ein Passwort ein, dass mindestens 6 Zeichen lang ist.");
				}
			}
		};
		einstellungenForm.add(neuesPasswortPasswordTextField, bestätigenButton, abbrechenButton, feedbackPanel);
		add(einstellungenForm);
	}
	
}
