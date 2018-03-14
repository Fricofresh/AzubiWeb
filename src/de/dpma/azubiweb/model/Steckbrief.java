package de.dpma.azubiweb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Steckbrief
 *
 */
@Entity

public class Steckbrief implements Serializable {
	
	@Id
	private int id;
	
	// TODO In Service überprüfen ob es das Referat auch gibt.
	@ManyToMany
	private List<Referat> besuchteReferate;
	
	private String erwartungen;
	
	@OneToOne
	private User user;
	
	private static final long serialVersionUID = 1L;
	
	public Steckbrief() {
		
		super();
	}
	
	public int getId() {
		
		return this.id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public List<Referat> getBesuchteReferate() {
		
		return this.besuchteReferate;
	}
	
	public void setBesuchteReferate(List<Referat> besuchteReferate) {
		
		this.besuchteReferate = besuchteReferate;
	}
	
	public String getErwartungen() {
		
		return this.erwartungen;
	}
	
	public void setErwartungen(String erwartungen) {
		
		this.erwartungen = erwartungen;
	}
	
	public User getUser() {
		
		return this.user;
	}
	
	public void setUser(User user) {
		
		this.user = user;
	}
	
}
