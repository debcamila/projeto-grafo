package repository;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		GerarDataSet gerarDataset = new GerarDataSet(8);
		
		gerarDataset.gerarJson("Madonna");
		
		/*ArtistaData artista = new TestarJson("dataset.json").CarregarDataSet();
		
		System.out.println(artista.contarVertices());*/
		
	}
}
