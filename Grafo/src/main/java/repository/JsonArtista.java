package repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import AVL.ArtistaTree;
import de.umass.lastfm.Artist;

import java.util.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonArtista {
	private HttpURLConnection con;
	public static ArtistaTree tabelaArtistas = new ArtistaTree();
	public static int cont=0;
	
	public JsonArtista() {
	}
	
	public void conectar() {
		try {
			URL url = new URL("http://ws.audioscrobbler.com/2.0");
			try {
				con = (HttpURLConnection)url.openConnection();
				con.setConnectTimeout(10000);
				con.setReadTimeout(10000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Artista[] getSimilares(String artista, String key) throws IOException{
		List<Artist> artistObj=null;
		Artista[] artistas;
		
		if(!tabelaArtistas.busca(artista) && this.cont<=180) {
			cont++;
			System.out.println(cont+":"+artista);
			
			tabelaArtistas.inserir(new Artista(artista));
			
			artistas= new Artista[10];
			try {
			artistObj = (List<Artist>) Artist.getSimilar(artista, 10, key);
			}catch(Exception e) {
				try {
					Thread.sleep(30000);
					return new Artista[0];
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(this.cont%200==0) {
				try {
					Thread.sleep(30000);
				}catch(Exception e) {
					e.printStackTrace();					
				}
			}
			
			if(artistObj==null) {
				return new Artista[0];
			}
			
			for(int i=0; i<artistas.length; i++) {
				try {
					if(artistObj.size()>0) {
						artistas[i] = new Artista(artistObj.get(i).getName());
					}
				}catch(Exception e){
					return new Artista[0];
				}
			}
			
			return artistas;
		}
		return new Artista[0];

	}
	private String getJsonAttribute(String json, String atr) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode obj = mapper.readTree(json);
		JsonNode desiredString = obj.get(atr);
		
		return desiredString.toString();
		
	}
	public Artista[] toArray(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, Artista[].class);
	}
	
	public Artista[] fromJson() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode obj = mapper.readTree(new File("dadosteste.json"));
		
		String artistasJson = this.getJsonAttribute(obj.get("similarartists").toString(), "artist");
		
		return this.toArray(artistasJson);
		
	}
}
