import Algorithms.DynamicProgramming.UglyNumbers;
import Algorithms.Graph.*;

public class Driver {
	public static void main(String[] args) {
		System.out.println("20th Ugly number: " + UglyNumbers.nthUglyNumber(200));;
		System.out.println("20th Ugly number: " + UglyNumbers.nthUglyNumberDP(200));
	}
}
