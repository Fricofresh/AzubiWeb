package de.dpma.azubiweb;

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

public class InitialInsert {
	
	@SpringBean
	private UserService userService;
	
	@SpringBean
	private RolleService rolleService;
	
	@SpringBean
	private AusbildungsartService ausbildungsartService;
	
	@SpringBean
	private ReferatService referatService;
	
	public InitialInsert() {
		
		Injector.get().inject(this);
		// INFO die beiden Methoden müssen zuerst ausgeführt werden, bevor die
		// Benutzer angelegt werden.
		insertAllRolle();
		insertAllAusbildungsart();
		
		insertAllUser();
		insertAllAusbildungsreferat();
	}
	
	private void insertAllRolle() {
		
		System.out.println(rolleService);
		rolleService.saveRolle(new Rolle(Beschreibung.AZUBI.getRolleId(), "Auszubildende"));
		rolleService.saveRolle(new Rolle(Beschreibung.AL.getRolleId(), "Ausbildungsleiter"));
		rolleService.saveRolle(new Rolle(Beschreibung.A.getRolleId(), "Ausbilder"));
	}
	
	private void insertAllAusbildungsart() {
		
		ausbildungsartService.saveAusbildungsart(new Ausbildungsart("Fachinformatiker Fachrichtung Systemintegration",
				"Fachinformatikerin Fachrichtung Systemintegration", "FISI", false));
		ausbildungsartService
				.saveAusbildungsart(new Ausbildungsart("Fachinformatiker Fachrichtung Anwendungsentwicklung",
						"Fachinformatikerin Fachrichtung Anwendungsentwicklung", "FIAE", false));
		// TODO Informieren, ob die anderen Beruffsbilder Täglich das
		// Berichtsheft schreiben müssen
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
	
	private void insertAllUser() {
		
		String password = "Anfang12";
		
		// Alle Azubis
		
		// Fachinformatiker
		// 2.Lehrjahr
		userService.saveUser(new User("FlSeidl", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Florian",
				"Seidl", ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2016, Geschlecht.männlich));
		userService.saveUser(
				new User("AlBacher", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Alexander", "Bacher",
						ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2016, Geschlecht.männlich));
		userService.saveUser(new User("AbAlMask", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Abdallah",
				"Al-Maskari", ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2016, Geschlecht.männlich));
		userService.saveUser(new User("KiSaeb", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Kinan",
				"Saeb", ausbildungsartService.getAusbildungsartByAbkürzung("FIAE"), 2016, Geschlecht.männlich));
		userService.saveUser(new User("KeBoehme", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Kenneth",
				"Böhmer", ausbildungsartService.getAusbildungsartByAbkürzung("FIAE"), 2016, Geschlecht.männlich));
		
		// 1.Lehrjahr
		userService.saveUser(new User("LuRohde", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Luca",
				"Rohde", ausbildungsartService.getAusbildungsartByAbkürzung("FIAE"), 2017, Geschlecht.männlich));
		userService.saveUser(new User("DaLindeg", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Daniel",
				"Lindeger", ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2017, Geschlecht.männlich));
		userService.saveUser(new User("MiMaier", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Michael",
				"Maier", ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2017, Geschlecht.männlich));
		userService.saveUser(new User("StJaura", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Stefan",
				"Jaura", ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2017, Geschlecht.männlich));
		userService.saveUser(new User("MaOhlrog", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Marcel",
				"Ohlrog", ausbildungsartService.getAusbildungsartByAbkürzung("FISI"), 2017, Geschlecht.männlich));
		// TODO Add all Azubis
		// userService.saveUser(new User("", password,
		// rolleService.getRolle(Rolle.Beschreibung.AZUBI), "", "",
		// ausbildungsartService.getAusbildungsartByAbkürzung(""), 2017,
		// Geschlecht.männlich));
		// End Fachinformatiker
		
		// TODO Add all Azubis
		userService.saveUser(new User("ElDenk", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Elisabeth",
				"Denk", ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2016, Geschlecht.weiblich));
		userService.saveUser(
				new User("JuKunze", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Juliane-Viktoria",
						"Kunze", ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2016, Geschlecht.weiblich));
		userService.saveUser(new User("MiSteinl", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Michaela",
				"Steinleitner", ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2016, Geschlecht.weiblich));
		userService.saveUser(new User("ChBrands", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI),
				"Christina", "Brandsetter", ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2017,
				Geschlecht.weiblich));
		userService.saveUser(new User("ElRedzep", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Elisa",
				"Redzepovic", ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2017, Geschlecht.weiblich));
		userService.saveUser(new User("NiReinha", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Nina",
				"Reinhardstätter", ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2017,
				Geschlecht.weiblich));
		userService.saveUser(new User("EmSmith", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Emily",
				"Smith", ausbildungsartService.getAusbildungsartByAbkürzung("KFB"), 2017, Geschlecht.weiblich));
		userService.saveUser(new User("FlKraft", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Florian",
				"Kraft", ausbildungsartService.getAusbildungsartByBerufsbildM("Tischler"), 2017, Geschlecht.männlich));
		userService.saveUser(new User("BeBurkha", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Benjamin",
				"Burkhardt",
				ausbildungsartService.getAusbildungsartByBerufsbildM("Elektroniker für Energie- und Gebäudetechnik"),
				2017, Geschlecht.männlich));
		userService.saveUser(new User("MaBader", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Manuel",
				"Bader",
				ausbildungsartService.getAusbildungsartByBerufsbildM("Elektroniker für Energie- und Gebäudetechnik"),
				2016, Geschlecht.männlich));
		userService.saveUser(new User("MaBock", password, rolleService.getRolle(Rolle.Beschreibung.AZUBI), "Martin",
				"Bock",
				ausbildungsartService.getAusbildungsartByBerufsbildM("Elektroniker für Energie- und Gebäudetechnik"),
				2016, Geschlecht.männlich));
		// End Alle Azubis
		
		// Alle AusbildungsLeiter
		userService.saveUser(new User("MiBaumga", password, rolleService.getRolle(Rolle.Beschreibung.AL), "Michael",
				"Baumgartner", Arrays.asList(ausbildungsartService.getAusbildungsartByAbkürzung("FISI"),
						ausbildungsartService.getAusbildungsartByAbkürzung("FIAE")),
				Geschlecht.männlich));
		userService.saveUser(new User("KaTitelT", password, rolleService.getRolle(Rolle.Beschreibung.AL), "Katia",
				"Titel-Tier", Arrays.asList(ausbildungsartService.getAusbildungsartByAbkürzung("FAMI")),
				Geschlecht.weiblich));
		userService.saveUser(new User("PeKlemm", password, rolleService.getRolle(Rolle.Beschreibung.AL), "Peter",
				"Klemm",
				Arrays.asList(ausbildungsartService.getAusbildungsartByAbkürzung("KFB"),
						ausbildungsartService.getAusbildungsartByBerufsbildM("Elektriker"),
						ausbildungsartService.getAusbildungsartByBerufsbildM("Tischler")),
				Geschlecht.männlich));
		userService.saveUser(
				new User("IlStremm", password, rolleService.getRolle(Rolle.Beschreibung.AL), "Ilona", "Stremme",
						Arrays.asList(ausbildungsartService.getAusbildungsartByAbkürzung("VFA")), Geschlecht.weiblich));
		// End Alle AusbildungsLeiter
		
		// Alle Ausbilder
		userService.saveUser(new User("PaStangl", password, rolleService.getRolle(Rolle.Beschreibung.A), "Patrik",
				"Stangl", Geschlecht.männlich));
		// userService.saveUser(
		// new User("", password, rolleService.getRolle(Rolle.Beschreibung.A),
		// "", "", Geschlecht.männlich));
		// userService.saveUser(
		// new User("", password, rolleService.getRolle(Rolle.Beschreibung.A),
		// "", "", Geschlecht.männlich));
		// userService.saveUser(
		// new User("", password, rolleService.getRolle(Rolle.Beschreibung.A),
		// "", "", Geschlecht.weiblich));
		// userService.saveUser(
		// new User("", password, rolleService.getRolle(Rolle.Beschreibung.A),
		// "", "", Geschlecht.weiblich));
		// userService.saveUser(
		// new User("", password, rolleService.getRolle(Rolle.Beschreibung.A),
		// "", "", Geschlecht.weiblich));
		// End Alle Ausbilder
	}
	
	private void insertAllAusbildungsreferat() {
		
		referatService
				.saveReferat(new Referat("2.3.3", userService.getUserByName("PaStangl"), "Strategische IT-Aufgaben"));
		referatService.saveReferat(new Referat("", userService.getUserByName(""), ""));
	}
	
}
