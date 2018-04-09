package de.dpma.azubiweb.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Das Repository für JPA, dass die Statements generiert und an die Datenbank
 * schickt. <br>
 * Das Repository arbeitet mit dem Model {@link Berichtsheft} zuständig.
 * 
 * @author Kenneth Böhmer
 */
@Repository
public interface BerichtsheftRepository extends CrudRepository<Berichtsheft, Integer> {
	
	/**
	 * Holt sich alle Berichtshefte.
	 */
	@Override
	public List<Berichtsheft> findAll();
	// public void updateBerichtsheft(Berichtsheft berichtsheft);
	
}
