package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10487_Closest_Sums {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int n, a[], cases = 1;
		while((n = sc.nextInt()) != 0){
			a = new int[n];
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			int m[][] = new int[n][n];
			for (int i = 0; i < m.length; i++) {
				for (int j = i + 1; j < m.length; j++) {
					m[i][j] = (a[i] != a[j])?a[i] + a[j]:Integer.MIN_VALUE;
				}
			}
			int k = sc.nextInt();
			
			out.println("Case " + cases++ + ":");
			
			for (int i = 0; i < k; i++) {
				int b = sc.nextInt();
				int diff = Integer.MAX_VALUE, result = 0;
				for (int j = 0; j < m.length; j++) {
					for (int l = j + 1; l < m.length; l++) {
						if(m[j][l] != Integer.MIN_VALUE)
							if(Math.abs(b - m[j][l]) < diff){
								diff = Math.abs(b - m[j][l]);
								result = m[j][l];
							}
					}
				}
				out.println("Closest sum to " + b + " is " + result + ".");
			}
		}
		
		out.flush();
		out.close();
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