package QS.AA.NetworkFlow;

// C. Array and Operations
// https://codeforces.com/problemset/problem/498/C

import java.io.*;
import java.util.*;
public class CF_498C {
	
	public static final int MOD = 1000_000_000 + 7;
	public static final double EPS = 1e-6;
	public static final boolean DEBUG = false;

	public static HashMap<Integer, Integer> getFactors(int num) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int d = 2;
		int cnt = 0;
		while (num % 2 == 0 && num != 0) { num /= 2; cnt++;}
		if (cnt > 0) hm.put(2, cnt);
		for (int i = d + 1; i * i < num; i += 2) {
			cnt = 0;
			while (num % i == 0) {
				num /= i;
				cnt++;
			}
			if (cnt > 0) hm.put(i, cnt);
		}
		if (num > 1) hm.put(num, 1);
		return hm;
	}

	public static void main(String[] args) throws java.io.IOException {
		final java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String[] tokens = br.readLine().trim().split("\\s");
		int n = Integer.parseInt(tokens[0]);
		int m = Integer.parseInt(tokens[1]);
		tokens = br.readLine().trim().split("\\s");
		HashMap<Integer, HashMap<Integer, Integer>> even = new HashMap<Integer, HashMap<Integer, Integer>>();
		HashMap<Integer, HashMap<Integer, Integer>> odd = new HashMap<Integer, HashMap<Integer, Integer>>();
		int efactors = 0;
    int ofactors = 0;
    int[] a = new int[n];
		boolean[] done = new boolean[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(tokens[i]);
		}
    int[] eve = new int[m];
    int[] od = new int[m];
		for (int i = 0; i < m; i++) {
			tokens = br.readLine().trim().split("\\s");
			int j = Integer.parseInt(tokens[0]);
			int k = Integer.parseInt(tokens[1]);
			if (j % 2 != 0) {int temp = j; j = k; k = temp;}
			j--;
			k--;
      eve[i] = j;
      od[i] = k;
			if (!done[j]) {
				HashMap<Integer, Integer> te = getFactors(a[j]);
				even.put(j, te);
        efactors += te.size();
				done[j] = true;
			}
			if (!done[k]) {
				HashMap<Integer, Integer> to = getFactors(a[k]);
				odd.put(k, to);
        ofactors += to.size();
				done[k] = true;
			}
		}
		int nodes = efactors + ofactors + 2;
		int s = 0;
		int t = nodes - 1;
    NetworkFlowSolverBase solver = new DinicsSolver(nodes, s, t);
    int ni = 1;
    ArrayList<ArrayList<Pair>> idxes = new ArrayList<ArrayList<Pair>>();
    for (int i = 0; i < n; i++) {
      idxes.add(new ArrayList<Pair>());
    }
    for (Integer e : even.keySet()) {
      HashMap<Integer, Integer> k = even.get(e);
      for (Integer d : k.keySet()) {
        idxes.get(e).add(new Pair(ni, d));
        solver.addEdge(s, ni++, k.get(d));
      }
    }

    for (Integer e : odd.keySet()) {
      HashMap<Integer, Integer> k = odd.get(e);
      for (Integer d : k.keySet()) {
        idxes.get(e).add(new Pair(ni, d));
        solver.addEdge(ni++, t, k.get(d));
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < idxes.get(eve[i]).size(); j++) {
        for (int k = 0; k < idxes.get(od[i]).size(); k++) {
          Pair t1 = idxes.get(eve[i]).get(j);
          Pair t2 = idxes.get(od[i]).get(k);
          if (t1.val ==  t2.val) {
            solver.addEdge(t1.idx, t2.idx, 5000);
          }
        }
      }
    }
    System.out.println(solver.getMaxFlow());
	}

  static class Pair {
    int idx, val;
    public Pair(int idx, int val) {
      this.idx = idx; this.val = val;
    }
  }

  private static class Edge {
    public int from, to;
    public Edge residual;
    public long flow;
    public final long capacity;
    
    public Edge(int from, int to, long capacity) {
      this.from = from;
      this.to = to;
      this.capacity = capacity;
    }

    public boolean isResidual() {
      return capacity == 0;
    }

    public long remainingCapacity() {
      return capacity - flow;
    }

    public void augment(long bottleNeck) {
      flow += bottleNeck;
      residual.flow -= bottleNeck;
    }

    public String toString(int s, int t) {
      String u = (from == s) ? "s" : ((from == t) ? "t" : String.valueOf(from));
      String v = (to == s) ? "s" : ((to == t) ? "t" : String.valueOf(to));
      return String.format("Edge %s -> %s | flow = %3d | capacity = %3d | is residual: %s", 
        u, v, flow, capacity, isResidual());
    }

    @Override
    public String toString() {
    	return "(" + from  + " " + to + " " + capacity + ")";
    }
  }


  private static abstract class NetworkFlowSolverBase {
    static final long INF = Long.MAX_VALUE / 2;
    final int n, s, t;
    protected boolean solved;
    protected long maxFlow;
    protected List<Edge>[] graph;
    public NetworkFlowSolverBase(int n, int s, int t) {
      this.n = n; 
      this.s = s; 
      this.t = t; 
      initializeEmptyFlowGraph();
    }
    private void initializeEmptyFlowGraph() {
      graph = new List[n];
      for (int i = 0; i < n; i++)
        graph[i] = new ArrayList<Edge>();
    }
    public void addEdge(int from, int to, long capacity) {
      if (capacity <= 0) 
        throw new IllegalArgumentException("Forward edge capacity <= 0");
      Edge e1 = new Edge(from, to, capacity);
      Edge e2 = new Edge(to, from, 0);
      e1.residual = e2;
      e2.residual = e1;
      graph[from].add(e1);
      graph[to].add(e2);
    }
    public List<Edge>[] getGraph() {
      execute();
      return graph;
    }
    public long getMaxFlow() {
      execute();
      return maxFlow;
    }
    private void execute() {
      if (solved) return; 
      solved = true;
      solve();
    }
    public abstract void solve();
  }
  
  private static class DinicsSolver extends NetworkFlowSolverBase {
    private int[] level;
    public DinicsSolver(int n, int s, int t) {
      super(n, s, t);
      level = new int[n];
    }

    @Override
    public void solve() {
     int[] next = new int[n];

      while (bfs()) {
        Arrays.fill(next, 0);
        for (long f = dfs(s, next, INF); f != 0; f = dfs(s, next, INF)) {
          maxFlow += f;
        }
      }
    }
    private boolean bfs() {
      Arrays.fill(level, -1);
      Deque<Integer> q = new ArrayDeque<>(n);
      q.offer(s);
      level[s] = 0;
      while (!q.isEmpty()) {
        int node = q.poll();
        for (Edge edge : graph[node]) {
          long cap = edge.remainingCapacity();
          if (cap > 0 && level[edge.to] == -1) {
            level[edge.to] = level[node] + 1;
            q.offer(edge.to);
          }
        }
      }
      return level[t] != -1;
    }
    private long dfs(int at, int[] next, long flow) {
      if (at == t) return flow;
      final int numEdges = graph[at].size();
      
      for (;next[at] < numEdges; next[at]++) {
        Edge edge = graph[at].get(next[at]);
        long cap = edge.remainingCapacity();
        if (cap > 0 && level[edge.to] == level[at] + 1) {

          long bottleNeck = dfs(edge.to, next, Math.min(flow, cap));
          if (bottleNeck > 0) {
            edge.augment(bottleNeck);
            return bottleNeck;
          }

        }
      }
      return 0;
    }
  }
}
