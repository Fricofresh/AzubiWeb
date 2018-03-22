package de.dpma.azubiweb.view;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.User;

public class BenutzerAnlage extends BenutzerVerwaltungParent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6527972472618497340L;
	
	public BenutzerAnlage() {
		
		super(new User());
		initial();
	}
	
	public BenutzerAnlage(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	private void initial() {
		
		titelLabel.setDefaultModelObject("Benutzer Anlegen");
	}
}
