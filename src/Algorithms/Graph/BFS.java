package Algorithms.Graph;
import java.util.Iterator;
import java.util.LinkedList;

public class BFS {		
	public static void breathFirstSearch ( Graph graph, int start ) {
		boolean[] visited = new boolean[graph.getNumberOfVertices().intValue()];
		LinkedList<Integer>[] adjList = graph.getAdjList();
		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[start] = true;
		queue.add(start);

		System.out.print("BFS traversal [");
		while(!queue.isEmpty()) {
			start = queue.poll();
			System.out.print(start + " ");
			Iterator<Integer> it = adjList[start].listIterator();
			while(it.hasNext()) {
				int node = it.next();
				if(!visited[node]) {
					visited[node] = true;
					queue.add(node);
				}
			}
		}
		System.out.print("\b]");
	}
}
