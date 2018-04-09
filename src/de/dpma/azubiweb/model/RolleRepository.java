package de.dpma.azubiweb.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Das Repository für JPA, dass die Statements generiert und an die Datenbank
 * schickt. <br>
 * Das Repository arbeitet mit dem Model {@link Rolle} zuständig.
 * 
 * @author Kenneth Böhmer
 */
public interface RolleRepository extends CrudRepository<Rolle, Integer> {
	
	/**
	 * Holt sich alle Rollen.
	 */
	@Override
	public List<Rolle> findAll();
}
