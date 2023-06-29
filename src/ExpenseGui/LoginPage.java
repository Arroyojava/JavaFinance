/* Author: Jordan Arroyo
 * Date: 2/13/22
 * Filename: LoginPage.java 
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField userIDField;
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	private JButton newUserButton;
	private JLabel lblNewLabel;
	private JButton resetFieldsButton;
	private JLabel messageLabel;

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel addExpenseLogoLabel;

	public LoginPage() {

		// Set Layout for Login Window
		/*********************************************************************************************/
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/img/128.png")));
		setTitle("Java Finance v1.4");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*********************************************************************************************/

		// Add Labels
		/*********************************************************************************************/
		JLabel userIDLabel = new JLabel("Username");
		userIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		userIDLabel.setBounds(153, 242, 100, 27);
		contentPane.add(userIDLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		passwordLabel.setBounds(153, 314, 100, 27);
		contentPane.add(passwordLabel);

		lblNewLabel = new JLabel("User Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 34));
		lblNewLabel.setBounds(153, 142, 201, 63);
		contentPane.add(lblNewLabel);

		messageLabel = new JLabel("");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		messageLabel.setBounds(168, 483, 175, 27);
		contentPane.add(messageLabel);
		/*********************************************************************************************/

		// Add TextFields, Buttons
		/*********************************************************************************************/
		userIDField = new JTextField();
		userIDField.setToolTipText("Username");
		userIDField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		userIDField.setBounds(153, 266, 201, 35);
		contentPane.add(userIDField);
		userIDField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		passwordField.setColumns(10);
		passwordField.setBounds(154, 336, 200, 35);
		contentPane.add(passwordField);

		JButton loginButton = new JButton("Login");
		loginButton.setForeground(Color.BLUE);
		loginButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		loginButton.setBounds(154, 395, 200, 35);
		contentPane.add(loginButton);

		newUserButton = new JButton("New User");
		newUserButton.setForeground(Color.BLUE);
		newUserButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		newUserButton.setBounds(358, 515, 116, 35);
		contentPane.add(newUserButton);

		resetFieldsButton = new JButton("Reset");
		resetFieldsButton.setForeground(Color.RED);
		resetFieldsButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		resetFieldsButton.setBounds(206, 446, 100, 35);
		contentPane.add(resetFieldsButton);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginPage.class.getResource("/img/key.png")));
		lblNewLabel_1.setBounds(115, 336, 39, 35);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LoginPage.class.getResource("/img/user32.png")));
		lblNewLabel_2.setBounds(115, 266, 39, 35);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Please enter your login credentials");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(153, 196, 201, 35);
		contentPane.add(lblNewLabel_3);
		
		addExpenseLogoLabel = new JLabel("");
		addExpenseLogoLabel.setIcon(new ImageIcon(LoginPage.class.getResource("/img/128.png")));
		addExpenseLogoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addExpenseLogoLabel.setBounds(206, 40, 105, 117);
		contentPane.add(addExpenseLogoLabel);
		/*********************************************************************************************/

		// Add Action Listeners
		/*********************************************************************************************/
		
		userIDField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			//	userIDField.setText("");
			}
		});
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String userName = userIDField.getText();
				String password = String.valueOf((passwordField).getPassword());

				try {
					con = DbConnection.getConnection();
					pst = con.prepareStatement("select * from user where username=? and password=?");
					pst.setString(1, userName);
					pst.setString(2, password);
					rs = pst.executeQuery();

					if (rs.next()) {

						String un = rs.getString("username");

						messageLabel.setForeground(Color.green);
						messageLabel.setText("Login Successful!");
						Main admin = new Main(un);
						admin.setVisible(true);
						dispose();

					} else {
						userIDField.setText("");
						messageLabel.setText("");
						passwordField.setText("");
						messageLabel.setForeground(Color.red);
						messageLabel.setText("Invalid Login Credentials!");
					}
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});

		newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUserSetup userSetup = new NewUserSetup();
				dispose();
				userSetup.setVisible(true);
			}
		});

		resetFieldsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.setText("");
				userIDField.setText("");
				messageLabel.setText("");

			}
		});
		/*********************************************************************************************/
		setLocationRelativeTo(null);

	}
}
