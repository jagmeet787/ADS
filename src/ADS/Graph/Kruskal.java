package ADS.Graph;

import java.util.Arrays;
import java.util.Set;

public class Kruskal {
	public static Graph kruskalMST(Graph graph) {
		Graph mst = new Graph(graph.isDirected(), graph.isWeighted());
		for (Node n : graph.getNodes())
			mst.addNode(new Node(n.getNodeNumber(), n.getLabel()));
		Set<Edge> edgeSet = graph.getEdges();
		Edge[] edges = edgeSet.toArray( new Edge[edgeSet.size()] );
		Arrays.sort(edges);
		for (Edge e : edges) {
			// if  adding this in mst don't create cycle then add in mst
			if ( !UnionFind.connected(e.getU(), e.getV()) ) {
				UnionFind.union(e.getU(), e.getV());
				mst.addEdge(e);
				if ( mst.getNumberOfEdges() == mst.getNumberOfVertices() - 1) break;
			}
		}
		graph.resetNodes();
		return mst;
	}
}
