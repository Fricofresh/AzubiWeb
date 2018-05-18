
package de.dpma.azubiweb.view.berichtsheft;

import java.util.ArrayList;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.service.UserService;
import de.dpma.azubiweb.view.RootPage;
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

	private static final long serialVersionUID = 1392603770886213724L;

	@SpringBean
	private BerichtsheftService berichtsheftService;

	protected UserService userService = session.getUserService();
	private BerichtsheftPanel currentPanel;

	private ArrayList<BerichtsheftPanel> panelHistory;

	public Berichtsheft() {
		super();
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
		int rolleID = this.currentUser.getRolle().getId();
		if (rolleID == Rolle.Beschreibung.A.getRolleId() || rolleID == Rolle.Beschreibung.AL.getRolleId()) {
			this.currentPanel = new OverviewAzubiListPanel("panel", currentUser, berichtsheftService, this);
		} else if (rolleID == Rolle.Beschreibung.AZUBI.getRolleId()) {
			this.currentPanel = new OverviewReportsListPanel("panel", currentUser, berichtsheftService, this, null);
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
		this.currentPanel = new OverviewAzubiListPanel("panel", currentUser, berichtsheftService, this);
		this.add(currentPanel);
	}

	@Override
	public void changeToOverviewReportsList(AzubiReports aReports) {
		this.panelHistory.add(currentPanel);
		this.remove(currentPanel);
		this.currentPanel = new OverviewReportsListPanel("panel", currentUser, berichtsheftService, this, aReports);
		this.add(currentPanel);
	}

	@Override
	public void changeToAzubiWeekDayPanel(de.dpma.azubiweb.model.Berichtsheft reportToView) {
		this.panelHistory.add(currentPanel);
		this.remove(currentPanel);
		this.currentPanel = new AzubiWeekPanel("panel", currentUser, berichtsheftService, this, reportToView);
		this.add(currentPanel);
	}

	@Override
	public void changeToSign(de.dpma.azubiweb.model.Berichtsheft reportToView) {
		this.panelHistory.add(currentPanel);
		this.remove(currentPanel);
		this.currentPanel = new SignPanel("panel", currentUser, berichtsheftService, this, reportToView);
		this.add(currentPanel);
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
