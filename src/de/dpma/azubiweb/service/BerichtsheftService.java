package de.dpma.azubiweb.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dpma.azubiweb.model.Berichtsheft;
import de.dpma.azubiweb.model.BerichtsheftRepository;
import de.dpma.azubiweb.model.User;

@Service
public class BerichtsheftService {
	@Autowired
	private BerichtsheftRepository berichtsheftRepository;

	public List<Berichtsheft> getAllBerichtsheft() {

		return berichtsheftRepository.findAll();
	}

	public Berichtsheft getCurrentBerichtsheftByUserAzubi(User u) {
		List<Berichtsheft> listBH = getAllBerichtsheft();
		int curWY=getCurrentWeekAndYear();
		for (int i = 0; i < listBH.size(); i++) {
			Berichtsheft temp = listBH.get(i);
			if (u.getId() == temp.getUser_Azubi().getId() && curWY == temp.getWeekAYear() ) {
				return temp;
			}
		}
		//---> Noch kein aktuelles BH angelegegt
		// Neues wird erstellt und zurückgegeben
		return new Berichtsheft(u, Berichtsheft.kindOfBH[0], getCurrentWeekAndYear());
		
		
	}

	public void saveBerichtsheft(Berichtsheft berichtsheft) {
		berichtsheftRepository.save(berichtsheft);
	}

	public static int getCurrentWeekAndYear() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR)*100+calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	public static boolean isReportInFuture(int value) {
		int curWeek = BerichtsheftService.getCurrentWeekAndYear();
		if (value <= curWeek) {
			return false;
		}
		return true;
	}
	

}
