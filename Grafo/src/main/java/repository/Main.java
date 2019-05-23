package repository;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		GerarDataSet gerarDataset = new GerarDataSet(3);
		
		gerarDataset.gerarJson("Cher");
	}
}
