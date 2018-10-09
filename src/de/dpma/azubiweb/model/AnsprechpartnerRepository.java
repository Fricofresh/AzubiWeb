package de.dpma.azubiweb.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AnsprechpartnerRepository extends CrudRepository<Ansprechpartner, Integer> {
	
	@Override
	public List<Ansprechpartner> findAll();
	
	public Ansprechpartner findByUser(User user);
	
	public List<Ansprechpartner> findByReferat(Referat referat);
}
