package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_108_Maximum_Sum {

	// O(n ^ 4) solved it in another java class with O(n ^ 3)
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		
		while((s = sc.nextLine()) != null) {
			int n = Integer.parseInt(s);
			
			int [][] a= new int[n][n];
			
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j) {
					a[i][j] = sc.nextInt();
					if(i > 0) a[i][j] += a[i - 1][j];
					if(j > 0) a[i][j] += a[i][j - 1];
					if(i > 0 && j > 0) a[i][j] -= a[i - 1][j - 1];
				}
			
			int max = -127 * 100 * 100;
			for(int i = 0; i < n; ++i) for(int j = 0; j < n; ++j)
				for(int k = i; k < n; ++k) for(int l = j; l < n; ++l) {
					int tmp = a[k][l];
					if(i > 0) tmp -= a[i - 1][l];
					if(j > 0) tmp -= a[k][j - 1];
					if(i > 0 && j > 0) tmp += a[i - 1][j - 1];
					max = Math.max(max, tmp);
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