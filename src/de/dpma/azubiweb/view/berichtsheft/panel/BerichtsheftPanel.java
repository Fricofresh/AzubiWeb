package de.dpma.azubiweb.view.berichtsheft.panel;

import org.apache.wicket.markup.html.panel.Panel;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.view.berichtsheft.PanelChange;
/**
 * Muttel aller Panels im Berichtsheft
 * @author VIT Student
 *
 */
public abstract class BerichtsheftPanel extends Panel {
	private static final long serialVersionUID = -55762552827500768L;
	private String name;
	protected User currentUser;
	protected BerichtsheftService service;
	protected PanelChange change;
	public BerichtsheftPanel(String id,User currentUser, BerichtsheftService berichtsheftService, PanelChange change,String name) {

		super(id);
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
