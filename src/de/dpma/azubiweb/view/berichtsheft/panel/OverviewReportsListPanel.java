package de.dpma.azubiweb.view.berichtsheft.panel;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.view.berichtsheft.AzubiReports;
import de.dpma.azubiweb.view.berichtsheft.PanelChange;

public class OverviewReportsListPanel extends BerichtsheftPanel {
	public static final String NAME = "OverviewReportsList";
	private AzubiReports reports;

	public OverviewReportsListPanel(String id, User currentUser, BerichtsheftService berichtsheftService,
			PanelChange change, AzubiReports reports) {

		super(id, currentUser, berichtsheftService, change, NAME);
		this.reports = reports;
		this.init();

	}

	public void init() {
		if (currentUser.getRolle().getId() == Rolle.Beschreibung.AZUBI.getRolleId()) {
			reports = createAzubiReportsByAzubi();
		}
		ListView<de.dpma.azubiweb.model.Berichtsheft> list = new ListView<de.dpma.azubiweb.model.Berichtsheft>(
				"reportListView", reports.getListReports()) {

			@Override
			protected void populateItem(ListItem<de.dpma.azubiweb.model.Berichtsheft> item) {
				de.dpma.azubiweb.model.Berichtsheft ar = item.getModelObject();

				item.add(new Label("nameLabel",
						ar.getUser_Azubi().getNachname() + ", " + ar.getUser_Azubi().getVorname()));
				item.add(new Label("weekLabel", "Woche: " + ar.getWeekAYear().split(";")[0]));
				Link<String> lk = new Link<String>("editLink") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						if (currentUser.getRolle().getId() == Rolle.Beschreibung.AZUBI.getRolleId()) {
							change.changeToAzubiWeekDayPanel(ar);
						} else {
							change.changeToSign(ar);
						}

					}
				};

				lk.setBody(Model.of("Ansehen"));
				item.add(lk);

			}
		};
		
		this.add(list);
	}

	public AzubiReports createAzubiReportsByAzubi() {
		AzubiReports reports = new AzubiReports(currentUser);
		List<de.dpma.azubiweb.model.Berichtsheft> rList = super.service.getAllBerichtsheft();
		for (int i = 0; i < rList.size(); i++) {
			de.dpma.azubiweb.model.Berichtsheft tempR = rList.get(i);
			if (tempR.getUser_Azubi().getId() == currentUser.getId()) {
				reports.addReport(tempR);
			}
		}
		return reports;
	}

}
