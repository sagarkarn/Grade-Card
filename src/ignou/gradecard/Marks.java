package ignou.gradecard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Marks {

	public static String htmlCode="";
	private HttpURLConnection conn = null;
	Marks() {
		System.out.println(getName());
	}

	public Boolean connection(String eno) {
		try {
			htmlCode="";
			System.out.println("connection method entered");
			conn = (HttpURLConnection) new URL(
					"https://gradecard.ignou.ac.in/gradecardM/Result.asp?Program=BCA&eno=" + eno + "&hidden_submit=ok")
							.openConnection();
			String data = "";
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				htmlCode = htmlCode + line;

			}
			System.out.println(htmlCode);
			
			JLabel label = new JLabel(htmlCode);
			JScrollPane scrol = new JScrollPane(label);
			JOptionPane.showMessageDialog(null,scrol,"login",1);

			rd.close();
			
			

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"\n Check your internet Connection", "Error",0);
			e.printStackTrace();
			return false;

		}
		return true;
	}
	public String getName() {
		
		
		int indexNameStart = htmlCode.indexOf("Name") + 6;
		int indexNameEnd = htmlCode.indexOf("<", indexNameStart);
		String name = "";
		for(int i = indexNameStart; i < indexNameEnd; i++) {
			name = name + htmlCode.charAt(i);
		}
		return name;
	}
	

}
