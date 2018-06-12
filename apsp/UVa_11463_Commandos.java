package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11463_Commandos {

	static int adj[][] = new int[100][100];
	static int INF = (int) 1e9, N;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), cases = 1;
		
		while(t-- > 0) {
			N = sc.nextInt();
			
			int r = sc.nextInt(), a, b;
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) adj[i][j] = (i == j)?0:INF;
			}
			
			for(int i = 0; i < r; ++i) {
				a = sc.nextInt(); b = sc.nextInt();
				adj[a][b] = 1; adj[b][a] = 1;
			}
			
			floyd();
			
			a = sc.nextInt(); b = sc.nextInt();
			int max = 0;
			for(int i = 0; i < N; ++i) {
				max = Math.max(max, adj[a][i] + adj[i][b]);
			}
			out.printf("Case %d: %d\n", cases++, max);
		}
		
		out.flush();
		out.close();
	}

	private static void floyd() {
		for(int k = 0; k < N; ++k)
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
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