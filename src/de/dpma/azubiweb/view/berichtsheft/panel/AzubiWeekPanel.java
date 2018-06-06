package de.dpma.azubiweb.view.berichtsheft.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.util.BerichtsheftData;
import de.dpma.azubiweb.view.berichtsheft.Berichtsheft;
import de.dpma.azubiweb.view.berichtsheft.PanelChange;
/**
 * Panel zum Eingaben des Berichtsheftes für die Woche
 * @author Benedikt Maier
 *
 */
public class AzubiWeekPanel extends BerichtsheftPanel {
	public static final String NAME ="AzubiWeek";
	private de.dpma.azubiweb.model.Berichtsheft report;
	private static final List<String> TYPES = Arrays.asList(new String[] { "Keine", "Urlaub", "Gleittag", "Krank" });
	public AzubiWeekPanel(User currentUser, BerichtsheftService berichtsheftService,PanelChange change, de.dpma.azubiweb.model.Berichtsheft report) {

		super(currentUser, berichtsheftService, change, NAME);
		this.report = report;
		this.init();

	}

	public void init() {
		if (report == null && Berichtsheft.testID == 2) {
			report = createTest();
		}
		ArrayList<String> data = BerichtsheftData.getDataFromXML(report.getData());
	}
	/**
	 * Erstellen des Testcases
	 * @return
	 */
	private de.dpma.azubiweb.model.Berichtsheft createTest() {
		de.dpma.azubiweb.model.Berichtsheft report = new de.dpma.azubiweb.model.Berichtsheft(currentUser,
				de.dpma.azubiweb.model.Berichtsheft.kindOfBH[0], 201822);
		report.setData(BerichtsheftData.getXMLFromData(
				new String[] { "MontagDaten", "DienstagDaten", "MittwochDaten", "DonnerDaten", "Gleittag" }, 22));
		return report;
	}
}
 