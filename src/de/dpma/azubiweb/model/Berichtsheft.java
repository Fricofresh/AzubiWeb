package de.dpma.azubiweb.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Berichtsheft
 *
 */
@Entity

public class Berichtsheft implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private User user;
	
	private String betrieblicheTätigkeiten;
	
	private String schulungen;
	
	private String berufsschule;
	
	@Column(nullable = false)
	private Date woche;
	
	// TODO defauled 4.1.3 und getter/setter
	@ManyToOne
	private Ausbildungsreferat referat;
	
	private boolean abgegeben;
	
	private boolean abgezeichnetAusbilder;
	
	private boolean abgezeichnetAusbildungsleiter;
	
	private static final long serialVersionUID = 1L;
	
	public Berichtsheft() {
		
		super();
	}
	
	public int getId() {
		
		return this.id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public String getBetrieblicheTätigkeiten() {
		
		return this.betrieblicheTätigkeiten;
	}
	
	public void setBetrieblicheTätigkeiten(String betrieblicheTätigkeiten) {
		
		this.betrieblicheTätigkeiten = betrieblicheTätigkeiten;
	}
	
	public String getSchulungen() {
		
		return this.schulungen;
	}
	
	public void setSchulungen(String schulungen) {
		
		this.schulungen = schulungen;
	}
	
	public String getBerufsschule() {
		
		return this.berufsschule;
	}
	
	public void setBerufsschule(String berufsschule) {
		
		this.berufsschule = berufsschule;
	}
	
	public boolean getAbgegeben() {
		
		return this.abgegeben;
	}
	
	public void setAbgegeben(boolean abgegeben) {
		
		this.abgegeben = abgegeben;
	}
	
	public boolean getAbgezeichnetAusbilder() {
		
		return this.abgezeichnetAusbilder;
	}
	
	public void setAbgezeichnetAusbilder(boolean abgezeichnetAusbilder) {
		
		this.abgezeichnetAusbilder = abgezeichnetAusbilder;
	}
	
	public boolean getAbgezeichnetAusbildungsleiter() {
		
		return this.abgezeichnetAusbildungsleiter;
	}
	
	public void setAbgezeichnetAusbildungsleiter(boolean abgezeichnetAusbildungsleiter) {
		
		this.abgezeichnetAusbildungsleiter = abgezeichnetAusbildungsleiter;
	}
	
	public Date getWoche() {
		
		return woche;
	}
	
	public void setWoche(Date woche) {
		
		this.woche = woche;
	}
	
	public User getUser() {
		
		return user;
	}
	
	public void setUser(User user) {
		
		this.user = user;
	}
	
}
