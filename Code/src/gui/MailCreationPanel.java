package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.UIManager;

import io.Codes;
import serverclient.ClientController;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class MailCreationPanel extends EventCreationPanel {
	private ClientController controller;

	// Sub-sections of MailCreationPanel
	private JComboBox<String> originComboBox;
	private JComboBox<String> destComboBox;
	private JComboBox<String> prioComboBox;
	private JTextField weightTextField;
	private JTextField volumeTextField;

	public MailCreationPanel(ClientController controller) {
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
		originComboBox.setModel(new DefaultComboBoxModel<String>(controller.getLocations()));
		originComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		originComboBox.setEditable(true);
		originComboBox.setBounds(150, 69, 170, 26);
		add(originComboBox);

		JLabel destLabel = new JLabel("Destination");
		destLabel.setForeground(Color.DARK_GRAY);
		destLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destLabel.setBounds(30, 116, 82, 26);
		add(destLabel);

		destComboBox = new JComboBox<String>();
		destComboBox.setModel(new DefaultComboBoxModel<String>(controller.getLocations()));
		destComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destComboBox.setEditable(true);
		destComboBox.setBounds(150, 116, 170, 26);
		add(destComboBox);

		JLabel priorityLabel = new JLabel("Priority");
		priorityLabel.setForeground(Color.DARK_GRAY);
		priorityLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		priorityLabel.setBounds(30, 164, 82, 26);
		add(priorityLabel);

		prioComboBox = new JComboBox<String>();
		prioComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { Codes.Priorities.InternationalAir , Codes.Priorities.InternationalStandard, Codes.Priorities.DomesticAir, Codes.Priorities.DomesticStandard }));
		prioComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		prioComboBox.setEditable(true);
		prioComboBox.setBounds(150, 164, 170, 26);
		add(prioComboBox);

		JLabel weightLabel = new JLabel("Weight");
		weightLabel.setForeground(Color.DARK_GRAY);
		weightLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weightLabel.setBounds(30, 211, 82, 26);
		add(weightLabel);

		weightTextField = new JTextField();
		weightTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weightTextField.setBounds(150, 211, 170, 26);
		add(weightTextField);

		JLabel volumeLabel = new JLabel("Volume");
		volumeLabel.setForeground(Color.DARK_GRAY);
		volumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeLabel.setBounds(30, 255, 82, 26);
		add(volumeLabel);

		volumeTextField = new JTextField();
		volumeTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeTextField.setBounds(150, 255, 170, 26);
		add(volumeTextField);

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		submitButton.setBounds(150, 300, 108, 31);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.requestMailCreationRoutes(originComboBox.getItemAt(originComboBox.getSelectedIndex()),
						destComboBox.getItemAt(destComboBox.getSelectedIndex()),
						prioComboBox.getItemAt(prioComboBox.getSelectedIndex()),
						Double.parseDouble(weightTextField.getText()),
						Double.parseDouble(volumeTextField.getText()));

			}
		});
		add(submitButton);

		JLabel titleLabel = new JLabel("Request for Delivery");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		titleLabel.setBounds(100, 11, 405, 31);
		add(titleLabel);
	}
}
