package de.dpma.azubiweb.view;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.User;

/**
 * Enthält alle Funktionen für {@link BenutzerAnlage} und
 * {@link BenutzerBearbeiten}.
 * 
 * @author Kenneth Böhmer
 *
 */
public class BenutzerAnlage extends BenutzerVerwaltungParent {
	
	private static final long serialVersionUID = 6527972472618497340L;
	
	/**
	 * @see #initial()
	 */
	public BenutzerAnlage() {
		
		super(new User());
		initial();
	}
	
	/**
	 * @see {@link BenutzerVerwaltungParent} und {@link #initial()}
	 * @param pageParameters
	 */
	public BenutzerAnlage(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	/**
	 * Wird aufgerufen, wenn ein Objekt von einer der Klassen, die von
	 * {@link BenutzerVerwaltungParent} erben, erstellt wurde bzw. die View, von
	 * einer der Klassen {@link BenutzerVerwaltungParent} erben, aufgerufen
	 * wurde. <br>
	 * Setzt die Überschrift.
	 */
	private void initial() {
		
		titelLabel.setDefaultModelObject("Benutzer Anlegen");
	}
}
