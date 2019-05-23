import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadDataSet {
	private HttpURLConnection con;
	
	public LoadDataSet() {
		try {
			URL url = new URL("http://ws.audioscrobbler.com/2.0");
			try {
				con = (HttpURLConnection)url.openConnection();
				con.setConnectTimeout(5000);
				con.setReadTimeout(5000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getSimilares(String artist, String key) throws IOException{
		/*String params = "method=artist.getsimilar&artist="+artist+"&api_key="+key+"&format=json";
		String jsonContent;
		DataOutputStream out;
		BufferedReader in;
		
		try {
			con.setDoOutput(true);
			out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(params);
			out.flush();
			out.close();
			
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			jsonContent = in.readLine();
			
			System.out.println(getJsonAttribute(jsonContent, "similarartists"));
			
			con.disconnect();
					in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String artistasJson = this.getJsonAttributeString(this.getJsonAttribute("a", "similarartists"), "artist");
		
		Artista[] artistas = this.toArray(artistasJson);
		
		for(Artista item : artistas) {
			System.out.println(item.getNome());
		}

	}
	public String getJsonAttribute(String json, String atr) throws IOException {
		String jsonString = "{\"map_\":{\"FirstName\":\"Deepak\",\"LastName\":\"Kabra\"}}";

		ObjectMapper mapper = new ObjectMapper();
		JsonNode obj = mapper.readTree(new File("dadosteste.json"));
		JsonNode desiredString = obj.get(atr);
		
		return desiredString.toString();
		
	}
	public Artista[] toArray(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, Artista[].class);
	}
	public String getJsonAttributeString(String json, String atr) throws IOException {
		String jsonString = "{\"map_\":{\"FirstName\":\"Deepak\",\"LastName\":\"Kabra\"}}";

		ObjectMapper mapper = new ObjectMapper();
		JsonNode obj = mapper.readTree(json);
		JsonNode desiredString = obj.get(atr);
		
		return desiredString.toString();
		
	}
}
