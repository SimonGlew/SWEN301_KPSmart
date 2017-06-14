package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import io.Codes;
import serverclient.ClientController;

@SuppressWarnings("serial")
public class CustomerPricePanel extends EventCreationPanel{
	private ClientController controller;

	// Sub-sections of MailCreationPanel
	private JComboBox<String> originComboBox;
	private JComboBox<String> destComboBox;
	private JComboBox<String> prioComboBox;
	private JTextField gramTextField;
	private JTextField volumeTextField;

	public CustomerPricePanel(ClientController controller) {
		this.controller = controller;
		initPanel();
	}

	public void update() {
		originComboBox.setModel(new DefaultComboBoxModel<String>(controller.getLocations()));
		destComboBox.setModel(new DefaultComboBoxModel<String>(controller.getLocations()));
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
		destComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destComboBox.setEditable(true);
		destComboBox.setBounds(150, 116, 170, 26);
		add(destComboBox);

		JLabel priorityLabel = new JLabel("Priority");
		priorityLabel.setForeground(Color.DARK_GRAY);
		priorityLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		priorityLabel.setBounds(30, 164, 130, 26);
		add(priorityLabel);

		prioComboBox = new JComboBox<String>();
		prioComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { Codes.Priorities.InternationalAir , Codes.Priorities.InternationalStandard, Codes.Priorities.DomesticAir, Codes.Priorities.DomesticStandard}));
		prioComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		prioComboBox.setEditable(false);
		prioComboBox.setBounds(150, 164, 170, 26);
		add(prioComboBox);

		JLabel gramLabel = new JLabel("Price Per Gram");
		gramLabel.setForeground(Color.DARK_GRAY);
		gramLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		gramLabel.setBounds(30, 211, 130, 26);
		add(gramLabel);

		gramTextField = new JTextField();
		gramTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		gramTextField.setBounds(150, 211, 170, 26);
		add(gramTextField);

		JLabel volumeLabel = new JLabel("Price Per Cube");
		volumeLabel.setForeground(Color.DARK_GRAY);
		volumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeLabel.setBounds(30, 255, 130, 26);
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
				controller.requestCustomerPriceUpdate((String)originComboBox.getSelectedItem(),
						(String)destComboBox.getSelectedItem(),
						(String)prioComboBox.getSelectedItem(),
						Double.parseDouble(gramTextField.getText()),
						Double.parseDouble(volumeTextField.getText()));

			}
		});
		add(submitButton);

		JLabel titleLabel = new JLabel("Customer Price Update");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		titleLabel.setBounds(100, 11, 405, 31);
		add(titleLabel);
	}
}
