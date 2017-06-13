package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import serverclient.ClientController;

public class TransportCostPanel extends EventCreationPanel{
	private ClientController controller;

	// Sub-sections of MailCreationPanel
	private JComboBox<String> originComboBox;
	private JComboBox<String> destComboBox;
	private JComboBox<String> prioComboBox;
	private JTextField gramTextField;
	private JLabel gramErrorLabel;
	private JTextField volumeTextField;
	private JLabel volumeErrorLabel;
	
	public TransportCostPanel(ClientController controller) {
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
		originComboBox.setBounds(122, 69, 154, 26);
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
		destComboBox.setBounds(122, 116, 154, 26);
		add(destComboBox);
		
		JLabel priorityLabel = new JLabel("Priority");
		priorityLabel.setForeground(Color.DARK_GRAY);
		priorityLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		priorityLabel.setBounds(30, 164, 82, 26);
		add(priorityLabel);

		prioComboBox = new JComboBox<String>();
		prioComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Earth", "Water", "Air"}));
		prioComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		prioComboBox.setBounds(122, 164, 154, 26);
		add(prioComboBox);

		JLabel gramLabel = new JLabel("Price Per Gram");
		gramLabel.setForeground(Color.DARK_GRAY);
		gramLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		gramLabel.setBounds(30, 211, 82, 26);
		add(gramLabel);

		gramTextField = new JTextField();
		gramTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!isValidNumber(gramErrorLabel.getText())) {
					gramErrorLabel.setText("Not a valid weight");
				} else {
					gramErrorLabel.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		gramTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		gramTextField.setBounds(122, 211, 154, 26);
		add(gramTextField);

		JLabel volumeLabel = new JLabel("Price per Cube");
		volumeLabel.setForeground(Color.DARK_GRAY);
		volumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeLabel.setBounds(30, 255, 82, 26);
		add(volumeLabel);

		volumeTextField = new JTextField();
		volumeTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!isValidNumber(volumeTextField.getText())) {
					volumeErrorLabel.setText("Not a valid volume");
				} else {
					volumeErrorLabel.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		volumeTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeTextField.setBounds(122, 255, 154, 26);
		add(volumeTextField);

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		submitButton.setBounds(10, 300, 108, 31);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isValidNumber(gramErrorLabel.getText()) && isValidNumber(volumeTextField.getText())) {
							controller.requestCustomerPriceUpdate(originComboBox.getItemAt(originComboBox.getSelectedIndex()),
							destComboBox.getItemAt(destComboBox.getSelectedIndex()),
							prioComboBox.getItemAt(prioComboBox.getSelectedIndex()),
							Double.parseDouble(gramTextField.getText()),
							Double.parseDouble(volumeTextField.getText()));
				}
			}
		});
		add(submitButton);

		JLabel titleLabel = new JLabel("Transport Cost Update");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		titleLabel.setBounds(10, 11, 305, 31);
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
