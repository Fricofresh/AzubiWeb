package de.dpma.azubiweb.view;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.UserSession;

public class RootPage extends WebPage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8864787036851596335L;
	
	private WebMarkupContainer navLeisteWebMarkupContainer;
	
	private WebMarkupContainer benutzerVerwaltungWebMarkupContainer;
	
	protected UserSession session = (UserSession) Session.get();
	
	protected User user = session.getUser();
	
	public RootPage() {
		
		super();
		initial();
	}
	
	public RootPage(final PageParameters params) {
		
		super(params);
		initial();
	}
	
	private void initial() {
		
		navLeisteWebMarkupContainer = new WebMarkupContainer("navLeisteWebMarkupContainer");
		
		benutzerVerwaltungWebMarkupContainer = new WebMarkupContainer("benutzerVerwaltungWebMarkupContainer");
		
		benutzerVerwaltungWebMarkupContainer.add(new Link<String>("benutzerAnlageLink") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				setResponsePage(BenutzerAnlage.class);
			}
		});
		benutzerVerwaltungWebMarkupContainer.add(new Link<String>("benutzerBearbeitenLink") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				setResponsePage(BenutzerListe.class);
			}
		});
		
		// TODO
		// https://ci.apache.org/projects/wicket/guide/8.x/single.html#_implement_visibilities_of_components_correctly
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
				
				Session.get().invalidateNow();
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
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				// TODO Do Something
			}
		});
		add(navLeisteWebMarkupContainer);
		navLeisteWebMarkupContainer.add(new Link<String>("steckbriefLink") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				// TODO Do Something
				
			}
		});
		
		navLeisteWebMarkupContainer.add(new Link<String>("einsatzplanLink") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				
				// TODO Do Something
			}
		});
	}
	
	protected void setVisibleNav(boolean hide) {
		
		navLeisteWebMarkupContainer.setVisible(!hide);
	}
}
