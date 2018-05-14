package de.dpma.azubiweb.view.berichtsheft.panel;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.view.berichtsheft.Berichtsheft;
import de.dpma.azubiweb.view.berichtsheft.PanelChange;
/**
 * Panel zum Eingaben des Berichtsheftes für die Woche
 * @author Benedikt Maier
 *
 */
public class AzubiWeekPanel extends BerichtsheftPanel {
	public static final String NAME ="AzubiWeek";
	public AzubiWeekPanel(String id,User currentUser, BerichtsheftService berichtsheftService,PanelChange change, de.dpma.azubiweb.model.Berichtsheft report) {

		super(id, currentUser, berichtsheftService, change, NAME);
		this.init();

	}

	public void init() {
		add(Berichtsheft.getLabelsWeek(5));
	}

}
 