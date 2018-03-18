package de.dpma.azubiweb.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
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
import de.dpma.azubiweb.util.BestätigenLink;

public class BenutzerListe extends BenutzerVerwaltungsBasePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7429354950006342422L;
	
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
		
		add(addAllUserToListView(userService.getUserByRolle(rolleService.getRolle(Beschreibung.AL)),
				"ausbildungsleiter"));
	}
	
	private void setAusbilderListe() {
		
		add(addAllUserToListView(userService.getUserByRolle(rolleService.getRolle(Beschreibung.A)), "ausbilder"));
	}
	
	private void setAzubiListe() {
		
		Rolle rolle = rolleService.getRolle(Beschreibung.AZUBI);
		List<Integer> ausbildungjahreData = new ArrayList<>();
		for (User user : userService.getUserByRolle(rolle))
			ausbildungjahreData.add(user.getEinstiegsjahr());
		ausbildungjahreData = new ArrayList<>(new HashSet<>(ausbildungjahreData));
		ListView<?> ausbildungsJahrListView = new ListView<Integer>("ausbildungsJahrListView", ausbildungjahreData) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<Integer> item) {
				
				Integer einstellungsjahr = item.getModelObject();
				item.add(new Label("ausbildungsJahrLabel", einstellungsjahr));
				
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
						item.add(new Label("ausbildungsartLabel", Model.of(ausbildungsart.getBerufsbildAbkürzung())));
						List<User> userData = new ArrayList<>();
						for (User user : userService.getUserByRolle(rolle)) {
							if (user.getAusbildungsart() != null && !user.getAusbildungsart().isEmpty()
									&& user.getAusbildungsart().get(0).getBerufsbildAbkürzung()
											.equals(ausbildungsart.getBerufsbildAbkürzung())
									&& String.valueOf(user.getEinstiegsjahr())
											.equals(String.valueOf(einstellungsjahr))) {
								userData.add(user);
							}
						}
						
						item.add(addAllUserToListView(userData, "azubi"));
					}
				};
				item.add(ausbildungsartListView);
			}
		};
		add(ausbildungsJahrListView);
	}
	
	private ListView<User> addAllUserToListView(List<User> userData, String preName) {
		
		return new ListView<User>(preName + "ListView", userData) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<User> item) {
				
				User user = item.getModelObject();
				item.add(new Label(preName + "Label", user.getVorname() + " " + user.getNachname()));
				
				item.add(new Link<String>("bearbeitenLink") {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					
					@Override
					public void onClick() {
						
						setResponsePage(new BenutzerBearbeiten(user));
					}
				});
				item.add(new BestätigenLink<String>("löschenLink", "Möchten Sie wicklich den Benutzer "
						+ user.getVorname() + " " + user.getNachname() + " löschen?") {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					
					@Override
					public void onClick(AjaxRequestTarget arg0) {
						
						userService.deleteUser(user);
						setResponsePage(BenutzerListe.class);
					}
				});
			}
		};
	}
}
