package Algorithms.Graph;

public class Node {
	final int nodeNumber; // node number
	final String label; // label or name of the node
	int discoveryTime; // discovery time used in DFS
	int finishingTime; // finishing time "    "   "
	boolean visited; // if the node is visited in a traversal
	enum colors { UNCOLORED, RED, BLUE, GREEN; }
	colors color;

	public Node(int nodeNumber, String label) {
		this.nodeNumber = nodeNumber;
		this.label = label;
		reset();
	}

	public void reset() {
		this.discoveryTime = this.finishingTime = -1;
		visited = false;
		this.color = colors.UNCOLORED;
	}

	public int getDiscoveryTime() {
		return discoveryTime;
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
		 return "(" + this.nodeNumber + " " + this.label + ")";
	}

}
