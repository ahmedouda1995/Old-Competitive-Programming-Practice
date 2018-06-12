package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11951_Area {

	static int grid[][] = new int[100][100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), cases = 1;
		
		while(t-- > 0) {
			int N = sc.nextInt(), M = sc.nextInt(), S = sc.nextInt();
			
			for(int i = 0; i < M; ++i) grid[0][i] = sc.nextInt();
			for(int i = 1; i < N; ++i) {
				for(int j = 0; j < M; ++j)
					grid[i][j] = grid[i - 1][j] + sc.nextInt();
			}
			
			int bestCost = 0, bestArea = 0;
			
			for(int i = 0; i < N; ++i) {
				for(int j = i; j < N; ++j) {
					int area = 0, cost = 0, st = 0;
					for(int k = 0; k < M; ++k) {
						int next = grid[j][k];
						next -= (i == 0)?0:grid[i - 1][k];
						
						cost += next;
						while(cost > S) {
							int tmp = grid[j][st];
							tmp -= (i == 0)?0:grid[i - 1][st];
							cost -= tmp;
							st++;
						}
						area = (k - st + 1) * (j - i + 1);
						
						if(area == bestArea && cost < bestCost)
							bestCost = cost;
						else if(area > bestArea) {
							bestArea = area;
							bestCost = cost;
						}
					}
				}
			}
			out.printf("Case #%d: %d %d\n", cases++, bestArea, bestCost);
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