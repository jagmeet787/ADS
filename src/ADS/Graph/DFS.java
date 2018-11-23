package ADS.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class DFS {
	
	static boolean[] visited;
	static HashMap<Node, LinkedList<Edge>> adjList;
	
	private static void _depthFirstSearch ( Node node ) {
		node.setVisited(true);
		System.out.print( node + " ");
		ListIterator<Edge> it = adjList.get(node).listIterator();
		while ( it.hasNext() ) {
			Node temp = it.next().getV();
			if ( !temp.isVisited() ) {
				_depthFirstSearch(temp);
			}
		}
	}

	public static void depthFirstSearch ( Graph graph, Node start ) {
		if ( !graph.contains(start) ) {
			System.err.println("Graph don't have node " + start);
			return;
		}
		adjList = graph.getAdjList();
		System.out.print("[");
		_depthFirstSearch(start);
		System.out.println("\b]");
	}

}
