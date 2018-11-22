package ADS.Graph;

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
	
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (!Edge.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Edge that = (Edge) obj;
        
        if ((this.getU() == null) ? (that.getU() != null) : !this.getU().equals(that.getU())) {
            return false;
        }
        
        if ((this.getV() == null) ? (that.getV() != null) : !this.getV().equals(that.getV())) {
            return false;
        }

        if (this.getWeight() != that.getWeight()) {
            return false;
        }

        return true;
    }
}
