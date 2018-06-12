package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_1196_TilingUpBlocks {

	static Pair [] a = new Pair[10000];
	static int dp[] = new int[10000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		
		while((n = sc.nextInt()) != 0) {
			for(int i = 0; i < n; ++i) {
				a[i] = new Pair(sc.nextInt(), sc.nextInt());
			}
			
			Arrays.sort(a, 0, n);
			
			out.println(lis(n));
		}
		out.println("*");
		out.flush();
		out.close();
	}
	
	private static int lis(int n) {
		int max = 0;
		dp[0] = 1;
		for(int i = 1; i < n; ++i) {
			int maxSoFar = 0;
			for(int j = i - 1; j >= 0; --j) {
				if(a[i].a >= a[j].a && a[i].b >= a[j].b) {
					maxSoFar = Math.max(maxSoFar, dp[j]);
				}
			}
			dp[i] = maxSoFar + 1;
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	static class Pair implements Comparable<Pair>{
		int a, b;
		
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		public int compareTo(Pair p) {
			if(Integer.compare(this.a, p.a) == 0)
				return Integer.compare(this.b, p.b);
			return Integer.compare(this.a, p.a);
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