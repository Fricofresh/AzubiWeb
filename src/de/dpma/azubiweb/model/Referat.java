package de.dpma.azubiweb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Referat
 *
 */
@Entity

public class Referat implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique = true, nullable = false)
	private String referat;
	
	@Column(unique = true, nullable = false)
	private String referatsname;
	
	@OneToMany(fetch = FetchType.EAGER)
	// @Column(unique = true)
	@JoinTable(name = "REFERAT_ANSPRECHPARTNER", joinColumns = @JoinColumn(name = "REFERAT_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ANSPRECHPARTNER_ID", referencedColumnName = "id"))
	private List<User> ansprechpartner = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;
	
	public Referat() {
		
	}
	
	public Referat(String referat, List<User> ansprechpartner, String referatsname) {
		
		this.referat = referat;
		this.ansprechpartner = ansprechpartner;
		this.referatsname = referatsname;
	}
	
	public Referat(String referat, User ansprechpartner, String referatsname) {
		
		this.referat = referat;
		this.ansprechpartner.add(ansprechpartner);
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
	
	public List<User> getAnsprechpartner() {
		
		return ansprechpartner;
	}
	
	public void setAnsprechpartner(List<User> ansprechpartner) {
		
		this.ansprechpartner = ansprechpartner;
	}
	
	public void addAnsprechpartner(User ansprechpartner) {
		
		this.ansprechpartner.add(ansprechpartner);
	}
	
	@Override
	public String toString() {
		
		return String.format("id=%d, referat=%s, referatsname=%s, ansprechpartner=%s", id, referat, referatsname,
				ansprechpartner);
	}
}
