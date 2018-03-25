package de.dpma.azubiweb.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.Referat;
import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.util.BestätigenPannel;

public class BenutzerListe extends BenutzerVerwaltungsBasePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7429354950006342422L;
	
	public BenutzerListe() {
		
		super();
		initial(null);
	}
	
	public BenutzerListe(PageParameters pageParameters) {
		
		super(pageParameters);
		initial(pageParameters);
	}
	
	public void initial(PageParameters pageParameters) {
		
		titelLabel.setDefaultModelObject("Benutzer Liste");
		setAzubiListe();
		setAusbilderListe();
		setAusbildungsleiterListe();
		
		Boolean isNew = false;
		WebMarkupContainer erfolgreicherAlertLabelParent = new WebMarkupContainer("erfolgreicherAlertLabelParent");
		Label erfolgreicherAlertLabel = new Label("erfolgreicherAlertLabel");
		erfolgreicherAlertLabelParent.setVisible(false);
		erfolgreicherAlertLabelParent.add(erfolgreicherAlertLabel);
		add(erfolgreicherAlertLabelParent);
		
		if (pageParameters != null && !pageParameters.isEmpty() && !pageParameters.get("user").isNull()
				&& !pageParameters.get("user").isEmpty()) {
			isNew = (pageParameters.get("isNew").isNull() || pageParameters.get("isNew").isEmpty() ? null
					: pageParameters.get("isNew").toBoolean());
			String name = pageParameters.get("user").toString();
			
			erfolgreicherAlertLabel.setEscapeModelStrings(false);
			if (isNew == null) {
				erfolgreicherAlertLabel.setDefaultModel(
						Model.of("Der Benutzer <strong>" + name + "</strong> wurde erfolgreich gelöscht."));
			}
			else {
				erfolgreicherAlertLabel.setDefaultModel(Model.of("Der Benutzer <strong>" + name
						+ "</strong> wurde erfolgreich " + (isNew ? "angelegt" : "bearbeitet") + "."));
			}
			erfolgreicherAlertLabelParent.setVisible(true);
		}
	}
	
	private void setAusbildungsleiterListe() {
		
		add(addAllUserToListView(userService.getUserByRolle(rolleService.getRolle(Beschreibung.AL)),
				"ausbildungsleiter"));
	}
	
	private void setAusbilderListe() {
		
		List<String> referatData = new ArrayList<>();
		for (Referat referat : referatService.getAllReferat())
			referatData.add(referat.getReferat());
		
		ListView<String> referatListView = new ListView<String>("referatListView", referatData) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<String> item) {
				
				String referat = item.getModelObject();
				Label referatLabel = new Label("referatLabel", Model.of(referat));
				item.add(referatLabel);
				List<User> userData = new ArrayList<>();
				for (User user : userService.getUserByRolle(rolleService.getRolle(Beschreibung.A)))
					if (referatService.getReferatByAnsprechpartner(user).getReferat().equals(referat))
						userData.add(user);
				item.add(addAllUserToListView(userData, "ausbilder"));
			}
		};
		add(referatListView);
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
				item.add(new BestätigenPannel<String>("löschenLink", "Möchten Sie wicklich den Benutzer "
						+ user.getVorname() + " " + user.getNachname() + " löschen?") {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					
					@Override
					public void onClick(AjaxRequestTarget arg0) {
						
						userService.deleteUser(user);
						setResponsePage(BenutzerListe.class,
								new PageParameters().add("user", user.getVorname() + " " + user.getNachname()));
					}
				});
			}
		};
	}
}
