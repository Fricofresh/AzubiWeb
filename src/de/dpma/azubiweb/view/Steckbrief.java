package de.dpma.azubiweb.view;

import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.RolleService;
import de.dpma.azubiweb.service.UserService;

public class Steckbrief extends RootPage {
	
	@SpringBean
	private UserService userService;
	
	@SpringBean
	private RolleService rolleService;
	
	private static final long serialVersionUID = 4658861470984188261L;
	
	public Steckbrief() {
		
		super();
		List<User> ausbildungsleiter = userService.getUserByRolle(rolleService.getRolle(Beschreibung.AL));
		ListView<User> ausbildungsleiterListView = new ListView<User>("ausbildungsleiterListView", ausbildungsleiter) {
			
			private static final long serialVersionUID = 8602112233927576927L;
			
			@Override
			protected void populateItem(ListItem<User> item) {
				
				// User ausbildungsleiter = item.getModelObject();
			}
		};
	}
}
