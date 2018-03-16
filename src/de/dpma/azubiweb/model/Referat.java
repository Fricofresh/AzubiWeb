package de.dpma.azubiweb.model;

import java.io.Serializable;

import javax.persistence.Column;
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
	
	@Column(nullable = false)
	private String referat;
	
	@Column(nullable = false)
	private String referatsname;
	
	@OneToOne
	private User ansprechpartner;
	
	private static final long serialVersionUID = 1L;
	
	public Referat() {
		
	}
	
	public Referat(String referat, User ansprechpartner, String referatsname) {
		
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
	
	public String getReferatsname() {
		
		return referatsname;
	}
	
	public void setReferatsname(String referatsname) {
		
		this.referatsname = referatsname;
	}
	
	public User getAnsprechpartner() {
		
		return ansprechpartner;
	}
	
	public void setAnsprechpartner(User ansprechpartner) {
		
		this.ansprechpartner = ansprechpartner;
	}
}
