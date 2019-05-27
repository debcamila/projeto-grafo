package grafo;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import repository.Artista;
import repository.ArtistaDataCollection;

public class Grafo {
	private static Graph<Artista, DefaultEdge> graph;
	
	public Grafo() {
		graph = new SimpleGraph(DefaultEdge.class);
		ArtistaDataCollection collection = new ArtistaDataCollection();
		
		collection.gerarVertices(graph);
		
		collection.gerarArestas(graph);
	}
}
