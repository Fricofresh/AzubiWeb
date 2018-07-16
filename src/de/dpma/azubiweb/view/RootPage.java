package de.dpma.azubiweb.view;

import java.text.ParseException;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.InitialInsert;
import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.UserSession;

/**
 * Stellt das Grundgerüst für alle Seiten da. <br>
 * 
 * @author Kenneth Böhmer
 */
public class RootPage extends WebPage {
	
	private static final long serialVersionUID = 8864787036851596335L;
	
	private WebMarkupContainer navLeisteWebMarkupContainer;
	
	private WebMarkupContainer benutzerVerwaltungWebMarkupContainer;
	
	/**
	 * Die aktuelle Session
	 */
	protected UserSession session = (UserSession) Session.get();
	
	/**
	 * Der aktueller Benutzer, der angemeldet ist.
	 */
	protected User user = session.getUser();
	
	/**
	 * Die Überschrift, dass Standartmäßig den Klassennamen nimmt.
	 */
	protected Label titelLabel = new Label("titelLabel", Model.of(this.getClass().getSimpleName()));
	
	/**
	 * @see #initial
	 */
	public RootPage() {
		
		super();
		try {
			initial();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see #initial
	 * @param params
	 * @throws ParseException 
	 */
	public RootPage(final PageParameters params)  {
		
		super(params);
		try {
			initial();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Setzt die Funktionen die einzelnen Punkte in der Navigationsleiste. <br>
	 * Initialisiert alle {@link Component}
	 * @throws ParseException 
	 */
	private void initial() throws ParseException {
		
		// Falls man eine leere Datenbank hat, wird sie wieder gefüllt. Hilft,
		// wenn man bei aplication.properties die DDL von hibernate auf
		// create-drop stellt um immer frische Daten zu haben.
		if (session.getUserService().getAllUser().isEmpty())
			new InitialInsert();
		
		navLeisteWebMarkupContainer = new WebMarkupContainer("navLeisteWebMarkupContainer");
		
		benutzerVerwaltungWebMarkupContainer = new WebMarkupContainer("benutzerVerwaltungWebMarkupContainer");
		
		benutzerVerwaltungWebMarkupContainer.add(new Link<String>("benutzerAnlageLink") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				setResponsePage(BenutzerAnlage.class);
			}
		});
		benutzerVerwaltungWebMarkupContainer.add(new Link<String>("benutzerBearbeitenLink") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				setResponsePage(BenutzerListe.class);
			}
		});
		
		navLeisteWebMarkupContainer.add(benutzerVerwaltungWebMarkupContainer);
		if (user != null && user.getRolle().getId() != Beschreibung.AL.getRolleId())
			benutzerVerwaltungWebMarkupContainer.setVisible(false);
		navLeisteWebMarkupContainer.add(new Link<String>("abmeldenLink") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				session.invalidateNow();
				setResponsePage(LoginPage.class);
			}
		});
		navLeisteWebMarkupContainer.add(new Link<String>("einstellungenLink") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				setResponsePage(Einstellungen.class);
			}
		});
		navLeisteWebMarkupContainer.add(new Link<String>("berichtsheftLink") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				// TODO Do Something
			}
		});
		add(navLeisteWebMarkupContainer);
		navLeisteWebMarkupContainer.add(new Link<String>("steckbriefLink") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				setResponsePage(Steckbrief.class);
				
			}
		});
		
		navLeisteWebMarkupContainer.add(new Link<String>("einsatzplanLink") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				// TODO Do Something
			}
		});
		add(titelLabel);
		navLeisteWebMarkupContainer.setVisible(session.isSignedIn());
	}
	
	/**
	 * Setzt die Sichtbarkeit Navigationsleiste
	 * 
	 * @param hide
	 *            true = unsichtbar, false = sichtbar
	 */
	protected void setVisibleNav(boolean hide) {
		
		navLeisteWebMarkupContainer.setVisible(!hide);
	}
}
