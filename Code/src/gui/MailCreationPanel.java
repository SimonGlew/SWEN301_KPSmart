package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.UIManager;
import serverclient.ClientController;

import javax.swing.DefaultComboBoxModel;

public class MailCreationPanel extends EventCreationPanel {
	private ClientController controller;

	// Sub-sections of MailCreationPanel
	private JComboBox<String> originComboBox;
	private JComboBox<String> destComboBox;
	private JTextField weightTextField;
	private JLabel weightErrorLabel;
	private JTextField volumeTextField;
	private JLabel volumeErrorLabel;
	private JTextField costTextField;
	private JTextField dayTextField;
	
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

		JLabel weightLabel = new JLabel("Weight");
		weightLabel.setForeground(Color.DARK_GRAY);
		weightLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weightLabel.setBounds(30, 164, 82, 26);
		add(weightLabel);

		weightTextField = new JTextField();
		weightTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!isValidNumber(weightTextField.getText())) {
					weightErrorLabel.setText("Not a valid weight");
				} else {
					weightErrorLabel.setText("");
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
		weightTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weightTextField.setBounds(122, 164, 154, 26);
		add(weightTextField);

		JLabel volumeLabel = new JLabel("Volume");
		volumeLabel.setForeground(Color.DARK_GRAY);
		volumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeLabel.setBounds(30, 211, 82, 26);
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
		volumeTextField.setBounds(122, 211, 154, 26);
		add(volumeTextField);
		
		JLabel costLabel = new JLabel("Cost");
		costLabel.setForeground(Color.DARK_GRAY);
		costLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		costLabel.setBounds(30, 258, 82, 26);
		add(costLabel);

		costTextField = new JTextField();
		costTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		costTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		costTextField.setBounds(122, 258, 154, 26);
		add(costTextField);
		
		JLabel dayLabel = new JLabel("Day");
		dayLabel.setForeground(Color.DARK_GRAY);
		dayLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		dayLabel.setBounds(30, 305, 82, 26);
		add(dayLabel);

		dayTextField = new JTextField();
		dayTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		dayTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		dayTextField.setBounds(122, 305, 154, 26);
		add(dayTextField);

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		submitButton.setBounds(10, 350, 108, 31);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isValidNumber(weightTextField.getText()) && isValidNumber(volumeTextField.getText())) {
							controller.requestMailCreation(originComboBox.getItemAt(originComboBox.getSelectedIndex()),
							destComboBox.getItemAt(destComboBox.getSelectedIndex()),
							Double.parseDouble(weightTextField.getText()),
							Double.parseDouble(volumeTextField.getText()), 
							Double.parseDouble(costTextField.getText()),
							dayTextField.getText());
				}
			}
		});
		add(submitButton);

		JLabel titleLabel = new JLabel("Request for Delivery");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		titleLabel.setBounds(10, 11, 305, 31);
		add(titleLabel);

		weightErrorLabel = new JLabel("");
		weightErrorLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
		weightErrorLabel.setForeground(Color.RED);
		weightErrorLabel.setBounds(128, 187, 264, 16);
		add(weightErrorLabel);

		volumeErrorLabel = new JLabel("");
		volumeErrorLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
		volumeErrorLabel.setForeground(Color.RED);
		volumeErrorLabel.setBounds(128, 234, 264, 16);
		add(volumeErrorLabel);
	}
}
