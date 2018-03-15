package de.dpma.azubiweb.model;

import java.io.Serializable;

import javax.persistence.Column;
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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -866415971372872899L;
	
	/**
	 * Azubi = <strong>A</strong>us<strong>zubi</strong>ldender <br>
	 * AL = <strong>A</strong>usbildungs<strong>l</strong>eiter <br>
	 * A = <strong>A</strong>usbilder
	 */
	public enum Beschreibung {
		/**
		 * Azubi = <strong>A</strong>us<strong>zubi</strong>ldender <br>
		 * AL = <strong>A</strong>usbildungs<strong>l</strong>eiter <br>
		 * A = <strong>A</strong>usbilder
		 */
		AZUBI(1), AL(2), A(3);
		
		private int rollen;
		
		private Beschreibung(int rollen) {
			
			this.rollen = rollen;
		}
		
		public int getRolleId() {
			
			return rollen;
		}
		
		public String getRolle(int rolle) {
			
			for (Beschreibung value : Beschreibung.values()) {
				if (value.rollen == rolle) {
					return value.name();
				}
			}
			return null;
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String beschreibung;
	
	public Rolle() {
		
	}
	
	public Rolle(int id, String beschreibung) {
		
		this.id = id;
		this.beschreibung = beschreibung;
	}
	
	public Rolle(String beschreibung) {
		
		this.beschreibung = beschreibung;
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
	
	@Override
	public String toString() {
		
		return this.beschreibung;
	}
}
