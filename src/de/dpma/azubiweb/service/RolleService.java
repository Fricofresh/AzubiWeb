package de.dpma.azubiweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.RolleRepository;

/**
 * <p>
 * Die Service Klasse für die Entity {@link Rolle}.
 * </p>
 * Kommuniziert mit {@link RolleRepository} und muss verwendet werden, wenn man
 * mit der Datenbank kommunizieren möchte. <br>
 * Es ist der Controller für JPA.
 * 
 * @author Kenneth Böhmer
 *
 */
@Service
public class RolleService {
	
	@Autowired
	private RolleRepository rolleRepository;
	
	/**
	 * Speichert eine Rolle.
	 * 
	 * @param rolle
	 *            Rolle
	 */
	public void saveRolle(Rolle rolle) {
		
		rolleRepository.save(rolle);
	}
	
	/**
	 * Holt sich alle Rollen.
	 * 
	 * @return Alle Rollen
	 */
	public List<Rolle> getAllRolles() {
		
		return rolleRepository.findAll();
	}
	
	/**
	 * Löscht eine Rolle.
	 * 
	 * @param rolle
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	public boolean deleteRolle(Rolle rolle) {
		
		try {
			rolleRepository.delete(rolle);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Löscht alle Rollen.
	 * 
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	public boolean deleteAllRolle() {
		
		try {
			rolleRepository.deleteAll();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Holt eine Rolle über dem ENUM {@link Beschreibung}
	 * 
	 * @param beschreibung
	 *            ENUM-Wert
	 * @return Rolle
	 */
	public Rolle getRolle(Beschreibung beschreibung) {
		
		System.out.println(beschreibung);
		return rolleRepository.findById(beschreibung.getRolleId()).get();
	}
	
}
