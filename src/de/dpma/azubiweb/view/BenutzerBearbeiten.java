package de.dpma.azubiweb.view;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.User;

public class BenutzerBearbeiten extends BenutzerVerwaltungParent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1996614134364296195L;
	
	public BenutzerBearbeiten(PageParameters params) {
		
		super(params);
		initial();
	}
	
	public BenutzerBearbeiten(User user) {
		
		super(user);
		initial();
	}
	
	private void initial() {
		
		titelLabel.setDefaultModelObject("Benutzer Bearbeiten");
	}
}
