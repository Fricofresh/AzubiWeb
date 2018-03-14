package de.dpma.azubiweb.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BerichtsheftRepository extends CrudRepository<Berichtsheft, Integer> {
	
	// public void updateBerichtsheft(Berichtsheft berichtsheft);
	
}
