/* Author: Jordan Arroyo
 * Date: 2/13/22
 * Filename: NewUserSetup.java 
 */

package ExpenseGui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class NewUserSetup extends JFrame {

	private JPanel contentPane;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField newUsernameField;
	private JPasswordField newPasswordField;
	private JTextField accField;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public NewUserSetup() {

		// Set Layout for Create new user window
		/*********************************************************************************************/
		setTitle("New User Registration");
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUserSetup.class.getResource("/img/128.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*********************************************************************************************/

		// Add Labels
		/*********************************************************************************************/
		JLabel createNewUserLabel = new JLabel(" New User");
		createNewUserLabel.setIcon(new ImageIcon(NewUserSetup.class.getResource("/img/user64.png")));
		createNewUserLabel.setFont(new Font("Segoe UI", Font.PLAIN, 34));
		createNewUserLabel.setForeground(Color.BLACK);
		createNewUserLabel.setBounds(181, 11, 257, 70);
		contentPane.add(createNewUserLabel);

		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		firstNameLabel.setBounds(221, 155, 180, 25);
		contentPane.add(firstNameLabel);

		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lastNameLabel.setBounds(221, 226, 180, 25);
		contentPane.add(lastNameLabel);

		JLabel newUsernameLabel = new JLabel("New Username*");
		newUsernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		newUsernameLabel.setBounds(10, 155, 180, 25);
		contentPane.add(newUsernameLabel);

		JLabel newPasswordLabel = new JLabel("New Password*");
		newPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		newPasswordLabel.setBounds(10, 226, 180, 25);
		contentPane.add(newPasswordLabel);

		JLabel messageLabel = new JLabel();
		messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		messageLabel.setBounds(511, 451, 240, 25);
		messageLabel.setForeground(Color.red);
		contentPane.add(messageLabel);

		JLabel addExpenseLogoLabel = new JLabel("");
		addExpenseLogoLabel.setIcon(new ImageIcon(NewUserSetup.class.getResource("/img/128.png")));
		addExpenseLogoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addExpenseLogoLabel.setBounds(10, 433, 105, 117);
		contentPane.add(addExpenseLogoLabel);

		JLabel accountNameLabel = new JLabel("Account Name*");
		accountNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		accountNameLabel.setBounds(432, 155, 180, 25);
		contentPane.add(accountNameLabel);

		JLabel accountTypeLabel = new JLabel("Account Type*");
		accountTypeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		accountTypeLabel.setBounds(432, 226, 180, 25);
		contentPane.add(accountTypeLabel);

		JLabel userInfoLabel = new JLabel("User Infomation");
		userInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		userInfoLabel.setBounds(221, 117, 180, 37);
		contentPane.add(userInfoLabel);

		JLabel loginLabel = new JLabel("Login Information");
		loginLabel.setFont(new Font("Segoe UI", Font.BOLD, 21));
		loginLabel.setBounds(10, 117, 201, 37);
		contentPane.add(loginLabel);

		JLabel accInfoLabel = new JLabel("Account Information");
		accInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		accInfoLabel.setBounds(432, 117, 227, 37);
		contentPane.add(accInfoLabel);
		/*********************************************************************************************/

		// Add TextFields, Buttons, Combo boxes, Spinner
		/*********************************************************************************************/
		firstNameTextField = new JTextField();
		firstNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		firstNameTextField.setBounds(221, 180, 180, 35);
		contentPane.add(firstNameTextField);
		firstNameTextField.setColumns(10);

		lastNameTextField = new JTextField();
		lastNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lastNameTextField.setBounds(221, 251, 180, 35);
		contentPane.add(lastNameTextField);
		lastNameTextField.setColumns(10);

		newUsernameField = new JTextField();
		newUsernameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		newUsernameField.setBounds(10, 179, 180, 35);
		contentPane.add(newUsernameField);
		newUsernameField.setColumns(10);

		newPasswordField = new JPasswordField();
		newPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		newPasswordField.setBounds(10, 251, 180, 35);
		contentPane.add(newPasswordField);

		accField = new JTextField();
		accField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		accField.setBounds(432, 180, 180, 35);
		contentPane.add(accField);
		accField.setColumns(10);

		JButton resetFieldsButton = new JButton("Reset");
		resetFieldsButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		resetFieldsButton.setForeground(Color.RED);
		resetFieldsButton.setBounds(267, 441, 90, 35);
		contentPane.add(resetFieldsButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cancelButton.setForeground(Color.RED);
		cancelButton.setBounds(562, 515, 90, 35);
		contentPane.add(cancelButton);

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		submitButton.setForeground(Color.BLUE);
		submitButton.setBounds(221, 396, 180, 35);
		contentPane.add(submitButton);

		JComboBox<String> accTypeComboBox = new JComboBox<String>();
		accTypeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		accTypeComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "-Select One-", "Personal ", "Savings", "Business", "Retirement" }));
		accTypeComboBox.setBounds(432, 251, 180, 35);
		contentPane.add(accTypeComboBox);

		JLabel asterikLabel = new JLabel("* = Required");
		asterikLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		asterikLabel.setBounds(534, 11, 105, 25);
		contentPane.add(asterikLabel);
		/*********************************************************************************************/

		// Add Action Listeners
		/*********************************************************************************************/
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstname = firstNameTextField.getText();
				String lastname = lastNameTextField.getText();
				String username = newUsernameField.getText();
				String password = String.valueOf(newPasswordField.getPassword());
				String accName = accField.getText().toLowerCase();
				String accType = accTypeComboBox.getSelectedItem().toString();
				try {
					do {
						if (!username.isEmpty() && !password.isEmpty() && !accName.isEmpty() && !accType.isEmpty()) {
							con = DbConnection.getConnection();
							pst = con.prepareStatement("select * from user where username = ?");
							pst.setString(1, username);
							rs = pst.executeQuery();
							if (rs.next()) {
								messageLabel.setText("Username already exist!");

							} else {
								PreparedStatement inUser = con.prepareStatement(
										"INSERT INTO user(Firstname, Lastname, Username, password) VALUES (?,?,?,?)");
								inUser.setString(1, firstname);
								inUser.setString(2, lastname);
								inUser.setString(3, username);
								inUser.setString(4, password);

								PreparedStatement inAcc = con.prepareStatement(
										"INSERT INTO account(Account_Name, Account_Type, Username_fk) VALUES (?,?,?)");
								inAcc.setString(1, accName);
								inAcc.setString(2, accType);
								inAcc.setString(3, username);
								inUser.executeUpdate();
								inAcc.execute();
								JOptionPane.showMessageDialog(null, "New User Profile Added Successfully!");

								pst.close();
								rs.close();
								inUser.close();
								inAcc.close();
								LoginPage loginPage = new LoginPage();
								dispose();
								loginPage.setVisible(true);
							}
						} else {
							messageLabel.setText("Required Fields cannot be empty!");
						}
					} while (username == null || password == null);
				} catch (Exception exc) {
					exc.printStackTrace();

				}
			}
		});

		resetFieldsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newPasswordField.setText("");
				newUsernameField.setText("");
				messageLabel.setText("");
				firstNameTextField.setText("");
				lastNameTextField.setText("");
				accField.setText("");
				accTypeComboBox.setSelectedIndex(-1);
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LoginPage loginPage = new LoginPage();
				dispose();
				loginPage.setVisible(true);
			}
		});
		/*********************************************************************************************/
		setLocationRelativeTo(null);
	}
}
