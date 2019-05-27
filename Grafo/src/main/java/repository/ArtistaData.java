package repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import com.fasterxml.jackson.annotation.JsonProperty;

import AVL.ArtistaTree;

public class ArtistaData extends Artista {
	private ArtistaData[] similar;
	private static int cont=0;
	
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
	public ArtistaTree pegarArvore() {
		ArtistaTree tree = new ArtistaTree();
		this.inserirNaArvore(tree);
		return tree;
	}
	void inserirNaArvore(ArtistaTree tree) {
		tree.inserir(this);
		for(int i=0; i<this.similar.length; i++) {
			similar[i].inserirNaArvore(tree);
		}
	}
	public ArtistaTree requisicoes() {
		ArtistaTree tree = new ArtistaTree();
		this.verRequisicoes(tree);
		return tree;
	}
	public void verRequisicoes(ArtistaTree tree) {
		if(similar.length>0) {
			tree.inserir(this);
		}
		for(int i=0; i<this.similar.length; i++) {
			similar[i].verRequisicoes(tree);
		}
	}
	public List<Artista> verNaoRequisitadosList(){
		List<Artista> nRequisitados = new ArrayList();
		ArtistaTree requests = this.requisicoes();
		this.verNaoRequisitadosNode(requests, nRequisitados);
		
		return nRequisitados;
	}
	public void verNaoRequisitadosNode(ArtistaTree tree, List<Artista> nRequisitados) {
		if(similar.length==0) {
			if(!tree.busca(this.name)) {
				tree.inserir(this);
				nRequisitados.add(this);
			}
		}
		for(int i=0; i<this.similar.length; i++) {
			similar[i].verNaoRequisitadosNode(tree, nRequisitados);
		}
	}
	public void gerarArestas(Graph<String, DefaultEdge> grafo) {
		for(ArtistaData artistaSimilar : similar) {
			if(!this.name.equals(artistaSimilar.name)) {
				if(!grafo.containsEdge(this.name, artistaSimilar.name)) {
					grafo.addEdge(this.name, artistaSimilar.name);
				}
			}
			artistaSimilar.gerarArestas(grafo);
		}
	}
}

