package de.dpma.azubiweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Ansprechpartner implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(optional = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Referat referat;
	
	public Ansprechpartner() {
		
	}
	
	public Ansprechpartner(Referat referat, User user) {
		
		this.user = user;
		this.referat = referat;
	}
	
	public User getUser() {
		
		return user;
	}
	
	public Referat getReferat() {
		
		return referat;
	}
}
