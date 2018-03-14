package de.dpma.azubiweb.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RolleRepository extends CrudRepository<Rolle, Integer> {
	
	@Override
	public List<Rolle> findAll();
}
