package de.dpma.azubiweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.model.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository ur;
	
	@Transactional
	public void saveUser(User user) {
		
		ur.save(user);
	}
}
