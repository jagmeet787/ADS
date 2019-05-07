package QS.AA.NetworkFlow;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

// MOBIVINA - MobiZone vs VinaGone
// https://www.spoj.com/problems/MOBIVINA/
public class SPOJ_MOBIVINA {
    public static void main(String[] args) throws IOException{
        Main.main(args);
    }	
}

class Main {
    static class Edge {
        int t, rev, cap, f;
        public Edge(int t, int rev, int cap) {
            this.t = t; this.rev = rev; this.cap = cap;
        }
    }

    public static void addEdge(List<Edge>[] graph, int s, int t, int cap) {
        graph[s].add(new Edge(t, graph[t].size(), cap));
        graph[t].add(new Edge(s, graph[s].size() - 1, cap));
    }

    static boolean dinicBfs(List<Edge>[] graph, int src, int dest, int[] dist) {
        Arrays.fill(dist, -1);
        dist[src] = 0;
        int[] Q = new int[graph.length];
        int sizeQ = 0;
        Q[sizeQ++] = src;
        for (int i = 0; i < sizeQ; i++) {
            int u = Q[i];
            for (Edge e : graph[u]) {
                if (dist[e.t] < 0 && e.f < e.cap) {
                    dist[e.t] = dist[u] + 1;
                    Q[sizeQ++] = e.t;
                }
            }
        }
        return dist[dest] >= 0;
    }

    static int dinicDfs(List<Edge>[] graph, int[] ptr, int[] dist, int dest, int u, int f) {
        if (u == dest)
            return f;
        for (; ptr[u] < graph[u].size(); ++ptr[u]) {
            Edge e = graph[u].get(ptr[u]);
            if (dist[e.t] == dist[u] + 1 && e.f < e.cap) {
                int df = dinicDfs(graph, ptr, dist, dest, e.t, Math.min(f, e.cap - e.f));
                if (df > 0) {
                    e.f += df;
                    graph[e.t].get(e.rev).f -= df;
                    return df;
                }
            }
        }
        return 0;
    }

    public static int maxFlow(List<Edge>[] graph, int src, int dest) {
        int flow = 0;
        int[] dist = new int[graph.length];
        while (dinicBfs(graph, src, dest, dist)) {
            int[] ptr = new int[graph.length];
            while (true) {
                int df = dinicDfs(graph, ptr, dist, dest, src, Integer.MAX_VALUE);
                if (df == 0)
                    break;
                flow += df;
            }
        }
        return flow;
    }

    public static void main(String[] args) throws IOException{
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        n = n + 2;
        List<Edge>[] graph = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
        int s = n - 2;
        int t = n - 1;
        String[] tokens = br.readLine().trim().split("\\s");
        for (int i = 0; i < n - 2; i++) {
          addEdge(graph, s, i, Integer.parseInt(tokens[i]));
        }
        
        tokens = br.readLine().trim().split("\\s");
        for (int i = 0; i < n - 2; i++) {
          addEdge(graph, i, t, Integer.parseInt(tokens[i]));
        }

        for (int i = 0; i < n - 2; i++) {
          tokens = br.readLine().trim().split("\\s");
          for (int j = i + 1; j < n - 2; j++) {
            int cost = Integer.parseInt(tokens[j]);
            if (cost > 0) addEdge(graph, i, j, cost);
          }
        }
        System.out.println(maxFlow(graph, s, t));
    }
}
