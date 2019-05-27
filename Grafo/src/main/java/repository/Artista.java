package repository;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Artista implements Comparable<Artista>{
	public String name;
	
	public Artista(@JsonProperty("name") String name) {
		this.name = name;
	}
	public String getNome() {
		return name;
	}
	public int compareTo(Artista artista) {
		return this.name.compareTo(artista.name);
	}
	public boolean equals(Artista artista) {
		return this.name.equals(artista.name);
	}
	public int hashCode() {
		return 1;
	}
}
