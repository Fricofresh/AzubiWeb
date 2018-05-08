package de.dpma.azubiweb.view.berichtsheft;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;

public class BerichtsheftABLOverviewPanel extends Panel {
	private static final long serialVersionUID = -4899783126052592059L;
	private User currentUser;
	private BerichtsheftService service;

	public BerichtsheftABLOverviewPanel(String id, User currentUser, BerichtsheftService berichtsheftService) {

		super(id);
		this.currentUser = currentUser;
		this.service = berichtsheftService;
		this.init();

	}

	private List<de.dpma.azubiweb.model.Berichtsheft> readBerichtshefteToSign() {
		ArrayList<de.dpma.azubiweb.model.Berichtsheft> tempRet = new ArrayList<>();
		List<de.dpma.azubiweb.model.Berichtsheft> allBH = service.getAllBerichtsheft();
		if (currentUser.getRolle().getBeschreibung().equals(Rolle.Beschreibung.A)) {
			for (int i = 0; i < allBH.size(); i++) {
				if (allBH.get(i).getUser_AB().equals(currentUser)) {
					tempRet.add(allBH.get(i));
				}
			}
		} else {
			for (int i = 0; i < allBH.size(); i++) {
				if (allBH.get(i).getUser_AL().equals(currentUser)) {
					tempRet.add(allBH.get(i));
				}
			}
		}

		return tempRet;
	}

	private void init() {
		List<de.dpma.azubiweb.model.Berichtsheft> bhToSign = this.readBerichtshefteToSign();
		if (bhToSign.size() == 0) {
			//NoAnswer
		} else {
			//Aufbau der Liste
			add(Berichtsheft.getLabelsWeek(5));
		}
		
	}

}
