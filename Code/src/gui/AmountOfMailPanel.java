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
		
				JLabel titleLabel = new JLabel("Mail Statistics");
				titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
				titleLabel.setBounds(100, 11, 209, 31);
				add(titleLabel);

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

		JLabel noItemsTitleLabel = new JLabel("Number of Items");
		noItemsTitleLabel.setForeground(Color.DARK_GRAY);
		noItemsTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		noItemsTitleLabel.setBounds(30, 164, 121, 26);
		add(noItemsTitleLabel);
		
		JLabel noItemsLabel = new JLabel("0");
		noItemsLabel.setForeground(Color.DARK_GRAY);
		noItemsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		noItemsLabel.setBounds(163, 164, 82, 26);
		add(noItemsLabel);

		JLabel volumeTitleLabel = new JLabel("Total Volume");
		volumeTitleLabel.setForeground(Color.DARK_GRAY);
		volumeTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeTitleLabel.setBounds(30, 211, 93, 26);
		add(volumeTitleLabel);
		
		JLabel volumeLabel = new JLabel("0.00");
		volumeLabel.setForeground(Color.DARK_GRAY);
		volumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeLabel.setBounds(163, 211, 82, 26);
		add(volumeLabel);

		JLabel weightTitleLabel = new JLabel("Total Weight");
		weightTitleLabel.setForeground(Color.DARK_GRAY);
		weightTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weightTitleLabel.setBounds(30, 255, 121, 26);
		add(weightTitleLabel);
		
		JLabel weightLabel = new JLabel("0.00");
		weightLabel.setForeground(Color.DARK_GRAY);
		weightLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weightLabel.setBounds(163, 255, 82, 26);
		add(weightLabel);
	}
}
