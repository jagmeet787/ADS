package ADS.Graph;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class BFS {		
	public static String breadthFirstSearch ( Graph graph, Node root ) {
		if ( !graph.contains(root) ) {
			System.err.println("Graph don't have node " + root);
			return "[]";
		}
		HashMap<Node, LinkedList<Edge>> adjList = graph.getAdjList();
		LinkedList<Node> queue = new LinkedList<Node>();
		Node start = new Node(root);
		start.setVisited(true);
		queue.add(start);
		
		StringBuilder result = new StringBuilder();
		result.append("[");
		while(!queue.isEmpty()) {
			start = queue.poll();
			result.append(start + " ");
			Iterator<Edge> it = adjList.get(start).listIterator();
			while(it.hasNext()) {
				Node node = it.next().getV();
				if(!node.isVisited()) {
					node.setVisited(true);;
					queue.add(node);
				}
			}
		}
		result.append("\b]");
		graph.resetNodes();
		return result.toString();
	}
	
	// return BFS of complete graph in String
	// System.out.println("BFS of all connected components : " + BFS.breadthFirstSearch(g));
	public static String breadthFirstSearch( Graph graph ) {
		HashMap<Node, LinkedList<Edge>> adjList = graph.getAdjList();
		LinkedList<Node> queue = new LinkedList<Node>();

		StringBuilder result = new StringBuilder();
		for ( Node n : adjList.keySet() ) {
			if ( !n.isVisited() ) {
				
				result.append("[");
				
				Node start = n;
				
				start.setVisited(true);
				queue.add(start);
				
				while(!queue.isEmpty()) {
					start = queue.poll();
					result.append(start + " ");
					Iterator<Edge> it = adjList.get(start).listIterator();
					
					while(it.hasNext()) {
						Node node = it.next().getV();
						if(!node.isVisited()) {
							node.setVisited(true);
							queue.add(node);
						}
					}
				}
				result.append("\b] ");
			}
		}
		graph.resetNodes();
		return result.toString();
	}
	
	public static String levelOfEachNode ( Graph graph, Node start ) {
		if ( !graph.contains(start) ) {
			System.err.println("Graph don't have node " + start);
			return "[]";
		}
		HashMap<Node, LinkedList<Edge>> adjList = graph.getAdjList();
		LinkedList<Node> queue = new LinkedList<Node>();
		StringBuilder result = new StringBuilder();
		start.setVisited(true);
		start.setLevel(1);
		queue.add(start);
		result.append("(Node, Level) [");
		while(!queue.isEmpty()) {
			start = queue.poll();
			result.append("(" + start + " " + start.getLevel() + ") ");
			Iterator<Edge> it = adjList.get(start).listIterator();
			while(it.hasNext()) {
				Node node = it.next().getV();
				if(!node.isVisited()) {
					node.setLevel(start.getLevel() + 1);
					node.setVisited(true);
					queue.add(node);
				}
			}
		}
		result.append("\b]");
		graph.resetNodes();
		return result.toString();
	}
}
