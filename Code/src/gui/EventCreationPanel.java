package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

public abstract class EventCreationPanel extends JPanel {
	
	protected Font errorFont = new Font("Tahoma", Font.ITALIC, 13);
	protected Color errorColor = Color.RED;

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
		if (str.isEmpty()) {
			return false;
		} else {
			return str.matches("[a-zA-Z0-9 ]+");
		}
	}
}
