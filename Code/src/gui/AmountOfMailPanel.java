package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.UIManager;
import serverclient.ClientController;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class AmountOfMailPanel extends JPanel {
	// Sub-sections of MailCreationPanel
	private JComboBox<String> originComboBox;
	private JComboBox<String> destComboBox;

	public AmountOfMailPanel() {
		initPanel();
	}

	private void initPanel() {
		setBackground(UIManager.getColor("Panel.background"));
		setLayout(null);

		JLabel originLabel = new JLabel("Origin");
		originLabel.setForeground(Color.DARK_GRAY);
		originLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		originLabel.setBounds(30, 69, 82, 26);
		add(originLabel);

		originComboBox = new JComboBox<String>();
		originComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "All", "Wellington", "Auckland", "Christchurch", "Dunedin", "Palmerston North", "Hamilton" }));
		originComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		originComboBox.setBounds(150, 69, 170, 26);
		add(originComboBox);

		JLabel destLabel = new JLabel("Destination");
		destLabel.setForeground(Color.DARK_GRAY);
		destLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destLabel.setBounds(30, 116, 82, 26);
		add(destLabel);

		destComboBox = new JComboBox<String>();
		destComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "All", "Wellington", "Auckland", "Christchurch", "Dunedin", "Palmerston North", "Hamilton" }));
		destComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destComboBox.setBounds(150, 116, 170, 26);
		add(destComboBox);

		JLabel noItemsLabel = new JLabel("Number of Items");
		noItemsLabel.setForeground(Color.DARK_GRAY);
		noItemsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		noItemsLabel.setBounds(30, 164, 121, 26);
		add(noItemsLabel);

		JLabel volumeLabel = new JLabel("Total Volume");
		volumeLabel.setForeground(Color.DARK_GRAY);
		volumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeLabel.setBounds(30, 211, 93, 26);
		add(volumeLabel);

		JLabel weightLabel = new JLabel("Total Weight");
		weightLabel.setForeground(Color.DARK_GRAY);
		weightLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weightLabel.setBounds(30, 255, 121, 26);
		add(weightLabel);

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		submitButton.setBounds(150, 300, 108, 31);
		add(submitButton);

		JLabel titleLabel = new JLabel("Mail Statistics");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		titleLabel.setBounds(100, 11, 209, 31);
		add(titleLabel);
		
		JLabel label = new JLabel("0");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label.setBounds(163, 164, 82, 26);
		add(label);
		
		JLabel label_1 = new JLabel("0.00");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_1.setBounds(163, 211, 82, 26);
		add(label_1);
		
		JLabel label_2 = new JLabel("0.00");
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_2.setBounds(163, 255, 82, 26);
		add(label_2);
	}
}
