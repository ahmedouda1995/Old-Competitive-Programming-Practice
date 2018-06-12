package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class UVa_10389_Subway {

	static double INF = 1e9;
	static ArrayList<Pair> adj[] = new ArrayList[205];
	static double dist[] = new double[205];
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		String s;
		s = br.readLine();
		int t = Integer.parseInt(s), sx, sy, ex, ey;
		br.readLine();
		int posX[] = new int[205];
		int posY[] = new int[205];
		int line[] = new int[205];
		
		while(t-- > 0) {
			int k = 0;
			s = br.readLine();
			st = new StringTokenizer(s);
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < 205; ++i) {
				adj[i] = new ArrayList<Pair>();
				dist[i] = INF;
			}
			
			posX[k] = sx; posY[k] = sy; k++;
			posX[k] = ex; posY[k] = ey; k++;
			
			double fact1 = 40.0 * (1000.0 / 60.0);
			double fact2 = 10.0 * (1000.0 / 60.0);
			
			while(br.ready() && (s = br.readLine()).length() > 0) {
				st = new StringTokenizer(s);
				while(!st.hasMoreTokens())
					st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				posX[k] = a; posY[k] = b; k++;
				int x, y;
				while(true) {
					while(!st.hasMoreTokens())
						st = new StringTokenizer(br.readLine());
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					if(x == -1 && y == -1) break;
					posX[k] = x; posY[k] = y;
					double d = dist(x, y, posX[k - 1], posY[k - 1]);
					adj[k].add(new Pair(k - 1, (d / fact1)));
					adj[k - 1].add(new Pair(k, (d / fact1)));
					k++;
				}
			}
			
			for(int i = 0; i < k; ++i) {
				for(int j = i + 1; j < k; ++j) {
						double d = dist(posX[i], posY[i], posX[j], posY[j]);
						adj[i].add(new Pair(j, (d / fact2)));
						adj[j].add(new Pair(i, (d / fact2)));
				}
			}

			dijkstra(0, 1);
			out.println((int)Math.round(dist[1]));
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static void dijkstra(int src, int dest) {
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		dist[src] = 0; pq.offer(new Pair(src, 0));
		
		while(!pq.isEmpty()) {
			Pair u = pq.poll();
			if(u.x == dest) return;
			if(u.y < dist[u.x]) continue;
			
			for(Pair e : adj[u.x]) {
				if(dist[u.x] + e.y < dist[e.x]) {
					dist[e.x] = dist[u.x] + e.y;
					pq.offer(new Pair(e.x, dist[e.x]));
				}
			}
		}
	}

	static double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
	
	static class Pair implements Comparable<Pair>{
		int x;
		double y;
		
		public Pair(int x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			return Double.compare(this.y, p.y);
		}
		
		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
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