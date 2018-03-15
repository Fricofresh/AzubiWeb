package de.dpma.azubiweb.service;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.dpma.azubiweb.model.User;

public class UserSession extends /* SecureWebSession */ AuthenticatedWebSession {
	
	private static final long serialVersionUID = -8171442512896032131L;
	
	@SpringBean
	// @Autowired
	private UserService userService;
	
	private String username;
	
	private User user;
	
	public UserSession(Request request) {
		
		super(request);
		Injector.get().inject(this);
	}
	
	@Override
	public boolean authenticate(final String username, final String password) {
		
		// new InitialInsert();
		boolean signedIn = userService.validateUser(username, password);
		if (signedIn) {
			this.username = username;
			this.user = userService.getUserByName(username);
		}
		return signedIn;
	}
	
	@Override
	public void signOut() {
		
		super.signOut();
		this.user = null;
	}
	
	@Override
	public Roles getRoles() {
		
		Roles rollen = new Roles();
		if (isSignedIn())
			rollen.add(getUser().getRolle().getBeschreibung());
		// for (Rolle rolle : rs.getAllRolles()) {
		// rollen.add(rolle.getBeschreibung());
		// }
		
		return rollen;
	}
	
	public User getUser() {
		
		// Falls der Benutzer angemeldet ist aber eine neue Session startet.
		// Wird der User gelöscht, deshalb wird der Benutzer wieder gesetzt.
		if (user == null && username != null && !username.isEmpty())
			this.user = userService.getUserByName(username);
		// Sicherheitsweiße wird hier der Benutzer abgemeldet
		else if (username == null || username.isEmpty())
			invalidateNow();
		return user;
	}
	
	public UserService getUserService() {
		
		return userService;
	}
}
