package de.dpma.azubiweb.view.berichtsheft;

import de.dpma.azubiweb.model.Berichtsheft;
import de.dpma.azubiweb.view.berichtsheft.panel.AzubiDayPanel;
import de.dpma.azubiweb.view.berichtsheft.panel.AzubiWeekPanel;
import de.dpma.azubiweb.view.berichtsheft.panel.OverviewAzubiListPanel;
import de.dpma.azubiweb.view.berichtsheft.panel.OverviewReportsListPanel;
import de.dpma.azubiweb.view.berichtsheft.panel.SignPanel;

/**
 * Interface zum Ändern der Berichtsheftseite auf ein anderes Panel
 * 
 * @author Benedikt Maier
 *
 */
public interface PanelChange {
	/**
	 * Wechselt zum Panel {@link OverviewAzubiListPanel}
	 */
	public void changeToOverviewAzubiList();
	/**
	 * Wechselt zum Panel {@link OverviewReportsListPanel}
	 * @param aReports: Berichtshefte nach Usern zugeordnet - {@link AzubiDayPanel}
	 */
	
	public void changeToOverviewReportsList(AzubiReports aReports);
/**
 * 
 * Wechselt zum Panel {@link AzubiWeekPanel} oder {@link AzubiDayPanel}
 * @param reportToView: Berichtsheft zum Bearbeiten
 */
	public void changeToAzubiWeekDayPanel(Berichtsheft reportToView);
/**
 * Wechselt zum Panel {@link SignPanel}
 * @param reportToView: Berichtsheft zum unterzeichnen
 */
	public void changeToSign(Berichtsheft reportToView);
	
	public void backPressed();
}
