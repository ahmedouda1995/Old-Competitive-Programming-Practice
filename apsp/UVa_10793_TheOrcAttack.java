package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10793_TheOrcAttack {

	static int INF = (int) 1e9, N;
	static int adj[][] = new int[101][101];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), m, cases = 1;
		
		while(t-- > 0) {
			
			N = sc.nextInt(); m = sc.nextInt();
			
			for(int i = 1; i <= N; ++i) {
				Arrays.fill(adj[i], INF);
				adj[i][i] = 0;
			}
			
			int u, v, c;
			
			for(int i = 0; i < m; ++i) {
				u = sc.nextInt();
				v = sc.nextInt();
				c = sc.nextInt();
				
				if(c < adj[u][v]) {
					adj[u][v] = adj[v][u] = c;
				}
			}
			
			floyd();
			
			int min = INF;
			
			for(int i = 1; i <= N; ++i) {
				if(adj[i][1] == adj[i][2] && adj[i][2] == adj[i][3] &&
				   adj[i][3] == adj[i][4] && adj[i][4] == adj[i][5]) {
					int tmp = -1;
					for(int j = 5; j <= N; ++j) tmp = Math.max(adj[i][j], tmp);
					if(tmp < min) {
						min = tmp;
					}
				}
			}
			out.printf("Map %d: ", cases++);
			if(min == INF) out.println(-1); else out.println(min);
		}
		
		out.flush();
		out.close();
	}

	private static void floyd() {
		for(int k = 1; k <= N; ++k)
			for(int i = 1; i <= N; ++i)
				for(int j = 1; j <= N; ++j)
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
	}

	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(this.x, p.x) == 0)
				return Integer.compare(this.y, p.y);
			return Integer.compare(this.x, p.x);
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