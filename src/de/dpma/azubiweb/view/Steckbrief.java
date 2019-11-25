package de.dpma.azubiweb.view;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;

import de.dpma.azubiweb.model.User.Geschlecht;
import de.dpma.azubiweb.service.SteckbriefService;

public class Steckbrief extends RootPage {
	
	@Autowired
	private SteckbriefService steckbriefService;
	
	// TODO Einbinden, sobald der Einsatzplan implementiert ist
	// @Autowired
	// private EinsatzplanService einsatzplanService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Steckbrief() {
		
		super();
		initial();
	}
	
	private void initial() {
		
		Image azubiImage = initAzubiImage();
		
		Label nameLabel = initNameLabel();
		
		Label referatLabel = initReferatLabel();
		
		// DropDownChoice<String> ausbilderDropDownChoice =
		// initAusbildungsleiterDropDownChoice();
		
		// DropDownChoice<String> ausbildungsstationDropDownChoice =
		// initAusbildungsstationDropDownChoice();
		
		add(azubiImage, nameLabel, referatLabel);
	}
	
	private Label initReferatLabel() {
		
		return new Label("referatLabel");
	}
	
	private Label initNameLabel() {
		
		return new Label("nameLabel", Model.of(session.getUser().getVorname() + " " + session.getUser().getNachname()));
	}
	
	private Image initAzubiImage() {
		
		Image azubiImage = new Image("azubiImage", "");
		azubiImage.add(new AttributeModifier("src",
				user.getGeschlecht() == Geschlecht.Herr ? "pictures/Businessman.png" : "pictures/Woman.png"));
		return azubiImage;
	}
	
	// private DropDownChoice<String> initAusbildungsleiterDropDownChoice() {
	//
	// // TODO Kommendes Referat holen und die ensprechenden Ansprechpartner
	// // anzeigen
	// List<User> ausbilder =
	// userService.getUserByRolle(rolleService.getRolle(Beschreibung.A));
	//
	// List<String> ausbilderNamen = new ArrayList<>();
	// for (User user : ausbilder) {
	// ausbilderNamen.add(user.getVorname() + " " + user.getNachname());
	// }
	// DropDownChoice<String> ausbilderDropDownChoice = new
	// DropDownChoice<>("ausbilderDropDownChoice",
	// ausbilderNamen);
	// ausbilderDropDownChoice.setDefaultModel(Model.of());
	//
	// return ausbilderDropDownChoice;
	// }
	//
	// private DropDownChoice<String> initAusbildungsstationDropDownChoice() {
	//
	// List<Referat> referate = referatService.getAllReferat();
	// List<String> referatsnamen = new ArrayList<>();
	// for (Referat referat : referate) {
	// referatsnamen.add(referat.getReferat() + " " +
	// referat.getReferatsname());
	// }
	// DropDownChoice<String> ausbildungsstationDropDownChoice = new
	// DropDownChoice<>(
	// "ausbildungsstationDropDownChoice", referatsnamen);
	// ausbildungsstationDropDownChoice.setDefaultModel(Model.of());
	//
	// return ausbildungsstationDropDownChoice;
	// }
}
