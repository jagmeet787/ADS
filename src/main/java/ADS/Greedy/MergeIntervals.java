package ADS.Greedy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class MergeIntervals {
    // count the maximum overlap in intervals
    public static int countOverlap(Interval[] arr) {
        if(arr.length < 1) return 0;
        Pair[] combined = new Pair[arr.length << 1];
        for (int i = 0; i < arr.length; i++) {
            combined[i << 1] = new Pair(arr[i].start, false);
            combined[(i << 1) + 1] = new Pair(arr[i].end, true);
        }
        Arrays.parallelSort(combined, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.time - o2.time;
            }
        });
        int maxCounter = 0;
        int counter = 0;
        for (int i = 0; i < combined.length; i++) {
            if (combined[i].isEnd) counter--;
            else counter++;
            maxCounter = (counter > maxCounter) ? counter : maxCounter;
        }
        return maxCounter;
    }

    // return the merged overlapped intervals
    public static Interval[] mergeIntervals(final Interval[] arr) {
        if(arr.length < 2) return arr;
        Arrays.parallelSort(arr, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        Stack<Interval> stk = new Stack<Interval>();
        stk.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            Interval cur = stk.peek();
            if (cur.end < arr[i].start) {
                stk.push(arr[i]);
            } else if (cur.end < arr[i].end) {
                stk.pop();
                cur.end = arr[i].end;
                stk.push(cur);
            }
        }
        Interval[] merged = new Interval[stk.size()];
        System.arraycopy(stk.toArray(), 0, merged, 0, merged.length);
        return  merged;
    }

    // O(nlogn) and O(1) space
    public static int getInplaceMergedIntervals(Interval[] arr) {
        if(arr.length < 2) return arr.length - 1;
        Arrays.parallelSort(arr, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[index].end < arr[i].start) {arr[++index] = arr[i];}
            else if (arr[index].end < arr[i].end) {
                arr[index].end = arr[i].end;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Interval[] arr = {new Interval(3, 15), new Interval(2, 5), new Interval(4, 8), new Interval(9, 12)};
        System.out.println(Arrays.deepToString(mergeIntervals(arr)));
        arr[0].end = 5;
        System.out.println(countOverlap(arr));
        arr[0]=new Interval(6,8);
        arr[1]=new Interval(1,9);
        arr[2]=new Interval(2,4);
        arr[3]=new Interval(4,7);
        int index = getInplaceMergedIntervals(arr);
        for (int i = 0; i <= index; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

class Pair {
    public int time;
    public boolean isEnd;
    public Pair(int time, boolean isEnd) {this.time = time; this.isEnd = isEnd;}
    @Override
    public String toString() {
        return "[" + time + " " + isEnd + "]";
    }
}

class Interval {
    public int start, end;
    public Interval(int start, int end) {
        this.start = start; this.end = end;
    }
    @Override
    public String toString() {
        return "[" + start + " " + end + "]";
    }
}