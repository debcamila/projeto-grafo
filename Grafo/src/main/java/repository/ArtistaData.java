package repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	public ArtistaTree pegarArvore() {
		ArtistaTree tree = new ArtistaTree();
		this.inserirNaArvore(tree);
		return tree;
	}
	void inserirNaArvore(ArtistaTree tree) {
		for(int i=0; i<this.similar.length; i++) {
			if(!tree.busca(similar[i].name) || similar[i].similar.length>0) {
				tree.inserir(similar[i]);
				similar[i].inserirNaArvore(tree);
			}
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
	public void verNaoRequisitados() {
		File file = new File("nRequisitados.txt");
		FileWriter fileWriter;
		BufferedWriter bWriter;
		int cont=0;
				
		List<Artista> nRequisitados = new ArrayList();
		ArtistaTree requests = this.requisicoes();
		this.verNaoRequisitadosNode(requests, nRequisitados);
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		try {
			fileWriter = new FileWriter(file);
			bWriter = new BufferedWriter(fileWriter);
			
			for(Artista item : nRequisitados) {
				cont++;
				bWriter.write(item.name);
				bWriter.newLine();
				if(cont%100==0) {
					bWriter.flush();
				}
			}
			bWriter.flush();
			bWriter.close();
			fileWriter.close();			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println(cont);
		
		
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
}
