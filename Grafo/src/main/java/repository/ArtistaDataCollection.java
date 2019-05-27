package repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import AVL.ArtistaTree;

public class ArtistaDataCollection {
	private List<ArtistaData> listArtistaData;
	
	public ArtistaDataCollection() {
		this.listArtistaData = new ArrayList<ArtistaData>();
		this.gerarCollection();
	}
	final void gerarCollection() {
		File file = new File("datasets");
		File[] arquivos = file.listFiles();
		TestarJson lerJson = new TestarJson();
		
		for(File item : arquivos) {
			System.out.println("1 - "+item.getName());
			lerJson.lerJson(item.getName());
			listArtistaData.add(lerJson.CarregarDataSet());
		}
		
	}
	public ArtistaTree pegarArvore() {
		ArtistaTree tree = new ArtistaTree();
		for(ArtistaData item : listArtistaData) {
			item.inserirNaArvore(tree);
		}
		return tree;
	}
	public ArtistaTree requisicoes() {
		ArtistaTree tree = new ArtistaTree();
		
		for(ArtistaData item : listArtistaData) {
			item.verRequisicoes(tree);
		}
		
		return tree;
	}
	public List<Artista> verNaoRequisitados(){
		List<Artista> nRequisitados = new ArrayList();
		ArtistaTree requests = this.requisicoes();
		
		for(ArtistaData item : listArtistaData) {
			item.verNaoRequisitadosNode(requests, nRequisitados);
		}
		
		return nRequisitados;
	}
	public void gerarVertices(Graph<String, DefaultEdge> grafo){
		int percent=0;
		int cont=0;
		ArtistaTree tree = this.pegarArvore();
		
		List<Artista> artistas = tree.toList();
		
		
		for(Artista item : artistas) {
			cont++;
			grafo.addVertex(item.name);
			if((cont%(artistas.size()/10))==0) {
				percent++;
				System.out.println("Adicionados:"+percent+"0%");
			}
		}		
	}
	public void gerarArestas(Graph<String, DefaultEdge> grafo) {
		for(ArtistaData artista : listArtistaData) {
			artista.gerarArestas(grafo);
		}
	}
}
