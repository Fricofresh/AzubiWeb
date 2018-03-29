package de.dpma.azubiweb.view;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.dpma.azubiweb.service.AusbildungsartService;
import de.dpma.azubiweb.service.ReferatService;
import de.dpma.azubiweb.service.RolleService;
import de.dpma.azubiweb.service.UserService;

/**
 * Diese Klasse enthält alle nötigen Service Klassen um die Benutzer zu
 * Verwalten.
 * 
 * @author Kenneth Böhmer
 *
 */
@AuthorizeInstantiation("Ausbildungsleiter")
public class BenutzerVerwaltungsBasePage extends RootPage {
	
	private static final long serialVersionUID = 8938080241246551570L;
	
	@SpringBean
	protected AusbildungsartService ausbildungsartService;
	
	@SpringBean
	protected RolleService rolleService;
	
	@SpringBean
	protected ReferatService referatService;
	
	protected UserService userService = session.getUserService();
	
	public BenutzerVerwaltungsBasePage() {
		
		super();
	}
	
	public BenutzerVerwaltungsBasePage(PageParameters pageParameters) {
		
		super(pageParameters);
	}
}
