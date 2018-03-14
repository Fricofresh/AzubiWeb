package de.dpma.azubiweb.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	public User findByUsernameIgnoreCase(String username);
	
	@Query("DELETE FROM User u WHERE u.username = ?1")
	public void deleteUserByUsername(String username);
	
	public User findById(int id);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET password = ?2 WHERE u.id = ?1")
	public void updateUserPassword(int id, String password);
	
	@Override
	public List<User> findAll();
	
}
