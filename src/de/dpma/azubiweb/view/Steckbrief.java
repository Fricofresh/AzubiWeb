package de.dpma.azubiweb.view;

import java.util.List;

import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.User;

public class Steckbrief extends BenutzerVerwaltungsBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Steckbrief() {
super();
List<User> ausbildungsleiter = userService.getUserByRolle(rolleService.getRolle(Beschreibung.AL));



}
}