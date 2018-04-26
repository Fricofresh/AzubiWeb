package de.dpma.azubiweb.view.berichtsheft;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class BerichtsheftALOverviewPanel extends Panel {

	public BerichtsheftALOverviewPanel(String id) {

		super(id);
		this.init();

	}

	private void init() {
		add(Berichtsheft.getLabelsWeek(5));
	}

}
