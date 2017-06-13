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
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DeliveryTimesPanel extends JPanel {
	// Sub-sections of MailCreationPanel
	private JComboBox<String> originComboBox;
	private JComboBox<String> destComboBox;

	public DeliveryTimesPanel() {
		initPanel();
	}

	private void initPanel() {
		setBackground(UIManager.getColor("Panel.background"));
		setLayout(null);
		
				JLabel titleLabel = new JLabel("Average Delivery Times");
				titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
				titleLabel.setVerticalAlignment(SwingConstants.TOP);
				titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
				titleLabel.setBounds(10, 11, 430, 47);
				add(titleLabel);

		JLabel originTitleLabel = new JLabel("Origin");
		originTitleLabel.setForeground(Color.DARK_GRAY);
		originTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		originTitleLabel.setBounds(80, 69, 82, 26);
		add(originTitleLabel);

		originComboBox = new JComboBox<String>();
		originComboBox.setModel(new DefaultComboBoxModel(new String[] {"Wellington", "Auckland", "Christchurch", "Dunedin", "Palmerston North", "Hamilton"}));
		originComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		originComboBox.setBounds(200, 69, 170, 26);
		add(originComboBox);

		JLabel destTitleLabel = new JLabel("Destination");
		destTitleLabel.setForeground(Color.DARK_GRAY);
		destTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destTitleLabel.setBounds(80, 116, 82, 26);
		add(destTitleLabel);

		destComboBox = new JComboBox<String>();
		destComboBox.setModel(new DefaultComboBoxModel(new String[] {"Wellington", "Auckland", "Christchurch", "Dunedin", "Palmerston North", "Hamilton"}));
		destComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destComboBox.setBounds(200, 116, 170, 26);
		add(destComboBox);
		
		JLabel priorityTitleLabel = new JLabel("Priority");
		priorityTitleLabel.setForeground(Color.DARK_GRAY);
		priorityTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		priorityTitleLabel.setBounds(80, 163, 82, 26);
		add(priorityTitleLabel);
		
		JComboBox<String> priorityComboBox = new JComboBox<String>();
		priorityComboBox.setModel(new DefaultComboBoxModel(new String[] {"Earth", "Water", "Air"}));
		priorityComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		priorityComboBox.setBounds(200, 163, 170, 26);
		add(priorityComboBox);

		JLabel avgDeliveryTitleLabel = new JLabel("Average Delivery Time");
		avgDeliveryTitleLabel.setForeground(Color.DARK_GRAY);
		avgDeliveryTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		avgDeliveryTitleLabel.setBounds(80, 211, 164, 26);
		add(avgDeliveryTitleLabel);
		
		JLabel avgDeliveryLabel = new JLabel("0 hours");
		avgDeliveryLabel.setForeground(Color.DARK_GRAY);
		avgDeliveryLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		avgDeliveryLabel.setBounds(257, 211, 82, 26);
		add(avgDeliveryLabel);
	}
}
