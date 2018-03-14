package de.dpma.azubiweb.view;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@AuthorizeInstantiation({"Auszubildende", "Ausbildungsleiter", "Ausbilder"})
public class StartPage extends RootPage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6466367063767148609L;
	
	public StartPage() {
		
		super();
		initial();
	}
	
	public StartPage(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	public void initial() {
		
		add(new Label("headerLabel", Model.of("Hallo " + user.getVorname())));
		
	}
}
