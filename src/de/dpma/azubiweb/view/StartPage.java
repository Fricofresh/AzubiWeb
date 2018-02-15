package de.dpma.azubiweb.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;

import de.dpma.azubiweb.service.UserService;

@WicketHomePage
public class StartPage extends WebPage {
	
	@SpringBean
	UserService us;
	// public StartPage(String id, IModel<?> model) {
	//
	// super(id, model);
	// // Hier kommen die Methoden, was passieren soll.
	// }
	
	public StartPage() {
		
		// handleAll();
	}
	
	private void handleAll() {
		
		// "azubiAnlageButton", "berichtsheftButton", "steckbriefButton",
		// "einsatzpalnButton", "loginLink"
		Button azubiAnlegen = new Button("azubiAnlageButton");
		
		azubiAnlegen.add(i -> System.out.println("hi") /*
														 * ur.save(new User())
														 */);
		Link<Label> loginLink = new Link<Label>("loginHref") {
			
			@Override
			public void onClick() {
				
				System.out.println("Viel Spaß beim Anmelden");
			}
		};
		loginLink.add(new Label("loginLink", "Login"));
		add(azubiAnlegen, new Button("berichtsheftButton"), new Button("steckbriefButton"),
				new Button("einsatzpalnButton"), loginLink, new Label("startpage", "Das ist ein Test"));
	}
}
