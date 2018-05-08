package de.dpma.azubiweb.view.berichtsheft;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;

public class BerichtsheftAzubi5DayPanel extends Panel {
	private User currentUser;
	private BerichtsheftService service;
	public BerichtsheftAzubi5DayPanel(String id,User currentUser, BerichtsheftService berichtsheftService) {

		super(id);
		this.currentUser = currentUser;
		this.service = berichtsheftService;
		this.init();

	}

	private void init() {
		de.dpma.azubiweb.model.Berichtsheft curBH = service.getCurrentBerichtsheftByUserAzubi(currentUser);
		add(Berichtsheft.getLabelsWeek(5));
	}

}
 