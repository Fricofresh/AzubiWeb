package de.dpma.azubiweb.service;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.dpma.azubiweb.model.User;

/**
 * Die Session ist für die Anmeldung eines Benutzers da.<br>
 * Authentifiziert den Benutzer.
 * 
 * @author Kenneth Böhmer
 *
 */
public class UserSession extends AuthenticatedWebSession {
	
	private static final long serialVersionUID = -8171442512896032131L;
	
	@SpringBean
	private UserService userService;
	
	private String username;
	
	private User user;
	
	public UserSession(Request request) {
		
		super(request);
		Injector.get().inject(this);
	}
	
	/**
	 * Authentifiziert den Benutzer. <br>
	 * Setzt den Benutzer und Benutzernamen um später wieder darauf zuzugreifen.
	 * <br>
	 * 
	 * @param username
	 *            der Benutzername
	 * @param password
	 *            das Passwort
	 * @return <strong>boolean</strong> Anmeldedaten == richtig
	 */
	@Override
	public boolean authenticate(final String username, final String password) {
		
		boolean signedIn = userService.validateUser(username, password);
		if (signedIn) {
			this.username = username;
			this.user = userService.getUserByName(username);
		}
		return signedIn;
	}
	
	/**
	 * Meldet den Benutzer ab und setzt die Benutzerdaten für diese Session auf
	 * null.
	 */
	@Override
	public void signOut() {
		
		super.signOut();
		this.user = null;
		this.username = null;
	}
	
	/**
	 * Setzt die Rolle des angemeldeten Benutzers.
	 * 
	 * @return
	 */
	@Override
	public Roles getRoles() {
		
		Roles rollen = new Roles();
		if (isSignedIn())
			rollen.add(getUser().getRolle().getBeschreibung());
		
		return rollen;
	}
	
	/**
	 * @return den aktuell angemeldeten Benutzer
	 */
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
