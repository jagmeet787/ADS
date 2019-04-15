package ADS.DataStructures;

import java.util.Arrays;

// weighted union find with path compression log*(n)
public class UnionFind {
	private int[] id;
	private int[] size;
	private int numComponents;

	public UnionFind(int size) {
		size = new int[size];
		id = new int[size];
		for (int i = 0; i < size; i++) {
			id[i] = i; size[i] = 1;
		}
		this.numComponents = size;
	}

	public int root(int p) {
		int root = p;
		while(id[root] != root) {
			root = id[root]; 
		}
		// path compression
		int next = p;
		while(p != root) {
			next = id[p];
			id[p] = root;
			p = next;
		}
		return root;
	}

	public void union(int p, int q) {
		int rootP = root(p);
		int rootQ = root(q);
		if(rootP == rootQ) return;
		if(size[rootP] < size[rootQ]) {
			id[rootP] = rootQ;
			size[rootQ] += size[rootP];
		} else {
			id[rootQ] = rootP;
			size[rootQ] += size[rootP];
		}
		this.numComponents--;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	public int numberOfConnectedComponents() {
		return this.numComponents;
	}

	public int size() {
		return this.id.length;
	}

	public int componentSize(int p) {
		return size[root(p)];
	}
}