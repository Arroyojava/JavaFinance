package ExpenseGui;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class IncomePanel extends JPanel {

	private JTextField incomeNameTextField;
	private JTextField nameTextFieldEditInc;
	private JTable table;

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	@SuppressWarnings("removal")
	public IncomePanel(String user) {
		String username = user;

		// Set Layout for add income tab
		/*********************************************************************************************/
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setBounds(0, 0, 804, 594);
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		add(tabbedPane);
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 22));

		JPanel panel = new JPanel();
		tabbedPane.addTab("Add Income", new ImageIcon(IncomePanel.class.getResource("/img/newAdd.png")), panel, null);
		panel.setLayout(null);
		/*********************************************************************************************/

		// Add Labels for add income tab
		/*********************************************************************************************/
		JLabel addNewIncomeLabel = new JLabel("Add New Income");
		addNewIncomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		addNewIncomeLabel.setBounds(10, 11, 186, 32);
		panel.add(addNewIncomeLabel);

		JLabel incomeNameLabel = new JLabel("Income Name");
		incomeNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeNameLabel.setBounds(10, 90, 121, 26);
		panel.add(incomeNameLabel);

		JLabel incomedateLabel = new JLabel("Date*");
		incomedateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomedateLabel.setBounds(160, 90, 130, 26);
		panel.add(incomedateLabel);

		JLabel incomeDescriptionLabel = new JLabel("Description");
		incomeDescriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeDescriptionLabel.setBounds(10, 237, 290, 26);
		panel.add(incomeDescriptionLabel);

		JLabel addIncomeLogoLabel = new JLabel("");
		addIncomeLogoLabel.setIcon(new ImageIcon(IncomePanel.class.getResource("/img/128.png")));
		addIncomeLogoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addIncomeLogoLabel.setBounds(10, 392, 105, 117);
		panel.add(addIncomeLogoLabel);

		JLabel asterikLabel = new JLabel("* = Required");
		asterikLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		asterikLabel.setBounds(530, 20, 121, 21);
		panel.add(asterikLabel);

		JLabel incomeAccountNameLabel = new JLabel("Account Name*");
		incomeAccountNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeAccountNameLabel.setBounds(310, 90, 157, 26);
		panel.add(incomeAccountNameLabel);

		JLabel incomeAmountLabel = new JLabel("Amount*");
		incomeAmountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeAmountLabel.setBounds(11, 165, 171, 23);
		panel.add(incomeAmountLabel);

		JLabel incomeCurrencyLabel = new JLabel("USD");
		incomeCurrencyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeCurrencyLabel.setBounds(150, 196, 46, 21);
		panel.add(incomeCurrencyLabel);
		/*********************************************************************************************/

		// Add Buttons, TextFields, Spinners, ComboBox, Date Chooser, ScrollPane for add
		// income tab
		/*********************************************************************************************/

		incomeNameTextField = new JTextField();
		incomeNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeNameTextField.setColumns(10);
		incomeNameTextField.setBounds(10, 117, 140, 35);
		panel.add(incomeNameTextField);

		JDateChooser incomeDateChooser = new JDateChooser();
		incomeDateChooser.setBounds(160, 117, 140, 35);
		incomeDateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel.add(incomeDateChooser);

		JTextArea incomeDescriptionTextArea = new JTextArea();
		incomeDescriptionTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		incomeDescriptionTextArea.setBackground(new Color(229, 229, 229));
		incomeDescriptionTextArea.setBounds(10, 264, 430, 117);
		panel.add(incomeDescriptionTextArea);

		JComboBox<String> inc = new JComboBox<String>();
		inc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		MutableComboBoxModel<String> incomeBox = (DefaultComboBoxModel<String>) inc.getModel();
		incomeBox.addElement("-Select one-");
		try {

			Connection con = DbConnection.getConnection();
			pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				incomeBox.addElement(rs.getString("Account_Name"));

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		inc.setBounds(310, 117, 145, 35);
		panel.add(inc);

		JSpinner incomeAmountSpinner = new JSpinner();
		incomeAmountSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		incomeAmountSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeAmountSpinner.setBounds(10, 189, 130, 35);
		panel.add(incomeAmountSpinner);

		JButton incomeCommitButton = new JButton("Commit");
		incomeCommitButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeCommitButton.setForeground(Color.BLUE);
		incomeCommitButton.setBounds(465, 475, 100, 35);
		panel.add(incomeCommitButton);

		JButton incomeResetFieldsButton = new JButton("Reset");
		incomeResetFieldsButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeResetFieldsButton.setForeground(Color.RED);
		incomeResetFieldsButton.setBounds(575, 475, 100, 35);
		panel.add(incomeResetFieldsButton);
		/*********************************************************************************************/

		// Add Action Listeners for add income tab
		/*********************************************************************************************/
		incomeCommitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String sql = "INSERT INTO income(Income_Amount, Income_Description, Name, Income_Date, Account_Name_fk, Username_fk) VALUES (?,?,?,?,?,?)";
					con = DbConnection.getConnection();
					pst = con.prepareStatement(sql);
					pst.setString(1, incomeAmountSpinner.getValue().toString());
					pst.setString(2, incomeDescriptionTextArea.getText());
					pst.setString(3, incomeNameTextField.getText());
					incomeDateChooser.getDate().getTime();
					java.sql.Date date = new java.sql.Date(incomeDateChooser.getDate().getTime());
					pst.setDate(4, date);
					pst.setString(5, inc.getSelectedItem().toString());
					pst.setString(6, username);

					pst.executeUpdate();

					pst.close();
					JOptionPane.showMessageDialog(null, "Income has been added!");
					ShowData(username);
				} catch (Exception exe) {
					JOptionPane.showMessageDialog(null,
							"Invalid or Empty Fields Found! \nRequired Fields are: Date, Amount, and Account Name \nVerify those fields and Try Again.");
					exe.printStackTrace();
				}

			}
		});

		incomeResetFieldsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				incomeAmountSpinner.setValue((double) 0);
				incomeDescriptionTextArea.setText("");
				incomeNameTextField.setText("");
				incomeDateChooser.setDate(null);
				inc.setSelectedIndex(0);

			}
		});

		// Set Layout for view/modify income panel
		/*********************************************************************************************/
		JPanel modifyIncomePanel = new JPanel();
		tabbedPane.addTab("View / Modify Incomes", new ImageIcon(IncomePanel.class.getResource("/img/newModify.png")),
				modifyIncomePanel, null);
		modifyIncomePanel.setLayout(null);

		JLabel lblModifyIncome = new JLabel("Modify Incomes");
		lblModifyIncome.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblModifyIncome.setBounds(10, 11, 186, 26);
		modifyIncomePanel.add(lblModifyIncome);

		JLabel incomeIDLabel = new JLabel("ID");
		incomeIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeIDLabel.setBounds(20, 260, 64, 27);
		modifyIncomePanel.add(incomeIDLabel);

		JLabel modifyNameLabel = new JLabel("Name");
		modifyNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyNameLabel.setBounds(123, 260, 97, 27);
		modifyIncomePanel.add(modifyNameLabel);

		JLabel modifyDateLabel = new JLabel("Date");
		modifyDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyDateLabel.setBounds(263, 260, 64, 27);
		modifyIncomePanel.add(modifyDateLabel);

		JLabel modifyAccountLabel = new JLabel("Account");
		modifyAccountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyAccountLabel.setBounds(413, 260, 72, 27);
		modifyIncomePanel.add(modifyAccountLabel);

		JLabel modifyAmountLabel = new JLabel("Amount");
		modifyAmountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyAmountLabel.setBounds(125, 326, 72, 27);
		modifyIncomePanel.add(modifyAmountLabel);

		JLabel incomeCurrencyLabel_1 = new JLabel("USD");
		incomeCurrencyLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeCurrencyLabel_1.setBounds(265, 357, 42, 21);
		modifyIncomePanel.add(incomeCurrencyLabel_1);

		JLabel addIncomeLogoLabel_1 = new JLabel("");
		addIncomeLogoLabel_1.setIcon(new ImageIcon(IncomePanel.class.getResource("/img/128.png")));
		addIncomeLogoLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		addIncomeLogoLabel_1.setBounds(10, 392, 105, 117);
		modifyIncomePanel.add(addIncomeLogoLabel_1);

		JLabel modifyDescriptionLabel = new JLabel("Description");
		modifyDescriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyDescriptionLabel.setBounds(125, 396, 104, 26);
		modifyIncomePanel.add(modifyDescriptionLabel);
		/*********************************************************************************************/

		// Add Buttons, TextFields, Spinners, ComboBox, Date Chooser, ScrollPane, Table
		// for view/modify income panel
		/*********************************************************************************************/

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 680, 203);
		scrollPane.setBorder(new LineBorder(Color.BLUE, 3));
		modifyIncomePanel.add(scrollPane);
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		JComboBox<String> incMod = new JComboBox<String>();
		incMod.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		MutableComboBoxModel<String> comboBoxEditInc = (DefaultComboBoxModel<String>) incMod.getModel();
		comboBoxEditInc.addElement("-Select one-");
		try {

			Connection con = DbConnection.getConnection();
			pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				comboBoxEditInc.addElement(rs.getString("Account_Name"));

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		incMod.setBounds(413, 283, 145, 35);
		modifyIncomePanel.add(incMod);

		nameTextFieldEditInc = new JTextField();
		nameTextFieldEditInc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		nameTextFieldEditInc.setColumns(10);
		nameTextFieldEditInc.setBounds(123, 283, 130, 35);
		modifyIncomePanel.add(nameTextFieldEditInc);

		JSpinner modifyAmountSpinner = new JSpinner();
		modifyAmountSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyAmountSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		modifyAmountSpinner.setBounds(125, 350, 130, 35);
		modifyIncomePanel.add(modifyAmountSpinner);

		JTextArea textAreaEditInc = new JTextArea();
		textAreaEditInc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textAreaEditInc.setBounds(125, 420, 433, 89);
		modifyIncomePanel.add(textAreaEditInc);

		JDateChooser dateChooserEditInc = new JDateChooser();
		dateChooserEditInc.setBounds(263, 283, 140, 35);
		dateChooserEditInc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modifyIncomePanel.add(dateChooserEditInc);

		JSpinner incomeIDSpinner = new JSpinner();
		incomeIDSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		incomeIDSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		incomeIDSpinner.setBounds(20, 283, 79, 35);
		modifyIncomePanel.add(incomeIDSpinner);

		JButton updateButton = new JButton("Update");

		updateButton.setForeground(Color.BLUE);
		updateButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		updateButton.setBounds(590, 333, 100, 35);
		modifyIncomePanel.add(updateButton);

		JButton resetButton = new JButton("Reset");
		resetButton.setForeground(Color.BLUE);
		resetButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		resetButton.setBounds(590, 381, 100, 35);
		modifyIncomePanel.add(resetButton);

		JButton refreshExpButton = new JButton("Refresh");

		refreshExpButton.setForeground(Color.BLUE);
		refreshExpButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		refreshExpButton.setBounds(590, 429, 100, 35);
		modifyIncomePanel.add(refreshExpButton);

		JButton deleteButton = new JButton("Delete");

		deleteButton.setForeground(Color.RED);
		deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		deleteButton.setBounds(590, 477, 100, 35);
		modifyIncomePanel.add(deleteButton);

		JButton exportButton = new JButton("Export");
		exportButton.setForeground(Color.GREEN);
		exportButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		exportButton.setBounds(590, 285, 100, 35);
		modifyIncomePanel.add(exportButton);

		/*********************************************************************************************/

		// Add Action Listeners for view/Modify income tab
		/*********************************************************************************************/

		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					JFileChooser filechooser = new JFileChooser();
					filechooser.showDialog(modifyIncomePanel, "Export");
					File export = filechooser.getSelectedFile();
					if (export != null) {

						export = new File(export.toString() + ".xlsx");
						Workbook wb = new XSSFWorkbook();
						Sheet sheet = wb.createSheet("Income");

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
						JOptionPane.showMessageDialog(null, "Incomes have been Exported!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "An Error has Occured!");
				}
			}
		});

		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!incomeIDSpinner.getValue().equals(0)) {
					try {
						con = DbConnection.getConnection();

						String amount = modifyAmountSpinner.getValue().toString();
						double a = Double.parseDouble(amount);
						if (a != 0) {
							String sql = "UPDATE income SET Income_Amount=? WHERE IncomeID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, modifyAmountSpinner.getValue().toString());
							pst.setString(2, incomeIDSpinner.getValue().toString());

							pst.executeUpdate();

						} else {
							String sql = "SELECT IncomeID, Income_Amount FROM income WHERE IncomeID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, incomeIDSpinner.getValue().toString());
							ResultSet rs = pst.executeQuery();
							if (rs.next()) {
								String acc = rs.getString("Income_Amount");
								String sql1 = "UPDATE income SET Income_Amount=? WHERE IncomeID=?";
								pst = con.prepareStatement(sql1);
								pst.setString(1, acc);
								pst.setString(2, incomeIDSpinner.getValue().toString());
								pst.executeUpdate();
							}
						}

						if (!nameTextFieldEditInc.getText().equals("")) {
							String sql = "UPDATE income SET Name=? WHERE IncomeID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, nameTextFieldEditInc.getText());
							pst.setString(2, incomeIDSpinner.getValue().toString());
							pst.executeUpdate();
						}

						if (!textAreaEditInc.getText().isBlank()) {
							String sql = "UPDATE income SET Income_Description=? WHERE IncomeID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, textAreaEditInc.getText());
							pst.setString(2, incomeIDSpinner.getValue().toString());
							pst.executeUpdate();
						}

						if (incMod.getSelectedIndex() != 0) {
							String sql = "UPDATE income SET Account_Name_fk=? WHERE IncomeID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, incMod.getSelectedItem().toString());
							pst.setString(2, incomeIDSpinner.getValue().toString());
							pst.executeUpdate();
						} else {
							String sql = "SELECT IncomeID, Account_Name_fk FROM income WHERE IncomeID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, incomeIDSpinner.getValue().toString());
							ResultSet rs = pst.executeQuery();
							if (rs.next()) {
								String acc = rs.getString("Account_Name_fk");

								String sql1 = "UPDATE income SET Account_Name_fk=? WHERE IncomeID=?";
								pst = con.prepareStatement(sql1);
								pst.setString(1, acc);
								pst.setString(2, incomeIDSpinner.getValue().toString());
								pst.executeUpdate();
							}
						}

						if (dateChooserEditInc.getDateEditor().getDate() != null) {
							String sql = "UPDATE income SET Income_Date=? WHERE IncomeID=?";
							pst = con.prepareStatement(sql);
							java.sql.Date date1 = new java.sql.Date(dateChooserEditInc.getDate().getTime());
							pst.setDate(1, date1);
							pst.setString(2, incomeIDSpinner.getValue().toString());

							pst.executeUpdate();

						} else {

							String sql = "SELECT IncomeID, Income_Date FROM income WHERE IncomeID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, incomeIDSpinner.getValue().toString());
							ResultSet rs = pst.executeQuery();

							if (rs.next()) {
								String day = rs.getString("Income_Date");

								String sql1 = "UPDATE income SET Income_Date=? WHERE IncomeID=?";
								pst = con.prepareStatement(sql1);
								pst.setString(1, day);
								pst.setString(2, incomeIDSpinner.getValue().toString());
								pst.executeUpdate();
							}
						}

						// pst.close();
						JOptionPane.showMessageDialog(null, "Income has been Updated!");
						ShowData(username);

					} catch (Exception exe) {
						JOptionPane.showMessageDialog(null, "Invalid Entry! Verify Fields and Try again!");
						System.out.print(exe);

					}
					try {
						pst.close();
						// con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "IncomeID cannot be 0!");

			}
		});

		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					con = DbConnection.getConnection();

					String sql = "SELECT * FROM income WHERE IncomeID =? AND Username_fk=?";
					pst = con.prepareStatement(sql);
					pst.setString(1, incomeIDSpinner.getValue().toString());
					pst.setString(2, username);
					ResultSet rs = pst.executeQuery();

					if (rs.next()) {
						double next = rs.getDouble("IncomeID");
						String username = rs.getString("Username_fk");

						String intID = incomeIDSpinner.getValue().toString();
						double in = Double.parseDouble(intID);

						if (in == next && username.equals(username)) {

							String sql1 = "DELETE FROM income WHERE IncomeID =? AND Username_fk=?";
							pst = con.prepareStatement(sql1);
							pst.setString(1, incomeIDSpinner.getValue().toString());
							pst.setString(2, username);
							pst.executeUpdate();
							pst.close();
							JOptionPane.showMessageDialog(null, "Income has been Removed!");
							ShowData(username);
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Invalid IncomeID Entered!\nVerify IncomeID and Try Again!");
					}

				} catch (Exception exe) {
					JOptionPane.showMessageDialog(null, exe);
				}

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

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameTextFieldEditInc.setText("");
				modifyAmountSpinner.setValue(0);
				textAreaEditInc.setText("");
				dateChooserEditInc.setDate(null);
				incomeIDSpinner.setValue(0);
				incMod.setSelectedIndex(0);

			}
		});

		incMod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					incMod.removeAllItems();
					comboBoxEditInc.addElement("-Select one-");
					pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
					pst.setString(1, username);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						comboBoxEditInc.addElement(rs.getString("Account_Name"));
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		inc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					inc.removeAllItems();
					incomeBox.addElement("-Select one-");
					pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
					pst.setString(1, username);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						incomeBox.addElement(rs.getString("Account_Name"));
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
	}

	// Method to generate table cells from database
	/*********************************************************************************************/
	void ShowData(String username) throws SQLException {

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");

		model.addColumn("Date");

		model.addColumn("Name");

		model.addColumn("Description");

		model.addColumn("Account");

		model.addColumn("Income Amount");

		try {
			con = DbConnection.getConnection();
			pst = con.prepareStatement("select * from income where Username_fk=?");
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] {

						rs.getString("IncomeID"),

						rs.getString("Income_Date"),

						rs.getString("Name"),

						rs.getString("Income_Description"),

						rs.getString("Account_Name_fk"),

						rs.getString("Income_Amount") });

			}

			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(120);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}