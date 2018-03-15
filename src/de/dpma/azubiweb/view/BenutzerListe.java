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
	
	public BenutzerListe() {
		
		super();
		initial();
	}
	
	public BenutzerListe(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	public void initial() {
		
		ListView<?> rolleListView = new ListView<Rolle>("rolleListView", rolleService.getAllRolles()) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<Rolle> item) {
				
				Rolle rolle = item.getModelObject();
				item.add(new Label("rolleLabel", Model.of(rolle.getBeschreibung())));
				List<Integer> ausbildungjahreData = new ArrayList<>();
				for (User u : session.getUserService().getAllUser())
					// if (u.getEinstiegsjahr() != null)
					ausbildungjahreData.add(u.getEinstiegsjahr());
				ausbildungjahreData = new ArrayList<>(new HashSet<>(ausbildungjahreData));
				ListView<?> ausbildungsJahrListView = new ListView<Integer>("ausbildungsJahrListView",
						ausbildungjahreData) {
					
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
								for (User user : session.getUserService().getAllUser()) {
									// TODO Wenn BUG user.getAusbildungsart()
									// ist immer
									// leer oder null behoben wurde
									// if
									// (user.getAusbildungsart().contains(ausbildungsart)
									// && String
									// .valueOf(user.getEinstiegsjahr()).equals(String.valueOf(einstellungsjahr)))
									// {
									if (String.valueOf(user.getEinstiegsjahr()).equals(String.valueOf(einstellungsjahr))
											&& user.getRolle().getId() == rolle.getId())
										userData.add(user);
									// }
								}
								ListView<?> benutzerListeListView = new ListView<User>("benutzerListView", userData) {
									
									/**
									 * 
									 */
									private static final long serialVersionUID = 1L;
									
									@Override
									protected void populateItem(ListItem<User> item) {
										
										User user = item.getModelObject();
										item.add(new Label("listPunktLabel",
												user.getVorname() + " " + user.getNachname()));
										if (!ausbildungsart.getBerufsbildAbkürzung().equals("FISI")
												&& rolle.getId() != Beschreibung.AZUBI.getRolleId())
											item.setVisible(false);
										
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
								item.add(benutzerListeListView);
							}
						};
						item.add(ausbildungsartListView);
					}
				};
				item.add(ausbildungsJahrListView);
			}
		};
		add(rolleListView);
	}
}
