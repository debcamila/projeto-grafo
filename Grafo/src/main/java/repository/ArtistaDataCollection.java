package repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import AVL.ArtistaTree;

public class ArtistaDataCollection {
	private List<ArtistaData> listArtistaData;
	
	public ArtistaDataCollection() {
		this.listArtistaData = new ArrayList<ArtistaData>();
	}
	public void gerarCollection() {
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
}
