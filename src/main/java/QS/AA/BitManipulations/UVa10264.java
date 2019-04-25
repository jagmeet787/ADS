package QS.AA.BitManipulations;

// 10264 - The Most Potent Corner
// https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1205
public class UVa10264 {
   // class Main {
        public static void main(String[] args) throws java.io.IOException{
        	java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        	StringBuilder out = new StringBuilder();
        	String input = "";
        	int[] nodes = null;
        	int[][] potency = null;
        	// System.out.println("Intilize");
        	while ((input = br.readLine()) != null) {
        		if (input.equals("")) break;
        		int n = Integer.parseInt(input);
        		nodes = new int[1 << n];
        		potency = new int[2][nodes.length];
        		for (int i = 0; i < nodes.length; i++) {
        			nodes[i] = Integer.parseInt(br.readLine());
        		}
        		int max = Integer.MIN_VALUE;
        		for (int i = 0; i < nodes.length; i++) {
        			for (int bit = 0; bit < n; bit++) {
        				potency[0][i] += nodes[i ^ (1 << bit)];
        			}
        			for (int bit = 0; bit < n; bit++) {
        				potency[1][i] = Math.max(potency[1][i], potency[0][i] + potency[0][i ^ (1 << bit)]);
        			}
        			max = Math.max(max, potency[1][i]);
        		}
        		out.append(max).append('\n');
        	}
        	System.out.print(out);
        }
   // }
}
