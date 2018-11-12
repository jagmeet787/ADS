package Algorithms.Graph;

import java.util.LinkedList;
import java.util.ListIterator;

public class DFS {
	
	static Graph G;
	static boolean[] visited;
	static LinkedList<Integer>[] adjList;
	
	private static void _depthFirstSearch ( Integer node ) {
		visited[node] = true;
		System.out.print( node + " ");
		ListIterator<Integer> it = adjList[node].listIterator();
		while ( it.hasNext() ) {
			Integer temp = it.next();
			if ( !visited[temp] ) {
				_depthFirstSearch(temp);
			}
		}
	}

	public static void depthFirstSearch ( Graph graph, Integer start ) {
		if ( !graph.contains(start) ) return;
		G = graph; 
		visited = new boolean[ graph.getNumberOfVertices().intValue() ];
		adjList = G.getAdjList();
		System.out.print("[");
		_depthFirstSearch(start);
		System.out.println("\b]");
	}

}
