package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10496_CollectingBeepers {

	static int N, INF = (int) 1e9;
	static int adjMat[][] = new int[11][11];
	static int memo[][] = new int[11][1 << 11];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		int posX[] = new int[11], posY[] = new int[11];
		
		
		while(t-- > 0) {
			sc.nextInt(); sc.nextInt();
			posX[0] = sc.nextInt(); posY[0] = sc.nextInt();
			N = sc.nextInt(); N++;
			for(int i = 1; i < N; ++i) {
				posX[i] = sc.nextInt();
				posY[i] = sc.nextInt();
			}
			
			for(int i = 0; i < N; ++i) {
				for(int j = i + 1; j < N; ++j) {
					adjMat[i][j] = adjMat[j][i] =
							Math.abs(posX[i] - posX[j]) + Math.abs(posY[i] - posY[j]);
				}
			}
			
			for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
			
			out.println("The shortest path has length " + solve(0, 1));
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int vertex, int mask) {
		if(mask == ((1 << N) - 1)) return adjMat[vertex][0];
		if(memo[vertex][mask] != -1) return memo[vertex][mask];
		
		int min = INF;
		for(int i = 0; i < N; ++i) {
			if(((1 << i) & mask) == 0) {
				min = Math.min(min, adjMat[vertex][i] + solve(i, (mask | (1 << i))));
			}
		}
		
		return memo[vertex][mask] = min;
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