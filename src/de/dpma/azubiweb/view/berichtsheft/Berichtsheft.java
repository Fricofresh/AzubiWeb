
package de.dpma.azubiweb.view.berichtsheft;

import java.util.ArrayList;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.service.UserService;
import de.dpma.azubiweb.view.RootPage;
import de.dpma.azubiweb.view.berichtsheft.panel.AzubiDayPanel;
import de.dpma.azubiweb.view.berichtsheft.panel.AzubiWeekPanel;
import de.dpma.azubiweb.view.berichtsheft.panel.BerichtsheftPanel;
import de.dpma.azubiweb.view.berichtsheft.panel.OverviewAzubiListPanel;
import de.dpma.azubiweb.view.berichtsheft.panel.OverviewReportsListPanel;
import de.dpma.azubiweb.view.berichtsheft.panel.SignPanel;

/**
 * Controller der Berichtsheft Seite Aufruf der veschdienenen Panels
 * 
 * @author Benedikt Maier
 *
 */
@AuthorizeInstantiation({ "Auszubildende", "Ausbildungsleiter", "Ausbilder" })
public class Berichtsheft extends RootPage implements PanelChange {
	/**
	 * 0: No test; 1-AzubiDayPanel; 2-AzubiWeekPanel; 3-OverviewAzubiListPanel;
	 * 4-OverviewReportsListPanel; 5-SignPanel;
	 */
	public static int testID = 0;
	private static final long serialVersionUID = 1392603770886213724L;

	@SpringBean
	private BerichtsheftService berichtsheftService;

	protected UserService userService = session.getUserService();
	private BerichtsheftPanel currentPanel;

	private ArrayList<BerichtsheftPanel> panelHistory;
	private IModel<String> infoModel;

	public Berichtsheft() {
		super();
		testID = 0;
		initial();

	}

	/**
	 * @see #initial()
	 * @param pageParameters
	 */
	public Berichtsheft(PageParameters pageParameters) {

		super(pageParameters);
		initial();
	}

	private User currentUser;

	/**
	 * Initialisierung der Seite, je nach Rollentyp
	 */
	private void initial() {
		this.currentUser = session.getUser();

		this.add(new Label("title", currentUser.getNachname() + ", " + currentUser.getVorname()));
		this.add(new Label("message", "Titel: " + currentUser.getRolle().getBeschreibung()));
		this.panelHistory = new ArrayList<>();

		switch (testID) {
		case 0:
			int rolleID = this.currentUser.getRolle().getId();
			if (rolleID == Rolle.Beschreibung.A.getRolleId() || rolleID == Rolle.Beschreibung.AL.getRolleId()) {
				this.currentPanel = new OverviewAzubiListPanel(currentUser, berichtsheftService, this);
			} else if (rolleID == Rolle.Beschreibung.AZUBI.getRolleId()) {
				this.currentPanel = new OverviewReportsListPanel(currentUser, berichtsheftService, this, null);
			}
			break;
		case 1:
			// Test A - AzubiDayPanel
			this.currentPanel = new AzubiDayPanel(currentUser, berichtsheftService, this, null);
			break;
		case 2:
			// Test B - AzubiWeekPanel
			this.currentPanel = new AzubiWeekPanel(currentUser, berichtsheftService, this, null);
			break;
		case 3:
			// Test C - OverviewAzubiListPanel
			this.currentPanel = new OverviewAzubiListPanel(currentUser, berichtsheftService, this);
			break;
		case 4:
			// Test D - OverviewReportsListPanel
			this.currentPanel = new OverviewReportsListPanel(currentUser, berichtsheftService, this, null);
			break;
		case 5:
			// Test E - SignPanel
			this.currentPanel = new SignPanel(currentUser, berichtsheftService, this, null);
			break;
		default:
			break;
		}
		this.add(currentPanel);
		Link<String> lk = new Link<String>("backLink") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				backPressed();
			}
		};

		lk.setBody(Model.of("Zurück"));
		this.add(lk);
		infoModel = Model.of("");
		this.add(new Label("info", infoModel));
	}

	/**
	 * Zurück Button gedrückt
	 */
	@Override
	public void backPressed() {
		if (panelHistory.size() > 0) {
			this.remove(currentPanel);
			currentPanel = panelHistory.get(panelHistory.size() - 1);
			panelHistory.remove(panelHistory.size() - 1);
			this.add(currentPanel);
		}
	}

	@Override
	public void changeToOverviewAzubiList() {
		this.panelHistory.add(currentPanel);
		this.remove(currentPanel);
		this.currentPanel = new OverviewAzubiListPanel(currentUser, berichtsheftService, this);
		this.add(currentPanel);
	}

	@Override
	public void changeToOverviewReportsList(AzubiReports aReports) {
		this.panelHistory.add(currentPanel);
		this.remove(currentPanel);
		this.currentPanel = new OverviewReportsListPanel(currentUser, berichtsheftService, this, aReports);
		this.add(currentPanel);
	}

	@Override
	public void changeToAzubiWeekDayPanel(de.dpma.azubiweb.model.Berichtsheft reportToView) {
		this.panelHistory.add(currentPanel);
		this.remove(currentPanel);
		this.currentPanel = new AzubiWeekPanel(currentUser, berichtsheftService, this, reportToView);
		this.add(currentPanel);
	}

	@Override
	public void changeToSign(de.dpma.azubiweb.model.Berichtsheft reportToView) {
		this.panelHistory.add(currentPanel);
		this.remove(currentPanel);
		this.currentPanel = new SignPanel(currentUser, berichtsheftService, this, reportToView);
		this.add(currentPanel);
	}

	@Override
	public void setInfo(String info) {
		infoModel.setObject(info);

	}

	public static Label[] getLabelsWeek(int days) {
		String[] week = new String[] { "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag",
				"Sonntag" };
		Label[] labels = new Label[days];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new Label("w" + i, week[i]);
		}
		return labels;

	}

	public static String getStringForDay(int day) {
		String[] week = new String[] { "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag",
				"Sonntag" };
		return week[day];

	}

}
