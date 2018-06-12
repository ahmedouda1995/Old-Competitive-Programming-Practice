package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class UVa_10986_Sendingemail {

	static int INF  = (int) 1e9;
	static ArrayList<Pair> adj[] = new ArrayList[20000];
	static int dist[] = new int[20000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n, m, s, dest, cases = 1;
		
		while(t-- > 0) {
			n = sc.nextInt(); m = sc.nextInt();
			s = sc.nextInt(); dest = sc.nextInt();
			
			for(int i = 0; i < n; ++i) {
				adj[i] = new ArrayList<Pair>();
				dist[i] = INF;
			}
			int a, b, w;
			for(int i = 0; i < m; ++i) {
				a = sc.nextInt(); b = sc.nextInt(); w = sc.nextInt();
				adj[a].add(new Pair(b, w)); adj[b].add(new Pair(a, w));
			}
			
			out.printf("Case #%d: ", cases++);
			dijkstra(s, dest);
			if(dist[dest] == INF)
				out.println("unreachable");
			else
				out.println(dist[dest]);
		}
		
		out.flush();
		out.close();
	}
	
	private static void dijkstra(int s, int dest) {
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		dist[s] = 0; pq.offer(new Pair(s, 0));
		
		while(!pq.isEmpty()) {
			Pair u = pq.poll();
			if(u.x == dest) return;
			if(u.y > dist[u.x]) continue;
			
			for(Pair e : adj[u.x]) {
				if(e.y + dist[u.x] < dist[e.x]) {
					dist[e.x] = e.y + dist[u.x];
					pq.offer(new Pair(e.x, dist[e.x]));
				}
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
			if(Integer.compare(this.y, p.y) == 0)
				return Integer.compare(this.x, p.x);
			return Integer.compare(this.y, p.y);
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