package QS.O.Array;

public class Median {
	// returns median of sorted array
	public int median(int[] arr, int n) {
		if((n & 1) == 1) return arr[n>>1];
		return (arr[n>>1] + arr[(n>>1)-1]) >> 1;
	}

	public int getMedian(int[] arr1, int a1, int[] arr2, int a2, int n) {
		if (n <= 0) return -1;
		if (n == 1) return (arr1[0] + arr2[0]) >> 1;
		if (n == 2) return (Math.max(arr1[0], arr2[0]) + 
							Math.min(arr1[1], arr2[1])) >> 1;
		int m1 = median(arr1, n);
		int m2 = median(arr2, n);
		if(m1 == m2) return m1;
		if(m1 < m2) {
			if((n & 1) == 0)
				return getMedian(arr1, ((n>>1)-1), arr2, a2, n >> 1 + 1);
			return getMedian(arr1, (n >> 1), arr2, a2, n >> 1);
		}
		if((n & 1) == 0)
			return getMedian(arr2, ((n >> 1) - 1), arr1, a1, n >> 1 + 1);
		return getMedian(arr2, (n >> 1), arr1, a1, n >> 1);
	}

	public static void main(String[] args) {
		System.out.println(new Median().getMedian(new int[]{1,4,5}, 0, new int[]{4,5,6}, 0, 2));
	}
}