package de.dpma.azubiweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Referat;
import de.dpma.azubiweb.model.ReferatRepository;
import de.dpma.azubiweb.model.User;

@Service
public class ReferatService {
	
	@Autowired
	private ReferatRepository referatRepository;
	
	public List<Referat> getAllReferat() {
		
		return referatRepository.findAll();
	}
	
	public void saveReferat(Referat referate) {
		
		referatRepository.save(referate);
	}
	
	public Referat getReferatById(int id) {
		
		return referatRepository.findById(id).get();
	}
	
	public Referat getReferatByReferat(String referat) {
		
		return referatRepository.findByReferat(referat);
	}
	
	public Referat getReferatByAnsprechpartner(User ansprechpartner) {
		
		return referatRepository.findByAnsprechpartner(ansprechpartner);
	}
	
	public boolean updateReferat(Referat referat) {
		
		try {
			referatRepository.delete(referat);
			referatRepository.save(referat);
			return true;
		}
		catch (Exception e) {
			return false;
		}
		
	}
}
