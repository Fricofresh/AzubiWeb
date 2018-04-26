package de.dpma.azubiweb.view.berichtsheft;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.User;
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

	private void initial() {
		this.currentUser = session.getUser();

		add(new Label("title", user.getNachname() + ", " + user.getVorname()));
		add(new Label("message", "Titel: " + session.getUser().getRolle().getBeschreibung()));
		Form<User> formBerichtsheft = new Form<>("form");

		if (user.getAusbildungsart().equals(Ausbildungsart.VALUES[0])) {
			add(new BerichtsheftAzubiPanel("panel"));
		} else if (user.getAusbildungsart().equals(Ausbildungsart.VALUES[1])) {
			add(new BerichtsheftAzubiPanel("panel"));
		} else {
			add(new BerichtsheftAzubiPanel("panel"));
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
