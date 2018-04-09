package de.dpma.azubiweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.AusbildungsartRepository;

/**
 * <p>
 * Die Service Klasse für die Entity {@link Ausbildungsart}.
 * </p>
 * Kommuniziert mit {@link AusbildungsartRepository} und muss verwendet werden,
 * wenn man mit der Datenbank kommunizieren möchte. <br>
 * Es ist der Controller für JPA.
 * 
 * @author Kenneth Böhmer
 */
@Service
public class AusbildungsartService {
	
	@Autowired
	private AusbildungsartRepository ausbildungsartRepository;
	
	/**
	 * Speichert eine Ausbildungsart.
	 * 
	 * @param ausbildungsart
	 *            Ausbildungsart
	 */
	public void saveAusbildungsart(Ausbildungsart ausbildungsart) {
		
		ausbildungsartRepository.save(ausbildungsart);
	}
	
	/**
	 * Löscht eine Ausbildungsart.
	 * 
	 * @param ausbildungsart
	 *            Ausbildungsart
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	public boolean deleteAusbildugnsart(Ausbildungsart ausbildungsart) {
		
		try {
			ausbildungsartRepository.delete(ausbildungsart);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Löscht eine Ausbildungsart nach dem weiblichen Berufssbild
	 * 
	 * @param berufsbildW
	 *            weibliches Berufsbild
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	public boolean deleteAusbildungsartByBerufsbildW(String berufsbildW) {
		
		try {
			ausbildungsartRepository.deleteAusbildungsartByBerufsbildW(berufsbildW);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Löscht eine Ausbildungsart nach dem männlichen Berufssbild
	 * 
	 * @param berufsbildM
	 *            männliches Berufsbild
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	public boolean deleteAusbildungsartByBerufsbildM(String berufsbildM) {
		
		try {
			ausbildungsartRepository.deleteAusbildungsartByBerufsbildM(berufsbildM);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Löscht eine Ausbildungsart nach der Abkürzung des Berufssbildes
	 * 
	 * @param berufsbild
	 *            abgekürztes Berufsbild
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	public boolean deleteAusbildungsartByBerufsbildAbkürzung(String berufsbild) {
		
		try {
			ausbildungsartRepository.deleteAusbildungsartByBerufsbildAbkürzung(berufsbild);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Holt sich eine Ausbildungsart nach der Abkürzung des Berufssbildes
	 * 
	 * @param abkürzung
	 *            abgekürztes Berufsbild
	 * @return das gefundene Ausbildungsart
	 */
	public Ausbildungsart getAusbildungsartByAbkürzung(String abkürzung) {
		
		return ausbildungsartRepository.findByBerufsbildAbkürzung(abkürzung);
	}
	
	/**
	 * Holt sich eine Ausbildungsart nach dem weiblichen Berufssbild
	 * 
	 * @param berufsbildW
	 *            weibliches Berufsbild
	 * @return das gefundene Ausbildungsart
	 */
	public Ausbildungsart getAusbildungsartByBerufsbildW(String berufsbildW) {
		
		return ausbildungsartRepository.findByBerufsbildW(berufsbildW);
	}
	
	/**
	 * Holt sich eine Ausbildungsart nach dem männlichen Berufssbild
	 * 
	 * @param berufsbildM
	 *            männliches Berufsbild
	 * @return das gefundene Ausbildungsart
	 */
	public Ausbildungsart getAusbildungsartByBerufsbildM(String berufsbildM) {
		
		return ausbildungsartRepository.findByBerufsbildAbkürzung(berufsbildM);
	}
	
	/**
	 * Holt sich alle Ausbildungsarten
	 * 
	 * @return alle Ausbildungsarten
	 */
	public List<Ausbildungsart> getAllAusbildungsart() {
		
		return ausbildungsartRepository.findAll();
	}
	
}
