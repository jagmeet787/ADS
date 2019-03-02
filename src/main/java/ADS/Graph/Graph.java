package ADS.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;


// Adjacency list representation of graph

public class Graph {
	
	HashMap<Node, LinkedList<Edge>> adjList;
	HashMap<Integer, Node> nodes;
	int numberOfEdges;
	long weight;
	final boolean directed;
	final boolean weighted;
	
	public Graph ( boolean directed, boolean weighted ) {
		adjList = new HashMap<Node, LinkedList<Edge>>();
		nodes = new HashMap<Integer, Node>();
		this.directed = directed;
		this.weighted = weighted;
		this.numberOfEdges = 0;
		this.weight = 0;
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
		nodes.put(n.getNodeNumber(), n);
		return true;
	}

	public boolean addEdge ( Edge e ) {
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
		Node u = nodes.get(e.getU().getNodeNumber());
		Node v = nodes.get(e.getV().getNodeNumber());
		LinkedList<Edge> edgeList = adjList.get(u);
		if ( edgeList.contains(e) ) {
			System.err.println("Edge " + e + " already exists!");
			return false;
		}
		edgeList.add(e);
		adjList.put(u, edgeList);
		u.setOutDegree(u.getOutDegree() + 1);
		v.setInDegree(v.getInDegree() + 1);
		if ( !directed ) {
			edgeList = adjList.get(v);
			edgeList.add(new Edge(v, u, e.getWeight()));
			adjList.put(v, edgeList);
			v.setOutDegree(v.getOutDegree() + 1);
			u.setInDegree(u.getInDegree() + 1);
		}
		this.numberOfEdges++;
		this.weight += e.getWeight();
		return true;
	}
	
	public int getNumberOfEdges() {
		return numberOfEdges;
	}

	public boolean isDirected() {
		return directed;
	}

	public boolean isWeighted() {
		return weighted;
	}
	
	
	public boolean isEmpty() {
		return (this.getNumberOfVertices() == 0);
	}

	public long getWeight() {
		return weight;
	}

	public boolean removeEdge ( Edge e ) {
		if ( !adjList.containsKey(e.getU()) || !adjList.containsKey(e.getV()) ) 
			return false;
		LinkedList<Edge> edgeList = adjList.get(e.getU());
		if ( !edgeList.contains(e) ) return false;
		edgeList.remove(e);
		adjList.put(e.getU(), edgeList);
		e.getU().setOutDegree(e.getU().getOutDegree() - 1);
		e.getV().setInDegree(e.getV().getInDegree() - 1);
		if ( !directed ) {
			edgeList = adjList.get(e.getV());
			if ( !edgeList.contains(e) ) return false;
			edgeList.remove(new Edge(e.getV(), e.getU(), e.getWeight()));
			adjList.put(e.getV(), edgeList);
			e.getV().setOutDegree(e.getV().getOutDegree() + 1);
			e.getU().setInDegree(e.getU().getInDegree() + 1);
			
		}
		this.numberOfEdges--;
		this.weight -= e.getWeight();
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
	// returns shallow copy of the edge list 
	public LinkedList<Edge> getEdgeList( Node n ) {
		if ( !adjList.containsKey(n) ) return null;
		LinkedList<Edge> edgeList = new LinkedList<Edge>();
		for (Edge edge : adjList.get(n)) {
			edgeList.add(edge);
		}
		return edgeList;
	}
	
	// returns shallow copy of the adjList of the graph
	public HashMap<Node, LinkedList<Edge>> getAdjList() {
		HashMap<Node, LinkedList<Edge>> adjL = new HashMap<Node, LinkedList<Edge>>();
		for (Node node : adjList.keySet()) {
			adjL.put(node, this.getEdgeList(node));
		}
		return adjL;
	}
	
	public Set<Edge> getEdges() {
		HashSet<Edge> edgeSet = new HashSet<Edge>();
		for (Node n : adjList.keySet()) {
			for (Edge e : getEdgeList(n)) {
				edgeSet.add(e);
			}
		}
		return edgeSet;
	}
	
	public Node getNode(int nodeNumber) {
		if (nodes.containsKey(nodeNumber))
			return nodes.get(nodeNumber);
		return null;
	}
	
	public Set<Node> getNodes() {
		HashSet<Node> hs = new HashSet<Node>();
		for (Node n : adjList.keySet()) hs.add(n);
		return hs;
	}
	
	public int size() {
		return this.getNumberOfVertices() + this.numberOfEdges;
	}
	
	public void resetNodes() {
		for ( Node n : adjList.keySet() ) n.reset();
	}
	
	@Override
	public String toString ( ) {
		StringBuilder result = new StringBuilder();
		result.append("{|V| = " + this.getNumberOfVertices() + 
				", |E| = " + this.getNumberOfEdges() + 
				(this.directed ? ", Directed" : ", Undirected") +
				(this.weighted ? ", Weight: " + this.getWeight() : "") + 
				"\n");
		for (Node n : adjList.keySet()) {
			result.append("[" + n + " deg[in: " + n.getInDegree() + " out: " + n.getOutDegree() + "]" + " = ");
			ListIterator<Edge> it = adjList.get(n).listIterator();
			if(it.hasNext()) result.append("[");
			else result.append("[  ");
			while (it.hasNext()) {
				Edge e = it.next();
				result.append(e.getU() + "-" + 
						(this.weighted ? "(" + e.getWeight() + ")": "") + 
						"->" + e.getV() + ", ");
			}
			result.append("\b\b]\n");
		}
		result.append("}");
		return result.toString();
	}

	public boolean contains(Node n) {
		if ( n == null ) return false;
		if ( adjList.containsKey(n) ) return true;
		return false;
	}
}
