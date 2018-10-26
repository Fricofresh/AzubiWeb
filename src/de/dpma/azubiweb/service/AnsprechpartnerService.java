package de.dpma.azubiweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Ansprechpartner;
import de.dpma.azubiweb.model.AnsprechpartnerRepository;
import de.dpma.azubiweb.model.User;

@Service
public class AnsprechpartnerService {
	
	@Autowired
	private AnsprechpartnerRepository ansprechpartnerRepository;
	
	public void addAnsprechpartner(Ansprechpartner ansprechpartner) {
		
		ansprechpartnerRepository.save(ansprechpartner);
	}
	
	public boolean deleteAnsprechpartner(User user) {
		
		try {
			ansprechpartnerRepository.delete(ansprechpartnerRepository.findByUser(user));
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteAnsprechpartner(int id) {
		
		try {
			ansprechpartnerRepository.delete(ansprechpartnerRepository.findByUser(id));
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	// public void deleteAnsprechpartner(String username) {
	//
	// ansprechpartnerRepository.deleteByUserUsername(username);
	// }
	
}
