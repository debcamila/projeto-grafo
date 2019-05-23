import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		GerarDataSet gerarDataset = new GerarDataSet(4);
		
		gerarDataset.gerarJson(1);
	}
}
