package de.dpma.azubiweb.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AusbildungsartRepository extends CrudRepository<Ausbildungsart, Integer> {
	
	@Override
	public List<Ausbildungsart> findAll();
	
	public Ausbildungsart findByBerufsbildAbkürzung(String berufsbildAbkürzung);
	
	public Ausbildungsart findByBerufsbildW(String BerufsbildW);
	
	public Ausbildungsart findByBerufsbildM(String BerufsbildM);
	
	public void deleteAusbildungsartByBerufsbildM(String berufsbildM);
	
	public void deleteAusbildungsartByBerufsbildW(String berufsbildW);
	
	public void deleteAusbildungsartByBerufsbildAbkürzung(String berufsbildAbkürzung);
}
