

import Algorithms.DynamicProgramming.EggDropping;

public class Driver {
	public static void main(String[] args) {
		System.out.println(EggDropping.eggDroppingRec(16, 3));
		System.out.println(EggDropping.eggDroppingDP(16, 4));
		System.out.println(EggDropping.eggDroppingDPMin(16, 4));
	}
}
