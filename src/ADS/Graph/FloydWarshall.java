package ADS.Graph;

import java.util.Arrays;

public class FloydWarshall {
	public static Integer[][] floydWarshall(Integer[][] adjMatrix) {
		Integer[][] D = new Integer[adjMatrix.length][adjMatrix[0].length];
		Integer[][] P = new Integer[adjMatrix.length][adjMatrix[0].length];
		for ( int i = 0; i < D.length; i++) 
			D[i] = Arrays.copyOf(adjMatrix[i], adjMatrix[i].length);
		
		for (int k = 0; k < D.length; k++) {
			for (int row = 0; row < D.length; row++) {
				for (int col = 0; col < D[row].length; col++) {
					Integer temp = Math.min(D[row][col], D[row][k] + D[k][col]);
					if (!temp.equals(D[row][col])) P[row][col] = k;
				}
			}
		}
		return D;
	}
}
