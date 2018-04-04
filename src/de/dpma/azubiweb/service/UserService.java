package de.dpma.azubiweb.service;

import java.util.List;

import org.apache.wicket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.model.UserRepository;
import de.dpma.azubiweb.util.PasswordAuthentication;

/**
 * <p>
 * Die Service Klasse für die Entity {@link User}.
 * </p>
 * Kommuniziert mit {@link UserRepository} und muss verwendet werden, wenn man
 * mit der Datenbank kommunizieren möchte. <br>
 * Es ist der Controller für JPA.
 * 
 * @author Kenneth Böhmer
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private PasswordAuthentication pa = new PasswordAuthentication();
	
	/**
	 * Speichert einen Benutzer.
	 * 
	 * @param user
	 *            Benutzer
	 */
	public void saveUser(User user) {
		
		user = checkUser(user);
		userRepository.save(user);
	}
	
	/**
	 * Überprüft ob das richtige Passwort eingegeben wurde für den Benutzer.
	 * <br>
	 * Das Passwort wird über {@link PasswordAuthentication} verschlüsselt und
	 * verglichen. <br>
	 * Wirft einen Fehler zur {@link Session} bzw. {@link UserSession}, falls
	 * der Benutzer nicht gefunden wurde.
	 *
	 * @param username
	 *            Benutzername
	 * @param password
	 *            Passwort
	 * @return <strong>boolean</strong> true wenn das Passwort mit dem in der
	 *         Datenbank übereinstimmt.
	 */
	public boolean validateUser(String username, String password) {
		
		if (getUserByName(username) == null || getUserByName(username).getUsername().isEmpty()) {
			Session.get().error("Benutzer nicht gefunden");
			return false;
		}
		if (pa.authenticate(password.toCharArray(), userRepository.findByUsernameIgnoreCase(username).getPassword()))
			return true;
		return false;
	}
	
	/**
	 * Holt sich den Benutzer über den Benutzernamen.
	 * 
	 * @param username
	 *            Benutzernamen
	 * @return den Benutzer
	 */
	public User getUserByName(String username) {
		
		return userRepository.findByUsernameIgnoreCase(username);
	}
	
	/**
	 * Holt sich den Benutzer über die ID.
	 * 
	 * @param id
	 * @return den Benutzer
	 */
	public User getUserByID(int id) {
		
		return userRepository.findById(id);
	}
	
	/**
	 * Aktuallisiert den Benutzer. <br>
	 * Momentan wird der Benutzer nur mit der {@link UserRepository#save(User)
	 * save(User)} Methode von {@link UserRepository} aufgerufen und kein echtes
	 * Update Befehl. <br>
	 * Vom übergebenen Benutzer werden {@link UserService#checkUser(User) die
	 * fehlenden Daten ergänzt} und das {@link PasswordAuthentication Passwort}
	 * verschlüsselt.
	 * 
	 */
	// TODO Richtiges Update statement ausführen
	// TODO Überprüfen ob die einfache save() Methode doch möglich ist
	@SuppressWarnings("Nicht Benutzbar")
	public boolean updateUser(User user) {
		
		try {
			user = checkUser(user);
			// userRepository.updateUser(user.getId(), user.getUsername(),
			// user.getPassword(), user.getRolle(),
			// user.getVorname(), user.getNachname(), user.getEmail(),
			// user.getAusbildungsart(),
			// user.getEinstiegsjahr(), user.getGeschlecht());
			// System.out.println(user);
			// userRepository.updateUser(user.getId(), user.getUsername(),
			// user.getPassword(), user.getRolle(),
			// user.getVorname(), user.getNachname(), user.getEmail(),
			// user.getEinstiegsjahr(),
			// user.getGeschlecht());
			// userRepository.updateUserAusbildungsart(user.getId(),
			// user.getAusbildungsart());
			
			userRepository.save(user);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Setzt den Benutzernamen und E-Mail, falls sie nicht übergeben wurden.<br>
	 * Verschlüsselt das {@link PasswordAuthentication Passwort}.
	 * 
	 * @param user
	 * @return bearbeiteter {@link User}
	 */
	private User checkUser(User user) {
		
		String vorname = user.getVorname();
		String nachname = user.getNachname();
		vorname = correctName(vorname);
		nachname = correctName(nachname);
		
		if (user.getUsername() == null || user.getUsername().trim().isEmpty())
			user.setUsername(vorname.substring(0, 2) + (nachname.length() <= 6 ? nachname : nachname.substring(0, 6)));
		if (user.getEmail() == null || user.getEmail().isEmpty())
			user.setEmail(vorname + "." + nachname + "@dpma.de");
		if (!pa.isHashed(user.getPassword()))
			user.setPassword(pa.hash(user.getPassword().toCharArray()));
		return user;
	}
	
	/**
	 * Wandelt die Umlaute wie äöü in ae, oe und ue um.
	 * 
	 * @param name
	 * @return umgewandelter String
	 */
	private String correctName(String name) {
		
		name = name.replaceAll("ä", "ae");
		name = name.replaceAll("ö", "oe");
		name = name.replaceAll("ü", "ue");
		return name;
	}
	
	/**
	 * Löscht einen Benutzer aus der Datenbank.
	 * 
	 * @param user
	 *            Benutzer
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	
	public boolean deleteUser(User user) {
		
		try {
			userRepository.delete(user);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Löscht einen Benutzer aus der Datenbank.
	 * 
	 * @param id
	 *            BenutzerId
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	public boolean deleteUserById(int id) {
		
		try {
			userRepository.deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Löscht einen Benutzer aus der Datenbank.
	 * 
	 * @param username
	 *            Benutzername
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	public boolean deleteUserByUsername(String username) {
		
		try {
			userRepository.deleteUserByUsername(username);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Ruft die {@link UserRepository#updateUserPassword(int, String) } auf.
	 * <br>
	 * Hashed das Passwort und speichert es in die Datenbank.
	 * 
	 * @param user
	 *            Benutzer mit dem Aktuallisierten Passwort
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	public boolean updateUserPasswort(User user) {
		
		try {
			user = checkUser(user);
			userRepository.updateUserPassword(user.getId(), user.getPassword());
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Holt sich alle Benutzer aus der Datenbank.
	 * 
	 * @return Alle Benutzer
	 */
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}
	
	/**
	 * Holt sich alle Benutzer aus der Datenbank mit der gleichen Rolle.
	 * 
	 * @param rolle
	 *            Rolle
	 * @return Alle Benutzer mit der gleichen Rolle
	 */
	public List<User> getUserByRolle(Rolle rolle) {
		
		return userRepository.findByRolle(rolle);
	}
}
