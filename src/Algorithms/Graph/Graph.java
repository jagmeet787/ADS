package Algorithms.Graph;

import java.util.Iterator;
import java.util.LinkedList;

// Adjacency list representation of graph

public class Graph {

	Integer numberOfVertices;
	LinkedList<Integer>[] adjList;
	final Boolean directed;

	public Graph ( Integer numberOfVertices ) {
		this.numberOfVertices = new Integer(numberOfVertices.intValue());
		adjList = new LinkedList[numberOfVertices];
		for ( int i = 0; i < numberOfVertices; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
		this.directed = true;
	}

	public LinkedList<Integer>[] getAdjList() {
		return adjList;
	}

	public Graph ( Integer numberOfVertices, Boolean directed ) {
		this.numberOfVertices = numberOfVertices;
		adjList = new LinkedList[numberOfVertices];
		for ( int i = 0; i < numberOfVertices; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
		this.directed = directed;
	}
	
	public Integer getNumberOfVertices() {
		return numberOfVertices;
	}


	public void setNumberOfVertices(Integer numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
	}

	public void addEdge ( Integer u, Integer v ) {
		adjList[u].add(v);
		if ( !directed ) adjList[v].add(u);
	}

	public void removeEdge ( Integer u, Integer v ) {
		adjList[u].remove(v);
		if ( !directed ) adjList[v].remove(u);
	}
	
	public Boolean isConnected ( Integer u, Integer v ) {
		if ( !this.contains(u) && !this.contains(v)) return false;
		Iterator<Integer> it = this.adjList[u].listIterator();
		while ( it.hasNext() ) {
			Integer node = it.next();
			if ( node.equals(v) ) return true; 
		}
		return false;
	}
	
	public Boolean contains ( Integer node ) {
		if ( node.intValue() < 0 && 
				node.intValue() > this.numberOfVertices.intValue())
			return false;
		return true;
	}

	@Override
	public String toString ( ) {
		StringBuilder result = new StringBuilder();
		for ( int v = 0; v < this.numberOfVertices; v++) {
			result.append("Adjacency List of vertex " + v);
			result.append("head ");
			for ( Integer u : adjList[v]) {
				result.append(" -> " + u);
			}
			result.append('\n');
		}
		return result.toString();
	}

}
