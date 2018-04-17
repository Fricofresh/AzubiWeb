package de.dpma.azubiweb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: User
 *
 */
// TODO rekusives löschen
@Entity
public class User implements Serializable {

	public enum Geschlecht {
		männlich, weiblich;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(unique = true, nullable = false)
	private String password;

	private String vorname;

	private String nachname;

	@Column(unique = true, nullable = false)
	private String email;

	private Date geburtsDatum;

	private Integer einstiegsjahr;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_AUSBILDUNGSART", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "AUSBILDUNGSART_ID", referencedColumnName = "id"))
	private List<Ausbildungsart> ausbildungsart = new ArrayList<>();

	@ManyToOne
	private Rolle rolle;

	private Geschlecht geschlecht;

	private static final long serialVersionUID = 1L;

	public User() {

		super();
	}

	public User(int id, String username, String password, Rolle rolle, String vorname, String nachname, String email,
			Ausbildungsart ausbildung, int einstiegsjahr, Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.ausbildungsart = Arrays.asList(ausbildung);
		this.einstiegsjahr = einstiegsjahr;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname, String email,
			Ausbildungsart ausbildung, int einstiegsjahr, Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.ausbildungsart = Arrays.asList(ausbildung);
		this.einstiegsjahr = einstiegsjahr;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname, String email,
			Ausbildungsart ausbildung, Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.ausbildungsart = Arrays.asList(ausbildung);
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname,
			Ausbildungsart ausbildung, Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = vorname + "." + nachname + "@dpma.de";
		this.ausbildungsart = Arrays.asList(ausbildung);
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname,
			Ausbildungsart ausbildung, int einstiegsjahr, Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = vorname + "." + nachname + "@dpma.de";
		this.ausbildungsart = Arrays.asList(ausbildung);
		this.einstiegsjahr = einstiegsjahr;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname, String email,
			List<Ausbildungsart> ausbildung, int einstiegsjahr, Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.ausbildungsart = ausbildung;
		this.einstiegsjahr = einstiegsjahr;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname,
			List<Ausbildungsart> ausbildung, int einstiegsjahr, Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = vorname + "." + nachname + "@dpma.de";
		this.ausbildungsart = ausbildung;
		this.einstiegsjahr = einstiegsjahr;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname, String email,
			List<Ausbildungsart> ausbildung, Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.ausbildungsart = ausbildung;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname,
			List<Ausbildungsart> ausbildung, Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = vorname + "." + nachname + "@dpma.de";
		this.ausbildungsart = ausbildung;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname, String email,
			Ausbildungsart[] ausbildung, int einstiegsjahr, Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.ausbildungsart = Arrays.asList(ausbildung);
		this.einstiegsjahr = einstiegsjahr;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname,
			Ausbildungsart[] ausbildung, int einstiegsjahr, Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.ausbildungsart = Arrays.asList(ausbildung);
		this.einstiegsjahr = einstiegsjahr;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname, String email,
			Geschlecht geschlecht, Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String username, String password, Rolle rolle, String vorname, String nachname, Geschlecht geschlecht,
			Date geburtsDatum) {

		super();
		this.username = username;
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public User(String password, Rolle rolle, String vorname, String nachname, Geschlecht geschlecht,
			Date geburtsDatum) {

		super();
		this.password = password;
		this.rolle = rolle;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geschlecht = geschlecht;
		this.geburtsDatum = geburtsDatum;
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getUsername() {

		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public String getVorname() {

		return vorname;
	}

	public void setVorname(String vorname) {

		this.vorname = vorname;
	}

	public String getNachname() {

		return nachname;
	}

	public void setNachname(String nachname) {

		this.nachname = nachname;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public Integer getEinstiegsjahr() {

		return einstiegsjahr;
	}

	public void setEinstiegsjahr(Integer einstiegsjahr) {

		this.einstiegsjahr = einstiegsjahr;
	}

	public Date getGeburtsDatum() {
		return geburtsDatum;
	}

	public void setGeburtsDatum(Date geburtsDatum) {
		this.geburtsDatum = geburtsDatum;
	}

	public List<Ausbildungsart> getAusbildungsart() {

		return ausbildungsart;
	}

	public void setAusbildungsart(List<Ausbildungsart> ausbildungsart) {

		this.ausbildungsart = ausbildungsart;
	}

	public Rolle getRolle() {

		return rolle;
	}

	public void setRolle(Rolle rolle) {

		this.rolle = rolle;
	}

	public Geschlecht getGeschlecht() {

		return geschlecht;
	}

	public void setGeschlecht(Geschlecht geschlecht) {

		this.geschlecht = geschlecht;
	}

	@Override
	public String toString() {

		return String.format(
				"id=%d, Username=%s, Password=%s, Rolle=%s, Vorname=%s, Nachname=%s, E-Mail=%s, Ausbildungsart=%s, Einstiegsjahr=%d, Geschlecht=%s",
				id, username, password, rolle, vorname, nachname, email,
				ausbildungsart.size() == 1 ? ausbildungsart.get(0).toString() : ausbildungsart.toString(),
				einstiegsjahr, geschlecht);
	}

	public boolean isEmpty() {

		if (id == 0 && username == null || username.isEmpty() && password == null || password.isEmpty() && rolle == null
				|| rolle.toString().isEmpty() && vorname == null || vorname.isEmpty() && nachname == null
				|| nachname.isEmpty() && email == null || email.isEmpty() && ausbildungsart == null
				|| ausbildungsart.isEmpty() && einstiegsjahr == null && geschlecht == null
				|| geschlecht.toString().isEmpty())
			return true;
		return false;
	}

}
