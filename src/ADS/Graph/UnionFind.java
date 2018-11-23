package ADS.Graph;

import java.util.ArrayList;

public class UnionFind {

	public static boolean connected(Node p, Node q) {
		return root(p) == root(q);
	}

	// only the path compression is not the answer also implement
	// rank
	private static Node root(Node e) {
		// list to store all the nodes that are at lower level in tree
		// then storing the root in each node for path compression
		ArrayList<Node> list = new ArrayList<Node>();
		while ( e != e.getParent() ) {
//			this.id[e] = this.id[this.id[e]]; // path compression
			list.add(e);
			e = e.getParent();
		}
		for(Node n : list) {
			n.setParent(e);
//			this.rank[e] = update rank?
		}
		return e;
	}

	public static void union(Node p, Node q) {
		Node rootP = root(p);
		Node rootQ = root(q);
		if ( rootP.getRank() < rootQ.getRank() )
			rootP.setParent(rootQ);
		else if ( rootP.getRank() > rootQ.getRank() )
			rootQ.setParent(rootP);
		else {
			rootQ.setParent(rootP);
			rootP.setRank(rootP.getRank() + 1);
		}
	}
}
