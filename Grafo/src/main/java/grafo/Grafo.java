package grafo;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.scoring.ClusteringCoefficient;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import repository.Artista;
import repository.ArtistaDataCollection;

public class Grafo {
	private static Graph<String, DefaultEdge> graph;
	
	public Grafo() {
		graph = new SimpleGraph(DefaultEdge.class);
		ArtistaDataCollection collection = new ArtistaDataCollection();
		
		collection.gerarVertices(graph);
		
		collection.gerarArestas(graph);
	}
	
	public void menorCaminho(String artista1, String artista2) {
		ClusteringCoefficient dijkstra = new ClusteringCoefficient(graph);
		
		double path = dijkstra.getVertexScore(artista2);
		
		System.out.println(path);
		                 
	
		
		
	}
	
}
