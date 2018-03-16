package de.dpma.azubiweb.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.User;

public class BenutzerListe extends BenutzerVerwaltungsBasePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7429354950006342422L;
	
	List<User> allUser = session.getUserService().getAllUser();
	
	public BenutzerListe() {
		
		super();
		initial();
	}
	
	public BenutzerListe(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	public void initial() {
		
		setAzubiListe();
		setAusbilderListe();
		setAusbildungsleiterListe();
		
	}
	
	private void setAusbildungsleiterListe() {
		
		List<User> userData = new ArrayList<>();
		for (User user : allUser) {
			if (user.getRolle().getId() == Beschreibung.AL.getRolleId()) {
				userData.add(user);
			}
		}
		
		add(addAllUserToListView(userData));
	}
	
	private void setAusbilderListe() {
		
		List<User> userData = new ArrayList<>();
		for (User user : allUser) {
			if (user.getRolle().getId() == Beschreibung.A.getRolleId()) {
				userData.add(user);
			}
		}
		
		add(addAllUserToListView(userData));
	}
	
	private void setAzubiListe() {
		
		Rolle rolle = rolleService.getRolle(Beschreibung.AZUBI);
		add(new Label("azubiLabel", Model.of(rolle.getBeschreibung())));
		List<Integer> ausbildungjahreData = new ArrayList<>();
		for (User u : session.getUserService().getAllUser())
			if (u.getRolle().getId() == Beschreibung.AZUBI.getRolleId())
				ausbildungjahreData.add(u.getEinstiegsjahr());
		ausbildungjahreData = new ArrayList<>(new HashSet<>(ausbildungjahreData));
		ListView<?> ausbildungsJahrListView = new ListView<Integer>("ausbildungsJahrListView", ausbildungjahreData) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<Integer> item) {
				
				Integer einstellungsjahr = item.getModelObject();
				if (einstellungsjahr != null && rolle.getId() == Beschreibung.AZUBI.getRolleId())
					item.add(new Label("ausbildungsJahrLabel", einstellungsjahr));
				else
					item.add(new Label("ausbildungsJahrLabel").setVisible(false));
				List<Ausbildungsart> ausbildungsartData = ausbildungsartService.getAllAusbildungsart();
				ListView<?> ausbildungsartListView = new ListView<Ausbildungsart>("ausbildungsartListView",
						ausbildungsartData) {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					
					@Override
					protected void populateItem(ListItem<Ausbildungsart> item) {
						
						Ausbildungsart ausbildungsart = item.getModelObject();
						if (einstellungsjahr != null && rolle.getId() == Beschreibung.AZUBI.getRolleId())
							item.add(new Label("ausbildungsartLabel",
									Model.of(ausbildungsart.getBerufsbildAbkürzung())));
						else
							item.add(new Label("ausbildungsartLabel").setVisible(false));
						List<User> userData = new ArrayList<>();
						for (User user : allUser) {
							if ((user.getRolle().getId() == rolle.getId() && user.getAusbildungsart().get(0)
									.getBerufsbildAbkürzung().equals(ausbildungsart.getBerufsbildAbkürzung()))
									&& String.valueOf(user.getEinstiegsjahr())
											.equals(String.valueOf(einstellungsjahr))) {
								userData.add(user);
							}
						}
						
						item.add(addAllUserToListView(userData));
					}
				};
				item.add(ausbildungsartListView);
			}
		};
		add(ausbildungsJahrListView);
	}
	
	private ListView<User> addAllUserToListView(List<User> userData) {
		
		return new ListView<User>("benutzerListView", userData) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<User> item) {
				
				User user = item.getModelObject();
				item.add(new Label("listPunktLabel", user.getVorname() + " " + user.getNachname()));
				
				item.add(new Link<String>("bearbeitenLink") {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					
					@Override
					public void onClick() {
						
						setResponsePage(new BenutzerBearbeiten(user));
					}
				}, new Link<String>("löschenLink") {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					
					@Override
					public void onClick() {
						
						// TODO confirm Alert
					}
				});
			}
		};
	}
}
