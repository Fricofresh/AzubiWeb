package de.dpma.azubiweb.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Ausbildungsart
 *
 */
@Entity

public class Ausbildungsart implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String berufsbild;
	
	private boolean täglichesberichtsheft;
	
	private Date gültigAb;
	
	private static final long serialVersionUID = 1L;
	
	public Ausbildungsart() {
		
		super();
	}
	
	public int getId() {
		
		return this.id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public String getBerufsbild() {
		
		return this.berufsbild;
	}
	
	public void setBerufsbild(String berufsbild) {
		
		this.berufsbild = berufsbild;
	}
	
	public boolean getTäglichesberichtsheft() {
		
		return this.täglichesberichtsheft;
	}
	
	public void setTäglichesberichtsheft(boolean täglichesberichtsheft) {
		
		this.täglichesberichtsheft = täglichesberichtsheft;
	}
	
	public Date getGültigAb() {
		
		return this.gültigAb;
	}
	
	public void setGültigAb(Date gültigAb) {
		
		this.gültigAb = gültigAb;
	}
	
}
