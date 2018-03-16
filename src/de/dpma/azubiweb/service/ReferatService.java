package de.dpma.azubiweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Ausbildungsreferat;
import de.dpma.azubiweb.model.AusbildungsreferatRepository;

@Service
public class AusbildungsreferatService {
	
	@Autowired
	private AusbildungsreferatRepository ausbildungsreferatRepository;
	
	public List<Ausbildungsreferat> getAllAusbildungsreferat() {
		
		return ausbildungsreferatRepository.findAll();
	}
	
	public void saveAusbildungsreferat(Ausbildungsreferat ausbildungsreferat) {
		
		ausbildungsreferatRepository.save(ausbildungsreferat);
	}
	
	public Ausbildungsreferat getAusbildungsreferatById(int id) {
		
		return ausbildungsreferatRepository.findById(id).get();
	}
	
	public Ausbildungsreferat getAusbildungsreferatByReferat(String referat) {
		
		return ausbildungsreferatRepository.findByReferat(referat);
	}
}
