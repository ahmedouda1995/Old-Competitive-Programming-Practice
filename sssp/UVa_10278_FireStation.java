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
import java.util.TreeSet;

public class UVa_10278_FireStation {

	static int INF = (int) 1e9, N;
	static int dist[];
	static TreeSet<Integer> fireStations = new TreeSet<Integer>();
	static ArrayList<Pair> adj[] = new ArrayList[500];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), f;
		
		while(t-- > 0) {
			fireStations.clear();
			f = sc.nextInt(); N = sc.nextInt();
			dist = new int[N];
			
			for(int i = 0; i < N; ++i) {
				adj[i] = new ArrayList<Pair>();
				dist[i] = INF;
			}
			
			while(f-- > 0) {
				fireStations.add(sc.nextInt() - 1);
			}
			
			String s; StringTokenizer st;
			int a, b, c;
			
			while((s = sc.nextLine()) != null && s.length() > 0) {
				st = new StringTokenizer(s);
				a = Integer.parseInt(st.nextToken()) - 1;
				b = Integer.parseInt(st.nextToken()) - 1;
				c = Integer.parseInt(st.nextToken());
				
				adj[a].add(new Pair(b, c));
				adj[b].add(new Pair(a, c));
			}
			
			int max = INF; int res = 1;
			for(int i = 0; i < N; ++i) {
				Arrays.fill(dist, INF);
				int tmp = dijkstra(i);
				if(tmp < max) {
					res = i + 1;
					max = tmp;
				}
			}
			
			out.println(res);
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}

	private static int dijkstra(int k) {
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		for(int v : fireStations) {
			pq.offer(new Pair(v, 0));
			dist[v] = 0;
		}
		
		pq.offer(new Pair(k, 0));
		dist[k] = 0;
		
		while(!pq.isEmpty()) {
			Pair u = pq.poll();
			
			if(u.y > dist[u.x]) continue;
			
			for(Pair v : adj[u.x]) {
				if(dist[v.x] > dist[u.x] + v.y) {
					dist[v.x] = dist[u.x] + v.y;
					pq.offer(new Pair(v.x, dist[v.x]));
				}
			}
		}
		int max = -1;
		for(int i = 0; i < N; ++i) {
			max = Math.max(max, dist[i]);
		}
		return max;
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