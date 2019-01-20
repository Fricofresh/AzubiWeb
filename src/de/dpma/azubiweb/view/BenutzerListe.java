package de.dpma.azubiweb.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
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
import de.dpma.azubiweb.model.User.Geschlecht;
import de.dpma.azubiweb.util.AlertUtil.AlertType;
import de.dpma.azubiweb.util.BestätigenPannel;

/**
 * Controller für die Benutzer Liste. <br>
 * Die Klasse holt sich alle Benutzer von der Datenbank und zeigt diese in der
 * View an.
 * 
 * @author Kenneth Böhmer
 */
public class BenutzerListe extends BenutzerVerwaltungsBasePage {
	
	private static final long serialVersionUID = -7429354950006342422L;
	
	/**
	 * @see #initial(PageParameters)
	 */
	public BenutzerListe() {
		
		super();
		initial(null);
	}
	
	/**
	 * @see #initial(PageParameters)
	 * @param pageParameters
	 */
	public BenutzerListe(PageParameters pageParameters) {
		
		super(pageParameters);
		initial(pageParameters);
	}
	
	public BenutzerListe(User user) {
		
		super();
		initial(new PageParameters().add("user", user.getVorname() + " " + user.getNachname()));
	}
	
	public BenutzerListe(User user, boolean isNew) {
		
		super();
		initial(new PageParameters().add("isNew", isNew).add("user", user.getVorname() + " " + user.getNachname()));
	}
	
	/**
	 * Wird aufgerufen, wenn ein Objekt von {@link BenutzerListe} erstellt wurde
	 * bzw. die View von {@link BenutzerListe} aufgerufen wurde. <br>
	 * Belegt die {@link Component} mit Funktionen. <br>
	 * Setzt die einzelnen Benutzer in die Arkordeons. <br>
	 * Setzt den Alert Box inhalt.
	 * 
	 * @param pageParameters
	 *            derzeit: Informationen für die Alert Boxen.
	 */
	public void initial(PageParameters pageParameters) {
		
		titelLabel.setDefaultModelObject("Benutzer Liste");
		setAzubiListe();
		setAusbilderListe();
		setAusbildungsleiterListe();
		
		if (pageParameters != null && !pageParameters.isEmpty() && !pageParameters.get("user").isNull()
				&& !pageParameters.get("user").isEmpty()) {
			Boolean isNew = (pageParameters.get("isNew").isNull() || pageParameters.get("isNew").isEmpty() ? null
					: pageParameters.get("isNew").toBoolean());
			String name = pageParameters.get("user").toString();
			
			if (isNew == null) {
				setAlert(AlertType.SUCCESS, "Der Benutzer <strong>" + name + "</strong> wurde erfolgreich gelöscht.");
			}
			else {
				setAlert(AlertType.SUCCESS, "Der Benutzer <strong>" + name + "</strong> wurde erfolgreich "
						+ (isNew ? "angelegt" : "bearbeitet") + ".");
			}
		}
	}
	
	/**
	 * Setzt alle Ausbildungsleiter.
	 */
	private void setAusbildungsleiterListe() {
		
		add(addAllUserToListView(userService.getUserByRolle(rolleService.getRolle(Beschreibung.AL)),
				"ausbildungsleiter"));
	}
	
	/**
	 * Setzt alle Ausbilder.
	 */
	private void setAusbilderListe() {
		
		// TODO Vergleichen mit den Azubis und umschreiben.
		List<String> referatData = new ArrayList<>();
		// for (Referat referat : referatService.getAllReferat())
		// referatData.add(referat.getReferat());
		
		// Die Ausbilder werden nach Referat unterteilt.
		ListView<Referat> referatListView = new ListView<Referat>("referatListView", referatService.getAllReferat()) {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<Referat> item) {
				
				Referat referat = item.getModelObject();
				Label referatLabel = new Label("referatLabel",
						Model.of(referat.getReferat() + " " + referat.getReferatsname()));
				item.add(referatLabel);
				// Separieren der Benutzer nach Referat
				List<User> userData = referat.getAnsprechpartner();
				// new ArrayList<>();
				//
				// for (User user :
				// userService.getUserByRolle(rolleService.getRolle(Beschreibung.A)))
				// if
				// (referatService.getReferatByAnsprechpartner(user).getReferat().equals(referat))
				// userData.add(user);
				
				// Hinzufügen der gefilterten Benutzer in die Liste.
				item.add(addAllUserToListView(userData, "ausbilder"));
			}
		};
		add(referatListView);
	}
	
	private void setAzubiListe() {
		
		// Alle Azubis werden von der Datenbank geholt.
		Rolle rolle = rolleService.getRolle(Beschreibung.AZUBI);
		// Das Einstiegsjahr wird hinzugefügt
		List<Integer> ausbildungjahreData = new ArrayList<>();
		for (User user : userService.getUserByRolle(rolle))
			ausbildungjahreData.add(user.getEinstiegsjahr());
		// Doppelungen entfernen
		ausbildungjahreData = new ArrayList<>(new HashSet<>(ausbildungjahreData));
		// Die Azubis werden nach Einstiegsjahr unterteilt.
		ListView<?> ausbildungsJahrListView = new ListView<Integer>("ausbildungsJahrListView", ausbildungjahreData) {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<Integer> item) {
				
				Integer einstellungsjahr = item.getModelObject();
				item.add(new Label("ausbildungsJahrLabel", einstellungsjahr));
				List<Ausbildungsart> ausbildungsartData = ausbildungsartService.getAllAusbildungsart();
				// Azubis werden nach Ausbildungsart unterteilt
				ListView<?> ausbildungsartListView = new ListView<Ausbildungsart>("ausbildungsartListView",
						ausbildungsartData) {
					
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
	
	/**
	 * Fügt alle Listenpunkte indem die Benutzer enthalten sind.
	 * 
	 * @param userData
	 *            Liste aus den betroffenen Benutzer
	 * @param preName
	 *            für die {@link Component Componenten} um die richtigen ID zu
	 *            verwenden.
	 * @return
	 */
	private ListView<User> addAllUserToListView(List<User> userData, String preName) {
		
		return new ListView<User>(preName + "ListView", userData) {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<User> item) {
				
				User user = item.getModelObject();
				// Setzen des Listenpunktes
				// add(new Image("image2", new
				// PackageResourceReference(Home.class, "Image2.gif")));
				item.add(
						new Image(preName + "Geschlecht", "")
								.add(new AttributeModifier("src",
										user.getGeschlecht() == Geschlecht.Herr ? "pictures/Businessman.png"
												: "pictures/Woman.png"))
								.add(AttributeModifier.replace("class", "listenBild")));
				item.add(new Label(preName + "Label", user.getVorname() + " " + user.getNachname()));
				
				item.add(new Link<String>("bearbeitenLink") {
					
					private static final long serialVersionUID = 1L;
					
					@Override
					public void onClick() {
						
						setResponsePage(new BenutzerBearbeiten(user));
					}
				});
				// Fordert eine Bestätigung des Benutzers.
				item.add(new BestätigenPannel<String>("löschenLink", "Möchten Sie wicklich den Benutzer "
						+ user.getVorname() + " " + user.getNachname() + " löschen?") {
					
					private static final long serialVersionUID = 1L;
					
					@Override
					public void onClick(AjaxRequestTarget arg0) {
						
						System.out.println(user + " Benutzerliste");
						if (userService.deleteUser(user))
							// neuladen der Seite mit dazugehörigen Alert
							// Inhalt.
							setResponsePage(new BenutzerListe(user));
					}
				});
			}
		};
	}
}
