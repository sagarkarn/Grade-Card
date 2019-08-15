package ignou.gradecard;

import java.io.Serializable;

import ignou.gradecard.Database.Parameter;

public class DataType implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String name;

	DataType(int id,String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name;
	}

	
}