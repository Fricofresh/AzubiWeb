package de.dpma.azubiweb.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AusbildungsreferatRepository extends CrudRepository<Ausbildungsreferat, Integer> {
	
	@Override
	public List<Ausbildungsreferat> findAll();
	
	public Ausbildungsreferat findByReferat(String referat);
}
