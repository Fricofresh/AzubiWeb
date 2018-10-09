package de.dpma.azubiweb.view;

import java.text.ParseException;

import org.apache.wicket.AttributeModifier;
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
import de.dpma.azubiweb.util.AlertUtil;

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
	
	private Label alertMessage;
	
	/**
	 * @see #initial
	 */
	public RootPage() {
		
		super();
		try {
			initial();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see #initial
	 * @param params
	 * @throws ParseException
	 */
	public RootPage(final PageParameters params) {
		
		super(params);
		try {
			initial();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Setzt die Funktionen die einzelnen Punkte in der Navigationsleiste. <br>
	 * Initialisiert alle {@link Component}
	 * 
	 * @throws ParseException
	 */
	private void initial() throws ParseException {
		
		// Falls man eine leere Datenbank hat, wird sie wieder gefüllt. Hilft,
		// wenn man bei aplication.properties die DDL von hibernate auf
		// create-drop stellt um immer frische Daten zu haben.
		if (session.getUserService().getAllUser().isEmpty())
			new InitialInsert();
		
		navLeisteWebMarkupContainer = new WebMarkupContainer("navLeisteWebMarkupContainer");
		
		addBenutzerVerwaltung();
		addAbmelden();
		addEinstellungen();
		addBerichtsheft();
		addSteckbrief();
		addEinsatzplan();
		
		add(navLeisteWebMarkupContainer);
		
		addAlertMessage();
		
		add(titelLabel);
		
		navLeisteWebMarkupContainer.setVisible(session.isSignedIn());
	}
	
	private void addAlertMessage() {
		
		WebMarkupContainer alertMessageParent = new WebMarkupContainer("alertMessageParent", Model.of());
		alertMessage = new Label("alertMessage", Model.of());
		alertMessageParent.add(alertMessage);
		add(alertMessageParent);
	}
	
	protected void setAlert(AlertUtil.AlertType alertType, String message) {
		alertMessage.getParent().add(AttributeModifier.remove("hidden"));
		alertMessage.add(new AttributeModifier("class", new Model() {
			
			public Object getObject(final Component component) {
				
				String cssClass = AlertUtil.getCss(alertType);
				
				return cssClass;
			}
		}));
		alertMessage.setEscapeModelStrings(false);
		alertMessage.setDefaultModel(Model.of(message));
	}
	
	private void addEinsatzplan() {
		
		navLeisteWebMarkupContainer.add(new Link<String>("einsatzplanLink") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				// TODO Do Something
			}
		});
	}
	
	private void addSteckbrief() {
		
		navLeisteWebMarkupContainer.add(new Link<String>("steckbriefLink") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				// TODO Do Something
				
			}
		});
	}
	
	private void addBerichtsheft() {
		
		navLeisteWebMarkupContainer.add(new Link<String>("berichtsheftLink") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				// TODO Do Something
			}
		});
	}
	
	private void addEinstellungen() {
		
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
	}
	
	private void addAbmelden() {
		
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
	}
	
	private void addBenutzerVerwaltung() {
		
		benutzerVerwaltungWebMarkupContainer = new WebMarkupContainer("benutzerVerwaltungWebMarkupContainer");
		
		addBenutzerAnlage();
		
		benutzerBearbeiten();
		
		navLeisteWebMarkupContainer.add(benutzerVerwaltungWebMarkupContainer);
		
		if (user != null && user.getRolle().getId() != Beschreibung.AL.getRolleId())
			benutzerVerwaltungWebMarkupContainer.setVisible(false);
	}
	
	private void benutzerBearbeiten() {
		
		benutzerVerwaltungWebMarkupContainer.add(new Link<String>("benutzerBearbeitenLink") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				setResponsePage(BenutzerListe.class);
			}
		});
	}
	
	private void addBenutzerAnlage() {
		
		benutzerVerwaltungWebMarkupContainer.add(new Link<String>("benutzerAnlageLink") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				setResponsePage(BenutzerAnlage.class);
			}
		});
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
