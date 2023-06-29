/* Author: Jordan Arroyo
 * Date: 2/13/22
 * Filename: ExpensePanel.java 
 */

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;
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
public class ExpensePanel extends JPanel {

	private JTextField expensenameField;
	private JTable table;

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField nameTextField;

	@SuppressWarnings("removal")
	public ExpensePanel(String user) {
		String username = user;

		// Set Layout for add expense panel
		/*********************************************************************************************/
		setLayout(null);
		setBounds(0, 0, 800, 569);

		// Create tabbed pane for add expense tab
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane_1.setBounds(0, 0, 993, 640);
		add(tabbedPane_1);
		tabbedPane_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		JPanel addExpensePanel = new JPanel();

		addExpensePanel.setBorder(null);
		addExpensePanel.setFocusable(true);
		tabbedPane_1.addTab("Add Expense", new ImageIcon(ExpensePanel.class.getResource("/img/newAdd.png")),
				addExpensePanel, null);
		addExpensePanel.setLayout(null);
		/*********************************************************************************************/

		// Add Labels for add expense
		/*********************************************************************************************/
		JLabel addNewExpenseLabel = new JLabel("Add New Expense");
		addNewExpenseLabel.setBounds(10, 11, 220, 32);
		addNewExpenseLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		addExpensePanel.add(addNewExpenseLabel);

		JLabel dateLabel = new JLabel("Date*");
		dateLabel.setBounds(170, 90, 130, 26);
		dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		addExpensePanel.add(dateLabel);

		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		descriptionLabel.setBounds(10, 237, 290, 26);
		addExpensePanel.add(descriptionLabel);

		JLabel expenseNameLabel = new JLabel("Expense Name");
		expenseNameLabel.setBounds(10, 90, 140, 26);
		expenseNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		addExpensePanel.add(expenseNameLabel);

		JLabel categoryLabel = new JLabel("Category*");
		categoryLabel.setBounds(11, 165, 149, 23);
		categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		addExpensePanel.add(categoryLabel);

		JLabel amountLabel = new JLabel("Amount*");
		amountLabel.setBounds(170, 165, 171, 23);
		amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		addExpensePanel.add(amountLabel);

		JLabel asterikLabel = new JLabel("* = Required");
		asterikLabel.setBounds(530, 20, 114, 21);
		asterikLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		addExpensePanel.add(asterikLabel);

		JLabel addExpenseLogoLabel = new JLabel("");
		addExpenseLogoLabel.setBounds(10, 392, 105, 117);
		addExpenseLogoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addExpenseLogoLabel.setIcon(new ImageIcon(ExpensePanel.class.getResource("/img/128.png")));
		addExpensePanel.add(addExpenseLogoLabel);

		JLabel currencyLabel = new JLabel("USD");
		currencyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		currencyLabel.setBounds(315, 196, 48, 21);
		addExpensePanel.add(currencyLabel);

		JLabel paymentMethodLabel = new JLabel("Payment Method*");
		paymentMethodLabel.setBounds(498, 92, 159, 23);
		paymentMethodLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		addExpensePanel.add(paymentMethodLabel);

		JLabel accountNameLabel = new JLabel("Account Name*");
		accountNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		accountNameLabel.setBounds(331, 90, 157, 26);
		addExpensePanel.add(accountNameLabel);
		/*********************************************************************************************/

		// Add TextFields, Buttons, Combo boxes, Date Chooser, Spinner for add expense
		/*********************************************************************************************/
		JComboBox<String> categoryComboBox = new JComboBox<String>();
		categoryComboBox.setMaximumRowCount(25);
		categoryComboBox.setBounds(10, 189, 145, 35);
		categoryComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		categoryComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "-Select One-", "Auto", "Education",
				"Entertainment", "Family", "Gift", "Grocery", "Health", "Home", "Utility", "Other" }));
		categoryComboBox.setEditable(true);
		addExpensePanel.add(categoryComboBox);

		expensenameField = new JTextField();
		expensenameField.setBounds(10, 117, 140, 35);
		expensenameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		addExpensePanel.add(expensenameField);
		expensenameField.setColumns(10);

		JTextArea descriptionTextArea = new JTextArea();
		descriptionTextArea.setBounds(10, 264, 430, 117);
		descriptionTextArea.setBackground(Color.decode("#E5E5E5"));
		descriptionTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		addExpensePanel.add(descriptionTextArea);

		JComboBox<String> paymentMethodComboBox = new JComboBox<String>();
		paymentMethodComboBox.setBounds(500, 117, 145, 35);
		paymentMethodComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		paymentMethodComboBox
				.setModel(new DefaultComboBoxModel<String>(new String[] { "-Select One-", "Cash", "Debit", "Credit" }));
		addExpensePanel.add(paymentMethodComboBox);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(170, 117, 140, 35);
		dateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		addExpensePanel.add(dateChooser);

		JButton commitButton = new JButton("Commit");
		commitButton.setForeground(Color.BLUE);
		commitButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		commitButton.setBounds(465, 475, 100, 35);
		addExpensePanel.add(commitButton);

		JButton resetFieldsButton = new JButton("Reset");
		resetFieldsButton.setForeground(Color.RED);
		resetFieldsButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		resetFieldsButton.setBounds(575, 475, 100, 35);
		addExpensePanel.add(resetFieldsButton);

		JComboBox<String> box = new JComboBox<String>();
		box.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		MutableComboBoxModel<String> accComboBox = (DefaultComboBoxModel<String>) box.getModel();
		accComboBox.addElement("-Select One-");
		try {

			Connection con = DbConnection.getConnection();
			pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				accComboBox.addElement(rs.getString("Account_Name"));

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		box.setBounds(331, 117, 145, 35);
		addExpensePanel.add(box);

		JSpinner amountSpinner = new JSpinner();
		amountSpinner.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		amountSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		amountSpinner.setBounds(170, 189, 130, 35);
		addExpensePanel.add(amountSpinner);

		/*********************************************************************************************/

		// Set Action Listener for Commit and Reset Buttons for add expense
		/*********************************************************************************************/
		commitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double neg = (double) amountSpinner.getValue();
				double negAmount = neg * (-1);
				try {

					String sql = "INSERT INTO expense(Expense_Amount, Expense_Description, Category_Name_fk, "
							+ "Name, Payment_Method_fk, Expense_Date, Account_Name_fk, Username_fk) VALUES (?,?,?,?,?,?,?,?)";
					con = DbConnection.getConnection();
					pst = con.prepareStatement(sql);
					pst.setDouble(1, negAmount);
					pst.setString(2, descriptionTextArea.getText());
					pst.setString(3, categoryComboBox.getSelectedItem().toString());
					pst.setString(4, expensenameField.getText());
					pst.setString(5, paymentMethodComboBox.getSelectedItem().toString());
					dateChooser.getDate().getTime();
					java.sql.Date date = new java.sql.Date(dateChooser.getDate().getTime());
					pst.setDate(6, date);
					pst.setString(7, accComboBox.getSelectedItem().toString());
					pst.setString(8, username);

					pst.executeUpdate();

					pst.close();
					JOptionPane.showMessageDialog(null, "Expense has been added!");
					ShowData(username);
				} catch (Exception exe) {
					JOptionPane.showMessageDialog(null,
							"Invalid or Empty Fields Found! \nRequired Fields are: Date, Category, Payment Method and Amount "
									+ "\nVerify those fields and Try Again.");
				}
			}
		});

		resetFieldsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryComboBox.setSelectedIndex(0);
				expensenameField.setText("");
				amountSpinner.setValue(0);
				descriptionTextArea.setText("");
				paymentMethodComboBox.setSelectedIndex(0);
				dateChooser.setDate(null);
			}
		});
		/*********************************************************************************************/

		// Add new panel for view/modify expenses
		/*********************************************************************************************/
		JPanel removeExpensePanel = new JPanel();
		removeExpensePanel.setBorder(null);
		tabbedPane_1.addTab("View / Modify Expenses",
				new ImageIcon(ExpensePanel.class.getResource("/img/newModify.png")), removeExpensePanel, null);
		removeExpensePanel.setLayout(null);

		JLabel modifyDateLabel = new JLabel("Date");
		modifyDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyDateLabel.setBounds(239, 260, 64, 27);
		removeExpensePanel.add(modifyDateLabel);

		JLabel modifyNameLabel = new JLabel("Name");
		modifyNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyNameLabel.setBounds(99, 260, 97, 27);
		removeExpensePanel.add(modifyNameLabel);

		JLabel modifyDescriptionLabel = new JLabel("Description");
		modifyDescriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyDescriptionLabel.setBounds(125, 396, 104, 26);
		removeExpensePanel.add(modifyDescriptionLabel);

		JLabel modifyCatLabel = new JLabel("Category");
		modifyCatLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyCatLabel.setBounds(84, 323, 77, 27);
		removeExpensePanel.add(modifyCatLabel);

		JLabel modifyAccountLabel = new JLabel("Account");
		modifyAccountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyAccountLabel.setBounds(394, 323, 72, 27);
		removeExpensePanel.add(modifyAccountLabel);

		JLabel modifyPayLabel = new JLabel("Pay Method");
		modifyPayLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyPayLabel.setBounds(239, 323, 97, 27);
		removeExpensePanel.add(modifyPayLabel);

		JLabel modifyAmountLabel = new JLabel("Amount");
		modifyAmountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyAmountLabel.setBounds(394, 261, 72, 27);
		removeExpensePanel.add(modifyAmountLabel);

		JLabel ExpenseIDLabel = new JLabel("ID");
		ExpenseIDLabel.setBounds(10, 260, 64, 27);
		removeExpensePanel.add(ExpenseIDLabel);
		ExpenseIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		JLabel incomeCurrencyLabel = new JLabel("USD");
		incomeCurrencyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeCurrencyLabel.setBounds(531, 290, 44, 21);
		removeExpensePanel.add(incomeCurrencyLabel);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textArea.setBounds(125, 420, 433, 89);
		removeExpensePanel.add(textArea);

		JLabel lblModifyExpenses = new JLabel("Modify Expenses");
		lblModifyExpenses.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblModifyExpenses.setBounds(10, 11, 186, 26);
		removeExpensePanel.add(lblModifyExpenses);

		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		nameTextField.setBounds(99, 283, 130, 35);
		removeExpensePanel.add(nameTextField);
		nameTextField.setColumns(10);
		/*********************************************************************************************/

		// Add Buttons, Table, spinner and combobox for view/modify expenses
		/*********************************************************************************************/
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 680, 203);
		scrollPane.setBorder(new LineBorder(Color.BLUE, 3));
		removeExpensePanel.add(scrollPane);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		JButton refreshExpButton = new JButton("Refresh");
		refreshExpButton.setForeground(Color.BLUE);
		refreshExpButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		refreshExpButton.setBounds(590, 429, 100, 35);
		removeExpensePanel.add(refreshExpButton);

		JComboBox<String> comboBoxCat = new JComboBox<String>();
		comboBoxCat.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		comboBoxCat.setBounds(84, 350, 145, 35);
		comboBoxCat.setModel(new DefaultComboBoxModel<String>(new String[] { "-Select One-", "Auto", "Education",
				"Entertainment", "Family", "Gift", "Grocery", "Health", "Home", "Other" }));
		removeExpensePanel.add(comboBoxCat);

		JComboBox<String> com = new JComboBox<String>();

		com.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		MutableComboBoxModel<String> comboBoxAcc = (DefaultComboBoxModel<String>) com.getModel();
		comboBoxAcc.addElement("-Select One-");
		try {

			Connection con = DbConnection.getConnection();
			pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				comboBoxAcc.addElement(rs.getString("Account_Name"));

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		com.setBounds(394, 350, 145, 35);
		removeExpensePanel.add(com);

		JComboBox<String> comboBoxPayMeth = new JComboBox<String>();
		comboBoxPayMeth.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		comboBoxPayMeth.setBounds(239, 350, 145, 35);
		comboBoxPayMeth
				.setModel(new DefaultComboBoxModel<String>(new String[] { "-Select One-", "Cash", "Debit", "Credit" }));
		removeExpensePanel.add(comboBoxPayMeth);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(239, 283, 140, 35);
		dateChooser_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		removeExpensePanel.add(dateChooser_1);

		JButton updateButton = new JButton("Update");
		updateButton.setForeground(Color.BLUE);
		updateButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		updateButton.setBounds(590, 333, 100, 35);
		removeExpensePanel.add(updateButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setForeground(Color.RED);
		deleteButton.setBounds(590, 477, 100, 35);
		removeExpensePanel.add(deleteButton);
		deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		JSpinner removeAmountSpinner = new JSpinner();
		removeAmountSpinner.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		removeAmountSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		removeAmountSpinner.setBounds(394, 284, 130, 35);
		removeExpensePanel.add(removeAmountSpinner);

		JButton resetButton = new JButton("Reset");
		resetButton.setForeground(Color.BLUE);
		resetButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		resetButton.setBounds(590, 381, 100, 35);
		removeExpensePanel.add(resetButton);

		JSpinner expenseIDSpinner = new JSpinner();
		expenseIDSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		expenseIDSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		expenseIDSpinner.setBounds(10, 284, 79, 35);
		removeExpensePanel.add(expenseIDSpinner);

		JLabel addExpenseLogoLabel_1 = new JLabel("");
		addExpenseLogoLabel_1.setIcon(new ImageIcon(ExpensePanel.class.getResource("/img/128.png")));
		addExpenseLogoLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		addExpenseLogoLabel_1.setBounds(10, 392, 105, 117);
		removeExpensePanel.add(addExpenseLogoLabel_1);

		JButton exportButton = new JButton("Export");
		exportButton.setForeground(Color.GREEN);
		exportButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		exportButton.setBounds(590, 285, 100, 35);
		removeExpensePanel.add(exportButton);

		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					JFileChooser filechooser = new JFileChooser();
					filechooser.showDialog(removeExpensePanel, "Export");
					File export = filechooser.getSelectedFile();
					if (export != null) {

						export = new File(export.toString() + ".xlsx");
						Workbook wb = new XSSFWorkbook();
						Sheet sheet = wb.createSheet("Expenses");

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
						JOptionPane.showMessageDialog(null, "Expenses have been Exported!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "An Error has Occured!");
				}
			}
		});

		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!expenseIDSpinner.getValue().equals(0)) {
					try {

						con = DbConnection.getConnection();

						String neg = removeAmountSpinner.getValue().toString();
						double negAmount = Double.parseDouble(neg);
						double negCon = negAmount * (-1);
						if (negAmount != 0) {
							String sql = "UPDATE expense SET Expense_Amount=? WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							pst.setDouble(1, negCon);
							pst.setString(2, expenseIDSpinner.getValue().toString());
							pst.executeUpdate();
						} else {
							String sql = "SELECT ExpenseID, Expense_Amount FROM expense WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, removeAmountSpinner.getValue().toString());
							ResultSet rs = pst.executeQuery();
							if (rs.next()) {
								String acc = rs.getString("Expense_Amount");
								String sql1 = "UPDATE expense SET Expense_Amount=? WHERE ExpenseID=?";
								pst = con.prepareStatement(sql1);
								pst.setString(1, acc);
								pst.setString(2, expenseIDSpinner.getValue().toString());
								pst.executeUpdate();
							}
						}

						if (!nameTextField.getText().equals("")) {
							String sql = "UPDATE expense SET Name=? WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, nameTextField.getText());
							pst.setString(2, expenseIDSpinner.getValue().toString());
							pst.executeUpdate();
						}

						if (!textArea.getText().equals("")) {
							String sql = "UPDATE expense SET Expense_Description=? WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, textArea.getText());
							pst.setString(2, expenseIDSpinner.getValue().toString());
							pst.executeUpdate();
						}

						if (comboBoxCat.getSelectedIndex() != 0) {
							String sql = "UPDATE expense SET Category_Name_fk=? WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, comboBoxCat.getSelectedItem().toString());
							pst.setString(2, expenseIDSpinner.getValue().toString());
							pst.executeUpdate();
						} else {
							String sql = "SELECT ExpenseID, Category_Name_fk FROM expense WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, expenseIDSpinner.getValue().toString());
							ResultSet rs = pst.executeQuery();
							if (rs.next()) {
								String cat = rs.getString("Category_name_fk");
								String sql1 = "UPDATE expense SET Category_Name_fk=? WHERE ExpenseID=?";
								pst = con.prepareStatement(sql1);
								pst.setString(1, cat);
								pst.setString(2, expenseIDSpinner.getValue().toString());
								pst.executeUpdate();
							}
						}

						if (comboBoxPayMeth.getSelectedIndex() != 0) {
							String sql = "UPDATE expense SET Payment_Method_fk=? WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, comboBoxPayMeth.getSelectedItem().toString());
							pst.setString(2, expenseIDSpinner.getValue().toString());
							pst.executeUpdate();
						} else {
							String sql = "SELECT ExpenseID, Payment_Method_fk FROM expense WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, expenseIDSpinner.getValue().toString());
							ResultSet rs = pst.executeQuery();
							if (rs.next()) {
								String pay = rs.getString("Payment_Method_fk");
								String sql1 = "UPDATE expense SET Payment_Method_fk=? WHERE ExpenseID=?";
								pst = con.prepareStatement(sql1);
								pst.setString(1, pay);
								pst.setString(2, expenseIDSpinner.getValue().toString());
								pst.executeUpdate();
							}
						}

						if (com.getSelectedIndex() != 0) {
							String sql = "UPDATE expense SET Account_Name_fk=? WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, com.getSelectedItem().toString());
							pst.setString(2, expenseIDSpinner.getValue().toString());
							pst.executeUpdate();
						} else {
							String sql = "SELECT ExpenseID, Account_Name_fk FROM expense WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, expenseIDSpinner.getValue().toString());
							ResultSet rs = pst.executeQuery();
							if (rs.next()) {
								String acc = rs.getString("Account_Name_fk");

								String sql1 = "UPDATE expense SET Account_Name_fk=? WHERE ExpenseID=?";
								pst = con.prepareStatement(sql1);
								pst.setString(1, acc);
								pst.setString(2, expenseIDSpinner.getValue().toString());
								pst.executeUpdate();
							}
						}

						if (dateChooser_1.getDateEditor().getDate() != null) {
							String sql = "UPDATE expense SET Expense_Date=? WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							java.sql.Date date1 = new java.sql.Date(dateChooser_1.getDate().getTime());
							pst.setDate(1, date1);
							pst.setString(2, expenseIDSpinner.getValue().toString());
							pst.executeUpdate();
						} else {
							String sql = "SELECT ExpenseID, Expense_Date FROM expense WHERE ExpenseID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, expenseIDSpinner.getValue().toString());
							ResultSet rs = pst.executeQuery();
							if (rs.next()) {
								String day = rs.getString("Expense_Date");
								String sql1 = "UPDATE expense SET Expense_Date=? WHERE ExpenseID=?";
								pst = con.prepareStatement(sql1);
								pst.setString(1, day);
								pst.setString(2, expenseIDSpinner.getValue().toString());
								pst.executeUpdate();
							}
						}
						pst.close();
						JOptionPane.showMessageDialog(null, "Expense has been Updated!");
						ShowData(username);
					} catch (Exception exe) {
						JOptionPane.showMessageDialog(null, "Invalid Entry! Verify Fields and Try again!");
						System.out.print(exe);
					}
				} else
					JOptionPane.showMessageDialog(null, "ExpenseID cannot be 0!");
			}
		});

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxCat.setSelectedIndex(0);
				expenseIDSpinner.setValue((Integer) 0);
				nameTextField.setText("");
				removeAmountSpinner.setValue(0);
				textArea.setText("");
				comboBoxPayMeth.setSelectedIndex(0);
				dateChooser_1.setDate(null);
			}
		});

		refreshExpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ShowData(username);
				} catch (SQLException ext) {
					JOptionPane.showMessageDialog(null, ext);
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Chk 0");
				try {
					con = DbConnection.getConnection();
					String sql = "SELECT * FROM expense WHERE ExpenseID =? AND Username_fk=?";
					pst = con.prepareStatement(sql);
					pst.setString(1, expenseIDSpinner.getValue().toString());
					pst.setString(2, username);
					ResultSet rs = pst.executeQuery();

					if (rs.next()) {
						double next = rs.getDouble("ExpenseID");
						String username = rs.getString("Username_fk");
						String expID = expenseIDSpinner.getValue().toString();
						double exp = Double.parseDouble(expID);
						if (exp == next && username.equals(username)) {

							String sql1 = "DELETE FROM expense WHERE ExpenseID =? AND Username_fk=?";
							pst = con.prepareStatement(sql1);
							pst.setString(1, expenseIDSpinner.getValue().toString());
							pst.setString(2, username);
							pst.executeUpdate();
							pst.close();
							JOptionPane.showMessageDialog(null, "Expense has been Removed!");
							ShowData(username);
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Invalid ExpenseID Entered!\nVerify ExpenseID and Try Again!");
					}

				} catch (Exception exe) {
					JOptionPane.showMessageDialog(null, exe);
				}

			}
		});

		box.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					box.removeAllItems();
					accComboBox.addElement("-Select one-");
					pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
					pst.setString(1, username);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						accComboBox.addElement(rs.getString("Account_Name"));
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		com.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					com.removeAllItems();
					comboBoxAcc.addElement("-Select one-");
					pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
					pst.setString(1, username);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						comboBoxAcc.addElement(rs.getString("Account_Name"));
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		/*********************************************************************************************/
	}

	// Method to simulate mouse click
	/*********************************************************************************************/
	@SuppressWarnings("unused")
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

		model.addColumn("Date");

		model.addColumn("Name");

		model.addColumn("Description");

		model.addColumn("Category");

		model.addColumn("Account");

		model.addColumn("Pay Method");

		model.addColumn("Amount");
		try {
			con = DbConnection.getConnection();
			pst = con.prepareStatement("select * from expense where Username_fk=?");
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString("ExpenseID"),

						rs.getString("Expense_Date"),

						rs.getString("Name"),

						rs.getString("Expense_Description"),

						rs.getString("Category_Name_fk"),

						rs.getString("Account_Name_fk"),

						rs.getString("Payment_Method_fk"),

						rs.getString("Expense_Amount") });
			}

			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(30);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(120);
			table.getColumnModel().getColumn(4).setPreferredWidth(75);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);
			table.getColumnModel().getColumn(6).setPreferredWidth(75);
			table.getColumnModel().getColumn(7).setPreferredWidth(75);

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}