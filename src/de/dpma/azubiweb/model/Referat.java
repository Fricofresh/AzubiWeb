package de.dpma.azubiweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Referat
 *
 */
@Entity

public class Referat implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String referat;
	
	private String referatsbezeichnung;
	
	@OneToOne
	private User verantwortlich;
	
	private static final long serialVersionUID = 1L;
	
	public Referat() {
		
		super();
	}
	
	public int getId() {
		
		return this.id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public String getReferat() {
		
		return this.referat;
	}
	
	public void setReferat(String referat) {
		
		this.referat = referat;
	}
	
	public String getReferatsbezeichnung() {
		
		return this.referatsbezeichnung;
	}
	
	public void setReferatsbezeichnung(String referatsbezeichnung) {
		
		this.referatsbezeichnung = referatsbezeichnung;
	}
	
	public User getVerantwortlich() {
		
		return this.verantwortlich;
	}
	
	public void setVerantwortlich(User verantwortlich) {
		
		this.verantwortlich = verantwortlich;
	}
	
}
