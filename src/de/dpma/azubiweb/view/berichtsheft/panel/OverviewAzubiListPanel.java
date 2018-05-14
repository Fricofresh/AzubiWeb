package de.dpma.azubiweb.view.berichtsheft.panel;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

import de.dpma.azubiweb.model.Berichtsheft;
import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.view.berichtsheft.AzubiReports;
import de.dpma.azubiweb.view.berichtsheft.PanelChange;

/**
 * Überichtsliste für AL oder AB der Azubis zum Unterzeichnen
 * 
 * @author Benedikt Maier
 *
 */
public class OverviewAzubiListPanel extends BerichtsheftPanel {

	private static final long serialVersionUID = 1513754883052295939L;
	public static final String NAME = "OverviewAzubiList";
	private Rolle rolle;

	public OverviewAzubiListPanel(String id, User currentUser, BerichtsheftService berichtsheftService,
			PanelChange change) {
		super(id, currentUser, berichtsheftService, change, NAME);
		this.init();
	}

	/**
	 * Initialisierung des Panels Azubiübericht Ließt alle BH aus die der User zu
	 * unterzeichnen hat und sortiert sie dann nach den Azubis ->
	 * {@link AzubiReports} <br>
	 * Erstellt davon eine {@link ListView} und zeigt diese im Panel an. Über diese
	 * gelangt man zu {@link OverviewReportsListPanel}, die alle BH für den Azubi
	 * anzeigt.
	 */

	public void init() {
		rolle = currentUser.getRolle();
		List<de.dpma.azubiweb.model.Berichtsheft> allReports = super.service.getAllBerichtsheft();
		ArrayList<de.dpma.azubiweb.model.Berichtsheft> reportsByUser = new ArrayList<>();
		if (rolle.getId() == Rolle.Beschreibung.A.getRolleId()) {
			for (int i = 0; i < allReports.size(); i++) {
				if (allReports.get(i).getUser_AB().getId() == currentUser.getId()) {
					reportsByUser.add(allReports.get(i));
				}
			}
		} else {
			for (int i = 0; i < allReports.size(); i++) {
				if (allReports.get(i).getUser_AL().getId() == currentUser.getId()) {
					reportsByUser.add(allReports.get(i));
				}
			}
		}

		ArrayList<AzubiReports> azubiReports = null;
		if (reportsByUser.size() > 0) {
			azubiReports = this.getAzubiReports(reportsByUser);

		}
		if (azubiReports == null) {
			azubiReports = new ArrayList<>();
			azubiReports = createTestReports();
		}

		ListView<AzubiReports> list = new ListView<AzubiReports>("azubiListView", azubiReports) {

			@Override
			protected void populateItem(ListItem<AzubiReports> item) {
				AzubiReports ar = item.getModelObject();

				item.add(new Label("nameLabel", ar.getuAzubi().getNachname() + " " + ar.getuAzubi().getVorname()));
				item.add(new Label("countReports", "Anzahl: " + ar.getListReports().size()));
				Link<String> lk = new Link<String>("editLink") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {

						change.changeToOverviewReportsList(ar);
					}
				};
				lk.setBody(Model.of("Ansehen"));
				item.add(lk);

			}
		};

		this.add(list);
	}

	private ArrayList<AzubiReports> createTestReports() {
		ArrayList<AzubiReports> reports = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			AzubiReports temp = new AzubiReports(new User(i, "usName" + i, "Anfang12",
					new Rolle(Rolle.Beschreibung.AZUBI.name()), "VN" + i, "NM" + i, "", null, 0, null, null));
			for (int j = 0; j < Math.random() * 10; j++) {
				temp.addReport(
						new de.dpma.azubiweb.model.Berichtsheft(temp.getuAzubi(), Berichtsheft.kindOfBH[0], "01;2018"));
			}
			reports.add(temp);
		}
		return reports;
	}

	/**
	 * Erstellt die Azubiliste mit den Berichtsheften
	 * 
	 * @param reportsByUser:
	 *            Alle vom User zu unterzeichnenden Berichtshefte
	 * @return
	 */
	private ArrayList<AzubiReports> getAzubiReports(ArrayList<de.dpma.azubiweb.model.Berichtsheft> reportsByUser) {
		ArrayList<AzubiReports> retReport = new ArrayList<>();
		for (int i = 0; i < reportsByUser.size(); i++) {
			boolean newUser = true;
			;
			de.dpma.azubiweb.model.Berichtsheft tempRep = reportsByUser.get(i);
			for (int j = 0; j < retReport.size(); j++) {
				if (retReport.get(j).getUserId() == tempRep.getUser_Azubi().getId()) {
					AzubiReports temp = retReport.get(j);
					temp.addReport(tempRep);
					retReport.set(j, temp);
					newUser = false;
				}
			}
			if (newUser) {
				retReport.add(new AzubiReports(tempRep.getUser_Azubi(), tempRep));
			}
		}
		return retReport;
	}

}
