package ADS.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Bipartite {
	static int[] color;
	static LinkedList<Integer>[] adjList;
	private static boolean _isBipertite(int i) {
		color[i] = 1;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(i);
		while ( !queue.isEmpty() ) {
			int u = queue.poll();
			ListIterator<Integer> it = adjList[u].listIterator();
			while ( it.hasNext() ) {
				int v = it.next();
				if ( color[v] == -1 ) {
					color[v] = 1 - color[u];
					queue.push(v);
				}
				else if ( color[u] == color[v] ) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isBipartite(Graph graph) {
		// -1 is not visited or uncolored
		// 0 is red
		// 1 is blue
		color = new int[graph.getNumberOfVertices()];
		Arrays.fill(color, -1);
		
		adjList = graph.getAdjList();
		
		for (int i = 0; i < graph.getNumberOfVertices(); i++) {
			if ( color[i] == -1 ) {
				if ( _isBipertite( i ) == false ) return false;
			}
		}
		return true;
	}
}
