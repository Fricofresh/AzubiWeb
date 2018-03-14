package de.dpma.azubiweb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Ausbildungsreferate
 *
 */
@Entity

public class Ausbildungsreferat implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String referat;
	
	@OneToOne
	private User ansprechpartner;
	
	@Column(nullable = false)
	private String referatsname;
	
	private static final long serialVersionUID = 1L;
	
	public Ausbildungsreferat() {
		
	}
	
	public Ausbildungsreferat(String referat, User ansprechpartner, String referatsname) {
		
		this.referat = referat;
		this.ansprechpartner = ansprechpartner;
		this.referatsname = referatsname;
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
	
	public User getAnsprechpartner() {
		
		return this.ansprechpartner;
	}
	
	public void setAnsprechpartner(User ansprechpartner) {
		
		this.ansprechpartner = ansprechpartner;
	}
	
	public String getReferatsname() {
		
		return this.referatsname;
	}
	
	public void setReferatsname(String referatsname) {
		
		this.referatsname = referatsname;
	}
	
}
