package QS.AA.DP;
// 11790 - Murcia's Skyline
// https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2890
public class UVa11790 {
// class Main {
    public static void main(String[] args) throws java.io.IOException{
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        int n = 0;
        String[] height = null;
        String[] width = null;
        int caseNo = 1;
        while (tc-- > 0) {
            n = Integer.parseInt(br.readLine());
            height = br.readLine().trim().split("\\s");
            width = br.readLine().trim().split("\\s");        		
            int[] h = new int[n];
            int[] w = new int[n];
            int[] lengths = new int[n];
            int[] dec = new int[n];
            for (int i = 0; i < n; i++) {
            	h[i] = Integer.parseInt(height[i]);
            	w[i] = Integer.parseInt(width[i]);
            	lengths[i] = w[i];
            	dec[i] = w[i];
            }
            for (int j = lengths.length - 2; j >=0; j--) {
            	for (int i = j + 1; i < lengths.length; i++) {
            		if (h[j] < h[i])
            			lengths[j] = Math.max(lengths[j], w[j] + lengths[i]);
            		if (h[j] > h[i])
            			dec[j] = Math.max(dec[j], w[j] + dec[i]);
            	}	
            }
            int inc = Integer.MIN_VALUE, dis = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
            	if (inc < lengths[i]) inc = lengths[i];
            	if (dis < dec[i]) dis = dec[i];
            }
            if (inc < dis) {
            	out.append("Case " + (caseNo++) + ". Decreasing (" + dis + "). Increasing (" + inc + ").").append('\n');
            } else {
            	out.append("Case " + (caseNo++) + ". Increasing (" + inc + "). Decreasing (" + dis + ").").append('\n');
            }
        }
        System.out.print(out);
    }
}