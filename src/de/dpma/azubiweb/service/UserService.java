package de.dpma.azubiweb.service;

import org.apache.wicket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.model.UserRepository;
import de.dpma.azubiweb.util.PasswordAuthentication;

//@Controller
//@RequestMapping("../model")
//@Component
//@RestController
@Service
public class UserService {
	
	@Autowired
	// @SpringBean
	// @Inject
	private UserRepository userRepository;
	
	private PasswordAuthentication pa = new PasswordAuthentication();
	
	// @Transactional
	public void saveUser(User user) {
		
		user.setPassword(pa.hash(user.getPassword().toCharArray()));
		if (user.getEmail() == null || user.getEmail().isEmpty())
			user.setEmail(user.getVorname() + "." + user.getNachname() + "@dpma.de");
		userRepository.save(user);
	}
	
	// @Transactional
	public boolean validateUser(String username, String password) {
		
		if (getUserByName(username) == null || getUserByName(username).getUsername().isEmpty()) {
			Session.get().error("Benutzer nicht gefunden");
			return false;
		}
		if (pa.authenticate(password.toCharArray(), userRepository.findByUsernameIgnoreCase(username).getPassword()))
			return true;
		return false;
	}
	
	// @Transactional
	public User getUserByName(String username) {
		
		return userRepository.findByUsernameIgnoreCase(username);
	}
	
	public User getUserByID(int id) {
		
		return userRepository.findById(id);
	}
	
	public boolean updateUser(User user) {
		
		try {
			// userRepository.updateUser(user.getId(), user.getUsername(),
			// user.getPassword(), user.getRights(),
			// user.getVorname(), user.getNachname(), user.getEmail(),
			// user.getEinstiegsjahr());
			checkUser(user);
			deleteUserById(user.getId());
			user.setPassword(pa.hash(user.getPassword().toCharArray()));
			userRepository.save(user);
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	private User checkUser(User user) {
		
		if (user.getEmail() == null || user.getEmail().isEmpty())
			user.setEmail(user.getVorname() + "." + user.getNachname() + "@dpma.de");
		if (!pa.isHashed(user.getPassword()))
			user.setPassword(pa.hash(user.getPassword().toCharArray()));
		return user;
	}
	
	public boolean deleteUser(User user) {
		
		try {
			userRepository.delete(user);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteUserById(int id) {
		
		try {
			userRepository.deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteUserByUsername(String username) {
		
		try {
			userRepository.deleteUserByUsername(username);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean updateUserPasswort(User user) {
		
		try {
			checkUser(user);
			userRepository.updateUserPassword(user.getId(), user.getPassword());
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
