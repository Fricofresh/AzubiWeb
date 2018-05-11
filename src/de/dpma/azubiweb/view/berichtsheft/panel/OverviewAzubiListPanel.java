package de.dpma.azubiweb.view.berichtsheft.panel;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.view.BenutzerBearbeiten;
import de.dpma.azubiweb.view.berichtsheft.Berichtsheft;
import de.dpma.azubiweb.view.berichtsheft.PanelChange;
import de.dpma.azubiweb.view.berichtsheft.panel.OverviewAzubiListPanel.AzubiReports;

/**
 * Überichtsliste für AL oder AB der Azubis zum Unterzeichnen
 * 
 * @author VIT Student
 *
 */
public class OverviewAzubiListPanel extends BerichtsheftPanel {
	public static final String NAME = "OverviewAzubiList";
	private Rolle rolle;

	public OverviewAzubiListPanel(String id, User currentUser, BerichtsheftService berichtsheftService,
			PanelChange change) {
		super(id, currentUser, berichtsheftService, change, NAME);
		this.init();
	}

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
		if (reportsByUser.size() > 0) {
			ArrayList<AzubiReports> azubiReports = this.getAzubiReports(reportsByUser);

		}

		ListView<AzubiReports> list = new ListView<OverviewAzubiListPanel.AzubiReports>("azubiListView") {

			@Override
			protected void populateItem(ListItem<AzubiReports> item) {
				AzubiReports ar = item.getModelObject();

				item.add(new Label("nameLabel", ar.getuAzubi().getNachname() + " " + ar.getuAzubi().getVorname()));
				item.add(new Label("countReports", "Anzahl: " + ar.getListReports().size()));
				item.add(new Link<String>("bearbeitenLink") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {

//						System.out.println(user);
//						setResponsePage(new BenutzerBearbeiten(user));
					}
				});

			}
		};

		add(Berichtsheft.getLabelsWeek(5));
	}

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

	class AzubiReports {
		private User uAzubi;
		private ArrayList<de.dpma.azubiweb.model.Berichtsheft> listReports;

		public AzubiReports(User uAzubi) {
			this.uAzubi = uAzubi;
			listReports = new ArrayList<>();
		}

		public AzubiReports(User uAzubi, de.dpma.azubiweb.model.Berichtsheft firstReport) {
			this.uAzubi = uAzubi;
			listReports = new ArrayList<>();
			listReports.add(firstReport);
		}

		public void addReport(de.dpma.azubiweb.model.Berichtsheft report) {
			this.listReports.add(report);
//			this.listReports.sort(c);
		}

		public int getUserId() {
			return uAzubi.getId();
		}

		public User getuAzubi() {
			return uAzubi;
		}

		public ArrayList<de.dpma.azubiweb.model.Berichtsheft> getListReports() {
			return listReports;
		}

	}

}
