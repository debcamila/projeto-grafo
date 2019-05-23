import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GerarDataSet {
	private JsonArtista artistas;
	private int interacoesMaximas;
	
	public GerarDataSet(int interacoesMaximas) {
		this.artistas = new JsonArtista();
		this.interacoesMaximas = interacoesMaximas;
	}
	
	public void gerarJson(int interacoes) throws IOException {
		String jsonContent = this.gerarDataSet(interacoes);
		File file = new File("dataset.json");
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
		
		if(interacoes<interacoesMaximas) {
			artistas = this.artistas.getSimilares(artista, "ba44ff677696c0b89159d566ed476981");
			if(artistas!=null) {
				for(Artista item : artistas) {
					if(cont==artistas.length) {
						artistaContent+=this.gerarDataSet(item.getNome(), (interacoes+1));
					}else {
						artistaContent+=this.gerarDataSet(item.getNome(), (interacoes+1))+",";
					}
					cont++;
				}
			}
		}
		return artistaContent + "]}";
		
	}
	public String gerarDataSet(int interacoes) throws IOException {
		String artistaContent = "{\"name\": \"cher\",\"similar\": [";
		Artista[] artistas=null;
		int cont=1;
		
		if(interacoes<interacoesMaximas) {
			artistas = this.artistas.fromJson();
			if(artistas!=null) {
				for(Artista item : artistas) {
					if(cont==artistas.length) {
						artistaContent+=this.gerarDataSet(item.getNome(), (interacoes+1));
					}else {
						artistaContent+=this.gerarDataSet(item.getNome(), (interacoes+1))+",";
					}
					cont++;
				}
			}
		}
		
		return artistaContent + "]}";
		
	}
}
