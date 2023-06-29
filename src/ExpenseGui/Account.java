package ExpenseGui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.MutableComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class Account extends JPanel {
	private JTextField accNameTextField;
	private JTextField modAccTextField;
	private JTable table;

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	public Account(String user) {
		String username = user;

		// Set Layout and Add Panels for add account tab
		/*********************************************************************************************/
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(2, 0, 711, 586);
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		add(tabbedPane);

		JPanel addModAccPanel = new JPanel();
		tabbedPane.addTab("Add / Modify Account", new ImageIcon(Account.class.getResource("/img/newModify.png")),
				addModAccPanel, null);
		addModAccPanel.setLayout(null);
		addModAccPanel.setFont(new Font("Segoe UI", Font.PLAIN, 22));

		JPanel addAccPanel = new JPanel();
		addAccPanel.setBorder(new LineBorder(Color.BLUE, 3));
		addAccPanel.setBounds(10, 44, 330, 337);
		addModAccPanel.add(addAccPanel);
		addAccPanel.setLayout(null);

		JLabel newAccNameLabel = new JLabel("New Account Name");
		newAccNameLabel.setBounds(10, 11, 163, 26);
		addAccPanel.add(newAccNameLabel);
		newAccNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		JLabel lblAccountType = new JLabel("Account Type");
		lblAccountType.setBounds(10, 85, 151, 26);
		addAccPanel.add(lblAccountType);
		lblAccountType.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		JButton commitButtonAddAcc = new JButton("Commit");
		commitButtonAddAcc.setBounds(10, 291, 100, 35);
		addAccPanel.add(commitButtonAddAcc);
		commitButtonAddAcc.setForeground(Color.BLUE);
		commitButtonAddAcc.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		JComboBox<String> comboBoxAccType = new JComboBox<String>();
		comboBoxAccType.setBounds(10, 114, 145, 35);
		addAccPanel.add(comboBoxAccType);
		comboBoxAccType.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		comboBoxAccType.setModel(new DefaultComboBoxModel<String>(
				new String[] { "-Select One-", "Personal ", "Savings", "Business", "Retirement" }));

		accNameTextField = new JTextField();
		accNameTextField.setBounds(10, 39, 145, 35);
		addAccPanel.add(accNameTextField);
		accNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		accNameTextField.setColumns(10);

		JPanel modAccPanel = new JPanel();
		modAccPanel.setBounds(350, 44, 337, 337);
		addModAccPanel.add(modAccPanel);
		modAccPanel.setBorder(new LineBorder(Color.BLUE, 3));
		modAccPanel.setLayout(null);

		JLabel lblSelectAccountTo = new JLabel("Account to Modify");
		lblSelectAccountTo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSelectAccountTo.setBounds(10, 11, 172, 26);
		modAccPanel.add(lblSelectAccountTo);

		JLabel lblNewAccountType = new JLabel("Modify Account Type");
		lblNewAccountType.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewAccountType.setBounds(10, 182, 176, 26);
		modAccPanel.add(lblNewAccountType);

		JLabel lblModifiedAccountName = new JLabel("Modify Account Name");
		lblModifiedAccountName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblModifiedAccountName.setBounds(10, 95, 189, 26);
		modAccPanel.add(lblModifiedAccountName);
		JComboBox<String> comboBoxModAccType = new JComboBox<String>();
		comboBoxModAccType.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		comboBoxModAccType.setModel(new DefaultComboBoxModel<String>(
				new String[] { "-Select One-", "Personal ", "Savings", "Business", "Retirement" }));
		comboBoxModAccType.setBounds(10, 212, 145, 35);
		modAccPanel.add(comboBoxModAccType);

		JComboBox<String> accBox = new JComboBox<String>();
		accBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		MutableComboBoxModel<String> comboBoxModAccSelect = (DefaultComboBoxModel<String>) accBox.getModel();
		accBox.setBounds(10, 36, 145, 35);
		modAccPanel.add(accBox);

		JButton modifyCommitButton = new JButton("Commit");

		modifyCommitButton.setForeground(Color.BLUE);
		modifyCommitButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		modifyCommitButton.setBounds(10, 291, 100, 35);
		modAccPanel.add(modifyCommitButton);

		modAccTextField = new JTextField();
		modAccTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		modAccTextField.setColumns(10);
		modAccTextField.setBounds(10, 120, 145, 35);
		modAccPanel.add(modAccTextField);

		JLabel addLogoLabel = new JLabel("");
		addLogoLabel.setBounds(8, 392, 105, 117);
		addModAccPanel.add(addLogoLabel);
		addLogoLabel.setIcon(new ImageIcon(Account.class.getResource("/img/128.png")));
		addLogoLabel.setHorizontalAlignment(SwingConstants.TRAILING);

		JLabel addNewAccLabel = new JLabel("Add New Account");
		addNewAccLabel.setBounds(10, 11, 211, 32);
		addModAccPanel.add(addNewAccLabel);
		addNewAccLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

		JLabel modifyAccountLabel = new JLabel("Modify Account");
		modifyAccountLabel.setBounds(350, 11, 172, 32);
		addModAccPanel.add(modifyAccountLabel);
		modifyAccountLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

		JPanel viewAccPanel = new JPanel();
		tabbedPane.addTab("View / Remove Accounts", new ImageIcon(Account.class.getResource("/img/vewAcc.png")),
				viewAccPanel, null);
		viewAccPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 330, 337);
		scrollPane.setBorder(new LineBorder(Color.BLUE, 3));
		viewAccPanel.add(scrollPane);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		JLabel allAccountsLabel = new JLabel("All Accounts");
		allAccountsLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		allAccountsLabel.setBounds(10, 11, 186, 32);
		viewAccPanel.add(allAccountsLabel);

		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(Account.class.getResource("/img/128.png")));
		logoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		logoLabel.setBounds(8, 392, 105, 117);
		viewAccPanel.add(logoLabel);

		JButton exportButton = new JButton("Export");
		exportButton.setForeground(Color.GREEN);
		exportButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		exportButton.setBounds(240, 392, 100, 35);
		viewAccPanel.add(exportButton);

		JPanel remAccPanel = new JPanel();
		remAccPanel.setLayout(null);
		remAccPanel.setBorder(new LineBorder(Color.BLUE, 3));
		remAccPanel.setBounds(350, 44, 337, 337);
		viewAccPanel.add(remAccPanel);

		JLabel selectAccRemLabel_1 = new JLabel("Select Account");
		selectAccRemLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		selectAccRemLabel_1.setBounds(10, 220, 151, 26);
		remAccPanel.add(selectAccRemLabel_1);

		JTextPane txtpnwhenRemovingAn_1 = new JTextPane();
		txtpnwhenRemovingAn_1.setText(
				"*** REMOVING AN ACCOUNT WILL ALSO REMOVE ALL EXPENSES AND INCOMES LINKED TO THAT ACCOUNT! ***\r\n \r\n*** MAKE SURE YOU HAVE THE CORRECT ACCOUNT SELECTED! ***");
		txtpnwhenRemovingAn_1.setForeground(new Color(51, 0, 255));
		txtpnwhenRemovingAn_1.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		txtpnwhenRemovingAn_1.setEnabled(false);
		txtpnwhenRemovingAn_1.setEditable(false);
		txtpnwhenRemovingAn_1.setBounds(10, 11, 317, 198);
		remAccPanel.add(txtpnwhenRemovingAn_1);

		JButton removeAccCommitButton = new JButton("DELETE");
		removeAccCommitButton.setForeground(Color.RED);
		removeAccCommitButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		removeAccCommitButton.setBounds(10, 291, 100, 35);
		remAccPanel.add(removeAccCommitButton);

		JComboBox<String> box = new JComboBox<String>();
		box.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		MutableComboBoxModel<String> comboBoxRemAcc = (DefaultComboBoxModel<String>) box.getModel();
		box.setBounds(10, 245, 180, 35);
		remAccPanel.add(box);

		JLabel lblRemoveAccount = new JLabel("Remove Account");
		lblRemoveAccount.setBounds(350, 11, 211, 32);
		viewAccPanel.add(lblRemoveAccount);
		lblRemoveAccount.setFont(new Font("Segoe UI", Font.BOLD, 20));

		JButton refreshButton = new JButton("Refresh");

		refreshButton.setForeground(Color.BLUE);
		refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		refreshButton.setBounds(123, 392, 100, 35);
		viewAccPanel.add(refreshButton);

		/*********************************************************************************************/

		// Add TextFields, Buttons, Combo boxes, Spinner for account tab
		/*********************************************************************************************/
		comboBoxModAccSelect.addElement("-Select one-");
		try {
			Connection con = DbConnection.getConnection();
			pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
			pst.setString(1, user);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				comboBoxModAccSelect.addElement(rs.getString("Account_Name"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxRemAcc.addElement("-Select one-");
		try {
			Connection con = DbConnection.getConnection();
			pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
			pst.setString(1, user);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				comboBoxRemAcc.addElement(rs.getString("Account_Name"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*********************************************************************************************/

		// Add Action Listeners for account tab
		/*********************************************************************************************/

		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ShowData(username);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		removeAccCommitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (box.getSelectedIndex() != 0) {
					try {
						con = DbConnection.getConnection();
						String sql = "DELETE FROM account WHERE Account_Name =? AND Username_fk=?";
						pst = con.prepareStatement(sql);
						pst.setString(1, box.getSelectedItem().toString());
						pst.setString(2, username);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Account Deleted Successfully!");
						box.removeAllItems();
						comboBoxRemAcc.addElement("-Select one-");
						pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
						pst.setString(1, user);
						ResultSet rs = pst.executeQuery();
						while (rs.next()) {
							comboBoxRemAcc.addElement(rs.getString("Account_Name"));
						}
						accBox.removeAllItems();
						comboBoxModAccSelect.addElement("-Select one-");
						pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
						pst.setString(1, user);
						ResultSet rs1 = pst.executeQuery();
						while (rs1.next()) {
							comboBoxModAccSelect.addElement(rs1.getString("Account_Name"));
						}
						ShowData(username);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Account must be selected!");
					}
				} else
					JOptionPane.showMessageDialog(null, "No Account Selected");
			}
		});

		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser filechooser = new JFileChooser();
					filechooser.showDialog(viewAccPanel, "Export");
					File export = filechooser.getSelectedFile();
					if (export != null) {
						export = new File(export.toString() + ".xlsx");
						Workbook wb = new XSSFWorkbook();
						Sheet sheet = wb.createSheet("Accounts");
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
						JOptionPane.showMessageDialog(null, "Accounts have been Exported!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "An Error has Occured!");
				}
			}
		});

		commitButtonAddAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!accNameTextField.getText().equals("")) {
						if (comboBoxAccType.getSelectedIndex() != 0) {
							con = DbConnection.getConnection();
							pst = con.prepareStatement("select * from user where username = ?");
							pst.setString(1, username);
							rs = pst.executeQuery();
							PreparedStatement addAcc = con.prepareStatement(
									"INSERT INTO account(Account_Name, Account_Type, Username_fk) VALUES (?,?,?)");
							addAcc.setString(1, accNameTextField.getText());
							addAcc.setString(2, comboBoxAccType.getSelectedItem().toString());
							addAcc.setString(3, username);
							addAcc.execute();
							JOptionPane.showMessageDialog(null, "New Account Added Successfully!");
							
							box.removeAllItems();
							comboBoxRemAcc.addElement("-Select one-");
							pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
							pst.setString(1, user);
							ResultSet rs = pst.executeQuery();
							while (rs.next()) {
								comboBoxRemAcc.addElement(rs.getString("Account_Name"));
							}
							accBox.removeAllItems();
							comboBoxModAccSelect.addElement("-Select one-");
							pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
							pst.setString(1, user);
							ResultSet rs1 = pst.executeQuery();
							while (rs1.next()) {
								comboBoxModAccSelect.addElement(rs1.getString("Account_Name"));
							}
						} else {
							JOptionPane.showMessageDialog(null, "Account Type cannot be blank!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Account Name cannot be blank!");
					}
					ShowData(username);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});

		modifyCommitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (accBox.getSelectedIndex() != 0) {
						if (comboBoxModAccType.getSelectedIndex() != 0 && modAccTextField.getText().equals("")) {
							con = DbConnection.getConnection();
							String sql = "UPDATE account SET Account_Type=?, Account_Name=? WHERE Account_Name=? AND Username_fk=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, comboBoxModAccType.getSelectedItem().toString());
							pst.setString(2, accBox.getSelectedItem().toString());
							pst.setString(3, accBox.getSelectedItem().toString());
							pst.setString(4, username);
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Account Updated Successfully!");
						} else if (!modAccTextField.getText().equals("")
								&& comboBoxModAccType.getSelectedIndex() == 0) {
							con = DbConnection.getConnection();
							String sql = "UPDATE account SET Account_Name=? WHERE Account_Name=? AND Username_fk=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, modAccTextField.getText());
							pst.setString(2, accBox.getSelectedItem().toString());
							pst.setString(3, username);
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Account Updated Successfully!");
						} else if (!modAccTextField.getText().equals("")
								&& comboBoxModAccType.getSelectedIndex() != 0) {
							con = DbConnection.getConnection();
							String sql = "UPDATE account SET Account_Type=?, Account_Name=? WHERE Account_Name=? AND Username_fk=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, comboBoxModAccType.getSelectedItem().toString());
							pst.setString(2, modAccTextField.getText());
							pst.setString(3, accBox.getSelectedItem().toString());
							pst.setString(4, username);
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Account Updated Successfully!");
						} else
							JOptionPane.showMessageDialog(null, "Selected Account and Account Name cannot be Blank!");
						box.removeAllItems();
						comboBoxRemAcc.addElement("-Select one-");
						pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
						pst.setString(1, user);
						ResultSet rs = pst.executeQuery();
						while (rs.next()) {
							comboBoxRemAcc.addElement(rs.getString("Account_Name"));
						}
						accBox.removeAllItems();
						comboBoxModAccSelect.addElement("-Select one-");
						pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
						pst.setString(1, user);
						ResultSet rs1 = pst.executeQuery();
						while (rs1.next()) {
							comboBoxModAccSelect.addElement(rs1.getString("Account_Name"));
						}
						ShowData(username);
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
	}

	// Method to generate table cells from database
	/*********************************************************************************************/
	void ShowData(String username) throws SQLException {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Type");
		try {
			con = DbConnection.getConnection();
			pst = con.prepareStatement("select * from account where Username_fk=?");
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString("AccountID"), rs.getString("Account_Name"),
						rs.getString("Account_Type"), });
			}
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}