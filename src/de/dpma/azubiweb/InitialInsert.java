package de.dpma.azubiweb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.dpma.azubiweb.model.Ausbildungsart;
import de.dpma.azubiweb.model.Referat;
import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.Rolle.Beschreibung;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.model.User.Geschlecht;
import de.dpma.azubiweb.service.AusbildungsartService;
import de.dpma.azubiweb.service.ReferatService;
import de.dpma.azubiweb.service.RolleService;
import de.dpma.azubiweb.service.UserService;

/**
 * Speichert ein Grundgerüst an Daten in die Datenbank, um die Anwendung testen
 * zu können.
 * 
 * @author Kenneth Böhmer
 */
public class InitialInsert {
	
	@SpringBean
	private UserService userService;
	
	@SpringBean
	private RolleService rolleService;
	
	@SpringBean
	private AusbildungsartService ausbildungsartService;
	
	@SpringBean
	private ReferatService referatService;
	
	/**
	 * @throws ParseException
	 * @see {@link #insertAllRolle()}<br>
	 *      {@link #insertAllAusbildungsart()}<br>
	 *      {@link #insertAllUser()}<br>
	 *      {@link #insertAllReferat()}
	 */
	public InitialInsert() throws ParseException {
		
		Injector.get().inject(this);
		// INFO die beiden Methoden müssen zuerst ausgeführt werden, bevor die
		// Benutzer angelegt werden.
		insertAllRolle();
		insertAllAusbildungsart();
		// INFO Benutzer müssen zuerst angelegt werden, bevor die
		// Ausbildungsreferate angelegt werden.
		insertAllUser();
		insertAllReferat();
	}
	
	/**
	 * Fügt alle Rollen in der Datenbank hinzu.
	 */
	private void insertAllRolle() {
		
		rolleService.saveRolle(new Rolle(Beschreibung.AZUBI.getRolleId(), "Auszubildende"));
		rolleService.saveRolle(new Rolle(Beschreibung.AL.getRolleId(), "Ausbildungsleiter"));
		rolleService.saveRolle(new Rolle(Beschreibung.A.getRolleId(), "Ausbilder"));
	}
	
	/**
	 * Fügt ale Ausbildungsarten in der Datenbank hinzu.
	 */
	private void insertAllAusbildungsart() {
		
		ausbildungsartService.saveAusbildungsart(new Ausbildungsart("Fachinformatiker Fachrichtung Systemintegration",
				"Fachinformatikerin Fachrichtung Systemintegration", "FISI", false));
		ausbildungsartService
				.saveAusbildungsart(new Ausbildungsart("Fachinformatiker Fachrichtung Anwendungsentwicklung",
						"Fachinformatikerin Fachrichtung Anwendungsentwicklung", "FIAE", false));
		ausbildungsartService.saveAusbildungsart(
				new Ausbildungsart("Kaufmann für Büromanagement", "Kauffrau für Büromanagement", "KFB", false));
		ausbildungsartService
				.saveAusbildungsart(new Ausbildungsart("Fachangestellter für Medien- und Informationsdienste",
						"Fachangestellte für Medien- und Informationsdienste", "FAMI", true));
		ausbildungsartService.saveAusbildungsart(new Ausbildungsart("Tischler", "Tischlerin", "Tischler/in", false));
		ausbildungsartService.saveAusbildungsart(new Ausbildungsart("Elektroniker für Energie- und Gebäudetechnik",
				"Elektronikerin für Energie- und Gebäudetechnik", "Elektroniker/in", false));
		ausbildungsartService.saveAusbildungsart(
				new Ausbildungsart("Verwaltungsfachangestellter", "Verwaltungsfachangestellte", "VFA", true));
	}
	
	/**
	 * Fügt alle Benutzer in der Datenbank hinzu.
	 * 
	 * @throws ParseException
	 */
	private void insertAllUser() throws ParseException {
		
		String password = "Anfang12";
		SimpleDateFormat format = new SimpleDateFormat("DD.MM.YYYY");
		
		// Alle Azubis
		
		// Fachinformatiker
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Daniel", "Ramsey",
				ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2016, Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Walt", "Eveli",
				ausbildungsartService.getAusbildungsartByAbkürzung("FAMI"), 2016, Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Felix", "Dach",
				ausbildungsartService.getAusbildungsartByAbkürzung("VFA"), 2016, Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Annika", "Doris",
				ausbildungsartService.getAusbildungsartByAbkürzung("FIAE"), 2016, Geschlecht.weiblich));
		userService.saveUser(new User("KeBoehme", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Kenneth",
				"Böhmer", ausbildungsartService.getAusbildungsartByAbkürzung("FIAE"), 2016, Geschlecht.männlich));
		
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Ulrich", "Hubertus",
				ausbildungsartService.getAusbildungsartByAbkürzung("FIAE"), 2017, Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Fabian", "Eduard",
				ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2017, Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Fritz", "Maier",
				ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2017, Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Stefan", "Bauer",
				ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2017, Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Susanne", "Ansgar",
				ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2017, Geschlecht.weiblich));
		// TODO Add all Azubis
		// userService.saveUser(new User("", password,
		// rolleService.getRolle(Rolle.Beschreibung.AZUBI), "", "",
		// ausbildungsartService.getAusbildungsartByAbkürzung(""), 2017,
		// Geschlecht.männlich));
		// End Fachinformatiker
		
		// TODO Add all Azubis
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Heino", "Kress",
				ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2016, Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Herbert", "Leiter",
				ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2016, Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Theodor", "Raptus",
				ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2017, Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Oliver", "Eckehard",
				ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2017, Geschlecht.männlich));
		userService
				.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Nina", "Reinhardstätter",
						ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2017, Geschlecht.weiblich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Markus", "Beier",
				ausbildungsartService.getAusbildungsartByAbkürzung("Elektroniker/in"), 2016, Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Martin", "Kolumnus",
				ausbildungsartService.getAusbildungsartByAbkürzung("Tischler/in"), 2016, Geschlecht.männlich));
		// End Alle Azubis
		
		// Alle AusbildungsLeiter
		userService.saveUser(new User("MiBaumga", password, rolleService.getRolle(Rolle.Beschreibung.AL), "Michael",
				"Richter", Arrays.asList(ausbildungsartService.getAusbildungsartByAbkürzung("FISI"),
						ausbildungsartService.getAusbildungsartByAbkürzung("FIAE")),
				Geschlecht.männlich));
		userService.saveUser(new User("MaRupert", password, rolleService.getRolle(Rolle.Beschreibung.AL), "Max",
				"Rupert", Arrays.asList(ausbildungsartService.getAusbildungsartByAbkürzung("FAMI")),
				Geschlecht.männlich));
		userService.saveUser(new User("PaDieter", password, rolleService.getRolle(Rolle.Beschreibung.AL), "Paul",
				"Dieter",
				Arrays.asList(ausbildungsartService.getAusbildungsartByAbkürzung("KFB"),
						ausbildungsartService.getAusbildungsartByBerufsbildM("Elektroniker/in"),
						ausbildungsartService.getAusbildungsartByBerufsbildM("Tischler/in")),
				Geschlecht.männlich));
		// End Alle AusbildungsLeiter
		
		// Alle Ausbilder
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.A), "Patrick", "PatPat",
				Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.A), "Kristiane", "Christina",
				Geschlecht.weiblich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.A), "Agust", "Mensch",
				Geschlecht.männlich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.A), "Matina", "Keller",
				Geschlecht.weiblich));
		userService.saveUser(
				new User(password, rolleService.getRolle(Rolle.Beschreibung.A), "Olga", "Geber", Geschlecht.weiblich));
		userService.saveUser(
				new User(password, rolleService.getRolle(Rolle.Beschreibung.A), "Bianca", "Bad", Geschlecht.weiblich));
		userService.saveUser(new User(password, rolleService.getRolle(Rolle.Beschreibung.A), "Nicole", "Fröhlich",
				Geschlecht.weiblich));
		// End Alle Ausbilder
	}
	
	/**
	 * Fügt alle Referate in der Datenbank hinzu.
	 */
	private void insertAllReferat() {
		
		referatService
				.saveReferat(new Referat("2.4.3", userService.getUserByName("KrChrist"), "Strategische IT-Aufgaben"));
		referatService.saveReferat(
				new Referat("2.3.3", userService.getUserByName("AgMensch"), "Management für Prozesse und SOA"));
		referatService.saveReferat(
				new Referat("2.4.1", userService.getUserByName("PaPatPat"), "DPMApatente/-gebrauchsmuster und SAP"));
		referatService
				.saveReferat(new Referat("2.4.2", userService.getUserByName("MaKeller"), "DEPATIS und DPMAmarken"));
		referatService.saveReferat(new Referat("3", userService.getUserByName("OlGeber"), "Marke und Design"));
		referatService.saveReferat(new Referat("4.2.4",
				Arrays.asList(userService.getUserByName("BiBad"), userService.getUserByName("NiFroehl")),
				"Gebäude- und Raummanagement, Innerer Dienst"));
		// referatService.saveReferat(new Referat("",
		// userService.getUserByName(""), ""));
		// referatService.saveReferat(new Referat("",
		// userService.getUserByName(""), ""));
		// referatService.saveReferat(new Referat("",
		// userService.getUserByName(""), ""));
	}
	
}
