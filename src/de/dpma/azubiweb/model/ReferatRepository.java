package de.dpma.azubiweb.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReferatRepository extends CrudRepository<Referat, Integer> {
	
	public List<Referat> findAll();
	
	public Referat findByReferat(String referat);
	
	public void deleteByReferat(String referat);
	
	public Referat findByAnsprechpartner(User ansprechpartner);
	
	@Transactional
	@Modifying
	@Query("UPDATE Referat SET ansprechpartner = ?2 WHERE id = ?1")
	public void updateAnsprechpartner(int id, List<User> user);
}
