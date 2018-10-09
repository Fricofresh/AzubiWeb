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
	
	public AlertUtil() {
		
	}
	
	public static String getCss(AlertType alertType) {
		
		if (AlertType.SUCCESS.equals(alertType)) {
			return "";
		}
		else if (AlertType.ERROR.equals(alertType)) {
			return "";
		}
		else if (AlertType.WARNING.equals(alertType)) {
			return "";
		}
		else if (AlertType.INFORMATION.equals(alertType)) {
			return "";
		}
		
		return null;
	}
}
