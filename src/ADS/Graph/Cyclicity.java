package ADS.Graph;

import java.util.LinkedList;
import java.util.ListIterator;

public class Cyclicity {
	
	static LinkedList<Integer>[] adjList;
	static boolean[] visited;
	
	private static boolean _isCyclic(int vertex, int parent) {
		visited[vertex] = true;
		ListIterator<Integer> it = adjList[vertex].listIterator();
		while ( it.hasNext() ) {
			int node = it.next();
			if ( !visited[node] ) {
				if ( _isCyclic(node, vertex) ) return true;
			}
			else if ( visited[node] && node != parent ) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isCyclic(Graph graph) {
		adjList = graph.getAdjList();
		int numberOfVertices = graph.getNumberOfVertices();
		visited = new boolean[numberOfVertices];
		for ( int i = 0; i < numberOfVertices; i++) {
			if ( !visited[i] ) {
				if ( _isCyclic(i, -1) ) return true;
			}
		}
		return false;
	}
}
