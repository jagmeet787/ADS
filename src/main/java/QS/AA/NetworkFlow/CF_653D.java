package QS.AA.NetworkFlow;

// D. Delivery Bears
// https://codeforces.com/problemset/problem/653/D

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class CF_653D {
	
	public static final int MOD = 1000_000_000 + 7;
	public static final double EPS = 1e-9;
	public static final boolean DEBUG = false;

    static int n, m, k, en;
    static int[] q, dis, to, pre, c, last, cur;
    static Edge[] e;

    static void init() {
        q = new int[233];
        dis = new int[233];
        to = new int[2333];
        pre = new int[2333];
        c = new int[2333];
        last = new int[2333];
        cur = new int[2333];
        e = new Edge[550];
    }

    public static boolean check(double lim) {
        en = 1;
        Arrays.fill(last, 0);
        NetworkFlowSolverBase solver = new DinicsSolver(n + 1, 1, n);
    	for (int i = 1; i <= m; i++) {
            int capacity = (int)(e[i].capacity/lim);
    		if (capacity != 0) solver.addEdge( e[i].from, e[i].to, capacity);
    	}
    	long flow = solver.getMaxFlow();
    	return  flow >= k;
    } 

	public static void main(String[] args) throws java.io.IOException {
		final java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		final StringBuilder out = new StringBuilder();	
		String[] tokens = br.readLine().trim().split("\\s");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		k = Integer.parseInt(tokens[2]);
        init();
		double l = 0.0, r = 0.0, mid = 0.0;
		for (int i = 1; i <= m; i++) {
			tokens = br.readLine().trim().split("\\s");
			int u = Integer.parseInt(tokens[0]);
			int v = Integer.parseInt(tokens[1]);
			int w = Integer.parseInt(tokens[2]);
			e[i] = new Edge(u, v, w);
            r = Math.max(r, w);
		}

		while (r - l > EPS) {
			mid = l + (r - l) / 2;
			if (check(mid)) l = mid;
			else r = mid;
		}

		mid = mid * k;
		mid = Math.max(mid, 1);

        System.out.printf("%.7f\n", mid);
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