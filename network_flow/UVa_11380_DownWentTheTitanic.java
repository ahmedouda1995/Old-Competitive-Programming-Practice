package network_flow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa_11380_DownWentTheTitanic {

	static int N, M, P, src, sink;
	static final int INF = (int) 1e9;
	static char grid[][] = new char[30][30];
	static int resid[][];
	static int p[];
	static int dr[] = {0, 0, -1, 1};
	static int dc[] = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		ArrayList<Integer> adj[];
		
		while(sc.ready()) {
			N = sc.nextInt();
			M = sc.nextInt();
			P = sc.nextInt();
			
			String s;
			
			for(int i = 0; i < N; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < M; ++j)
					grid[i][j] = s.charAt(j);
			}
			
			int pr = N * M + 2;
			
		}
		
		out.flush();
		out.close();
	}
	
	private static int augment(int v, int minEdge) {
		if(v == src) return minEdge;
		
		minEdge = augment(p[v], Math.min(minEdge, resid[p[v]][v]));
		resid[p[v]][v] -= minEdge; resid[v][p[v]] += minEdge;
		return minEdge;
	}

	static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M && grid[x][y] != '~';
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