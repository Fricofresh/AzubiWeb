package de.dpma.azubiweb.view;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.User;

/**
 * Zur besserren Unterscheidung für die Anwender wird diese Klasse benötigt.<br>
 * Die Controller aufgaben übernimmt {@link BenutzerVerwaltungParent}.
 * 
 * @author Kenneth Böhmer
 *
 */
public class BenutzerBearbeiten extends BenutzerVerwaltungParent {
	
	private static final long serialVersionUID = 1996614134364296195L;
	
	/**
	 * @see {@link BenutzerVerwaltungParent} und {@link #initial()}
	 * @param pageParameters
	 */
	public BenutzerBearbeiten(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	/**
	 * @see {@link BenutzerVerwaltungParent} und {@link #initial()}
	 * @param user
	 *            der zu bearbeitende Benutzer
	 */
	public BenutzerBearbeiten(User user) {
		
		super(user);
		initial();
	}
	
	/**
	 * Wird aufgerufen, wenn ein Objekt von {@link BenutzerBearbeiten} erstellt
	 * wurde bzw. die View von {@link BenutzerBearbeiten} aufgerufen wurde. <br>
	 * Setzt die Überschrift und belegt die {@link Component} mit Funktionen.
	 */
	private void initial() {
		
		titelLabel.setDefaultModelObject("Benutzer Bearbeiten");
	}
}
