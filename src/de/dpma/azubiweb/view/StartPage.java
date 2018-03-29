package de.dpma.azubiweb.view;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Controller für die Startseite. <br>
 * Wird von {@link LoginPage}, bei erfolgreicher Anmeldung, weitergeleitet.
 * 
 * @author Kenneth Böhmer
 */
@AuthorizeInstantiation({"Auszubildende", "Ausbildungsleiter", "Ausbilder"})
public class StartPage extends RootPage {
	
	private static final long serialVersionUID = 6466367063767148609L;
	
	/**
	 * @see #initial()
	 */
	public StartPage() {
		
		super();
		initial();
	}
	
	/**
	 * @see #initial()
	 * @param pageParameters
	 */
	public StartPage(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	/**
	 * Wird aufgerufen, wenn ein Objekt von {@link StartPage} erstellt wurde
	 * bzw. die View von {@link StartPage} aufgerufen wurde. <br>
	 * Setzt die Überschrift in Startseite und setzt den Vornamen bei der
	 * Begrüßung hinzu.
	 */
	private void initial() {
		
		titelLabel.setDefaultModelObject("Startseite");
		add(new Label("headerLabel", Model.of("Hallo " + user.getVorname())));
		
	}
}
