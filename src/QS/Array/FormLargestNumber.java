package QS.Array;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class FormLargestNumber {
    public static String largestNumber(final List<Integer> A) {
        if (A.size() == 0) return "";
        if (A.size() == 1) return "" + A.get(0);
        ArrayList<Integer> list = new ArrayList<Integer>(A);
        Collections.sort(list, new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                Long n1 = Long.parseLong("" + a + b);
                Long n2 = Long.parseLong("" + b + a);
                if ( n1 < n2 ) return 1;
                return -1;
            }
        });
        StringBuilder number = new StringBuilder("");
        for ( Integer e : list ) {
            if ( number.length() == 0 && e == 0 ) continue;
            number.append(e);
        }
        if (number.length()>0) return number.toString();
        return "0";
    }
}
