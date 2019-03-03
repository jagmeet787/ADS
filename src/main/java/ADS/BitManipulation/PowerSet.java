package ADS.BitManipulation;

public class PowerSet {

    private void _powerSetRecursive(int[] set, int at, boolean[] used) {
        if (at == set.length) {
            System.out.print("{");
            for (int i = 0; i < set.length; i++) {
                if (used[i]) System.out.print(set[i] + ", ");
            }
            System.out.println("}");
        } else {
            // include element
            used[at] = true;
            _powerSetRecursive(set, at + 1, used);

            // exclude element
            used[at] = false;
            _powerSetRecursive(set, at + 1, used);
        }
    }

    public void powerSetRecursive(int[] set) {
        _powerSetRecursive(set, 0, new boolean[set.length]);
    }

    public void powerSet(int[] set) {
        int n = set.length;
        int numberSubsets = 1 << set.length;
        for (int i = 0; i < numberSubsets; i++) {
            System.out.print("{ ");
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    System.out.print(set[j] + " ");
                }
            }
            System.out.println("}");
        }
    }
}