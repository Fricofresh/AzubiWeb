package de.dpma.azubiweb.view;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

@AuthorizeInstantiation("Ausbildungsleiter")
public class BenutzerListe extends RootPage {
	
}
