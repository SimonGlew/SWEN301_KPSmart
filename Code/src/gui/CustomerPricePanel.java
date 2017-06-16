package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.security.auth.callback.ChoiceCallback;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

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
	private JLabel originErrorLabel;
	private JLabel destErrorLabel;
	private JLabel weightErrorLabel;
	private JLabel volumeErrorLabel;
	private JLabel submitErrorLabel;
	private JTextComponent originText;
	private JTextComponent destText;

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
		originText = (JTextComponent) originComboBox.getEditor().getEditorComponent();
		originText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkOrigin();
			}
		});
		originComboBox.addActionListener(e -> checkOrigin());
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
		destText = (JTextComponent) destComboBox.getEditor().getEditorComponent();
		destText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkDest();
			}
		});
		destComboBox.addActionListener(e -> checkDest());
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
		gramTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				 checkPricePerGram();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {}
			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		gramTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		gramTextField.setBounds(150, 211, 170, 26);
		add(gramTextField);

		JLabel volumeLabel = new JLabel("Price Per Cube");
		volumeLabel.setForeground(Color.DARK_GRAY);
		volumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeLabel.setBounds(30, 255, 130, 26);
		add(volumeLabel);

		volumeTextField = new JTextField();
		volumeTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				 checkPricePerCube();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {}
			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		volumeTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeTextField.setBounds(150, 255, 170, 26);
		add(volumeTextField);

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		submitButton.setBounds(150, 300, 108, 31);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!checkOrigin() || !checkDest() || !checkPricePerCube() || !checkPricePerGram()) {
					submitErrorLabel.setText("Invalid form. Please check inputs");
					return;
				} else {
					submitErrorLabel.setText("");
				}
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
		
		originErrorLabel = new JLabel("");
		originErrorLabel.setFont(errorFont);
		originErrorLabel.setForeground(errorColor);
		originErrorLabel.setBounds(150, 95, 249, 14);
		add(originErrorLabel);
		
		destErrorLabel = new JLabel("");
		destErrorLabel.setForeground(errorColor);
		destErrorLabel.setFont(errorFont);
		destErrorLabel.setBounds(150, 142, 249, 14);
		add(destErrorLabel);
		
		weightErrorLabel = new JLabel("");
		weightErrorLabel.setForeground(errorColor);
		weightErrorLabel.setFont(errorFont);
		weightErrorLabel.setBounds(150, 237, 249, 14);
		add(weightErrorLabel);
		
		volumeErrorLabel = new JLabel("");
		volumeErrorLabel.setForeground(errorColor);
		volumeErrorLabel.setFont(errorFont);
		volumeErrorLabel.setBounds(150, 281, 249, 14);
		add(volumeErrorLabel);
		
		submitErrorLabel = new JLabel("");
		submitErrorLabel.setForeground(errorColor);
		submitErrorLabel.setFont(errorFont);
		submitErrorLabel.setBounds(150, 330, 249, 14);
		add(submitErrorLabel);
	}

	private boolean checkOrigin() {
		String str = originText.getText();
		if (isValidString(str)) {
			originErrorLabel.setText("");
			return true;
		} else {
			originErrorLabel.setText("Invalid name");
			return false;
		}
	}
	
	private boolean checkDest() {
		String str = destText.getText();
		if (isValidString(str)) {
			destErrorLabel.setText("");
			return true;
		} else {
			destErrorLabel.setText("Invalid name");
			return false;
		}
	}
	
	private boolean checkPricePerGram() {
		if (isValidNumber(gramTextField.getText())) {
			weightErrorLabel.setText("");
			return true;
		} else {
			weightErrorLabel.setText("Invalid price");
			return false;
		}
	}
	
	private boolean checkPricePerCube() {
		if (isValidNumber(volumeTextField.getText())) {
			volumeErrorLabel.setText("");
			return true;
		} else {
			volumeErrorLabel.setText("Invalid price");
			return false;
		}
	}

	public void clear() {
		gramTextField.setText("");
		volumeTextField.setText("");
	}
}
