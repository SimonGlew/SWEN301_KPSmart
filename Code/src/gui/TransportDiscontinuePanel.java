package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import serverclient.ClientController;

public class TransportDiscontinuePanel extends EventCreationPanel{
	private ClientController controller;

	// Sub-sections of MailCreationPanel
	private JComboBox<String> originComboBox;
	private JComboBox<String> destComboBox;
	private JComboBox<String> compComboBox;
	private JComboBox<String> prioComboBox;
	private JTextField gramTextField;
	private JLabel gramErrorLabel;
	private JTextField volumeTextField;
	private JLabel volumeErrorLabel;
	private JTextField periodTextField;
	private JTextField durationTextField;
	
	public TransportDiscontinuePanel(ClientController controller) {
		this.controller = controller;
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
				new String[] { "Wellington", "Auckland", "Christchurch", "Dunedin", "Palmerston North", "Hamilton" }));
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
				new String[] { "Wellington", "Auckland", "Christchurch", "Dunedin", "Palmerston North", "Hamilton" }));
		destComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destComboBox.setBounds(150, 116, 170, 26);
		add(destComboBox);
		
		JLabel companyLabel = new JLabel("Company");
		companyLabel.setForeground(Color.DARK_GRAY);
		companyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		companyLabel.setBounds(30, 164, 82, 26);
		add(companyLabel);

		compComboBox = new JComboBox<String>();
		compComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Company quan", "Company tu", "Company shree" }));
		compComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		compComboBox.setBounds(150, 164, 170, 26);
		add(compComboBox);
		
		JLabel priorityLabel = new JLabel("Priority");
		priorityLabel.setForeground(Color.DARK_GRAY);
		priorityLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		priorityLabel.setBounds(30, 211, 130, 26);
		add(priorityLabel);

		prioComboBox = new JComboBox<String>();
		prioComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Earth", "Water", "Air"}));
		prioComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		prioComboBox.setBounds(150, 211, 170, 26);
		add(prioComboBox);

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		submitButton.setBounds(150, 260, 108, 31);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isValidNumber(gramErrorLabel.getText()) && isValidNumber(volumeTextField.getText())) {
							controller.requestTransportDiscontinued(originComboBox.getItemAt(originComboBox.getSelectedIndex()),
							destComboBox.getItemAt(destComboBox.getSelectedIndex()),
							compComboBox.getItemAt(compComboBox.getSelectedIndex()),
							prioComboBox.getItemAt(prioComboBox.getSelectedIndex()));
				}
			}
		});
		add(submitButton);

		JLabel titleLabel = new JLabel("Transport Discontinue");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		titleLabel.setBounds(100, 11, 405, 31);
		add(titleLabel);

		gramErrorLabel = new JLabel("");
		gramErrorLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
		gramErrorLabel.setForeground(Color.RED);
		gramErrorLabel.setBounds(128, 211, 264, 16);
		add(gramErrorLabel);

		volumeErrorLabel = new JLabel("");
		volumeErrorLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
		volumeErrorLabel.setForeground(Color.RED);
		volumeErrorLabel.setBounds(128, 255, 264, 16);
		add(volumeErrorLabel);
	}
}
