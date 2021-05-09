package test;


import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TimeSeries {


	public class Columns{
		private String name;
		private ArrayList<Float> floats;

		public Columns(String name){
			this.name = name;
			this.floats = new ArrayList<Float>();
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public ArrayList<Float> getFloats() {
			return floats;
		}

		public void setFloats(ArrayList<Float> floats) {
			this.floats = floats;
		}


	}

	private String csvName;
	private Columns[] cols;

	public TimeSeries(String csvFileName) {


	}
	public void readCsv(){
		String line = "";
		String splitBy = ",";
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvName));
			while ((line = br.readLine()) != null)   //returns a Boolean value
			{
				String[] employee = line.split(splitBy);    // use comma as separator
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
