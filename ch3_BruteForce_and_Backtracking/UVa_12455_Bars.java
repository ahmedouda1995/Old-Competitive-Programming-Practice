package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_12455_Bars {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0){
			int size = sc.nextInt(), n = sc.nextInt(), m = (int) Math.pow(2, n);
			int [] a = new int[n];
			for (int i = 0; i < n; i++) a[i] = sc.nextInt();
			boolean printed = false;
			
			for (int i = 0; i < m; i++) {
				int sum = 0;
				for (int j = 0; j < n; j++) {
					sum += ((i >> j) & 1) == 1?a[j]:0;
				}
				if(sum == size) {out.println("YES"); printed = true; break;}
			}
			
			if(!printed) out.println("NO");
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
