package de.dpma.azubiweb.view.berichtsheft;

import java.util.ArrayList;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.BerichtsheftRepository;
import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.service.UserService;
import de.dpma.azubiweb.view.RootPage;
import de.dpma.azubiweb.view.berichtsheft.panel.BerichtsheftPanel;
import de.dpma.azubiweb.view.berichtsheft.panel.OverviewAzubiListPanel;
import de.dpma.azubiweb.view.berichtsheft.panel.OverviewReportsListPanel;

/**
 * Controller der Berichtsheft Seite
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
	private String currentId;
	private BerichtsheftPanel currentPanel;

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

	@SuppressWarnings("unlikely-arg-type")
	private void initial() {
		this.currentUser = session.getUser();

		this.add(new Label("title", currentUser.getNachname() + ", " + currentUser.getVorname()));
		add(new Label("message", "Titel: " + currentUser.getRolle().getBeschreibung()));
		Form<User> formBerichtsheft = new Form<>("form");

		int rolleID = this.currentUser.getRolle().getId();
		if (rolleID == Rolle.Beschreibung.A.getRolleId() || rolleID == Rolle.Beschreibung.AL.getRolleId()) {
			this.currentPanel = new OverviewAzubiListPanel("panel", currentUser, berichtsheftService, this);
		} else if (rolleID == Rolle.Beschreibung.AZUBI.getRolleId()) {
			this.currentPanel = new OverviewReportsListPanel("panel", currentUser, berichtsheftService, this);
		}

		add(currentPanel);

	}

	@Override
	public void changeView(String id) {

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

}
