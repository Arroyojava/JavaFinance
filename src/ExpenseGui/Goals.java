package ExpenseGui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Goals extends JPanel {

	private JTextField goalNametextField;
	private JTable table;

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textField_1;
	private JTextField activeGoalTextBox;
	private JTextField textField;

	@SuppressWarnings("removal")
	public Goals(String user) {
		String username = user;

		// Set Layout for add Goal panel
		/*********************************************************************************************/
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 993, 640);
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		add(tabbedPane);

		JPanel addGoalPanel = new JPanel();
		addGoalPanel.setBorder(null);
		addGoalPanel.setFocusable(true);

		tabbedPane.addTab("Add Goal", new ImageIcon(Goals.class.getResource("/img/newAdd.png")), addGoalPanel, null);
		addGoalPanel.setLayout(null);

		JLabel addExpenseLogoLabel = new JLabel("");
		addExpenseLogoLabel.setIcon(new ImageIcon(Goals.class.getResource("/img/128.png")));
		addExpenseLogoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addExpenseLogoLabel.setBounds(10, 392, 105, 117);
		addGoalPanel.add(addExpenseLogoLabel);
		/*********************************************************************************************/

		// Add Labels for add Goal
		/*********************************************************************************************/
		JLabel WhatAreYouSavingForLabel = new JLabel("What are you saving for?");
		WhatAreYouSavingForLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		WhatAreYouSavingForLabel.setBounds(10, 11, 269, 32);
		addGoalPanel.add(WhatAreYouSavingForLabel);

		JLabel goalNameLabel = new JLabel("Goal Name*");
		goalNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		goalNameLabel.setBounds(10, 90, 114, 26);
		addGoalPanel.add(goalNameLabel);

		JLabel TargetDateLabel = new JLabel("Target Date*");
		TargetDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		TargetDateLabel.setBounds(170, 90, 130, 26);
		addGoalPanel.add(TargetDateLabel);

		JLabel AmountSavedLabel = new JLabel("Amount Saved Already");
		AmountSavedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		AmountSavedLabel.setBounds(11, 165, 197, 23);
		addGoalPanel.add(AmountSavedLabel);

		JLabel currencyLabel = new JLabel("USD");
		currencyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		currencyLabel.setBounds(150, 196, 43, 21);
		addGoalPanel.add(currencyLabel);

		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		descriptionLabel.setBounds(10, 237, 171, 26);
		addGoalPanel.add(descriptionLabel);

		JLabel targetAmountLabal = new JLabel("Target Amount*");
		targetAmountLabal.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		targetAmountLabal.setBounds(331, 90, 171, 23);
		addGoalPanel.add(targetAmountLabal);

		JLabel EstWeeklySavingLabel = new JLabel("Est. Weekly Savings Goal");
		EstWeeklySavingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		EstWeeklySavingLabel.setBounds(465, 264, 214, 26);
		addGoalPanel.add(EstWeeklySavingLabel);

		JLabel estWeeklySavingLabel = new JLabel("");
		estWeeklySavingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		estWeeklySavingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		estWeeklySavingLabel.setBounds(501, 301, 84, 26);
		addGoalPanel.add(estWeeklySavingLabel);

		JLabel currencyLabel_1 = new JLabel("USD");
		currencyLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		currencyLabel_1.setBounds(593, 304, 52, 21);
		addGoalPanel.add(currencyLabel_1);
		/*********************************************************************************************/

		// Add TextFields, TextArea, Buttons, Date Chooser, Spinner for add goal
		/*********************************************************************************************/
		goalNametextField = new JTextField();
		goalNametextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		goalNametextField.setColumns(10);
		goalNametextField.setBounds(10, 117, 140, 35);
		addGoalPanel.add(goalNametextField);

		JDateChooser targetDateChooser = new JDateChooser();
		targetDateChooser.setBounds(170, 117, 140, 35);
		targetDateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		addGoalPanel.add(targetDateChooser);

		JSpinner amountSavedSpinner = new JSpinner();
		amountSavedSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		amountSavedSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		amountSavedSpinner.setBounds(10, 189, 130, 35);
		addGoalPanel.add(amountSavedSpinner);

		JTextArea descriptionTextArea = new JTextArea();
		descriptionTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		descriptionTextArea.setBackground(new Color(229, 229, 229));
		descriptionTextArea.setBounds(10, 264, 430, 117);
		addGoalPanel.add(descriptionTextArea);

		JSpinner targetAmountSpinner = new JSpinner();
		targetAmountSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		targetAmountSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		targetAmountSpinner.setBounds(331, 117, 130, 35);
		addGoalPanel.add(targetAmountSpinner);

		JButton addButton = new JButton("ADD");
		addButton.setForeground(Color.BLUE);
		addButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		addButton.setBounds(465, 475, 100, 35);
		addGoalPanel.add(addButton);

		JButton resetFieldsButton = new JButton("Reset");
		resetFieldsButton.setForeground(Color.RED);
		resetFieldsButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		resetFieldsButton.setBounds(575, 475, 100, 35);
		addGoalPanel.add(resetFieldsButton);

		JButton generateAmountButton = new JButton("Generate Amount");
		generateAmountButton.setForeground(Color.BLUE);
		generateAmountButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		generateAmountButton.setBounds(481, 344, 183, 35);
		addGoalPanel.add(generateAmountButton);

		JLabel asterikLabel = new JLabel("* = Required");
		asterikLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		asterikLabel.setBounds(530, 20, 109, 21);
		addGoalPanel.add(asterikLabel);
		/*********************************************************************************************/

		// Set Action Listeners for add Goal panel
		/*********************************************************************************************/

		generateAmountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double avg = weekAvg(targetDateChooser, targetAmountSpinner, amountSavedSpinner);
				estWeeklySavingLabel.setText("$" + avg);

			}
		});

		resetFieldsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				goalNametextField.setText("");
				targetDateChooser.setDate(null);
				targetAmountSpinner.setValue(0);
				amountSavedSpinner.setValue(0);
				descriptionTextArea.setText("");
				estWeeklySavingLabel.setText("0");

			}
		});

		// Set Layout for add Active Goal panel
		/*********************************************************************************************/
		JPanel activeGoalsPanel = new JPanel();

		tabbedPane.addTab("Active Goals", new ImageIcon(Goals.class.getResource("/img/graph.png")), activeGoalsPanel,
				null);
		activeGoalsPanel.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.BLUE, 3));
		panel_3.setBounds(340, 11, 354, 498);
		activeGoalsPanel.add(panel_3);
		panel_3.setLayout(null);
		/*********************************************************************************************/

		// Add Labels for Active Goals
		/*********************************************************************************************/
		JLabel activeGoalNameLabel = new JLabel("Goal Name:");
		activeGoalNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		activeGoalNameLabel.setBounds(10, 51, 114, 35);
		panel_3.add(activeGoalNameLabel);

		JLabel goalDetailsLabel = new JLabel("Goal Details");
		goalDetailsLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		goalDetailsLabel.setBounds(10, 11, 130, 32);
		panel_3.add(goalDetailsLabel);

		JLabel activeTargetDateLabel = new JLabel("Target Date:");
		activeTargetDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		activeTargetDateLabel.setBounds(10, 103, 130, 35);
		panel_3.add(activeTargetDateLabel);

		JLabel activeTargetAmountLabel = new JLabel("Target Amount:");
		activeTargetAmountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		activeTargetAmountLabel.setBounds(10, 151, 130, 35);
		panel_3.add(activeTargetAmountLabel);

		JLabel activeAmountSavedLabel = new JLabel("Amount Saved:");
		activeAmountSavedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		activeAmountSavedLabel.setBounds(10, 196, 130, 35);
		panel_3.add(activeAmountSavedLabel);

		JLabel activeDescriptionLabel = new JLabel("Description:");
		activeDescriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		activeDescriptionLabel.setBounds(10, 254, 114, 26);
		panel_3.add(activeDescriptionLabel);

		JLabel lblActive = new JLabel("Active Goals");
		lblActive.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblActive.setBounds(10, 11, 147, 32);
		activeGoalsPanel.add(lblActive);

		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(Goals.class.getResource("/img/128.png")));
		logoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		logoLabel.setBounds(10, 392, 105, 117);
		activeGoalsPanel.add(logoLabel);

		JLabel amountAddLabel = new JLabel("Amount to Add:");
		amountAddLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		amountAddLabel.setBounds(10, 255, 130, 23);
		activeGoalsPanel.add(amountAddLabel);

		// Add TextFields, TextArea, Buttons, Date Chooser, Spinner for Active Goals
		/*********************************************************************************************/
		JDateChooser targetDateChooserActive = new JDateChooser();
		targetDateChooserActive.setBounds(147, 103, 140, 35);
		targetDateChooserActive.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		panel_3.add(targetDateChooserActive);

		JSpinner activeTargetAmountSpinner = new JSpinner();
		activeTargetAmountSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		activeTargetAmountSpinner.setEnabled(false);
		activeTargetAmountSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		activeTargetAmountSpinner.setBounds(147, 152, 140, 35);
		panel_3.add(activeTargetAmountSpinner);

		JSpinner activeAmountSavedSpinner = new JSpinner();
		activeAmountSavedSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		activeAmountSavedSpinner.setEnabled(false);
		activeAmountSavedSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		activeAmountSavedSpinner.setBounds(147, 197, 140, 35);
		panel_3.add(activeAmountSavedSpinner);

		JTextArea activeDescriptionTextArea = new JTextArea();
		activeDescriptionTextArea.setEditable(false);
		activeDescriptionTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		activeDescriptionTextArea.setBackground(new Color(229, 229, 229));
		activeDescriptionTextArea.setBounds(137, 254, 203, 171);
		panel_3.add(activeDescriptionTextArea);

		activeGoalTextBox = new JTextField();
		activeGoalTextBox.setEditable(false);
		activeGoalTextBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		activeGoalTextBox.setColumns(10);
		activeGoalTextBox.setBounds(147, 51, 140, 35);
		panel_3.add(activeGoalTextBox);

		JButton editButton = new JButton("Edit");
		editButton.setForeground(Color.BLUE);
		editButton.setHorizontalAlignment(SwingConstants.LEADING);
		editButton.setIcon(new ImageIcon(Goals.class.getResource("/img/newModify24.png")));
		editButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		editButton.setBounds(251, 10, 89, 37);
		panel_3.add(editButton);

		JButton updateGoalButton = new JButton("Update");
		updateGoalButton.setEnabled(false);
		updateGoalButton.setForeground(Color.BLUE);
		updateGoalButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		updateGoalButton.setBounds(10, 450, 100, 35);
		panel_3.add(updateGoalButton);

		JButton cancelGoalButton = new JButton("Cancel");
		cancelGoalButton.setEnabled(false);

		cancelGoalButton.setForeground(Color.RED);
		cancelGoalButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cancelGoalButton.setBounds(240, 450, 100, 35);
		panel_3.add(cancelGoalButton);

		textField_1 = new JTextField();
		textField_1.setBounds(137, 57, 130, 27);
		textField_1.setColumns(10);

		JPanel overviewPanel = new JPanel();
		tabbedPane.addTab("Goals Overview", new ImageIcon(Goals.class.getResource("/img/flag.png")), overviewPanel,
				null);
		overviewPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 680, 139);
		scrollPane.setBorder(new LineBorder(Color.BLUE, 3));
		overviewPanel.add(scrollPane);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		JProgressBar progressBar1 = new JProgressBar();
		progressBar1.setStringPainted(true);
		progressBar1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		progressBar1.setBounds(163, 50, 167, 26);
		progressBar1.setMinimum(0);
		activeGoalsPanel.add(progressBar1);

		JProgressBar progressBar2 = new JProgressBar();
		progressBar2.setStringPainted(true);
		progressBar2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		progressBar2.setBounds(163, 85, 167, 26);
		progressBar2.setMinimum(0);
		activeGoalsPanel.add(progressBar2);

		JProgressBar progressBar3 = new JProgressBar();
		progressBar3.setStringPainted(true);
		progressBar3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		progressBar3.setBounds(163, 120, 167, 26);
		progressBar3.setMinimum(0);
		activeGoalsPanel.add(progressBar3);

		JProgressBar progressBar4 = new JProgressBar();
		progressBar4.setStringPainted(true);
		progressBar4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		progressBar4.setBounds(163, 155, 167, 26);
		progressBar4.setMinimum(0);
		activeGoalsPanel.add(progressBar4);

		JProgressBar progressBar5 = new JProgressBar();
		progressBar5.setStringPainted(true);
		progressBar5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		progressBar5.setBounds(163, 190, 167, 26);
		progressBar5.setMinimum(0);
		activeGoalsPanel.add(progressBar5);

		JRadioButton goal1 = new JRadioButton();
		goal1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		goal1.setBounds(6, 50, 151, 26);
		activeGoalsPanel.add(goal1);

		JRadioButton goal2 = new JRadioButton("");
		goal2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		goal2.setBounds(6, 85, 151, 26);
		activeGoalsPanel.add(goal2);

		JRadioButton goal3 = new JRadioButton("");
		goal3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		goal3.setBounds(6, 120, 151, 26);
		activeGoalsPanel.add(goal3);

		JRadioButton goal4 = new JRadioButton("");
		goal4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		goal4.setBounds(6, 155, 151, 26);
		activeGoalsPanel.add(goal4);

		JRadioButton goal5 = new JRadioButton("");
		goal5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		goal5.setBounds(6, 190, 151, 26);
		activeGoalsPanel.add(goal5);

		ButtonGroup goalGroup = new ButtonGroup();
		goalGroup.add(goal1);
		goalGroup.add(goal2);
		goalGroup.add(goal3);
		goalGroup.add(goal4);
		goalGroup.add(goal5);

		JSpinner addAmountSpinner = new JSpinner();
		addAmountSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		addAmountSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		addAmountSpinner.setBounds(150, 249, 105, 35);
		activeGoalsPanel.add(addAmountSpinner);

		JButton addAmountButton = new JButton("");
		addAmountButton.setEnabled(false);

		addAmountButton.setIcon(new ImageIcon(Goals.class.getResource("/img/plus.png")));
		addAmountButton.setForeground(Color.BLUE);
		addAmountButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		addAmountButton.setBounds(258, 249, 72, 35);
		activeGoalsPanel.add(addAmountButton);

		JLabel todayDateLabel = new JLabel("Today's Date:");
		todayDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		todayDateLabel.setBounds(10, 296, 115, 23);
		activeGoalsPanel.add(todayDateLabel);

		JLabel dateLabel = new JLabel("");
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String currentDate = simpleDateFormat.format(new Date());
		dateLabel.setText(currentDate);
		dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		dateLabel.setBounds(124, 296, 115, 23);
		activeGoalsPanel.add(dateLabel);

		textField = new JTextField();
		textField.setBounds(821, 80, 130, 32);
		activeGoalsPanel.add(textField);
		textField.setEnabled(false);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		
				JLabel lblEstWeeklySaving_2 = new JLabel("Est. Weekly Savings Goal");
				lblEstWeeklySaving_2.setBounds(10, 330, 206, 26);
				activeGoalsPanel.add(lblEstWeeklySaving_2);
				lblEstWeeklySaving_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				
						JLabel currencyLabel_1_1 = new JLabel("USD");
						currencyLabel_1_1.setBounds(105, 357, 48, 21);
						activeGoalsPanel.add(currencyLabel_1_1);
						currencyLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
						
								JLabel activeEstWeeklySavingLabel = new JLabel("0");
								activeEstWeeklySavingLabel.setBounds(20, 354, 75, 26);
								activeGoalsPanel.add(activeEstWeeklySavingLabel);
								activeEstWeeklySavingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
								activeEstWeeklySavingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		JLabel addExpenseLogoLabel_2 = new JLabel("");
		addExpenseLogoLabel_2.setBounds(10, 392, 105, 117);
		addExpenseLogoLabel_2.setIcon(new ImageIcon(Goals.class.getResource("/img/128.png")));
		addExpenseLogoLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		overviewPanel.add(addExpenseLogoLabel_2);
		
		JButton exportButton = new JButton("Export");
		
		exportButton.setForeground(Color.GREEN);
		exportButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		exportButton.setBounds(590, 239, 100, 35);
		overviewPanel.add(exportButton);
		
		JLabel allGoalLabel = new JLabel("All Goals");
		allGoalLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		allGoalLabel.setBounds(10, 11, 147, 32);
		overviewPanel.add(allGoalLabel);
		
		JButton refreshExpButton = new JButton("Refresh");
		
		refreshExpButton.setForeground(Color.BLUE);
		refreshExpButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		refreshExpButton.setBounds(480, 239, 100, 35);
		overviewPanel.add(refreshExpButton);
		
		JLabel goalIDLabel = new JLabel("ID");
		goalIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		goalIDLabel.setBounds(10, 202, 64, 27);
		overviewPanel.add(goalIDLabel);
		
		JSpinner goalIDSpinner = new JSpinner();
		goalIDSpinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		goalIDSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		goalIDSpinner.setBounds(10, 239, 100, 35);
		overviewPanel.add(goalIDSpinner);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteButton.setForeground(Color.RED);
		deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		deleteButton.setBounds(120, 239, 100, 35);
		overviewPanel.add(deleteButton);
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int goal =  (int) goalIDSpinner.getValue();
				if (goal != 0) {
					try {
						con = DbConnection.getConnection();
						String sql = "SELECT * FROM goals WHERE GoalID =? AND Username_fk=?";
						pst = con.prepareStatement(sql);
						pst.setString(1, goalIDSpinner.getValue().toString());
						pst.setString(2, username);
						ResultSet rs = pst.executeQuery();

						if (rs.next()) {
							String next = rs.getString("GoalID");
							String username = rs.getString("Username_fk");

							if (goalIDSpinner.getValue().toString().equals(next) && username.equals(username)) {

								String sql1 = "DELETE FROM goals WHERE GoalID=? AND Username_fk=?";
								pst = con.prepareStatement(sql1);
								pst.setString(1, goalIDSpinner.getValue().toString());
								pst.setString(2, username);
								pst.executeUpdate();

								goal1.setText("");
								progressBar1.setValue(0);

								goal2.setText("");
								progressBar2.setValue(0);

								goal3.setText("");
								progressBar3.setValue(0);

								goal4.setText("");
								progressBar4.setValue(0);

								goal5.setText("");
								progressBar5.setValue(0);

							}
							pst.close();
							JOptionPane.showMessageDialog(null, "Goal has been Removed!");
							ShowData(username);

							click(tabbedPane, 100, 100);
						}

						else {
							JOptionPane.showMessageDialog(null, "Error! Please Try Again!");
						}

					} catch (Exception exe) {
						JOptionPane.showMessageDialog(null, exe);

					}

				} else {
					JOptionPane.showMessageDialog(null, "Nothing to Delete!");
				}

		
			}
		});
		
		refreshExpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ShowData(username);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
			
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (goal5.getText().isBlank()) {

					try {

						double amount = (double) targetAmountSpinner.getValue();
						if (!goalNametextField.getText().isBlank() && amount != 0
								&& targetDateChooser.getDateEditor().getDate() != null) {
							String sql = "INSERT INTO goals(Goal_Name, Goal_Target_Date, Goal_Target_Amount, "
									+ "Goal_Amount_Saved, Goal_Description, Username_fk) VALUES (?,?,?,?,?,?)";
							con = DbConnection.getConnection();
							pst = con.prepareStatement(sql);
							pst.setString(1, goalNametextField.getText());
							targetDateChooser.getDate().getTime();
							java.sql.Date date = new java.sql.Date(targetDateChooser.getDate().getTime());
							pst.setDate(2, date);
							pst.setString(3, targetAmountSpinner.getValue().toString());
							pst.setString(4, amountSavedSpinner.getValue().toString());
							pst.setString(5, descriptionTextArea.getText());
							pst.setString(6, username);

							pst.executeUpdate();

							pst.close();
							JOptionPane.showMessageDialog(null, "Goal has been added!");
							ShowData(username);
							click(tabbedPane, 100, 100);
						} else {
							JOptionPane.showMessageDialog(null,
									"Invalid or Empty Fields Found! \nRequired Fields are: Goal Name, Target Date and Target Amount "
											+ "\nVerify those fields and Try Again.");
						}

					} catch (Exception exe) {
						JOptionPane.showMessageDialog(null,
								"Invalid or Empty Fields Found! \nRequired Fields are: Goal Name, Target Date and Target Amount "
										+ "\nVerify those fields and Try Again.");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Goal NOT Added! \nThe Maximum Amount of Goals Have Been Met! \nDelete or Complete Goals to Add More!");
				}

			}
		});

		addAmountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double aAmount = (double) addAmountSpinner.getValue();
				if (aAmount != 0) {
					try {
						Connection con = DbConnection.getConnection();

						if (goal1.isSelected() && !goal1.getText().isBlank()) {
							double sAmount = progressBar1.getValue();
							double newAmount = sAmount + aAmount;
							String name = goal1.getText();

							pst = con.prepareStatement(
									"UPDATE goals SET Goal_Amount_Saved=? WHERE Goal_Name=? AND Username_fk=?");
							pst.setDouble(1, newAmount);
							pst.setString(2, name);
							pst.setString(3, username);
							pst.executeUpdate();
							progressBar1.setValue((int) newAmount);
							activeAmountSavedSpinner.setValue(newAmount);

						}

						else if (goal2.isSelected() && !goal2.getText().isBlank()) {
							double sAmount = progressBar2.getValue();
							double newAmount = sAmount + aAmount;
							String name = goal2.getText();

							pst = con.prepareStatement(
									"UPDATE goals SET Goal_Amount_Saved=? WHERE Goal_Name=? AND Username_fk=?");
							pst.setDouble(1, newAmount);
							pst.setString(2, name);
							pst.setString(3, username);
							pst.executeUpdate();
							progressBar2.setValue((int) newAmount);
							activeAmountSavedSpinner.setValue(newAmount);

						} else if (goal3.isSelected() && !goal3.getText().isBlank()) {
							double sAmount = progressBar3.getValue();
							double newAmount = sAmount + aAmount;
							String name = goal3.getText();

							pst = con.prepareStatement(
									"UPDATE goals SET Goal_Amount_Saved=? WHERE Goal_Name=? AND Username_fk=?");
							pst.setDouble(1, newAmount);
							pst.setString(2, name);
							pst.setString(3, username);
							pst.executeUpdate();
							progressBar3.setValue((int) newAmount);
							activeAmountSavedSpinner.setValue(newAmount);

						} else if (goal4.isSelected() && !goal4.getText().isBlank()) {
							double sAmount = progressBar4.getValue();
							double newAmount = sAmount + aAmount;
							String name = goal4.getText();

							pst = con.prepareStatement(
									"UPDATE goals SET Goal_Amount_Saved=? WHERE Goal_Name=? AND Username_fk=?");
							pst.setDouble(1, newAmount);
							pst.setString(2, name);
							pst.setString(3, username);
							pst.executeUpdate();
							progressBar4.setValue((int) newAmount);
							activeAmountSavedSpinner.setValue(newAmount);

						} else if (goal5.isSelected() && !goal5.getText().isBlank()) {
							double sAmount = progressBar5.getValue();
							double newAmount = sAmount + aAmount;
							String name = goal5.getText();

							pst = con.prepareStatement(
									"UPDATE goals SET Goal_Amount_Saved=? WHERE Goal_Name=? AND Username_fk=?");
							pst.setDouble(1, newAmount);
							pst.setString(2, name);
							pst.setString(3, username);
							pst.executeUpdate();
							progressBar5.setValue((int) newAmount);
							activeAmountSavedSpinner.setValue(newAmount);

						}
						JOptionPane.showMessageDialog(null, "Amount added!");

						ShowData(username);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nothing to Add!");
				}

				double avg = weekAvg(targetDateChooserActive, activeTargetAmountSpinner, activeAmountSavedSpinner);

				activeEstWeeklySavingLabel.setText("$" + avg);

			}
		});

		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					Connection con = DbConnection.getConnection();
					pst = con.prepareStatement("select * from goals where Username_fk=?");
					pst.setString(1, username);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) {
						String goal = rs.getString("Goal_Name");
						goal1.setText(goal);

						double tAmount = rs.getDouble("Goal_Target_Amount");
						progressBar1.setMaximum((int) tAmount);

						double sAmount = rs.getDouble("Goal_Amount_Saved");
						progressBar1.setValue((int) sAmount);

					}
					if (rs.next()) {
						String goal = rs.getString("Goal_Name");
						goal2.setText(goal);

						double tAmount = rs.getDouble("Goal_Target_Amount");
						progressBar2.setMaximum((int) tAmount);

						double sAmount = rs.getDouble("Goal_Amount_Saved");
						progressBar2.setValue((int) sAmount);

					}
					if (rs.next()) {
						String goal = rs.getString("Goal_Name");
						goal3.setText(goal);

						double tAmount = rs.getDouble("Goal_Target_Amount");
						progressBar3.setMaximum((int) tAmount);

						double sAmount = rs.getDouble("Goal_Amount_Saved");
						progressBar3.setValue((int) sAmount);
					}
					if (rs.next()) {
						String goal = rs.getString("Goal_Name");
						goal4.setText(goal);

						double tAmount = rs.getDouble("Goal_Target_Amount");
						progressBar4.setMaximum((int) tAmount);

						double sAmount = rs.getDouble("Goal_Amount_Saved");
						progressBar4.setValue((int) sAmount);
					}
					if (rs.next()) {
						String goal = rs.getString("Goal_Name");
						goal5.setText(goal);

						double tAmount = rs.getDouble("Goal_Target_Amount");
						progressBar5.setMaximum((int) tAmount);

						double sAmount = rs.getDouble("Goal_Amount_Saved");
						progressBar5.setValue((int) sAmount);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		updateGoalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					con = DbConnection.getConnection();

					String sql = "UPDATE goals SET Goal_Name=?, Goal_Target_Date=?, Goal_Target_Amount=?, Goal_Amount_Saved=?, Goal_Description=? WHERE Goal_Name=? AND Username_fk=?";
					pst = con.prepareStatement(sql);
					pst.setString(1, activeGoalTextBox.getText());
					java.sql.Date date1 = new java.sql.Date(targetDateChooserActive.getDate().getTime());
					pst.setDate(2, date1);
					pst.setString(3, activeTargetAmountSpinner.getValue().toString());
					pst.setString(4, activeAmountSavedSpinner.getValue().toString());
					pst.setString(5, activeDescriptionTextArea.getText());
					pst.setString(6, textField.getText());
					pst.setString(7, username);
					pst.executeUpdate();
					pst.close();
					JOptionPane.showMessageDialog(null, "Goal has been Updated!");
					ShowData(username);
					click(tabbedPane, 100, 100);

				} catch (Exception exe) {
					JOptionPane.showMessageDialog(null, exe);
				}
				double avg = weekAvg(targetDateChooserActive, activeTargetAmountSpinner, activeAmountSavedSpinner);

				activeEstWeeklySavingLabel.setText("$" + avg);

				activeGoalTextBox.setEditable(false);
				activeDescriptionTextArea.setEditable(false);
				activeAmountSavedSpinner.setEnabled(false);
				activeTargetAmountSpinner.setEnabled(false);
				updateGoalButton.setEnabled(false);
				cancelGoalButton.setEnabled(false);
			}

		});

		editButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!activeGoalTextBox.getText().isBlank()) {

					activeGoalTextBox.setEditable(true);
					activeDescriptionTextArea.setEditable(true);
					activeAmountSavedSpinner.setEnabled(true);
					activeTargetAmountSpinner.setEnabled(true);
					updateGoalButton.setEnabled(true);
					cancelGoalButton.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Nothing to Edit!");
				}

			}
		});

		cancelGoalButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				activeGoalTextBox.setEditable(false);
				activeDescriptionTextArea.setEditable(false);
				activeAmountSavedSpinner.setEnabled(false);
				activeTargetAmountSpinner.setEnabled(false);
				updateGoalButton.setEnabled(false);
				cancelGoalButton.setEnabled(false);
			}
		});

		goal1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!goal1.getText().isBlank()) {
					addAmountButton.setEnabled(true);

					try {

						Connection con = DbConnection.getConnection();
						pst = con.prepareStatement("select * from goals where Username_fk=? AND Goal_Name=?");
						pst.setString(1, username);
						pst.setString(2, goal1.getText());
						ResultSet rs = pst.executeQuery();
						if (rs.next()) {
							String goal = rs.getString("Goal_Name");
							activeGoalTextBox.setText(goal);
							textField.setText(goal);

							double tAmount = rs.getDouble("Goal_Target_Amount");
							activeTargetAmountSpinner.setValue(tAmount);

							double sAmount = rs.getDouble("Goal_Amount_Saved");
							activeAmountSavedSpinner.setValue(sAmount);

							Date date = rs.getDate("Goal_Target_Date");
							targetDateChooserActive.setDate(date);

							String desc = rs.getString("Goal_Description");
							activeDescriptionTextArea.setText(desc);

							double avg = weekAvg(targetDateChooserActive, activeTargetAmountSpinner,
									activeAmountSavedSpinner);

							activeEstWeeklySavingLabel.setText("$ " + avg);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					addAmountButton.setEnabled(false);
					textField.setText("");
					targetDateChooserActive.setDate(null);
					activeGoalTextBox.setText("");
					activeDescriptionTextArea.setText("");
					activeAmountSavedSpinner.setValue(0);
					activeTargetAmountSpinner.setValue(0);
					activeEstWeeklySavingLabel.setText("0");

				}

			}
		});

		goal2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!goal2.getText().isBlank()) {
					addAmountButton.setEnabled(true);

					try {

						Connection con = DbConnection.getConnection();
						pst = con.prepareStatement("select * from goals where Username_fk=? AND Goal_Name=?");
						pst.setString(1, username);
						pst.setString(2, goal2.getText());
						ResultSet rs = pst.executeQuery();
						if (rs.next()) {
							String goal = rs.getString("Goal_Name");
							activeGoalTextBox.setText(goal);
							textField.setText(goal);

							double tAmount = rs.getDouble("Goal_Target_Amount");
							activeTargetAmountSpinner.setValue(tAmount);

							double sAmount = rs.getDouble("Goal_Amount_Saved");
							activeAmountSavedSpinner.setValue(sAmount);

							Date date = rs.getDate("Goal_Target_Date");
							targetDateChooserActive.setDate(date);

							String desc = rs.getString("Goal_Description");
							activeDescriptionTextArea.setText(desc);

							double avg = weekAvg(targetDateChooserActive, activeTargetAmountSpinner,
									activeAmountSavedSpinner);

							activeEstWeeklySavingLabel.setText("$" + avg);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				else {

					addAmountButton.setEnabled(false);
					textField.setText("");
					targetDateChooserActive.setDate(null);
					activeGoalTextBox.setText("");
					activeDescriptionTextArea.setText("");
					activeAmountSavedSpinner.setValue(0);
					activeTargetAmountSpinner.setValue(0);
					activeEstWeeklySavingLabel.setText("0");

				}

			}
		});

		goal3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!goal3.getText().isBlank()) {
					addAmountButton.setEnabled(true);
					try {

						Connection con = DbConnection.getConnection();
						pst = con.prepareStatement("select * from goals where Username_fk=? AND Goal_Name=?");
						pst.setString(1, username);
						pst.setString(2, goal3.getText());
						ResultSet rs = pst.executeQuery();
						if (rs.next()) {
							String goal = rs.getString("Goal_Name");
							activeGoalTextBox.setText(goal);
							textField.setText(goal);

							double tAmount = rs.getDouble("Goal_Target_Amount");
							activeTargetAmountSpinner.setValue(tAmount);

							double sAmount = rs.getDouble("Goal_Amount_Saved");
							activeAmountSavedSpinner.setValue(sAmount);

							Date date = rs.getDate("Goal_Target_Date");
							targetDateChooserActive.setDate(date);

							String desc = rs.getString("Goal_Description");
							activeDescriptionTextArea.setText(desc);

							double avg = weekAvg(targetDateChooserActive, activeTargetAmountSpinner,
									activeAmountSavedSpinner);

							activeEstWeeklySavingLabel.setText("$" + avg);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				else {

					addAmountButton.setEnabled(false);
					textField.setText("");
					targetDateChooserActive.setDate(null);
					activeGoalTextBox.setText("");
					activeDescriptionTextArea.setText("");
					activeAmountSavedSpinner.setValue(0);
					activeTargetAmountSpinner.setValue(0);
					activeEstWeeklySavingLabel.setText("0");

				}

			}
		});

		goal4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!goal4.getText().isBlank()) {
					addAmountButton.setEnabled(true);
					try {

						Connection con = DbConnection.getConnection();
						pst = con.prepareStatement("select * from goals where Username_fk=? AND Goal_Name=?");
						pst.setString(1, username);
						pst.setString(2, goal4.getText());
						ResultSet rs = pst.executeQuery();
						if (rs.next()) {
							String goal = rs.getString("Goal_Name");
							activeGoalTextBox.setText(goal);
							textField.setText(goal);

							double tAmount = rs.getDouble("Goal_Target_Amount");
							activeTargetAmountSpinner.setValue(tAmount);

							double sAmount = rs.getDouble("Goal_Amount_Saved");
							activeAmountSavedSpinner.setValue(sAmount);

							Date date = rs.getDate("Goal_Target_Date");
							targetDateChooserActive.setDate(date);

							String desc = rs.getString("Goal_Description");
							activeDescriptionTextArea.setText(desc);

							double avg = weekAvg(targetDateChooserActive, activeTargetAmountSpinner,
									activeAmountSavedSpinner);

							activeEstWeeklySavingLabel.setText("$" + avg);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				else {

					addAmountButton.setEnabled(false);
					textField.setText("");
					targetDateChooserActive.setDate(null);
					activeGoalTextBox.setText("");
					activeDescriptionTextArea.setText("");
					activeAmountSavedSpinner.setValue(0);
					activeTargetAmountSpinner.setValue(0);
					activeEstWeeklySavingLabel.setText("0");

				}

			}
		});

		goal5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!goal5.getText().isBlank()) {
					addAmountButton.setEnabled(true);
					try {

						Connection con = DbConnection.getConnection();
						pst = con.prepareStatement("select * from goals where Username_fk=? AND Goal_Name=?");
						pst.setString(1, username);
						pst.setString(2, goal5.getText());
						ResultSet rs = pst.executeQuery();
						if (rs.next()) {
							String goal = rs.getString("Goal_Name");
							activeGoalTextBox.setText(goal);
							textField.setText(goal);

							double tAmount = rs.getDouble("Goal_Target_Amount");
							activeTargetAmountSpinner.setValue(tAmount);

							double sAmount = rs.getDouble("Goal_Amount_Saved");
							activeAmountSavedSpinner.setValue(sAmount);

							Date date = rs.getDate("Goal_Target_Date");
							targetDateChooserActive.setDate(date);

							String desc = rs.getString("Goal_Description");
							activeDescriptionTextArea.setText(desc);

							double avg = weekAvg(targetDateChooserActive, activeTargetAmountSpinner,
									activeAmountSavedSpinner);

							activeEstWeeklySavingLabel.setText("$" + avg);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				else {
					addAmountButton.setEnabled(false);
					textField.setText("");
					targetDateChooserActive.setDate(null);
					activeGoalTextBox.setText("");
					activeDescriptionTextArea.setText("");
					activeAmountSavedSpinner.setValue(0);
					activeTargetAmountSpinner.setValue(0);
					activeEstWeeklySavingLabel.setText("0");

				}

			}
		});
		
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					JFileChooser filechooser = new JFileChooser();
					filechooser.showDialog(overviewPanel, "Export");
					File export = filechooser.getSelectedFile();
					if (export != null) {

						export = new File(export.toString() + ".xlsx");
						Workbook wb = new XSSFWorkbook();
						Sheet sheet = wb.createSheet("Goals");

						Row rowCol = sheet.createRow(0);
						for (int i = 0; i < table.getColumnCount(); i++) {
							Cell cell = rowCol.createCell(i);
							cell.setCellValue(table.getColumnName(i).toString());
						}

						for (int j = 0; j < table.getRowCount(); j++) {
							Row row = sheet.createRow(j + 1);
							for (int k = 0; k < table.getColumnCount(); k++) {
								Cell cell = row.createCell(k);
								if (table.getValueAt(j, k) != null) {
									cell.setCellValue(table.getValueAt(j, k).toString());
								}
							}
						}

						FileOutputStream out = new FileOutputStream(new File(export.toString()));
						wb.write(out);
						wb.close();
						out.close();
						JOptionPane.showMessageDialog(null, "Goals have been Exported!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "An Error has Occured!");
				}
			}
		});
		/*********************************************************************************************/

	}

	// Method to calculate weekly average savings
	/*********************************************************************************************/
	public static double weekAvg(Component date, Component targetAmount, Component savingAmount) {

		double targSpinner = (double) ((JSpinner) targetAmount).getValue();
		double savSpinner = (double) ((JSpinner) savingAmount).getValue();

		if (((JDateChooser) date).getDateEditor().getDate() != null && targSpinner != 0) {

			java.sql.Date targDate = new java.sql.Date(((JDateChooser) date).getDate().getTime());

			LocalDate localDate = LocalDate.now();

			String d1 = targDate.toString();
			String d2 = localDate.toString();

			LocalDate date1 = LocalDate.parse(d1);
			LocalDate date2 = LocalDate.parse(d2);

			double numDays = ChronoUnit.DAYS.between(date2, date1);

			double numWeeks = Math.round(numDays / 7);

			double newTotal = targSpinner - savSpinner;

			double weeklySaving = Math.round(newTotal / numWeeks);

			return weeklySaving;

		}

		return 0;

	}

	// Method to simulate mouse click
	/*********************************************************************************************/
	private static void click(Component target, int x, int y) {
		MouseEvent press, release, click;
		Point point;
		long time;

		point = new Point(x, y);

		SwingUtilities.convertPointToScreen(point, target);

		time = System.currentTimeMillis();
		press = new MouseEvent(target, MouseEvent.MOUSE_PRESSED, time, 0, x, y, point.x, point.y, 1, false,
				MouseEvent.BUTTON1);
		release = new MouseEvent(target, MouseEvent.MOUSE_RELEASED, time, 0, x, y, point.x, point.y, 1, false,
				MouseEvent.BUTTON1);
		click = new MouseEvent(target, MouseEvent.MOUSE_CLICKED, time, 0, x, y, point.x, point.y, 1, false,
				MouseEvent.BUTTON1);

		target.dispatchEvent(press);
		target.dispatchEvent(release);
		target.dispatchEvent(click);
	}

	// Method to generate table cells from database
	/*********************************************************************************************/
	void ShowData(String username) throws SQLException {

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");

		model.addColumn("Target Date");

		model.addColumn("Name");

		model.addColumn("Description");

		model.addColumn("Amount Saved");

		model.addColumn("Target Amount");

		try {
			con = DbConnection.getConnection();
			pst = con.prepareStatement("select * from goals where Username_fk=?");
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString("GoalID"),

						rs.getString("Goal_Target_Date"),

						rs.getString("Goal_Name"),

						rs.getString("Goal_Description"),

						rs.getString("Goal_Amount_Saved"),

						rs.getString("Goal_Target_Amount"), });

			}

			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(150);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}
