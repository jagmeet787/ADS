package QS.Array;
import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {

    // Print 2D matrix in a spiral
    public static ArrayList<Integer> spiralOrder(final List<ArrayList<Integer>> A) {
        int Top = 0, Bottom = A.size() - 1, Left = 0, Right = A.get(0).size() - 1;
        int dir = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (Left <= Right && Top <= Bottom) {
            if ( dir == 0 ) {
                for ( int i = Left; i <= Right; i++)
                    list.add(A.get(Top).get(i));
                Top++;
            } else if ( dir == 1 ) {
                for ( int i = Top; i <= Bottom; i++)
                    list.add(A.get(i).get(Right));
                Right--;
            } else if ( dir == 2 ) {
                for ( int i = Right; i >= Left; i--)
                    list.add(A.get(Bottom).get(i));
                Bottom--;
            } else if ( dir == 3 ) {
                for ( int i = Bottom; i >= Top; i--)
                    list.add(A.get(i).get(Left));
                Left++;
            }
            dir = (dir+1)%4;
        }
        return list;
    }
    
}
