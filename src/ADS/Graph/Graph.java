package ADS.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// Adjacency list representation of graph

public class Graph {
	
	HashMap<Node, LinkedList<Edge>> adjList;
	final boolean directed;
	final boolean weighted;
	
	public Graph ( boolean directed, boolean weighted ) {
		adjList = new HashMap<Node, LinkedList<Edge>>();
		this.directed = directed;
		this.weighted = weighted;
	}
	
	public boolean addNode( Node n ) {
		if ( n == null ) {
			System.err.println("null node.");
			return false;
		}
		if ( adjList.containsKey(n) ) {
			System.err.println(n + " already exists.");
			return false;
		}
		adjList.put(n, new LinkedList<Edge>());
		return true;
	}

	public boolean addEdge ( Edge e ) {
		System.out.println("Adding edge " + e);
		if ( e == null ) {
			System.err.println( "edge can't be null " );
			return false;
		}
		if ( !adjList.containsKey(e.getU()) || !adjList.containsKey(e.getV()) ) {
			if ( !adjList.containsKey(e.getU()) && !adjList.containsKey(e.getV())) 
				System.err.println("Node " + e.getU() + " and " + e.getV() + " doesn't exist in the graph.");
			else if ( !adjList.containsKey(e.getU()) )
				System.err.println("Node " + e.getU() + " doesn't exist in the graph.");
			else 
				System.err.println("Node " + e.getV() + " doesn't exist in the graph.");		
			return false;
		}
		LinkedList<Edge> edgeList = adjList.get(e.getU());
		if ( edgeList.contains(e) ) {
			System.err.println("Edge " + e + " already exists!");
			return false;
		}
		edgeList.add(e);
		adjList.put(e.getU(), edgeList);
		if ( !directed ) {
			edgeList = adjList.get(e.getV());
			edgeList.add(new Edge(e.getV(), e.getU(), e.getWeight()));
			adjList.put(e.getV(), edgeList);
		}
		return true;
	}
	
	public boolean removeEdge ( Edge e ) {
		if ( !adjList.containsKey(e.getU()) || !adjList.containsKey(e.getV()) ) 
			return false;
		LinkedList<Edge> edgeList = adjList.get(e.getU());
		if ( !edgeList.contains(e) ) return false;
		edgeList.remove(e);
		adjList.put(e.getU(), edgeList);
		if ( !directed ) {
			edgeList = adjList.get(e.getV());
			if ( !edgeList.contains(e) ) return false;
			edgeList.remove(new Edge(e.getV(), e.getU(), e.getWeight()));
			adjList.put(e.getV(), edgeList);
		}
		return true;
	}
	
	public boolean isConnected ( Node u, Node v ) {
		if ( !adjList.containsKey(u) || !adjList.containsKey(v) ) 
			return false;
		LinkedList<Edge> edgeList = adjList.get(u);
		for (Edge edge : edgeList) {
			if ( edge.getU().equals(u) && edge.getV().equals(v) ) 
				return true;
		}
		return false;
	}
	
	public int getNumberOfVertices() {
		return adjList.size();
	}
	// returns deep copy of the edge list of n node
	public LinkedList<Edge> getEdgeList( Node n ) {
		if ( !adjList.containsKey(n) ) return null;
		LinkedList<Edge> edgeList = new LinkedList<Edge>();
		for (Edge edge : adjList.get(n)) {
			edgeList.add(edge);
		}
		return edgeList;
	}
	
	// returns deep copy of the adjList of the graph
	public HashMap<Node, LinkedList<Edge>> getAdjList() {
		HashMap<Node, LinkedList<Edge>> adjL = new HashMap<Node, LinkedList<Edge>>();
		for (Node node : adjList.keySet()) {
			adjL.put(node, this.getEdgeList(node));
		}
		return adjL;
	}
	
	public Set<Node> getNodes() {
		HashSet<Node> hs = new HashSet<Node>();
		for (Node n : adjList.keySet()) hs.add(n);
		return hs;
	}
	
	@Override
	public String toString ( ) {
		StringBuilder result = new StringBuilder();
		result.append("{\n");
		for (Node n : adjList.keySet()) {
			result.append("[" + n + " = ");
			result.append(adjList.get(n));
			result.append("]\n");
		}
		result.append("}");
		return result.toString();
	}
}
