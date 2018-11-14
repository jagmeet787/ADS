package Algorithms.Graph;

public class Edge {
	final Node u; // starting vertex
	final Node v;
	final long weight;
	
	public Edge(Node u, Node v, long weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
	}

	public Node getU() {
		return u;
	}

	public Node getV() {
		return v;
	}

	public long getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return "{" + this.getU() + "-(" + this.weight + ")->" + this.getV() + "}";
	}
}
