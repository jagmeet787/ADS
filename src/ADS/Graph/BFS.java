package ADS.Graph;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;

import javax.naming.spi.DirStateFactory.Result;

public class BFS {		
	public static String breathFirstSearch ( Graph graph, int start ) {
		boolean[] visited = new boolean[graph.getNumberOfVertices()];
		LinkedList<Integer>[] adjList = graph.getAdjList();
		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[start] = true;
		queue.add(start);
		
		StringBuilder result = new StringBuilder();
		result.append("[");
		while(!queue.isEmpty()) {
			start = queue.poll();
			result.append(start + " ");
			Iterator<Integer> it = adjList[start].listIterator();
			while(it.hasNext()) {
				int node = it.next();
				if(!visited[node]) {
					visited[node] = true;
					queue.add(node);
				}
			}
		}
		result.append("\b]");
		return result.toString();
	}
	
	// return BFS of complete graph in String
	// System.out.println("BFS of all connected components : " + BFS.breadthFirstSearch(g));
	public static String breadthFirstSearch( Graph graph ) {
		boolean[] visited = new boolean[graph.getNumberOfVertices()];
		LinkedList<Integer>[] adjList = graph.getAdjList();
		LinkedList<Integer> queue = new LinkedList<Integer>();

		StringBuilder result = new StringBuilder();
		for ( int i = 0; i < graph.getNumberOfVertices(); i++) {
			if ( !visited[i] ) {
				
				result.append("[");
				
				int start = i;
				visited[start] = true;
				queue.add(start);
				
				while(!queue.isEmpty()) {
					start = queue.poll();
					result.append(start + " ");
					Iterator<Integer> it = adjList[start].listIterator();
					
					while(it.hasNext()) {
						int node = it.next();
						if(!visited[node]) {
							visited[node] = true;
							queue.add(node);
						}
					}
				}
				result.append("\b]");
			}
		}
		return result.toString();
	}
	
	public static String levelOfEachNode ( Graph graph, int start ) {
		boolean[] visited = new boolean[graph.getNumberOfVertices()];
		LinkedList<Integer>[] adjList = graph.getAdjList();
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int[] level = new int[graph.getNumberOfVertices()];
		StringBuilder result = new StringBuilder();
		
		visited[start] = true;
		level[start] = 1;
		queue.add(start);
		result.append("(Node, Level) [");
		while(!queue.isEmpty()) {
			start = queue.poll();
			result.append("(" + start + " " + level[start] + ") ");
			Iterator<Integer> it = adjList[start].listIterator();
			while(it.hasNext()) {
				int node = it.next();
				if(!visited[node]) {
					level[node] = level[start] + 1;
					visited[node] = true;
					queue.add(node);
				}
			}
		}
		result.append("\b]");
		return result.toString();
	}
}
