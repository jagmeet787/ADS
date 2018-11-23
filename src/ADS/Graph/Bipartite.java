package ADS.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import ADS.Graph.Node.colors;

public class Bipartite {
	static HashMap<Node, LinkedList<Edge>> adjList;
	private static boolean _isBipertite(Node i) {
		i.setColor(colors.GREEN);
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(i);
		while ( !queue.isEmpty() ) {
			Node u = queue.poll();
			ListIterator<Edge> it = adjList.get(u).listIterator();
			while ( it.hasNext() ) {
				Node v = it.next().getV();
				if ( v.getColor() == colors.UNCOLORED ) {
					if ( u.getColor() == colors.GREEN ) 
						v.setColor(colors.RED);
					else v.setColor(colors.GREEN);
					queue.push(v);
				}
				else if ( u.getColor() == v.getColor() ) {
					System.out.println(u + " and " + v + " has same " + u.getColor() + " and " + v.getColor() + " color");
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isBipartite(Graph graph) {
		if (graph.isDirected()) {
			System.err.println("Works only for undirected graphs.");
			return false;
		}
		adjList = graph.getAdjList();
		for ( Node n : adjList.keySet() ) {
			if ( n.getColor() == colors.UNCOLORED ) {
				if ( _isBipertite( n ) == false ) return false;
			}
		}
		graph.resetNodes();
		return true;
	}
}
