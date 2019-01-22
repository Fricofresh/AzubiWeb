package de.dpma.azubiweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Steckbrief;
import de.dpma.azubiweb.model.SteckbriefRepository;
import de.dpma.azubiweb.model.User;

@Service
public class SteckbriefService {
	
	@Autowired
	private SteckbriefRepository steckbriefRepository;
	
	public Steckbrief getSteckbrief(User user) {
		
		return steckbriefRepository.findByUser(user);
	}
	
	public void setSteckbrief(Steckbrief steckbrief) {
		
		steckbriefRepository.save(steckbrief);
	}
}
