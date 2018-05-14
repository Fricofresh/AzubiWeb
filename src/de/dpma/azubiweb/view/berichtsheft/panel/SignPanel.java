package de.dpma.azubiweb.view.berichtsheft.panel;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.view.berichtsheft.Berichtsheft;
import de.dpma.azubiweb.view.berichtsheft.PanelChange;
/**
 * Panel zum Abzeichnen des BerichtheftesMa!
 * @author Benedikt Maier
 *
 */
public class SignPanel extends BerichtsheftPanel {
	public static final String NAME = "Sign";
	public SignPanel(String id,User currentUser, BerichtsheftService berichtsheftService, PanelChange change, de.dpma.azubiweb.model.Berichtsheft report) {

		super(id, currentUser, berichtsheftService, change, NAME);
		this.init();

	}

	public void init() {
		add(Berichtsheft.getLabelsWeek(5));
	}

}
