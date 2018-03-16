package de.dpma.azubiweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Referat;
import de.dpma.azubiweb.model.ReferatRepository;

@Service
public class ReferatService {
	
	@Autowired
	private ReferatRepository referatRepository;
	
	public List<Referat> getAllAusbildungsreferat() {
		
		return referatRepository.findAll();
	}
	
	public void saveAusbildungsreferat(Referat referate) {
		
		referatRepository.save(referate);
	}
	
	public Referat getAusbildungsreferatById(int id) {
		
		return referatRepository.findById(id).get();
	}
	
	public Referat getAusbildungsreferatByReferat(String referat) {
		
		return referatRepository.findByReferat(referat);
	}
}
