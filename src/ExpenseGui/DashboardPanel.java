package ExpenseGui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.MutableComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("serial")
public class DashboardPanel extends JPanel {

	Connection con;
	PreparedStatement pst;
	PreparedStatement pt;
	ResultSet rs;
	Statement st;
	private JTable table;
	JComboBox<String> accountsBox;
	MutableComboBoxModel<String> accComboBox;
	JPanel panel;

	public DashboardPanel(String user) throws SQLException {

		String username = user;

		// Set Layout for add Dashbaord panel
		/*********************************************************************************************/
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 855, 625);
		add(tabbedPane);
		panel = new JPanel();

		tabbedPane.addTab("Overview", new ImageIcon(DashboardPanel.class.getResource("/img/user32.png")), panel, null);
		tabbedPane.setEnabledAt(0, true);
		panel.setLayout(null);
		/*********************************************************************************************/

		// Add Labels for Dashboard Panel
		/*********************************************************************************************/
		JLabel welomeLabel = new JLabel("Welcome " + username);
		welomeLabel.setBounds(213, 0, 365, 65);
		welomeLabel.setForeground(Color.BLACK);
		welomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
		panel.add(welomeLabel);

		JLabel iconLabel = new JLabel("");
		iconLabel.setBounds(10, 392, 105, 117);
		iconLabel.setIcon(new ImageIcon(DashboardPanel.class.getResource("/img/128.png")));
		iconLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(iconLabel);

		JLabel selectAccountLabel = new JLabel("Select Account: ");
		selectAccountLabel.setBounds(333, 79, 142, 32);
		selectAccountLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(selectAccountLabel);

		JLabel accBalanceLabel = new JLabel("Account Balance:");
		accBalanceLabel.setBounds(125, 392, 155, 32);
		accBalanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(accBalanceLabel);

		JLabel accBalance = new JLabel();
		accBalance.setHorizontalAlignment(SwingConstants.CENTER);
		accBalance.setBounds(279, 392, 123, 32);
		accBalance.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel.add(accBalance);

		JLabel accOverviewLabel = new JLabel("Account Statment");
		accOverviewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		accOverviewLabel.setBounds(10, 78, 179, 32);
		panel.add(accOverviewLabel);

		JLabel todayDateLabel = new JLabel("Date:");
		todayDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		todayDateLabel.setBounds(10, 57, 57, 23);
		panel.add(todayDateLabel);

		JLabel dateLabel = new JLabel("04-10-2022");
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String currentDate = simpleDateFormat.format(new Date());
		dateLabel.setText(currentDate);
		dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		dateLabel.setBounds(74, 57, 115, 23);
		panel.add(dateLabel);
		/*********************************************************************************************/

		// Add Buttons, table and Combo boxes for Dashboard Panel
		/*********************************************************************************************/
		JButton exportButton = new JButton("Export");
		exportButton.setForeground(Color.GREEN);
		exportButton.setBounds(521, 477, 169, 35);
		exportButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel.add(exportButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 122, 680, 259);
		scrollPane.setBorder(new LineBorder(Color.BLUE, 3));
		panel.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		JButton refreshButton = new JButton("Refresh");

		refreshButton.setForeground(Color.BLUE);
		refreshButton.setBounds(521, 433, 169, 35);
		refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel.add(refreshButton);
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 22));

		accountsBox = new JComboBox<String>();

		accountsBox.setBounds(485, 79, 205, 32);
		accountsBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		MutableComboBoxModel<String> accComboBox = (DefaultComboBoxModel<String>) accountsBox.getModel();
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
		panel.add(accountsBox);

		JButton calcButton = new JButton("Calculator");
		calcButton.setForeground(Color.BLUE);
		calcButton.setHorizontalAlignment(SwingConstants.LEFT);
		calcButton.setIcon(new ImageIcon(DashboardPanel.class.getResource("/img/calculator(32).png")));
		calcButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		calcButton.setBounds(521, 384, 169, 41);
		panel.add(calcButton);
		/*********************************************************************************************/

		// Add ActionListeners for Dashboard Panel
		/*********************************************************************************************/
		String acc = (String) accountsBox.getSelectedItem();
		createTable(username, acc);
		accBalance.setText(accTotal());
		ShowData(username);

		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser filechooser = new JFileChooser();
					filechooser.showDialog(panel, "Export");
					File export = filechooser.getSelectedFile();
					if (export != null) {
						export = new File(export.toString() + ".xlsx");
						Workbook wb = new XSSFWorkbook();
						Sheet sheet = wb.createSheet("Statement");
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
						JOptionPane.showMessageDialog(null, "Account Statement have been Exported!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "An Error has Occured!");
				}
			}
		});

		calcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				calculator calc = new calculator();
			}
		});

		accountsBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String select = (String) accountsBox.getSelectedItem();
				try {
					con = DbConnection.getConnection();
					st = con.createStatement();
					String sql = "DROP TABLE tableTemp";
					st.execute(sql);
					createTable(username, select);
					ShowData(username);
					accBalance.setText(accTotal());
				} catch (SQLException e1) {

				}
			}
		});

		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					con = DbConnection.getConnection();
					st = con.createStatement();

					String sql = "DROP TABLE tableTemp";
					st.execute(sql);

					accountsBox.removeAllItems();
					pst = con.prepareStatement("select Account_Name from account where Username_fk=?");
					pst.setString(1, username);
					ResultSet rs1 = pst.executeQuery();
					while (rs1.next()) {
						accComboBox.addElement(rs1.getString("Account_Name"));
					}

					String select = (String) accountsBox.getSelectedItem();

					createTable(username, select);

					ShowData(username);

					accBalance.setText(accTotal());

				} catch (SQLException e1) {
					// TODO Auto-generated catch block

				}
			}
		});

		panel.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				try {
					ShowData(username);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});

	}
	/*********************************************************************************************/

	// Method to obtain Account total
	/*********************************************************************************************/
	public String accTotal() {
		try {
			pt = con.prepareStatement("SELECT SUM(Amount) total FROM tableTemp");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {

				String total = rs.getString("total");
				return total;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	// Method to create temporary table
	/*********************************************************************************************/
	void createTable(String username, String account) throws SQLException {
		try {
			con = DbConnection.getConnection();
			pst = con.prepareStatement("CREATE TEMPORARY TABLE IF NOT EXISTS tableTemp AS "
					+ "(SELECT Name, Expense_Date AS Date, Expense_Description As Description, Expense_Amount AS Amount FROM expense "
					+ "Where Username_fk=? AND Account_Name_fk=?" + "UNION ALL "
					+ "SELECT Name, Income_Date AS Date, Income_Description As Description, Income_Amount AS Amount FROM income "
					+ "Where Username_fk=? AND Account_Name_fk=?)");
			pst.setString(1, username);
			pst.setString(2, account);
			pst.setString(3, username);
			pst.setString(4, account);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	/*********************************************************************************************/

	// Method to generate table cells from database
	/*********************************************************************************************/
	void ShowData(String username) throws SQLException {

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Date");

		model.addColumn("Name");

		model.addColumn("Description");

		model.addColumn("Amount");
		try {
			con = DbConnection.getConnection();
			pst = con.prepareStatement("select * from tableTemp");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] {

						rs.getString("Date"),

						rs.getString("Name"),

						rs.getString("Description"),

						rs.getString("Amount") });
			}

			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(140);
			table.getColumnModel().getColumn(1).setPreferredWidth(160);
			table.getColumnModel().getColumn(2).setPreferredWidth(260);
			table.getColumnModel().getColumn(3).setPreferredWidth(75);

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	/*********************************************************************************************/
}
