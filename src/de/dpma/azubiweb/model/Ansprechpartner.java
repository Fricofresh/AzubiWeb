package de.dpma.azubiweb.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Ansprechpartner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "referat_id")
	private Referat referat;
	
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
