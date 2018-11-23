package ADS.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class Cyclicity {
	
	static HashMap<Node, LinkedList<Edge>> adjList;
	
	private static boolean _isCyclic(Node vertex, Node parent) {
		vertex.setVisited(true);
		ListIterator<Edge> it = adjList.get(vertex).listIterator();
		while ( it.hasNext() ) {
			Node node = it.next().getV();
			if ( !node.isVisited() ) {
				if ( _isCyclic(node, vertex) ) return true;
			}
			else if ( node.isVisited() && node != parent ) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isCyclic(Graph graph) {
		if (graph.isDirected()) {
			System.err.println("Works only for undirected graphs.");
			return false;
		}
		adjList = graph.getAdjList();
		for ( Node i : graph.getNodes() ) {
			if ( !i.isVisited() ) {
				if ( _isCyclic(i, null) ) return true;
			}
		}
		graph.resetNodes();
		return false;
	}
}
