package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10724_RoadConstruction {

	// consider all paths if they go through new road and are cheaper only
	
	static int N;
	static double INF = 1e9;
	static double adj[][] = new double [50][50];
	static double dist[][] = new double [50][50];
	static double ref[][] = new double [50][50];
	static double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int M;
		
		int posX[] = new int[50];
		int posY[] = new int[50];
		
		while((N = sc.nextInt()) != 0 | (M = sc.nextInt()) != 0) {
			for(int i = 0; i < N; ++i) {
				posX[i] = sc.nextInt();
				posY[i] = sc.nextInt();
			}
			
			for(int i = 0; i < N; ++i) {
				Arrays.fill(adj[i], INF);
				Arrays.fill(ref[i], INF);
				adj[i][i] = 0; ref[i][i] = 0;
			}
			int a, b;
			for(int i = 0; i < M; ++i) {
				a = sc.nextInt() - 1; b = sc.nextInt() - 1;
				double d = dist(posX[a], posY[a], posX[b], posY[b]);
				adj[a][b] = adj[b][a] = d;
			}
			
			floyd1();
			
			int u = 0, v = 0;
			double max = -INF;
			double minDist = INF;
			
			for(int i = 0; i < N; ++i) {
				for(int j = i + 1; j < N; ++j) {
					if(ref[i][j] == INF) {
						double dd = dist(posX[i], posY[i], posX[j], posY[j]);
						double d = floyd2(i, j, dd);
						if(Math.abs(max - d) < EPS) {
							if(dd < minDist) {
								max = d; u = i; v = j; minDist = dd;
							}
						}
						else if(d > max) {
							max = d; u = i; v = j; minDist = dd;
						}
					}
				}
			}
			
			if(max <= 1)
				out.println("No road required");
			else
				out.println((u + 1) + " " + (v + 1));
		}
		
		out.flush();
		out.close();
	}

	private static void floyd1() {
		for(int k = 0; k < N; ++k)
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
	}

	public static double floyd2(int u, int v, double d) {
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < N; ++j)
				dist[i][j] = adj[i][j];
		
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < N; ++j)
				dist[i][j] = Math.min(dist[i][j], Math.min(dist[i][u] + d + dist[v][j], dist[i][v] + d + dist[u][j]));
		
		double res = 0;
		
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < N; ++j)
				res += adj[i][j] - dist[i][j];
		
		return res;
	}
	
	static double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
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