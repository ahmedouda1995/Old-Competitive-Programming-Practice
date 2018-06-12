package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_1233_USHER {

	static int INF = (int) 1e9;
	static int adj[][] = new int[501][501];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), b, p, n;
		
		while(t-- > 0) {
			b = sc.nextInt(); p = sc.nextInt();
			
			n = sc.nextInt();
			
			for(int i = 0; i <= p; ++i) {
				for(int j = 0; j <= p; ++j) adj[i][j] = INF;
			}
			
			while(n-- > 0) {
				adj[0][sc.nextInt()] = 0;
			}
			int w, to;
			for(int i = 1; i <= p; ++i) {
				n = sc.nextInt();
				while(n-- > 0) {
					w = sc.nextInt();
					to = sc.nextInt();
					if(w < adj[i][to])
						adj[i][to] = w;
				}
			}
			
			floyd(p);
			
			int k = adj[0][0];
			int res = 0;
			if(k < b) {
				while(b > k) {
					res++;
					b = b - k + 1;
				}
			}
			
			out.println(res);
		}
		
		out.flush();
		out.close();
	}

	private static void floyd(int n) {
		for(int k = 0; k <= n; ++k)
			for(int i = 0; i <= n; ++i)
				for(int j = 0; j <= n; ++j)
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