package ignou.gradecard;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Database {

	Parameter p;
	ArrayList<DataType> list = new ArrayList<>();

	Database() {
	}

	interface Parameter {
		Scanner sc = new Scanner(System.in);
		String pname = sc.nextLine();
		int pid = sc.nextInt();

		void setData();

	}

	public void insert(int id, String name) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("login", false));
			DataType dt = new DataType(id, name);
			list.add(0, dt);
			if(list.size() > 5) {
				for(int i = list.size()-1; i > 4; i--)
					list.remove(i);
			}
			out.writeObject(list);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		finally {
			try {
				if(out != null)
					out.close();
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}

	}

	@SuppressWarnings("unchecked")
	public ArrayList<DataType> read() throws EOFException {
		
		ArrayList<DataType> list2 = new ArrayList<>();
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("login"));
			
			try {
				
			list2 = (ArrayList<DataType>) in.readObject();
			}
			catch(EOFException e) {
				list2.clear();
			}
			list = list2;

			for (DataType dt : list) {
				System.out.println(dt.id);
				System.out.println(dt.name);
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		finally {
			try {
				if(in != null) {
					in.close();
					
				}
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		return list;

	}

}
