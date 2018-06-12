package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa_10610_GopherandHawks {

	static ArrayList<Integer> adj[] = new ArrayList[1002];
	static int dist[] = new int[1002];
	static int INF = (int) 1e9, N;
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int v, t;
		
		double [] posX = new double[1002];
		double [] posY = new double[1002];
		
		while((v = sc.nextInt()) != 0 | (t = sc.nextInt()) != 0) {
			
			double allowed = v * t * 60;
			
			int k = 0;
			
			while(!sc.nextIsEmpty()) {
				posX[k] = Double.parseDouble(sc.next());
				posY[k++] = Double.parseDouble(sc.next());
			}
			N = k;
			
			for(int i = 0; i < k; ++i) {
				dist[i] = INF;
				adj[i] = new ArrayList<Integer>();
			}
			for(int i = 0; i < k; ++i) {
				for(int j = i + 1; j < k; ++j) {
					if(dist(posX[i], posY[i], posX[j], posY[j]) <= allowed + EPS) {
						adj[i].add(j); adj[j].add(i);
					}
				}
			}
			
			bfs(0, 1);
			if(dist[1] == INF)
				out.println("No.");
			else
				out.printf("Yes, visiting %d other holes.\n", dist[1] - 1);
		}
		
		out.flush();
		out.close();
	}
	
	private static void bfs(int src, int dest) {
		Queue<Integer> q = new LinkedList<Integer>();
		dist[src] = 0; q.offer(src);
		
		while(!q.isEmpty()) {
			int u = q.poll();
			if(u == dest) return;
			for(int v : adj[u]) {
				if(dist[v] == INF) {
					dist[v] = dist[u] + 1;
					q.offer(v);
				}
			}
		}
	}

	static double dist(double x1, double y1, double x2, double y2) {
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

		public boolean nextIsEmpty() throws IOException {
			String s = br.readLine();
			if(s.isEmpty())
				return true;
			st = new StringTokenizer(s);
			return false;
		}
		
		public boolean ready() throws IOException {return br.ready();}
	}
}