package de.dpma.azubiweb.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.dpma.azubiweb.model.User.Geschlecht;

/**
 * Das Repository für JPA, dass die Statements generiert und an die Datenbank
 * schickt. <br>
 * Das Repository arbeitet mit dem Model {@link User} zuständig.
 * 
 * @author Kenneth Böhmer
 */
public interface UserRepository extends CrudRepository<User, Integer> {
	
	/**
	 * Holt sich den Benutzer über den Benutzernamen.
	 * 
	 * @param username
	 *            Benutzernamen
	 * @return den Benutzer
	 */
	public User findByUsernameIgnoreCase(String username);
	
	/**
	 * Löscht einen Benutzer aus der Datenbank abhängig vom Benutzernamen.
	 * 
	 * @param username
	 *            Benutzername
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	@Query("DELETE FROM User u WHERE u.username = ?1")
	public void deleteUserByUsername(String username);
	
	/**
	 * Holt sich alle Benutzer.
	 */
	@Override
	public List<User> findAll();
	
	/**
	 * Holt sich alle Benutzer aus der Datenbank mit der gleichen Rolle.
	 * 
	 * @param rolle
	 *            Rolle
	 * @return Alle Benutzer mit der gleichen Rolle
	 */
	public List<User> findByRolle(Rolle rolle);
	
	/**
	 * Aktuallisiert den Benutzernamen eines Benutzers.
	 * 
	 * @param id
	 *            User ID
	 * @param username
	 *            Benutzernamen
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User u SET username = ?2 WHERE u.id = ?1")
	public void updateUserUsername(int id, String username);
	
	/**
	 * Aktuallisiert das Passwort eines Benutzers.
	 * 
	 * @param id
	 *            User ID
	 * @param password
	 *            das neue Passwort (gehäshed)
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User u SET password = ?2 WHERE u.id = ?1")
	public void updateUserPassword(int id, String password);
	
	/**
	 * Aktuallisiert den Vornamen eines Benutzers.
	 * 
	 * @param id
	 *            User ID
	 * @param vorname
	 *            den neuen Vornamen
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User u SET vorname = ?2 WHERE u.id = ?1")
	public void updateUserVorname(int id, String vorname);
	
	/**
	 * Aktuallisiert den Nachnamen eines Benutzers.
	 * 
	 * @param id
	 *            User ID
	 * @param nachname
	 *            den neuen Nachnamen
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User u SET nachname = ?2 WHERE u.id = ?1")
	public void updateUserNachname(int id, String nachname);
	
	/**
	 * Aktuallisiert die E-Mail-Adresse eines Benutzers.
	 * 
	 * @param id
	 *            User ID
	 * @param email
	 *            die neue E-Mail-Adresse
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User u SET email = ?2 WHERE u.id = ?1")
	public void updateUserEmail(int id, String email);
	
	/**
	 * Aktuallisiert die Ausbildungsart eines Benutzers.
	 * 
	 * @param id
	 *            User ID
	 * @param ausbildungsart
	 *            die neue Ausbildungsart
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.ausbildungsart = ?2 WHERE u.id = ?1")
	public void updateUserAusbildungsart(int id, List<Ausbildungsart> ausbildungsart);
	
	/**
	 * Aktuallisiert das Einstiegsjahr eines Benutzers.
	 * 
	 * @param id
	 *            User ID
	 * @param einstiegsjahr
	 *            das neue Einstiegsjahr
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User u SET einstiegsjahr = ?2 WHERE u.id = ?1")
	public void updateUserEinstiegsjahr(int id, Integer einstiegsjahr);
	
	/**
	 * Aktuallisiert die Rolle eines Benutzers.
	 * 
	 * @param id
	 *            User ID
	 * @param rolle
	 *            die neue Rolle
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User u SET rolle = ?2 WHERE u.id = ?1")
	public void updateUserRolle(int id, Rolle rolle);
	
	/**
	 * Aktuallisiert das Geschlecht eines Benutzers.
	 * 
	 * @param id
	 *            User ID
	 * @param geschlecht
	 *            das neue Geschlecht
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User u SET geschlecht = ?2 WHERE u.id = ?1")
	public void updateUserGeschlecht(int id, Geschlecht geschlecht);
	
	/**
	 * Aktuallisiert einen ganzen Benutzer.
	 * 
	 * @param id
	 *            User ID
	 * @param username
	 *            Benutzernamen
	 * @param password
	 *            Passwort
	 * @param rolle
	 *            Rolle
	 * @param vorname
	 *            Vorname
	 * @param nachname
	 *            Nachname
	 * @param email
	 *            E-Mail-Adresse
	 * @param einstiegsjahr
	 *            Einstiegsjahr
	 * @param geschlecht
	 *            Geschlecht
	 */
	@Transactional
	@Modifying
	// @Query("UPDATE User u SET username = ?2, password = ?3, rolle = ?4,
	// vorname = ?5, nachname = ?6, email = ?7, ausbildung = ?8, einstiegsjahr =
	// ?9, geschlecht = ?10 WHERE u.id = ?1")
	@Query("UPDATE User u SET username = ?2,  password = ?3, rolle = ?4, vorname = ?5, nachname = ?6, email = ?7, einstiegsjahr = ?8,  geschlecht = ?9 WHERE u.id = ?1")
	public void updateUser(int id, String username, String password, Rolle rolle, String vorname, String nachname,
			String email, /* List<Ausbildungsart> ausbildung, */ Integer einstiegsjahr, Geschlecht geschlecht);
}
