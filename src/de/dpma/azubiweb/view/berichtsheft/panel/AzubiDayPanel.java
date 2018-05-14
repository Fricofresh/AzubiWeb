package de.dpma.azubiweb.view.berichtsheft.panel;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.view.berichtsheft.Berichtsheft;
import de.dpma.azubiweb.view.berichtsheft.PanelChange;
/**
 * Panel zum Eingaben des Berichtsheftes für Montag bis Freitag
 * @author Benedikt Maier
 *
 */
public class AzubiDayPanel extends BerichtsheftPanel {
	public static final String NAME = "AzubiDay";

	public AzubiDayPanel(String id, User currentUser, BerichtsheftService berichtsheftService, PanelChange change) {

		super(id, currentUser, berichtsheftService, change, NAME);
		this.init();

	}

	public void init() {
		de.dpma.azubiweb.model.Berichtsheft curBH = service.getCurrentBerichtsheftByUserAzubi(currentUser);
		add(Berichtsheft.getLabelsWeek(5));
	}

}
