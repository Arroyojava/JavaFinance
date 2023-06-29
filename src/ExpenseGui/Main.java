/* Author: Jordan Arroyo
 * Date: 2/13/22
 * Filename: Main.java 
 */

package ExpenseGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneLightContrastIJTheme;

@SuppressWarnings("serial")
public class Main extends JFrame {

	JPanel contentPaneMain;
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

	public Main(String user) throws SQLException {

		String username = user;

		ExpensePanel ep = new ExpensePanel(username);
		ep.ShowData(username);
		IncomePanel ip = new IncomePanel(username);
		ip.ShowData(username);
		DashboardPanel dp = new DashboardPanel(username);
		
		Account ac = new Account(username);
		ac.ShowData(username);
		Goals gl = new Goals(username);
		gl.ShowData(username);

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/128.png")));
		setTitle("Java Finance v1.4");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(910, 610);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("Edit");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_2 = new JMenu("Help");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu_2);

		contentPaneMain = new JPanel();
		contentPaneMain.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPaneMain.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneMain);

		contentPaneMain.add(tabbedPane);
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 24));

		tabbedPane.add("Dashboard", dp);
		tabbedPane.add(" Expense    ", ep);
		tabbedPane.add(" Income    ", ip);
		tabbedPane.add(" Goals       ", gl);
		tabbedPane.add(" Account   ", ac);

		tabbedPane.setIconAt(0, new ImageIcon(Main.class.getResource("/img/newDash.png")));
		tabbedPane.setIconAt(1, new ImageIcon(Main.class.getResource("/img/newExpensez.png")));
		tabbedPane.setIconAt(2, new ImageIcon(Main.class.getResource("/img/newIncomes.png")));
		tabbedPane.setIconAt(3, new ImageIcon(Main.class.getResource("/img/newGoal.png")));
		tabbedPane.setIconAt(4, new ImageIcon(Main.class.getResource("/img/newAcc.png")));

		setResizable(false);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {

		try {
			FlatAtomOneLightContrastIJTheme.setup();

		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				;
				LoginPage login = new LoginPage();
				login.setVisible(true);

			}
		});
	}
}