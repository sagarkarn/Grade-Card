package ignou.gradecard;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 157, 461);
		contentPane.add(panel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(154, 0, 530, 461);
		tabbedPane.setFont(new Font("Consolas",Font.BOLD,15));
		tabbedPane.addTab("1st Sem", new FirstSemTab());
		tabbedPane.addTab("2nd Sem", new SecoendSemTab());
		tabbedPane.addTab("3rd Sem", new ThirdSemTab());
		tabbedPane.addTab("4th sem", new FourthSemTab());
		tabbedPane.addTab("5th sem", new FifthSemTab());
		tabbedPane.addTab("6th sem", new SixthSemTab());
		tabbedPane.setSelectedComponent(new SecoendSemTab());
		contentPane.add(tabbedPane);
		setVisible(true);
	}
}
