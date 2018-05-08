package de.dpma.azubiweb.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
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
	
	@Column(unique = true, nullable = false)
	private String berufsbildM;
	
	@Column(unique = true, nullable = false)
	private String berufsbildW;
	
	@Column(unique = true)
	private String berufsbildAbkürzung;
	
	private boolean täglichesberichtsheft;
	
	private Date gültigAb;
	
	private static final long serialVersionUID = 1L;
	
	public Ausbildungsart() {
		
	}
	
	public Ausbildungsart(int id, String berufsbildM, String berufsbildW, String berufsbildAbkürzung,
			boolean täglichesberichtsheft, Date gültigAb) {
		
		this.id = id;
		this.berufsbildM = berufsbildM;
		this.berufsbildW = berufsbildW;
		this.berufsbildAbkürzung = berufsbildAbkürzung;
		this.täglichesberichtsheft = täglichesberichtsheft;
		this.gültigAb = gültigAb;
	}
	
	public Ausbildungsart(String berufsbildM, String berufsbildW, String berufsbildAbkürzung,
			boolean täglichesberichtsheft, Date gültigAb) {
		
		this.berufsbildM = berufsbildM;
		this.berufsbildW = berufsbildW;
		this.berufsbildAbkürzung = berufsbildAbkürzung;
		this.täglichesberichtsheft = täglichesberichtsheft;
		this.gültigAb = gültigAb;
	}
	
	public Ausbildungsart(String berufsbildM, String berufsbildW, String berufsbildAbkürzung,
			boolean täglichesberichtsheft) {
		
		this.berufsbildM = berufsbildM;
		this.berufsbildW = berufsbildW;
		this.berufsbildAbkürzung = berufsbildAbkürzung;
		this.täglichesberichtsheft = täglichesberichtsheft;
	}
	
	public int getId() {
		
		return id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public String getBerufsbildM() {
		
		return berufsbildM;
	}
	
	public void setBerufsbildM(String berufsbildM) {
		
		this.berufsbildM = berufsbildM;
	}
	
	public String getBerufsbildW() {
		
		return berufsbildW;
	}
	
	public void setBerufsbildW(String berufsbildW) {
		
		this.berufsbildW = berufsbildW;
	}
	
	public String getBerufsbildAbkürzung() {
		
		return berufsbildAbkürzung;
	}
	
	public void setBerufsbildAbkürzung(String berufsbildAbkürzung) {
		
		this.berufsbildAbkürzung = berufsbildAbkürzung;
	}
	
	public boolean isTäglichesberichtsheft() {
		
		return täglichesberichtsheft;
	}
	
	public void setTäglichesberichtsheft(boolean täglichesberichtsheft) {
		
		this.täglichesberichtsheft = täglichesberichtsheft;
	}
	
	public Date getGültigAb() {
		
		return gültigAb;
	}
	
	public void setGültigAb(Date gültigAb) {
		
		this.gültigAb = gültigAb;
	}
	
	@Override
	public String toString() {
		
		return String.format(
				"id=%d, Berufsbildküzel=%s, BerufsbildM=%s, BerufsbildW=%s, GültigAb=%tF, Täglichesberichtsheft=%b", id,
				berufsbildAbkürzung, berufsbildM, berufsbildW, gültigAb, täglichesberichtsheft);
	}
}
