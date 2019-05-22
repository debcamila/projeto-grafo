import com.fasterxml.jackson.annotation.JsonProperty;

public class Artista {
	private String name;
	
	public Artista(@JsonProperty("name") String name) {
		this.name = name;
	}
	public String getNome() {
		return name;
	}
}
