package ch3_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class No_LIS_K {
	
	static int N, MAX = (int) 1e5;
	static int arr[];
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		N = sc.nextInt();
		int k = sc.nextInt();
		arr = new int[N];
		for(int i = 0; i < N; ++i) arr[i] = sc.nextInt();
		
		long prev[] = new long[N];
		Arrays.fill(prev, 1);
		long cur[] = new long[N];
		
		long res = 0;
		res += N;
		
		BIT ft = new BIT(MAX);
		
		for(int i = 1; i < k; ++i)
		{
			Arrays.fill(ft.f, 0);
			for(int j = 0; j < i; ++j)
				ft.update(arr[j] + 1, prev[j]);
			
			for(int j = i; j < N; ++j)
			{
				cur[j] = ft.rsq(arr[j]);
				ft.update(arr[j] + 1, prev[j]);
			}
			System.out.println(Arrays.toString(prev));
			
			prev = cur;
			cur = new long[N];
			res += ft.rsq(MAX);
		}
		System.out.println(Arrays.toString(prev));
		
		System.out.println(res);
		
		out.flush();
		out.close();
	}

	static class BIT
	{
		int N;
		long f[];
		
		public BIT(int n) {
			N = n;
			f = new long[n + 1];
		}
		
		void update(int idx, long val)
		{
			while(idx <= N) {
				f[idx] += val;
				idx += (idx & -idx);
			}
		}
		
		long rsq(int b)
		{
			long sum = 0;
			while(b > 0) {
				sum += f[b];
				b -= (b & -b);
			}
			return sum;
		}
	}
	
	static class Scanner
	{
		StringTokenizer st;
		BufferedReader br;
		
		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}
		
		public String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		
		public int nextInt() throws Exception { return Integer.parseInt(next()); }
		public String nextLine() throws Exception { return br.readLine(); }
		public long nextLong() throws Exception { return Long.parseLong(next()); }
	}
	
}
