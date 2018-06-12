package fenwick_tree;

import java.util.Arrays;

public class FenwickTree {

	int N;
	int f[];
	
	public FenwickTree(int n) {
		N = n;
		f = new int[N + 1];
	}
	
	void scale(int c) {
		for(int i = 1; i <= N; ++i)
			f[i] /= c;
	}
	
	int rsq(int b) {
		int sum = 0;
		while(b > 0) {
			sum += f[b];
			b -= (b & -b);
		}
		return sum;
	}
	
	int getSmallestIdx(int freq) {
		
		int lo = 1, hi = N, mid;
		
		while(lo < hi) {
			mid = (lo + hi) >> 1;
			if(rsq(mid) >= freq)
				hi = mid;
			else
				lo = mid + 1;
		}
		
		return lo;
	}
	
	int rsq(int a, int b) { return rsq(b) - rsq(a - 1); }
	
	void adjust(int idx, int val) {
		while(idx <= N) {
			f[idx] += val;
			idx += (idx & -idx);
		}
	}
	
	public static void main(String[] args) {
		int arr[] = {2,4,5,5,6,6,6,7,7,8,9};
		FenwickTree ft = new FenwickTree(10);
		
		for(int i = 0; i < arr.length; ++i) ft.adjust(i + 1, arr[i]);
		System.out.println(Arrays.toString(ft.f));

		System.out.println(ft.getSmallestIdx(2));
		
//		System.out.println(ft.rsq(1, 1));
//		System.out.println(ft.rsq(1, 2));
//		System.out.println(ft.rsq(1, 6));
//		System.out.println(ft.rsq(1, 10));
//		System.out.println(ft.rsq(3, 6));
//		
//		ft.adjust(5, 2);
//		
//		System.out.println(ft.rsq(1, 10));
	}
}