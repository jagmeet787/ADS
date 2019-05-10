package QS.AA.NetworkFlow;

// Cleaning the Space
// https://www.codechef.com/problems/SPCLN
import java.util.*;
import java.io.*;
import java.util.stream.Stream;

class Main {
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


	public static void main(String[] args) throws IOException{
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String[] tokens = br.readLine().trim().split("\\s");
		int n = Integer.parseInt(tokens[0]);
		int m = Integer.parseInt(tokens[1]);
		int x = 0;
		int nodes = n + (n * m) + 2;
		int s = 0;
		int t = nodes-1;
		NetworkFlowSolverBase solver = new DinicsSolver(nodes, s, t);

		for (int i = 0; i < n; i++) {
			solver.addEdge(0, i+1, 1000_000_000);
			tokens = br.readLine().trim().split("\\s");
			for (int j = 0; j < m; j++) {
				x = Integer.parseInt(tokens[j]);
				if (x == -1) x = -1000_000_000;
		 		solver.addEdge(i + (j * n) + 1, i + (j + 1) * n + 1, 105 - x);
			}
			solver.addEdge(i + m * n + 1, n + m * n + 1, 1000_000_000);
		}

		int k = Integer.parseInt(br.readLine().trim());

		for (int i = 0; i < k; i++) {
			tokens = br.readLine().trim().split("\\s");
			int u = Integer.parseInt(tokens[0]);
			int v = Integer.parseInt(tokens[1]);
			for (int j = 0; j < m; j++) {
				solver.addEdge((u - 1) + j * n + 1, (v - 1) + (j + 1) * n + 1, 1000_000_000);
			}
		}
		long flow = solver.getMaxFlow();
		double ans = (double)((n * 105 - flow) / n);
		System.out.printf("%.2f\n", ans);
	}
}

class CC_SPCLN {
	public static void main(String[] args) throws IOException{
		Main.main(args);
	}
}