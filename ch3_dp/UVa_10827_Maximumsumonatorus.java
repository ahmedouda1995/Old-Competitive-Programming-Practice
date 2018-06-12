package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10827_Maximumsumonatorus {

	static int grid[][];
	static int N;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			N = sc.nextInt();
			grid = new int[2 * N - 1][2 * N - 1];
			
			for(int i = 0; i < N; ++i) {
				grid[0][i] = sc.nextInt();
				if(i != N - 1) grid[0][N + i] = grid[0][i];
			}
			for(int i = 1; i < N; ++i)
				for(int j = 0; j < N; ++j) {
					grid[i][j] = grid[i - 1][j] + sc.nextInt();
					if(j != N - 1) grid[i][N + j] = grid[i][j];
				}
			
			for(int i = N; i < 2 * N - 1; ++i) {
				for(int j = 0; j < 2 * N - 1; ++j) {
					grid[i][j] = grid[i - N][j] + grid[N - 1][j];
				}
			}
			
			int max = grid[0][0];
			
				for(int i = 0; i < 2 * N - 1; ++i) {
					for(int j = i; j < Math.min(2 * N - 1, N + i); ++j) {
						
						for(int st2 = 0; st2 < N; ++st2) {
							int next = grid[j][st2];
							next -= (i == 0)?0:grid[i - 1][st2];
							int sum = next;
							max = Math.max(max, sum);
							
							for(int k = st2 + 1; k < st2 + N; ++k) {
								next = grid[j][k];
								next -= (i == 0)?0:grid[i - 1][k];
								
								sum = Math.max(sum + next, next);
								max = Math.max(sum, max);
							}
						}
					}
			}
			
			out.println(max);
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