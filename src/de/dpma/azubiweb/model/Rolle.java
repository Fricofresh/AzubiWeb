package de.dpma.azubiweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Rechte
 *
 */
@Entity

public class Rolle implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String beschreibung;
	
	private static final long serialVersionUID = 1L;
	
	public Rolle() {
		
		super();
	}
	
	public int getId() {
		
		return this.id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public String getBeschreibung() {
		
		return beschreibung;
	}
	
	public void setBeschreibung(String beschreibung) {
		
		this.beschreibung = beschreibung;
	}
}
