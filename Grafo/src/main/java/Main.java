import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		LoadDataSet dataSet = new LoadDataSet();
		
		try {
			dataSet.getSimilares("cher", "ba44ff677696c0b89159d566ed476981");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
