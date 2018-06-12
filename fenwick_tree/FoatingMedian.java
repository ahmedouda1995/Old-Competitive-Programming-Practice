package fenwick_tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class FoatingMedian {
	
	static final int MOD = 65536;
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		System.out.println(sumOfMedians(	
				47,
				5621,
				1,
				125000,
				1700
));
		
		out.flush();
		out.close();
	}
	
	public static long sumOfMedians(int seed, int mul, int add, int N, int K)
	{
		int arr[] = new int[N];
		arr[0] = seed;
		
		for(int i = 1; i < N; ++i)
			arr[i] = (int) ((1L * arr[i - 1] * mul + add) % MOD);
		FenwickTree ft = new FenwickTree((int)MOD);
		
		for(int i = 0; i < K; ++i)
			ft.adjust(arr[i] + 1, 1);
		
		int mid = ((K + 1) >> 1);
		long sum = ft.getSmallestIdx(mid) - 1;
		
		for(int i = K; i < N; ++i)
		{
			ft.adjust(arr[i - K] + 1, -1);
			ft.adjust(arr[i] + 1, 1);
			sum += ft.getSmallestIdx(mid) - 1;
		}
		
		return sum;
	}
	
	static class FenwickTree {

		int N;
		long f[];
		
		public FenwickTree(int n) {
			N = n;
			f = new long[N + 1];
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
	}
	
	static class Pair implements Comparable<Pair>{
		long a;
		int b;
		
		public Pair(long x, int y) {
			a = x;
			b = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(Long.compare(a, p.a) == 0)
				return Integer.compare(b, p.b);
			return Long.compare(a, p.a);
		}
	}
	
	static class Scanner{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}