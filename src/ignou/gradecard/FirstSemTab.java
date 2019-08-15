package ignou.gradecard;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class FirstSemTab extends JPanel {
	private static final long serialVersionUID = 1L;
	Marks marks = null;
	JLabel label[] = new JLabel[5];

	String str="";
	public FirstSemTab() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 11, 495, 67);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblCoursecode = new JLabel("courseCode");
		lblCoursecode.setBounds(7, 0, 84, 27);
		panel.add(lblCoursecode);
		
		JLabel lblCoursename = new JLabel("courseName");
		lblCoursename.setBounds(106, 0, 321, 27);
		panel.add(lblCoursename);
		
		JLabel lblAssignNo = new JLabel("assign no:");
		lblAssignNo.setBounds(7, 38, 84, 18);
		panel.add(lblAssignNo);
		
		JLabel lblTermEnd = new JLabel("term end:");
		lblTermEnd.setBounds(104, 38, 84, 18);
		panel.add(lblTermEnd);
		
//		marks = new Marks();
//		String[][] result = firstSemesterResult();
//		for (int i = 0; i < 5; i++) {
//			for(int j = 0; j < 9; j++) {
//				if(j==1)
//					continue;
//				str = str+result[i][j]+ "  ";
//				
//			}
//			label[i] = new JLabel(str);
//			str="";
//			add(label[i]);
			
//		}

	}
public String[][] firstSemesterResult(){
		
		String number = "";
		int indexStart = 0, indexEnd = 0, indexOfCode;
		
		String[][] firstSemester = new String[5][9];
		firstSemester[0][0] = "FEG02";
		firstSemester[1][0] = "ECO01";
		firstSemester[2][0] = "BCS011";
		firstSemester[3][0] = "BCS012";
		firstSemester[4][0] = "BCSL013";
		for(int i = 0; i < 5; i++) {
			indexOfCode = Marks.htmlCode.indexOf(firstSemester[i][0]);
			for(int j = 2; j < 9; j++) {
				indexStart = Marks.htmlCode.indexOf("<strong>", indexOfCode) + 8;
				indexEnd = Marks.htmlCode.indexOf("<", indexStart);
				for(int k = indexStart; k < indexEnd; k++) {
					number = number + Marks.htmlCode.charAt(k);
				}
				indexOfCode = indexEnd;
				firstSemester[i][j] = number;
				number = "";
			}
			
			
		}
		
		
		return firstSemester;
	}
}
