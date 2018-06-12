package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_983_LocalizedSummingforBlurring {

	static long grid[][] = new long[1000][1000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int N, M; boolean first = true;
		
		while(sc.ready()) {
			if(first) first = false; else out.println();
			N = sc.nextInt(); M = sc.nextInt();
			
			for(int i = 0; i < N; ++i) grid[0][i] = sc.nextLong();
			
			for(int i = 1; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					grid[i][j] = sc.nextLong() + grid[i - 1][j];
				}
			}
			
			long total = 0;
			
			for(int i = 0; i <= (N - M); ++i) {
				for(int j = 0; j <= (N - M); j++) {
					long sum = 0;
					for(int k = 0; k < M; ++k) {
						sum += grid[i + M - 1][j + k];
						sum -= (i == 0)?0:grid[i - 1][j + k];
					}
					out.println(sum); total += sum;
				}
			}
			out.println(total);
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