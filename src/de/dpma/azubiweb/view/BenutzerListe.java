package de.dpma.azubiweb.view;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.dpma.azubiweb.model.User;

public class BenutzerListe extends BenutzerVerwaltungsBasePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7429354950006342422L;
	
	public BenutzerListe() {
		
		super();
		initial();
	}
	
	public BenutzerListe(PageParameters pageParameters) {
		
		super(pageParameters);
		initial();
	}
	
	public void initial() {
		
		List<User> userData = session.getUserService().getAllUser();
		
		ListView<?> benutzerListeListView = new ListView<User>("benutzerListView", userData) {
			
			@Override
			protected void populateItem(ListItem<User> item) {
				
				User user = (User) item.getModelObject();
				// RepeatingView parentRepeatingView = new
				// RepeatingView("parentRepeatingView");
				// add(item);
				item.add(new Label("listPunktLabel", user.getVorname() + " " + user.getNachname()));
				item.add(new Link<String>("bearbeitenLink") {
					
					@Override
					public void onClick() {
						
					}
				}, new Link<String>("löschenLink") {
					
					@Override
					public void onClick() {
						
					}
				});
			}
		};
		add(benutzerListeListView);
	}
}
