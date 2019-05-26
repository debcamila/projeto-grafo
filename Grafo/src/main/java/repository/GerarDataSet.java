package repository;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.List;

public class GerarDataSet {
	private JsonArtista artistas;
	private int interacoesMaximas;
	
	public GerarDataSet(int interacoesMaximas) {
		this.artistas = new JsonArtista();
		this.interacoesMaximas = interacoesMaximas;
	}
	public void gerarJson(String artista) throws IOException {
		this.gerarJsonNRequisitados(artista, 0);
	}
	
	public void gerarJsonNRequisitados(String artista, int cont) throws IOException {
		String jsonContent = this.gerarDataSet(artista, 0);
		String contString = new DecimalFormat("0000").format(cont);
		File file = new File("datasets\\dataset"+contString+".json");
		BufferedWriter bWriter;
		FileWriter writer;
		
		if(!file.exists()) {
			file.createNewFile();
		}
		
		writer = new FileWriter(file);
		bWriter = new BufferedWriter(writer);
		
		bWriter.write(jsonContent);
		bWriter.flush();
		bWriter.close();
		
		writer.close();
		
		
		
	}
	public String gerarDataSet(String artista, int interacoes) throws IOException {
		
		String artistaContent = "{\"name\": \""+artista+"\",\"similar\": [";
		Artista[] artistas;
		int cont=1;
		
		if(JsonArtista.cont<180 && interacoes<this.interacoesMaximas) {
			artistas = this.artistas.getSimilares(artista, "ba44ff677696c0b89159d566ed476981");
			if(artistas!=null) {
				for(Artista item : artistas) {
					if(item!=null) {
						if(cont==artistas.length) {
							artistaContent+=this.gerarDataSet(item.getNome(), (interacoes+1));
						}else {
							artistaContent+=this.gerarDataSet(item.getNome(), (interacoes+1))+",";
						}
						cont++;
					}
				}
			}
		}
		return artistaContent + "]}";
	}
	public void gerarDataSetNRequisitados() throws IOException {
		ArtistaDataCollection artista = new ArtistaDataCollection();
		artista.gerarCollection();
		
		SecureRandom random = new SecureRandom();
		int r;
		
		System.out.println(artista.pegarArvore().getQtdArtistas());

		/*JsonArtista.tabelaArtistas = artista.requisicoes();
		
		List<Artista> nRequisitados = artista.verNaoRequisitados();
						
		
		for(int i=160; i<200; i++) {
			JsonArtista.cont=0;
			r=random.nextInt(nRequisitados.size());
			if(r<nRequisitados.size()) {
				if(!JsonArtista.tabelaArtistas.busca(nRequisitados.get(r).name)) {
					this.gerarJsonNRequisitados(nRequisitados.get(r).name, i);
				}
			}else {
				r--;
				if(!JsonArtista.tabelaArtistas.busca(nRequisitados.get(r).name)) {
					this.gerarJsonNRequisitados(nRequisitados.get(r).name, i);
				}
			}
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
}
