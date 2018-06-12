package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_111_HistoryGrading {

	static int dp[] = new int[20];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		String s = sc.nextLine();
		int [] a;
		while(s != null) {
			map.clear();
			int n = Integer.parseInt(s);
			a = new int[n];
			for(int i = 0; i < n; ++i) map.put(i + 1, sc.nextInt());
			
			while((s = sc.nextLine()) != null && s.length() > 2) {
				StringTokenizer st = new StringTokenizer(s);
				for(int i = 0; i < n; ++i) a[Integer.parseInt(st.nextToken()) - 1] = i + 1;
				
				for(int i = 0; i < n; ++i) a[i] = map.get(a[i]);
				
				out.println(lis(a));
			}
		}
		
		
		out.flush();
		out.close();
	}
	
	private static int lis(int[] a) {
		dp[0] = 1;
		int max = 1;
		int maxSoFar;
		for (int i = 0; i < a.length; i++) {
			maxSoFar = 0;
			for (int j = i - 1; j >= 0; --j) {
				if(a[j] < a[i]) maxSoFar = Math.max(maxSoFar, dp[j]);
			}
			dp[i] = maxSoFar + 1;
			max = Math.max(max, dp[i]);
		}
		
		return max;
	}

//	private static int lis(int[] a) {
//		ArrayList<Integer> L = new ArrayList<Integer>();
//		int max = 1;
//		for(int i = 0; i < a.length; ++i) {
//			int pos = Collections.binarySearch(L, a[i]);
//			if(pos < 0) pos = -(pos + 1);
//			if(pos >= L.size()) L.add(a[i]);
//			else L.set(pos, a[i]);
//			
//			max = Math.max(max, L.size());
//		}
//		return max;
//	}
	
	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
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