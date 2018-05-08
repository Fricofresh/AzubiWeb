package de.dpma.azubiweb.view.berichtsheft;

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

/**
 * Controller der Berichtsheft Seite
 * 
 * @author Benedikt Maier
 *
 */
@AuthorizeInstantiation({ "Auszubildende", "Ausbildungsleiter", "Ausbilder" })
public class Berichtsheft extends RootPage {

	private static final long serialVersionUID = 1392603770886213724L;

	@SpringBean
	private BerichtsheftService berichtsheftService;
	
	protected UserService userService = session.getUserService();
	
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

		add(new Label("title", currentUser.getNachname() + ", " + currentUser.getVorname()));
		add(new Label("message", "Titel: " + currentUser.getRolle().getBeschreibung()));
		Form<User> formBerichtsheft = new Form<>("form");

		if (user.getRolle().getBeschreibung().equals(Rolle.Beschreibung.AZUBI)) {
			add(new BerichtsheftAzubi5DayPanel("panel",currentUser,berichtsheftService));
		} else if (user.getRolle().getBeschreibung().equals(Rolle.Beschreibung.A)) {
			add(new BerichtsheftABLOverviewPanel("panel",currentUser,berichtsheftService));
		} else {
			add(new BerichtsheftABLOverviewPanel("panel",currentUser,berichtsheftService));
		}

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
