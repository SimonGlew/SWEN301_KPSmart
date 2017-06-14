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

import io.Codes;
import serverclient.ClientController;

@SuppressWarnings("serial")
public class TransportCostPanel extends EventCreationPanel{
	private ClientController controller;

	// Sub-sections of MailCreationPanel
	private JComboBox<String> originComboBox;
	private JComboBox<String> destComboBox;
	private JComboBox<String> compComboBox;
	private JComboBox<String> prioComboBox;
	private JTextField gramTextField;
	private JTextField volumeTextField;
	private JTextField periodTextField;
	private JTextField durationTextField;
	private JTextField maxWeightTextField;
	private JTextField maxVolumeTextField;

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

		JLabel companyLabel = new JLabel("Company");
		companyLabel.setForeground(Color.DARK_GRAY);
		companyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		companyLabel.setBounds(30, 164, 82, 26);
		add(companyLabel);

		compComboBox = new JComboBox<String>();
		compComboBox.setModel(new DefaultComboBoxModel<String>(controller.getCompanies()));
		compComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		compComboBox.setEditable(true);
		compComboBox.setBounds(150, 164, 170, 26);
		add(compComboBox);

		JLabel priorityLabel = new JLabel("Priority");
		priorityLabel.setForeground(Color.DARK_GRAY);
		priorityLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		priorityLabel.setBounds(30, 211, 130, 26);
		add(priorityLabel);

		prioComboBox = new JComboBox<String>();
		prioComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { Codes.Priorities.InternationalAir , Codes.Priorities.InternationalStandard, Codes.Priorities.DomesticAir, Codes.Priorities.DomesticStandard}));
		prioComboBox.setEditable(true);
		prioComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		prioComboBox.setBounds(150, 211, 170, 26);
		add(prioComboBox);

		JLabel gramLabel = new JLabel("Price Per Gram");
		gramLabel.setForeground(Color.DARK_GRAY);
		gramLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		gramLabel.setBounds(30, 255, 130, 26);
		add(gramLabel);

		gramTextField = new JTextField();
		gramTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		gramTextField.setBounds(150, 255, 170, 26);
		add(gramTextField);

		JLabel volumeLabel = new JLabel("Price Per Cube");
		volumeLabel.setForeground(Color.DARK_GRAY);
		volumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeLabel.setBounds(30, 303, 130, 26);
		add(volumeLabel);

		volumeTextField = new JTextField();
		volumeTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeTextField.setBounds(150, 303, 170, 26);
		add(volumeTextField);

		JLabel periodLabel = new JLabel("Period");
		periodLabel.setForeground(Color.DARK_GRAY);
		periodLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		periodLabel.setBounds(30, 350, 130, 26);
		add(periodLabel);

		periodTextField = new JTextField();
		periodTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		periodTextField.setBounds(150, 350, 170, 26);
		add(periodTextField);

		JLabel durationLabel = new JLabel("Duration");
		durationLabel.setForeground(Color.DARK_GRAY);
		durationLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		durationLabel.setBounds(30, 397, 130, 26);
		add(durationLabel);

		durationTextField = new JTextField();
		durationTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		durationTextField.setBounds(150, 397, 170, 26);
		add(durationTextField);

		JLabel maxWeightLabel = new JLabel("Max Weight");
		maxWeightLabel.setForeground(Color.DARK_GRAY);
		maxWeightLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		maxWeightLabel.setBounds(350, 69, 130, 26);
		add(maxWeightLabel);

		maxWeightTextField = new JTextField();
		maxWeightTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		maxWeightTextField.setBounds(450, 69, 170, 26);
		add(maxWeightTextField);

		JLabel maxVolumeLabel = new JLabel("Max Volume");
		maxVolumeLabel.setForeground(Color.DARK_GRAY);
		maxVolumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		maxVolumeLabel.setBounds(350, 120, 130, 26);
		add(maxVolumeLabel);

		maxVolumeTextField = new JTextField();
		maxVolumeTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		maxVolumeTextField.setBounds(450, 120, 170, 26);
		add(maxVolumeTextField);

		JLabel daysLabel = new JLabel("Days");
		daysLabel.setForeground(Color.DARK_GRAY);
		daysLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		daysLabel.setBounds(30, 410, 130, 80);
		add(daysLabel);

		ArrayList<JCheckBox> dayBoxs = new ArrayList<JCheckBox>();
		JCheckBox m = new JCheckBox("Monday");
		m.setBounds(150, 440, 100, 20);
		JCheckBox t = new JCheckBox("Tuesday");
		t.setBounds(260, 440, 100, 20);
		JCheckBox w = new JCheckBox("Wednesday");
		w.setBounds(370, 440, 100, 20);
		JCheckBox th = new JCheckBox("Thursday");
		th.setBounds(150, 470, 100, 20);
		JCheckBox f = new JCheckBox("Friday");
		f.setBounds(260, 470, 100, 20);
		JCheckBox s = new JCheckBox("Saturday");
		s.setBounds(370, 470, 100, 20);
		JCheckBox su = new JCheckBox("Sunday");
		su.setBounds(150, 500, 100, 20);

		dayBoxs.add(m);
		dayBoxs.add(t);
		dayBoxs.add(w);
		dayBoxs.add(th);
		dayBoxs.add(f);
		dayBoxs.add(s);
		dayBoxs.add(su);
		for(JCheckBox i: dayBoxs) { i.updateUI(); add(i); }

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		submitButton.setBounds(150, 550, 108, 31);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String daySelected = "";

				for(JCheckBox j: dayBoxs){
					if(j.isSelected()){
						daySelected += j.getText() + " ";
					}
				}

				controller.requestTransportCostUpdate((String)originComboBox.getSelectedItem(),
						(String)destComboBox.getSelectedItem(),
						(String)compComboBox.getSelectedItem(),
						(String)prioComboBox.getSelectedItem(),
						Double.parseDouble(gramTextField.getText()),
						Double.parseDouble(volumeTextField.getText()),
						daySelected,
						Integer.parseInt(periodTextField.getText()),
						Double.parseDouble(durationTextField.getText()),
						Double.parseDouble(maxWeightTextField.getText()),
						Double.parseDouble(maxVolumeTextField.getText()));

			}
		});
		add(submitButton);

		JLabel titleLabel = new JLabel("Transport Cost Update");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		titleLabel.setBounds(100, 11, 405, 31);
		add(titleLabel);
	}
}
