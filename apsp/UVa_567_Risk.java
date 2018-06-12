package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_567_Risk {

	static int adj[][] = new int[21][21];
	static int N = 19, INF = (int) 1e9;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int cases = 1;
		
		while(sc.ready()) {
			int n;
			
			for(int i = 1; i <= N + 1; ++i) {
				Arrays.fill(adj[i], INF);
				adj[i][i] = 0;
			}
			int tmp;
			for(int i = 1; i <= N; ++i) {
				n = sc.nextInt();
				for(int j = 0; j < n; ++j) {
					tmp = sc.nextInt();
					adj[i][tmp] = 1;
					adj[tmp][i] = 1;
				}
			}
			
			floyd();
			
			int r = sc.nextInt(), a, b;
			out.printf("Test Set #%d\n", cases++);
			while(r-- > 0) {
				a = sc.nextInt(); b = sc.nextInt();
				out.printf("%2d to %2d: %d\n", a, b, (adj[a][b]));
			}
			out.println();
		}
		
		out.flush();
		out.close();
	}

	static void floyd() {
		for(int k = 1; k <= N + 1; ++k)
			for(int i = 1; i <= N + 1; ++i)
				for(int j = 1; j <= N + 1; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
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