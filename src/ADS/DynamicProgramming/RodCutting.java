package ADS.DynamicProgramming;

public class RodCutting {
	private static Integer _rodCuttingRec(Integer[] cuts, Integer start, Integer end) {
		if (end - start + 1 == 2) return 0;
		Integer cost = cuts[end] - cuts[start];
		// if there is only one cut then return the cost
		if ( end - start + 1 == 3 ) return cost;
		Integer minCost = Integer.MAX_VALUE;
		for (Integer i = start + 1; i < end; i++) {
			minCost = Math.min(minCost, _rodCuttingRec(cuts, start, i) + _rodCuttingRec(cuts, i, end));
		}
		return cost + minCost;
	}
	
	public static Integer rodCuttingRec(Integer[] cuts) {
		if (cuts.length <= 2) return 0;
		return _rodCuttingRec(cuts, 0, cuts.length - 1);
	}
	
	private static String backTrack(Integer[][] table, Integer[] cuts) {
		StringBuilder result = new StringBuilder();
		int row = table.length - 1;
		int col = 0;
		result.append('[');
		while(col >= table[0].length - 2 && row <= 1) {
			result.append("");
		}
		result.append(']');
		return result.toString();
	}
	
	public static Integer rodCuttingDP(Integer[] cuts) {
		if (cuts.length <= 2) return 0;
		Integer[][] table = new Integer[cuts.length][cuts.length];
		for (int i = 0; i < table.length - 1; i++) {
			table[i][i + 1] = 0;
			table[i + 1][i] = -1; // cut not possible
		}
		for (int row = table.length - 3; row >= 0; row--) {
			for (int col = row + 2; col < table[0].length; col++) {
				Integer cost = cuts[col] - cuts[row];
				Integer minCost = Integer.MAX_VALUE;
				for (int i = row + 1; i < col; i++) {
					if(minCost > Math.min(minCost, table[row][i] + table[i][col])) {
						minCost = Math.min(minCost, table[row][i] + table[i][col]);
						table[col][row] = i;
					}
					
				}
				table[row][col] = cost + minCost;
			}
		}
		
		// Backtrack
		System.out.println(backTrack(table, cuts));
//		
//	for(int i =0 ; i < table.length; i++) System.out.println(Arrays.toString(table[i]));
		return table[0][cuts.length - 1];
	}
}
