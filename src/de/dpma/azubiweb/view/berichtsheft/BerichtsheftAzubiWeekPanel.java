package de.dpma.azubiweb.view.berichtsheft;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;

public class BerichtsheftAzubiWeekPanel extends Panel {
	private User currentUser;
	private BerichtsheftService service;
	public BerichtsheftAzubiWeekPanel(String id,User currentUser, BerichtsheftService berichtsheftService) {

		super(id);
		this.currentUser = currentUser;
		this.service = berichtsheftService;
		this.init();

	}

	private void init() {
		add(Berichtsheft.getLabelsWeek(5));
	}

}
 