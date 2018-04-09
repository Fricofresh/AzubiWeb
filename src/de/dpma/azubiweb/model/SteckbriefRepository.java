package de.dpma.azubiweb.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Das Repository für JPA, dass die Statements generiert und an die Datenbank
 * schickt. <br>
 * Das Repository arbeitet mit dem Model {@link Steckbrief} zuständig.
 * 
 * @author Kenneth Böhmer
 */
public interface SteckbriefRepository extends CrudRepository<Steckbrief, Integer> {
	
	/**
	 * Holt sich alle Steckbriefe.
	 */
	@Override
	public List<Steckbrief> findAll();
}
