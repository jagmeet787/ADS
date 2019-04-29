package QS.AA.BitManipulations;

// 1241 - Jollybee Tournament
// https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3682
public class UVa1241 {
// class Main {
    public static void main(String[] args) throws java.io.IOException{
    	java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    	StringBuilder out = new StringBuilder();
    	int tc = Integer.parseInt(br.readLine());
    	boolean[] players = new boolean[1025];
    	int n = 0, m = 0, count = 0;
    	String[] toks = null;
    	while (tc-- > 0) {
    		toks = br.readLine().trim().split("\\s");
    		n = Integer.parseInt(toks[0]);
    		m = Integer.parseInt(toks[1]);
    		count = 1 << n;
    		for (int i = 0; i < count; i++)
    			players[i] = false;
    		// false is in game 
    		// true is left the game
    		toks = br.readLine().trim().split("\\s");
    		for (int i = 0; i < m; i++) {
    			players[Integer.parseInt(toks[i]) - 1] = true;
    		}

    		int ans = 0;
    		while (count > 1) {
    			int j = 0;
    			for (int i = 0; i < count; i+=2) {
    				// true ^ false and false ^ true 
    				// then it is walkover
    				if (players[i] ^ players[i + 1]) {
    					ans++;
    				}
    				players[j++] = players[i] & players[i+1];
    			}
    			count = count >> 1;
    		}
    		out.append(ans).append('\n');
    	}
    	System.out.print(out);
    }
}