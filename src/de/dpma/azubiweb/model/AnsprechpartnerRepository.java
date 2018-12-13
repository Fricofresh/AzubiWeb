package de.dpma.azubiweb.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AnsprechpartnerRepository extends CrudRepository<Ansprechpartner, Integer> {
	
	@Override
	public List<Ansprechpartner> findAll();
	
	public Ansprechpartner findByUser(User user);
	
	@Query("Select a FROM Ansprechpartner a WHERE a.user.id = ?1")
	public Ansprechpartner findByUser(int userid);
	
	// @Query("DELETE FROM Ansprechpartner a WHERE a.user = ?1")
	// public void deleteByUser(User user);
	//
	// @Query("DELETE FROM Ansprechpartner a WHERE a.user.id = ?1")
	// public void deleteByUserID(int userid);
	//
	// @Query("DELETE FROM Ansprechpartner a WHERE a.user.username = ?1")
	// public void deleteByUserUsername(String username);
	
	public List<Ansprechpartner> findByReferat(Referat referat);
}
