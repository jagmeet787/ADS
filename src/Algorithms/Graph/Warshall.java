package Algorithms.Graph;

import java.util.Arrays;

public class Warshall {

	// Transitive Closure of the graph
	// can be done efficiently using dfs or bfs = O(V(V+E))
	public static Object[][] warshall(Boolean[][] adjMatrix, Boolean ways) {
		Boolean[][] table = new Boolean[adjMatrix.length][adjMatrix[0].length];
		Integer[][] numberOfWays = new Integer[adjMatrix.length][adjMatrix[0].length];
		
		for ( int i = 0; i < table.length; i++) 
			table[i] = Arrays.copyOf(adjMatrix[i], adjMatrix[i].length);
		for ( int i = 0; i < numberOfWays.length; i++)
			Arrays.fill(numberOfWays[i], 0);
		
		for (int k = 0; k < table.length; k++) {
			for (int row = 0; row < table.length; row++) {
				for (int col = 0; col < table[row].length; col++) {
					if (table[row][k] == true && table[k][col] == true) {
						table[row][col] = true;
						numberOfWays[row][col] = (table[row][col] ? 1 : 0) + 
								numberOfWays[row][k] * numberOfWays[k][col];
					}
				}
			}
		}
//System.out.println(Arrays.deepToString(table));
		if (!ways) return table;
		return numberOfWays;
	}

}
