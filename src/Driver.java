
import ADS.Graph.*;

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
		g.addEdge(new Edge(n1, n3, 7));
		Node nr = new Node(4, "A");
		Node nt = new Node(1, "C");
		if ( nt.equals(n1) ) System.out.println("Yes " + nt.hashCode() + " " + nr.hashCode());
		else System.out.println("No " + nt.hashCode() + " " + nr.hashCode());
		g.addNode(nr);
		g.addNode(nt);
		g.addEdge(new Edge(nr, nt, 8));
		System.out.println(g);
		
	}
}
