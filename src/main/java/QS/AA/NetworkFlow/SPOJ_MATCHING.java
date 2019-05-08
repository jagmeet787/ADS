package QS.AA.NetworkFlow;

// MATCHING - Fast Maximum Matching
// https://www.spoj.com/problems/MATCHING/
import java.io.*;
import java.util.*;

public class SPOJ_MATCHING {
    public static void main(String[] args) throws IOException {
        Main.main(args);
    }
}

// https://en.wikipedia.org/wiki/Hopcroft–Karp_algorithm
// time complexity: O(E * sqrt(V))
class Main {
    public static final int MAX = 150007;
    static ArrayList<ArrayList<Integer>> G;
    static int n, m;
    static int[] match = new int[MAX], dist = new int[MAX];

    public static boolean bfs() {
        int i, u, v, len;
        Queue<Integer> Q = new LinkedList<Integer>();
        for(i=1; i<=n; i++) {
            if(match[i]==0) {
                dist[i] = 0;
                Q.add(i);
            }
            else dist[i] = Integer.MAX_VALUE;
        }
        dist[0] = Integer.MAX_VALUE;
        while(!Q.isEmpty()) {
            u = Q.poll();
            if(u!=0) {
                len = G.get(u).size();
                for(i=0; i < len; i++) {
                    v = G.get(u).get(i);
                    if(dist[match[v]]==Integer.MAX_VALUE) {
                        dist[match[v]] = dist[u] + 1;
                        Q.add(match[v]);
                    }
                }
            }
        }
        return (dist[0]!=Integer.MAX_VALUE);
    }

    public static boolean dfs(int u) {
        int i, v, len;
        if(u!=0) {
            len = G.get(u).size();
            for(i=0; i<len; i++) {
                v = G.get(u).get(i);
                if(dist[match[v]]==dist[u]+1) {
                    if(dfs(match[v])) {
                        match[v] = u;
                        match[u] = v;
                        return true;
                    }
                }
            }
            dist[u] = Integer.MAX_VALUE;
            return false;
        }
        return true;
    }

    public static int hopcroftKarp() {
        int matching = 0, i;
        while(bfs())
            for(i=1; i<=n; i++)
                if(match[i]==0 && dfs(i))
                    matching++;
        return matching;
    }



    public static void main(String[] args) throws IOException {
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        String[] tokens = br.readLine().trim().split("\\s");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        int p = Integer.parseInt(tokens[2]);
        int N = n + m;
        G = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= N; i++) {
            G.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < p; i++) {
          tokens = br.readLine().trim().split("\\s");
          int a = Integer.parseInt(tokens[0]);
          int b = Integer.parseInt(tokens[1]);
          G.get(a).add(n+b);
          G.get(n+b).add(a);
        }

        for (ArrayList<Integer> le : G) System.out.println(le);
        
        System.out.println(hopcroftKarp());
    }
}