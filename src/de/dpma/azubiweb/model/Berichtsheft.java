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
	public Berichtsheft() {
		super();
	}

	public Berichtsheft(User user_Azubi, User user_AB, User user_AL, Referat referat, int weekAYear) {
		super();
		this.user_Azubi = user_Azubi;
		this.user_AB = user_AB;
		this.user_AL = user_AL;
		this.referat = referat;
		this.weekAYear = weekAYear;
	}

	public Berichtsheft(User user_Azubi, String kind_BHL, int weekAYear) {
		super();
		this.user_Azubi = user_Azubi;
		this.kind_BHL = kind_BHL;
		this.weekAYear = weekAYear;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "userAZ", nullable = false)
	private User user_Azubi;
	@Column(name = "userAB")
	private User user_AB;
	@Column(name = "userAL", nullable = false)
	private User user_AL;

	@ManyToOne
	private Referat referat;

	private boolean status_submit;

	private boolean st_sign_AB;

	private boolean st_sign_AL;

	public static final String[] kindOfBH = { "Betrieb", "Berufsschule", "Schulungen","Verbund"};

	/**
	 * @see {@link Berichtsheft#kindOfBH}
	 */
	@Column(nullable = false)
	private String kind_BHL;

	/**
	 * Design: [YYYYww]
	 */
	@Column(nullable = false)
	private int weekAYear;

	@Column(nullable = false)
	private String data;
	private String errorMessage;
	private static final long serialVersionUID = 1L;

	// Hier alle Getter und Setter Methoden//
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser_Azubi() {
		return user_Azubi;
	}

	public void setUser_Azubi(User user_Azubi) {
		this.user_Azubi = user_Azubi;
	}

	public User getUser_AB() {
		return user_AB;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setUser_AB(User user_AB) {
		this.user_AB = user_AB;
	}

	public User getUser_AL() {
		return user_AL;
	}

	public void setUser_AL(User user_AL) {
		this.user_AL = user_AL;
	}

	public Referat getReferat() {
		return referat;
	}

	public void setReferat(Referat referat) {
		this.referat = referat;
	}

	public boolean isStatus_submit() {
		return status_submit;
	}

	public void setStatus_submit(boolean status_submit) {
		this.status_submit = status_submit;
	}

	public boolean isSt_sign_AB() {
		return st_sign_AB;
	}

	public void setSt_sign_AB(boolean st_sign_AB) {
		this.st_sign_AB = st_sign_AB;
	}

	public boolean isSt_sign_AL() {
		return st_sign_AL;
	}

	public void setSt_sign_AL(boolean st_sign_AL) {
		this.st_sign_AL = st_sign_AL;
	}

	public String getKind_BHL() {
		return kind_BHL;
	}

	public void setKind_BHL(String kind_BHL) {
		this.kind_BHL = kind_BHL;
	}

	public int getWeekAYear() {
		return weekAYear;
	}

	public void setWeekAYear(int weekAYear) {
		this.weekAYear = weekAYear;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
