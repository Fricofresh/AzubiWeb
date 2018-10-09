package de.dpma.azubiweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.dpma.azubiweb.model.Ansprechpartner;
import de.dpma.azubiweb.model.Referat;
import de.dpma.azubiweb.model.ReferatRepository;
import de.dpma.azubiweb.model.User;

/**
 * <p>
 * Die Service Klasse für die Entity {@link Referat}.
 * </p>
 * Kommuniziert mit {@link ReferatRepository} und muss verwendet werden, wenn
 * man mit der Datenbank kommunizieren möchte. <br>
 * Es ist der Controller für JPA.
 * 
 * @author Kenneth Böhmer
 */
@Service
@Transactional
public class ReferatService {
	
	@Autowired
	private ReferatRepository referatRepository;
	
	@Autowired
	private AnsprechpartnerService ansprechpartnerService;
	
	/**
	 * Gibt alle Referate zurück.
	 * 
	 * @return alle Referate
	 */
	public List<Referat> getAllReferat() {
		
		return referatRepository.findAll();
	}
	
	/**
	 * Speichert einen Referat
	 * 
	 * @param referat
	 *            Referat
	 */
	public void saveReferat(Referat referat) {
		
		referatRepository.save(referat);
	}
	
	/**
	 * Aktuallisiert einen oder mehrere Ansprechpartnern
	 * 
	 * @param referat
	 *            das Referat mit dem zuständigen Ansprechpartnern
	 */
	public void updateAnsprechpartner(Referat referat) {
		
		referatRepository.save(referat);
		// referatRepository.updateAnsprechpartner(referat.getId(),
		// referat.getAnsprechpartner());
		// oldAnsprechpartner = ansprechpartnerRepository.findByReferat(referat)
		// ansprechpartnerRepository.
	}
	
	/**
	 * Gibt ein Referat zurück über die ID.
	 * 
	 * @param id
	 *            Referats ID
	 * @return das gefundene Referat
	 */
	public Referat getReferatById(int id) {
		
		return referatRepository.findById(id).get();
	}
	
	/**
	 * Holt sich ein Referat über den Referatsnamen.
	 * 
	 * @param referat
	 *            der Referatsname in Zahlen z. B. (4.1.3)
	 * @return das gefundene Referat
	 */
	public Referat getReferatByReferat(String referat) {
		
		return referatRepository.findByReferat(referat);
	}
	
	/**
	 * Holt sich das zuständige Referat, abhängig vom Ansprechpartner.
	 * 
	 * @param ansprechpartner
	 *            Ansprechpartner
	 * @return das gefundene Referat
	 */
	public Referat getReferatByAnsprechpartner(User ansprechpartner) {
		
		return referatRepository.findByAnsprechpartner(ansprechpartner);
	}
	
	/**
	 * Aktuallisiert ein ganzes Referat.
	 * 
	 * @param referat
	 *            Referat
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	public boolean updateReferat(Referat referat) {
		
		try {
			// for (User user : referat.getAnsprechpartner()) {
			// referatRepository.updateAnsprechpartner(referat.getId(), user);
			// }
			// referatRepository.delete(referat);
			
			referatRepository.save(referat);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Löscht ein Referat über das {@link Referat}.
	 * 
	 * @param referat
	 *            Referat
	 * @return true == Falls keine Exception geworfen wurde.
	 */
	public boolean deleteReferat(Referat referat) {
		
		try {
			for (User user : referat.getAnsprechpartner()) {
				deleteAnsprechpartner(user);
			}
			referatRepository.delete(referat);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public void addAnsprechpartner(Ansprechpartner ansprechpartner) {
		
		ansprechpartnerService.addAnsprechpartner(ansprechpartner);
	}
	
	public void deleteAnsprechpartner(User user) {
		
		ansprechpartnerService.deleteAnsprechpartner(user);
	}
}
