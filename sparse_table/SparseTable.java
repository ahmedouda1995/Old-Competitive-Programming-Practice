package sparse_table;

import java.util.Arrays;

public class SparseTable {

	// rmq + sum range any associative function f(f(1, 2), f(3)) = f(f(1), f(2, 3))
	// Number of contiguous subarrays with gcd equal to 1
	// https://www.hackerearth.com/practice/notes/sparse-table/
	// amazing tutorial
	
	private int N, arr[], spT[][];
	
	public SparseTable(int a[]) {
		N = a.length;
		arr = a;
		spT = new int[N][log2(N) + 1];
		
		for(int i = 0; i < N; ++i) spT[i][0] = i;
		
		for(int j = 1; (1 << j) <= N; ++j) {
			for(int i = 0; i + (1 << j) <= N; ++i) {
				if(arr[spT[i][j - 1]] < arr[spT[i + (1 << (j - 1))][j - 1]])
					spT[i][j] = spT[i][j - 1];
				else
					spT[i][j] = spT[i + (1 << (j - 1))][j - 1];
			}
		}
	}
	
	int rmq(int i, int j) {
		int k = log2(j - i + 1);
		
		if(arr[spT[i][k]] < arr[spT[j - (1<<k) + 1][k]]) return spT[i][k];
		else return spT[j - (1<<k) + 1][k];
	}
	
	static int log2(int n) { return (int)(Math.log(n) / Math.log(2)); }
	
	public static void main(String[] args) {
		int a[] = {18, 17, 13, 19, 15, 11, 20};
		
		SparseTable sparse = new SparseTable(a);
		for(int i = 0; i < a.length; ++i) System.out.println(Arrays.toString(sparse.spT[i]));
		System.out.println(a[sparse.rmq(0, 1)]);
	
	}
}