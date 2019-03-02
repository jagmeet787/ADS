package QS.AA.BitManipulations;

// 10264 - The Most Potent Corner
public class UVa10264 {
//    class Main {
        public static void main(String[] args) {
            int k = 2;
            for (int i = 0; i < (1 << k); i++) {
                for (int j = 0; j < k; j++) {
                    System.out.print((i ^ (1 << j)) + " ");
                }
                System.out.println();
            }
        }
//    }
}
