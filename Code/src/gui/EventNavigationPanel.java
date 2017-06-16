package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.UIManager;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class EventNavigationPanel extends JPanel {

	public EventNavigationPanel() {
		initPanel();
	}

	private void initPanel() {
		setBackground(UIManager.getColor("Panel.background"));
		setLayout(null);

		JLabel titleLabel = new JLabel("Event Navigation");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		titleLabel.setBounds(10, 11, 430, 40);
		add(titleLabel);
	}
}
