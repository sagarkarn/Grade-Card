package ignou.gradecard;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textLogin;
	private ArrayList<DataType> list;
	private JLabel lblName[] = new JLabel[100];
	private JLabel lblEnrollmentNo[] = new JLabel[100];
	private JPanel panel[] = new JPanel[100];
	private JScrollPane scrollPane;
	private Database database;
	private JButton btnNewButton[] = new JButton[100];
	private Marks marks;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	void startMainPage() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 * 
	 * @throws EOFException
	 */
	public LoginPage() throws EOFException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		drawGraphics();
		database = new Database();

		if (new File("login").exists()) {
			list = database.read();

			setToRecent();
		}
		marks = new Marks();
		setVisible(true);

	}

	// Login page GUI
	public void drawGraphics() {
		JLabel lblEno = new JLabel("Enrollment Number");
		lblEno.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 24));
		lblEno.setBounds(237, 91, 209, 50);
		contentPane.add(lblEno);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "BCA", "MCA" }));
		comboBox.setBounds(168, 144, 75, 37);
		contentPane.add(comboBox);

		textLogin = new JTextField();
		textLogin.setBounds(253, 144, 197, 37);
		textLogin.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					onBtnLoginClick();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

		});
		contentPane.add(textLogin);
		textLogin.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin.setBounds(297, 191, 89, 37);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				onBtnLoginClick();

			}
		});
		contentPane.add(btnLogin);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(168, 271, 348, 179);
		scrollPane.setLayout(null);
		contentPane.add(scrollPane);

	}

	// Login Condition
	public Boolean conditions(JTextField label) {

		String labelText = label.getText().toString();
		if (labelText.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Enrollment no. should not be empty");
			return false;
		}
		if (labelText.length() != 9) {
			JOptionPane.showMessageDialog(this, "Enrollment should be numeric 9 digit");
			return false;
		}
		try {
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Number Format Exception");
			return false;
		}

		return true;
	}

	public void onBtnLoginClick() {
		if (conditions(textLogin)) {
			database.insert(Integer.parseInt(textLogin.getText()), "name");
			JOptionPane.showMessageDialog(this, "Success Login");
			if (marks.getName().length() < 5) {
				JOptionPane.showMessageDialog(null, "enrollment no. not found");
			} else {
				if (marks.connection(textLogin.getText())) {
					dispose();
					startMainPage();
				}
			}
		}
	}

	void setToRecent() {
		int py = 1;
		for (int i = 0; i < list.size(); i++) {
			panel[i] = new JPanel();
			panel[i].setBounds(1, py, 270, 30);
			panel[i].setLayout(null);
			panel[i].setBackground(Color.YELLOW);
			scrollPane.add(panel[i]);

			lblEnrollmentNo[i] = new JLabel(String.valueOf(list.get(i).id));
			lblEnrollmentNo[i].setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblEnrollmentNo[i].setBounds(10, 0, 100, 30);
			panel[i].add(lblEnrollmentNo[i]);

			lblName[i] = new JLabel("Name");
			lblName[i].setBounds(100, 0, 100, 30);
			panel[i].add(lblName[i]);

			// Recent Button
			btnNewButton[i] = new JButton(new ImageIcon("images\\icons8_Right_52px.png"));
			btnNewButton[i].setBackground(Color.ORANGE);
			btnNewButton[i].setActionCommand(Integer.toString(i));
			btnNewButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int clicked = Integer.parseInt(e.getActionCommand());
					String eno = lblEnrollmentNo[clicked].getText();
					if (marks.connection(eno)) {
						if (marks.getName().length() > 2) {
							dispose();
							startMainPage();
						}
					}
				}
			});
			btnNewButton[i].setBorderPainted(false);
			btnNewButton[i].setFocusable(false);
			btnNewButton[i].setBounds(271, py, 74, 30);
			scrollPane.add(btnNewButton[i]);

			py += 31;

		}
	}
}
