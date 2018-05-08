package de.dpma.azubiweb.view.berichtsheft.panel;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.view.berichtsheft.Berichtsheft;
import de.dpma.azubiweb.view.berichtsheft.PanelChange;

public class AzubiWeekPanel extends BerichtsheftPanel {
	public static final String NAME ="AzubiWeek";
	public AzubiWeekPanel(String id,User currentUser, BerichtsheftService berichtsheftService,PanelChange change) {

		super(id, currentUser, berichtsheftService, change, NAME);
		this.init();

	}

	private void init() {
		add(Berichtsheft.getLabelsWeek(5));
	}

}
 