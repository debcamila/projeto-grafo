package repository;

import com.fasterxml.jackson.annotation.JsonProperty;

import AVL.ArtistaTree;

public class ArtistaData extends Artista {
	private ArtistaData[] similar;
	
	public ArtistaData(@JsonProperty("name") String name, 
					   @JsonProperty("similar") ArtistaData[] similar) {
		super(name);
		this.similar=similar;
	}
	public void verSimilares() {
		System.out.println(this.name);
		for(int i=0; i<this.similar.length; i++) {
			similar[i].verSimilares();
		}
	}
	public int contarVertices() {
		ArtistaTree tree = new ArtistaTree();
		this.contarVertices(tree);
		return tree.getQtdArtistas();
	}
	void contarVertices(ArtistaTree tree) {
		for(int i=0; i<this.similar.length; i++) {
			if(!tree.busca(similar[i].name)) {
				tree.inserir(similar[i]);
				similar[i].contarVertices(tree);
			}
		}
	}
}
