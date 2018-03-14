package de.dpma.azubiweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.RolleRepository;

@Service
public class RolleService {
	
	@Autowired
	private RolleRepository rolleRepository;
	
	public void saveRolle(Rolle rolle) {
		
		rolleRepository.save(rolle);
	}
	
	public List<Rolle> getAllRolles() {
		
		return rolleRepository.findAll();
	}
	
	public boolean deleteRolle(Rolle rolle) {
		
		try {
			rolleRepository.delete(rolle);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteAllRolle() {
		
		try {
			rolleRepository.deleteAll();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public Rolle getRolle(Beschreibung beschreibung) {
		
		return rolleRepository.findById(beschreibung.getRolleId()).get();
	}
	
}
