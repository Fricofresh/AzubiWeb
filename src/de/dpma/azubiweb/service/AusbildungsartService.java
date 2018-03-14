package de.dpma.azubiweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.AusbildungsartRepository;

@Service
public class AusbildungsartService {
	
	@Autowired
	private AusbildungsartRepository ausbildungsartRepository;
	
	public void saveAusbildungsart(Ausbildungsart ausbildungsart) {
		
		ausbildungsartRepository.save(ausbildungsart);
	}
	
	public boolean deleteAusbildugnsart(Ausbildungsart ausbildungsart) {
		
		try {
			ausbildungsartRepository.delete(ausbildungsart);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteAusbildungsartByBerufsbildW(String berufsbildW) {
		
		try {
			ausbildungsartRepository.deleteAusbildungsartByBerufsbildW(berufsbildW);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteAusbildungsartByBerufsbildM(String berufsbildM) {
		
		try {
			ausbildungsartRepository.deleteAusbildungsartByBerufsbildM(berufsbildM);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteAusbildungsartByBerufsbildAbkürzung(String berufsbild) {
		
		try {
			ausbildungsartRepository.deleteAusbildungsartByBerufsbildAbkürzung(berufsbild);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public Ausbildungsart getAusbildungsartByAbkürzung(String abkürzung) {
		
		return ausbildungsartRepository.findByBerufsbildAbkürzung(abkürzung);
	}
	
	public Ausbildungsart getAusbildungsartByBerufsbildW(String berufsbildW) {
		
		return ausbildungsartRepository.findByBerufsbildW(berufsbildW);
	}
	
	public Ausbildungsart getAusbildungsartByBerufsbildM(String berufsbildM) {
		
		return ausbildungsartRepository.findByBerufsbildAbkürzung(berufsbildM);
	}
	
	public List<Ausbildungsart> getAllAusbildungsart() {
		
		return ausbildungsartRepository.findAll();
	}
	
}
