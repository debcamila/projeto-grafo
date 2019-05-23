import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonArtista {
	private HttpURLConnection con;
	
	public JsonArtista() {
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
	public Artista[] getSimilares(String artist, String key) throws IOException{
		String params = "method=artist.getsimilar&artist="+artist+"&api_key="+key+"&format=json";
		String jsonContent;
		DataOutputStream out;
		BufferedReader in;
		File file = new File("datasets\\"+artist+".json");
		FileWriter writer;
		BufferedWriter buWriter;
		
		
		if(!file.exists()){
			file.createNewFile();
			/*try {
				con.setDoOutput(true);
				out = new DataOutputStream(con.getOutputStream());
				out.writeBytes(params);
				out.flush();
				out.close();
				
				con.setConnectTimeout(50000);
				con.setReadTimeout(50000);
				
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				jsonContent = in.readLine();
								
				con.disconnect();
				in.close();
				
				String artistasJson = this.getJsonAttribute(this.getJsonAttribute(jsonContent, "similarartists"), "artist");
				
				System.out.println(artistasJson);
				
				writer = new FileWriter(file);
				
				buWriter = new BufferedWriter(writer);
				buWriter.write(artistasJson);
				
				buWriter.flush();
				buWriter.close();
				writer.flush();
				writer.close();
				
				return toArray(artistasJson);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
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
