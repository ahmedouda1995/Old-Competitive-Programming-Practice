package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_1198_TheGeodeticSetProblem {

	static int V, INF = (int) 1e9;
	static int adj[][];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		V = sc.nextInt();
		
		adj = new int[V + 1][V + 1];
		
		for(int i = 1; i <= V; ++i) {
			Arrays.fill(adj[i], INF);
			adj[i][i] = 0;
		}
		
		StringTokenizer st;
		
		for(int i = 1; i <= V; ++i) {
			st = new StringTokenizer(sc.nextLine());
			
			while(st.hasMoreTokens()) {
				adj[i][Integer.parseInt(st.nextToken())] = 1;
			}
		}
		
		floyd();
		
		long ref = (((1L) << V) - 1 << 1);
		
		int m = sc.nextInt();
		
		String a[];
		
		while(m-- > 0) {
			a = sc.nextLine().split(" ");
			long comp = 0L;
			for(int i = 0; i < a.length; ++i) {
				for(int j = i + 1; j < a.length; ++j) {
					comp |= getSet(Integer.parseInt(a[i]), Integer.parseInt(a[j]));
				}
			}
			if(comp == ref) out.println("yes"); else out.println("no");
		}
			
		out.flush();
		out.close();
	}

	private static long getSet(int u, int v) {
		
		long I = 0L;
		
		for(int i = 1; i <= V; ++i) {
			if(adj[u][i] + adj[i][v] == adj[u][v]) I |= ((1L) << i);
		}
		
		return I;
	}
	
	private static void floyd() {
		for(int k = 1; k <= V; ++k)
			for(int i = 1; i <= V; ++i)
				for(int j = 1; j <= V; ++j)
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