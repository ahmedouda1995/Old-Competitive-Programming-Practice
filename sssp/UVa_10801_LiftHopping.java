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

public class UVa_10801_LiftHopping {

	static int N, K, INF = (int) 1e9;
	static ArrayList<Pair> adj[];
	static int time[];
	static boolean stops[][];
	static int dist[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready()) {
			N = sc.nextInt(); K = sc.nextInt();
			int n = N * 100;
			adj = new ArrayList[n];
			stops = new boolean[N][100];
			time = new int[N];
			dist = new int[n];
			
			for(int i = 0; i < N; ++i) time[i] = sc.nextInt();
			
			for(int i = 0; i < n; ++i) {
				adj[i] = new ArrayList<Pair>();
			}
			StringTokenizer st;
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(sc.nextLine());
				int a, b; a = Integer.parseInt(st.nextToken());
				stops[i][a] = true;
				while(st.hasMoreTokens()) {
					b = Integer.parseInt(st.nextToken());
					stops[i][b] = true;
					adj[a + 100 * i].add(new Pair(b + 100 * i, (b - a) * time[i]));
					adj[b + 100 * i].add(new Pair(a + 100 * i, (b - a) * time[i]));
					a = b;
				}
			}
			
			for(int i = 0; i < 100; ++i) {
				for(int j = 0; j < N; ++j) {
					for(int k = j + 1; k < N; ++k) {
						if(stops[j][i] && stops[k][i]) {
							adj[i + 100 * j].add(new Pair(i + 100 * k, 60));
							adj[i + 100 * k].add(new Pair(i + 100 * j, 60));
						}
					}
				}
			}
			
			dijkstra();
			
			int res = INF;
			for(int i = 0; i < N; ++i) res = Math.min(res, dist[K + i * 100]);
			
			if(res == INF)
				out.println("IMPOSSIBLE");
			else
				out.println(res);
		}
		
		out.flush();
		out.close();
	}

	private static void dijkstra() {
		Arrays.fill(dist, INF);
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		
		for(int i = 0; i < N; ++i) {
			if(stops[i][0]) {
				pq.offer(new Pair(100 * i, 0));
				dist[100 * i] = 0;
			}
		}
		
		while(!pq.isEmpty()) {
			Pair u = pq.poll();
			
			if(u.x == K) return;
			if(u.y > dist[u.x]) continue;
			
			for(Pair v : adj[u.x]) {
				if(dist[v.x] > dist[u.x] + v.y) {
					dist[v.x] = dist[u.x] + v.y;
					pq.add(new Pair(v.x, dist[v.x]));
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
			return Integer.compare(this.y, p.y);
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