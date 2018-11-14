package Algorithms.Graph;

import java.util.HashMap;
import java.util.LinkedList;

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
		if ( adjList.containsKey(n) ) return false;
		adjList.put(n, new LinkedList<Edge>());
		return true;
	}

	public boolean addEdge ( Edge e ) {
		if ( !adjList.containsKey(e.getU()) || !adjList.containsKey(e.getV()) ) 
			return false;
		LinkedList<Edge> edgeList = adjList.get(e.getU());
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
	
	@Override
	public String toString ( ) {
		StringBuilder result = new StringBuilder();
		for (Node n : adjList.keySet()) {
			result.append("[" + n + " = ");
			result.append(adjList.get(n));
			result.append("]\n");
		}
		return result.toString();
	}

}
