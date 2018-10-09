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
	
	public void deleteAnsprechpartner(User user) {
		
		ansprechpartnerRepository.delete(ansprechpartnerRepository.findByUser(user));
	}
	
	public void deleteAnsprechpartner(int id) {
		
		ansprechpartnerRepository.delete(ansprechpartnerRepository.findByUser(id));
	}
	
	// public void deleteAnsprechpartner(String username) {
	//
	// ansprechpartnerRepository.deleteByUserUsername(username);
	// }
	
}
