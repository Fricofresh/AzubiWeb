package de.dpma.azubiweb.view.berichtsheft;

import java.util.ArrayList;

import de.dpma.azubiweb.model.User;

public class AzubiReports {
	private User uAzubi;
	private ArrayList<de.dpma.azubiweb.model.Berichtsheft> listReports;

	public AzubiReports(User uAzubi) {
		this.uAzubi = uAzubi;
		listReports = new ArrayList<>();
	}

	public AzubiReports(User uAzubi, de.dpma.azubiweb.model.Berichtsheft firstReport) {
		this.uAzubi = uAzubi;
		listReports = new ArrayList<>();
		listReports.add(firstReport);
	}

	public void addReport(de.dpma.azubiweb.model.Berichtsheft report) {
		this.listReports.add(report);
		// this.listReports.sort(c);
	}

	public int getUserId() {
		return uAzubi.getId();
	}

	public User getuAzubi() {
		return uAzubi;
	}

	public ArrayList<de.dpma.azubiweb.model.Berichtsheft> getListReports() {
		return listReports;
	}

}
