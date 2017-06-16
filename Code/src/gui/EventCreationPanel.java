package gui;

import javax.swing.JPanel;

public abstract class EventCreationPanel extends JPanel {

	// Input validation methods
	/**
	 * Returns true if str is a valid double.
	 * @param str
	 * 			Number in String format
	 * @return
	 * 			True - str is a valid double
	 * 			False - str isn't a valid double
	 */
	protected boolean isValidNumber(String str) {
		try {
			double d = Double.parseDouble(str);
			return d >= 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Returns true if str is a valid string input
	 * @param str
	 * @return
	 */
	protected boolean isValidString(String str) {
		return false;
	}
}
