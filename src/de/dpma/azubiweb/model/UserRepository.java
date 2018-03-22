package de.dpma.azubiweb.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.dpma.azubiweb.model.User.Geschlecht;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	public User findByUsernameIgnoreCase(String username);
	
	@Query("DELETE FROM User u WHERE u.username = ?1")
	public void deleteUserByUsername(String username);
	
	public User findById(int id);
	
	@Override
	public List<User> findAll();
	
	public List<User> findByRolle(Rolle rolle);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET password = ?2 WHERE u.id = ?1")
	public void updateUserPassword(int id, String password);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET username = ?2 WHERE u.id = ?1")
	public void updateUserUsername(int id, String username);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET vorname = ?2 WHERE u.id = ?1")
	public void updateUserVorname(int id, String vorname);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET nachname = ?2 WHERE u.id = ?1")
	public void updateUserNachname(int id, String nachname);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET email = ?2 WHERE u.id = ?1")
	public void updateUserEmail(int id, String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET einstiegsjahr = ?2 WHERE u.id = ?1")
	public void updateUserEinstiegsjahr(int id, Integer einstiegsjahr);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET password = ?2 WHERE u.id = ?1")
	public void updateUserAusbildungsart(int id, List<Ausbildungsart> ausbildungsart);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET rolle = ?2 WHERE u.id = ?1")
	public void updateUserRolle(int id, Rolle rolle);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET geschlecht = ?2 WHERE u.id = ?1")
	public void updateUserGeschlecht(int id, Geschlecht geschlecht);
	
	@Transactional
	@Modifying
	// @Query("UPDATE User u SET username = ?2, password = ?3, rolle = ?4,
	// vorname = ?5, nachname = ?6, email = ?7, ausbildung = ?8, einstiegsjahr =
	// ?9, geschlecht = ?10 WHERE u.id = ?1")
	@Query("UPDATE User u SET username = ?2,  password = ?3, rolle = ?4, vorname = ?5, nachname = ?6, email = ?7, einstiegsjahr = ?8,  geschlecht = ?9 WHERE u.id = ?1")
	public void updateUser(int id, String username, String password, Rolle rolle, String vorname, String nachname,
			String email, /* List<Ausbildungsart> ausbildung, */ Integer einstiegsjahr, Geschlecht geschlecht);
}
