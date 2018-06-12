package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa_481_What_Goes_Up {

	static int L_id[] = new int[100000];
	static int p[] = new int[100000];
	static int a[] = new int[100000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		int n;
		int i = 0;
		while((s = sc.nextLine()) != null) {
			n = Integer.parseInt(s);
			a[i++] = n;
		}
		
		lis(i, out);
		
		out.flush();
		out.close();
	}
	
	private static void lis(int n, PrintWriter out) {
		
		ArrayList<Integer> L = new ArrayList<Integer>();
		int lis = 0, lis_end = 0;
		
		for (int i = 0; i < n; ++i) {
			int pos = Collections.binarySearch(L, a[i]);
			
			if(pos < 0) pos = -(pos + 1);
			if(pos >= L.size()) L.add(a[i]);
			else L.set(pos, a[i]);
			
			L_id[pos] = i;
			p[i] = (pos > 0)?L_id[pos - 1]:-1;
			if (pos + 1 >= lis) {
		        lis = pos + 1;
		        lis_end = i;
		    }
		}
		
		out.println(lis);
		out.println("-");
		reconstruct(lis_end, out);
	}
	
	// BELOW GIVES TLE
	
//	private static int lis(int n) {
//		int max = 1, maxIndex = 0;
//		dp[0] = 1;
//		res[0] = 0;
//		for(int i = 1; i < n; ++i) {
//			int maxSoFar = 0, maxSoFarIndex = i;
//			for(int j = i - 1; j >= 0; --j) {
//				if(a[i] > a[j]) {
//					if(maxSoFar <= dp[j]) {
//						maxSoFar = dp[j];
//						maxSoFarIndex = j;
//					}
//				}
//			}
//			
//			dp[i] = maxSoFar + 1;
//			res[i] = maxSoFarIndex;
//			
//			if(max <= dp[i]) {
//				max = dp[i];
//				maxIndex = i;
//			}
//		}
//		M = maxIndex;
//		return max;
//	}

	private static void reconstruct(int lis_end, PrintWriter out) {
		Stack<Integer> s = new Stack<Integer>();
		int x = lis_end;
	    while(x != 0) {
	    	s.push(a[x]);
	    	x = p[x];
	    }
		out.println(a[x]);
		while(!s.isEmpty()) {
			out.println(s.pop());
		}
	}

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