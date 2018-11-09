package Algorithms.DynamicProgramming;

import java.util.Arrays;

public class EggDropping {
	public static Integer eggDroppingRec(Integer floors, Integer eggs) {
		if ( floors == 0 || floors == 1) return floors;
		if ( eggs == 1 ) return floors;
		Integer cost = Integer.MAX_VALUE;
		for ( int i = 1; i <= floors; i++) {
			cost = Math.min(cost, 
				Math.max(eggDroppingRec( i - 1, eggs - 1 ), 
					eggDroppingRec( floors - i, eggs ) ) );
		}
		return cost + 1;
	}

	public static Integer eggDroppingDP(Integer floors, Integer eggs) {
		if ( floors == 0 || floors == 1 ) return floors;
		if( eggs == 1 ) return floors;
		Integer[][] table = new Integer[eggs][floors + 1];
		Integer[][] pie = new Integer[eggs][floors + 1];
		
		for ( int i = 0; i < table[0].length; i++) {
			table[0][i] = i;
			pie[0][i] = 1;
		}
		for ( int i = 0; i < table.length; i++) {
			table[i][0] = 0; table[i][1] = 1;
			pie[i][0] = -1; pie[i][1] = 1;
			
		}

		for ( int row = 1; row < table.length; row++) {
			for ( int col = 2; col < table[0].length; col++) {
				Integer cost = Integer.MAX_VALUE;
				for ( int k = 1; k <= col; k++) {
					// cost = Math.min( cost,
					// 	Math.max( table[row - 1][k - 1],
					// 		table[row][col - k] ) );
					Integer newCost = Math.max(table[row - 1][k - 1],
						table[row][col - k]);
					if(newCost < cost) {
						cost = newCost;
						pie[row][col] = k;
					}
				}
				table[row][col] = 1 + cost;
			}
		}
		for (int i = 0; i < pie.length; i++)
			System.out.println(Arrays.toString(pie[i]));

		return table[eggs - 1][floors];
	}

	public static Integer eggDroppingDPMin(Integer floors, Integer eggs) {
		if ( floors == 0 || floors == 1 ) return floors;
		if( eggs == 1 ) return floors;
		Integer[][] table = new Integer[2][floors + 1];

		for ( int i = 0; i < table[0].length; i++)
			table[0][i] = i;
		table[1][0] = 0;
		table[1][1] = 1;

		for ( int row = 1; row < eggs; row++) {
			for ( int col = 2; col < table[0].length; col++) {
				Integer cost = Integer.MAX_VALUE;
				for ( int k = 1; k <= col; k++) {
					cost = Math.min( cost,
						Math.max( table[(row + 1) % 2][k - 1],
							table[row % 2][col - k] ) );
				}
				table[row % 2][col] = 1 + cost;
			}
		}
			
		return table[(eggs - 1) % 2][floors];
	}
}
