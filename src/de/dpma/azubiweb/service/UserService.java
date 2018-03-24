package de.dpma.azubiweb.service;

import java.util.List;

import org.apache.wicket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Rolle;
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
	private UserRepository userRepository;
	
	private PasswordAuthentication pa = new PasswordAuthentication();
	
	public void saveUser(User user) {
		
		user = checkUser(user);
		userRepository.save(user);
	}
	
	public boolean validateUser(String username, String password) {
		
		if (getUserByName(username) == null || getUserByName(username).getUsername().isEmpty()) {
			Session.get().error("Benutzer nicht gefunden");
			return false;
		}
		if (pa.authenticate(password.toCharArray(), userRepository.findByUsernameIgnoreCase(username).getPassword()))
			return true;
		return false;
	}
	
	public User getUserByName(String username) {
		
		return userRepository.findByUsernameIgnoreCase(username);
	}
	
	public User getUserByID(int id) {
		
		return userRepository.findById(id);
	}
	
	// TODO Richtiges Update statement ausführen
	// TODO Überprüfen ob die einfache save() Methode doch möglich ist
	@SuppressWarnings("Nicht Benutzbar")
	public boolean updateUser(User user) {
		
		try {
			user = checkUser(user);
			// userRepository.updateUser(user.getId(), user.getUsername(),
			// user.getPassword(), user.getRolle(),
			// user.getVorname(), user.getNachname(), user.getEmail(),
			// user.getAusbildungsart(),
			// user.getEinstiegsjahr(), user.getGeschlecht());
			// System.out.println(user);
			// userRepository.updateUser(user.getId(), user.getUsername(),
			// user.getPassword(), user.getRolle(),
			// user.getVorname(), user.getNachname(), user.getEmail(),
			// user.getEinstiegsjahr(),
			// user.getGeschlecht());
			// userRepository.updateUserAusbildungsart(user.getId(),
			// user.getAusbildungsart());
			
			userRepository.save(user);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	private User checkUser(User user) {
		
		if (user.getUsername() == null || user.getUsername().trim().isEmpty())
			user.setUsername(user.getVorname().substring(0, 2)
					+ (user.getNachname().length() <= 6 ? user.getNachname() : user.getNachname().substring(0, 6)));
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
			user = checkUser(user);
			userRepository.updateUserPassword(user.getId(), user.getPassword());
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}
	
	public List<User> getUserByRolle(Rolle rolle) {
		
		return userRepository.findByRolle(rolle);
	}
}
