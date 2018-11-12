package Algorithms.Graph;

import java.util.Iterator;
import java.util.LinkedList;

// Adjacency list representation of graph

public class Graph {

	final Integer numberOfVertices;
	LinkedList<Integer>[] adjList;
	final Boolean directed;

	@SuppressWarnings("unchecked")
	public Graph ( Integer numberOfVertices ) {
		this.numberOfVertices = new Integer(numberOfVertices.intValue());
		adjList = new LinkedList[this.numberOfVertices.intValue()];
		for ( int i = 0; i < numberOfVertices; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
		this.directed = true;
	}

	public LinkedList<Integer>[] getAdjList() {
		return adjList;
	}

	@SuppressWarnings("unchecked")
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

	public void addEdge ( Integer u, Integer v ) {
		if ( !this.contains(u) && !this.contains(v)) return;
		adjList[u].add(v);
		if ( !directed ) adjList[v].add(u);
	}

	public void removeEdge ( Integer u, Integer v ) {
		if ( !this.contains(u) && !this.contains(v)) return;
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
			result.append("{[" + v);
			result.append("]");
			for ( Integer u : adjList[v]) {
				result.append(" -> " + u);
			}
			result.append("} ");
		}
		return result.toString();
	}

}
