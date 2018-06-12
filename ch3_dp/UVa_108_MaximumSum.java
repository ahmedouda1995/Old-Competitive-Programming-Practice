package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_108_MaximumSum {

	// O(n ^ 3) using jay kadane
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		
		while((s = sc.nextLine()) != null) {
			int n = Integer.parseInt(s);
			
			int [][] a= new int[n][n];
			
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					a[i][j] = sc.nextInt();
			
			for(int i = 1; i < n; ++i)
				for(int j = 0; j < n; ++j)
					a[i][j] += a[i - 1][j];
			
			int max = a[0][0];
					
			for(int i = 0; i < n; ++i)
				for(int j = i; j < n; ++j) {
					int sum = a[j][0];
					if(i > 0) sum -= a[i - 1][0];
					
					for(int k = 1; k < n; ++k) {
						int next = a[j][k];
						if(i > 0) next -= a[i - 1][k];
						sum = Math.max(sum + next, next);
						max = Math.max(max, sum);
					}
				}
			out.println(max);
		}
		
		out.flush();
		out.close();
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