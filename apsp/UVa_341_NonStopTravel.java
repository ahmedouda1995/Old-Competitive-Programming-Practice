package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_341_NonStopTravel {

	static int N, INF = (int) 1e9;
	static int[][] adj = new int[11][11];
	static int [][] p = new int[11][11];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		int cases = 1;
		
		while((N = sc.nextInt()) != 0) {
			int m;
			
			for(int i = 1; i <= N; ++i) {
				for(int j = 1; j <= N; ++j) {
					if(i == j) adj[i][j] = 0; else adj[i][j] = INF;
					p[i][j] = i;
				}
			}
			
			for(int i = 1; i <= N; ++i) {
				m = sc.nextInt();
				
				while(m-- > 0) {
					adj[i][sc.nextInt()] = sc.nextInt();
				}
			}
			
			int a = sc.nextInt(), b = sc.nextInt();
			floyd();
			out.printf("Case %d: Path = ", cases++);
			print(a, b);
			out.printf("; %d second delay\n", adj[a][b]);
		}
		
		out.flush();
		out.close();
	}

	private static void print(int src, int dest) {
		if(src == dest) out.print(src);
		else {
			print(src, p[src][dest]);
			out.print(" " + dest);
		}
		
	}

	private static void floyd() {
		for(int k = 1; k <= N; ++k)
			for(int i = 1; i <= N; ++i)
				for(int j = 1; j <= N; ++j) {
					if(adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
						p[i][j] = p[k][j];
					}
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