package ADS.Graph;

public class Node {
	// Unique number identifying a node it must be unique 
	// in a graph
	final int nodeNumber; // node number
	final String label; // label or name of the node
	int discoveryTime; // discovery time used in DFS
	int finishingTime; // finishing time "    "   "
	boolean visited; // if the node is visited in a traversal
	int inDegree;
	int outDegree;
	int level; // used in bfs
	// for union find
	Node parent;
	int rank;
	enum colors { UNCOLORED, RED, BLUE, GREEN; }
	colors color;

	public Node(int nodeNumber, String label) {
		this.nodeNumber = nodeNumber;
		this.label = label;
		this.inDegree = 0;
		this.outDegree = 0;
		reset();
	}
	
	public Node(Node that) {
		this.nodeNumber = that.getNodeNumber();
		this.label = that.getLabel();
		this.inDegree = that.getInDegree();
		this.outDegree = that.getOutDegree();
		reset();
	}

	public void reset() {
		this.level = 0;
		this.discoveryTime = this.finishingTime = -1;
		visited = false;
		this.parent = this;
		this.rank = 0;
		this.color = colors.UNCOLORED;
	}

	public int getInDegree() {
		return inDegree;
	}

	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	public int getOutDegree() {
		return outDegree;
	}

	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}
	
	public int getDegree() {
		return this.getInDegree() + this.getOutDegree();
	}

	public int getDiscoveryTime() {
		return discoveryTime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setDiscoveryTime(int discoveryTime) {
		this.discoveryTime = discoveryTime;
	}

	public int getFinishingTime() {
		return finishingTime;
	}

	public void setFinishingTime(int finishingTime) {
		this.finishingTime = finishingTime;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public colors getColor() {
		return color;
	}

	public void setColor(colors color) {
		this.color = color;
	}

	public int getNodeNumber() {
		return nodeNumber;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		 return "{" + this.nodeNumber + "-" + this.label + "}";
	}
	
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Node.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Node that = (Node) obj;
        if (this.getNodeNumber() != that.getNodeNumber()) {
            return false;
        }
//        if (!this.getLabel().equals(that.getLabel())) {
//        	return false;
//        }

        return true;
    }
    
    @Override
    public int hashCode() {
    	return this.getNodeNumber();
    }

}
