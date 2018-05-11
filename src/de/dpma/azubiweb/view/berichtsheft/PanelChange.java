package de.dpma.azubiweb.view.berichtsheft;

import de.dpma.azubiweb.model.Berichtsheft;
import de.dpma.azubiweb.view.berichtsheft.panel.OverviewAzubiListPanel;

public interface PanelChange {	
	public void changeToOverviewAzubiList();
	public void changeToOverviewReportsList(AzubiReports aReports);
	public void changeToAzubiWeekDayPanel(Berichtsheft reportToView);
	public void changeToSign(Berichtsheft reportToView);
}
