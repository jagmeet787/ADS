
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import Algorithms.Graph.*;

public class Driver {
	public static void main(String[] args) {
		Graph g = new Graph(false, false);
		Node n1 = new Node(1, "A");
		Node n2 = new Node(2, "B");
		Node n3 = new Node(3, "C");
		Node n4 = new Node(4, "D");
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addEdge(new Edge(n1, n2, 5));
		g.addEdge(new Edge(n2, n3, 6));
		g.addEdge(new Edge(n1, n3, 7));
		System.out.println(g);
		HashMap<Node, LinkedList<Edge>> adjList = g.getAdjList();
		adjList.remove(n4);
		System.out.println(g);
		System.out.println(adjList);
		System.out.println(g.getAdjList());
		Set<Node> hs = g.getNodes();
		hs.remove(n4);
		System.out.println(hs);
		System.out.println(g.getNodes());
		System.out.println(g.getAdjList());
	}
}
