package de.dpma.azubiweb.util;

/**
 * 
 * @author Kenneth Böhmer
 *
 */
public class AlertUtil {
	
	public enum AlertType {
		SUCCESS, WARNING, ERROR, INFORMATION;
	}
	
	private final static String rootClasses = "alert alert-dismissible fade in position-sticky-bottom ";
	
	public AlertUtil() {
		
	}
	
	public static String getCss(AlertType alertType) {
		
		if (AlertType.SUCCESS.equals(alertType)) {
			return rootClasses + "alert-success";
		}
		else if (AlertType.ERROR.equals(alertType)) {
			return rootClasses + "alert-danger";
		}
		else if (AlertType.WARNING.equals(alertType)) {
			return rootClasses + "alert-warning";
		}
		else if (AlertType.INFORMATION.equals(alertType)) {
			return rootClasses + "alert-info";
		}
		
		return null;
	}
}
