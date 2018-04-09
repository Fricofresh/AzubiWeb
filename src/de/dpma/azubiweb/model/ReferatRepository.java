package de.dpma.azubiweb.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Das Repository für JPA, dass die Statements generiert und an die Datenbank
 * schickt. <br>
 * Das Repository arbeitet mit dem Model {@link Referat} zuständig.
 * 
 * @author Kenneth Böhmer
 */
public interface ReferatRepository extends CrudRepository<Referat, Integer> {
	
	/**
	 * Holt sich alle Referate.
	 */
	public List<Referat> findAll();
	
	/**
	 * Holt sich ein Referat über den Referatsnamen.
	 * 
	 * @param referat
	 *            der Referatsname in Zahlen z. B. (4.1.3)
	 * @return das gefundene Referat
	 */
	public Referat findByReferat(String referat);
	
	/**
	 * Löscht ein Referat über das {@link Referat}.
	 * 
	 * @param referat
	 *            Referat
	 */
	public void deleteByReferat(String referat);
	
	/**
	 * Holt sich das zuständige Referat, abhängig vom Ansprechpartner.
	 * 
	 * @param ansprechpartner
	 *            Ansprechpartner
	 * @return das gefundene Referat
	 */
	public Referat findByAnsprechpartner(User ansprechpartner);
	
	/**
	 * Aktuallisiert einen oder mehrere Ansprechpartnern
	 * 
	 * @param id
	 *            Referats ID
	 * @param user
	 *            Liste aus Ansprechpartner
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Referat SET ansprechpartner = ?2 WHERE id = ?1")
	public void updateAnsprechpartner(int id, List<User> user);
}
