package de.dpma.azubiweb.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReferatRepository extends CrudRepository<Referat, Integer> {
	
	public List<Referat> findAll();
	
	public Referat findByReferat(String referat);
	
	public void deleteByReferat(String referat);
	
	public Referat findByAnsprechpartner(User ansprechpartner);
}
