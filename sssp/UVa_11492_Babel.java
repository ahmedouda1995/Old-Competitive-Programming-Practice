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
import java.util.TreeMap;
import java.util.TreeSet;

public class UVa_11492_Babel {

	static int N, INF = (int) 1e9;
	static String words[];
	static ArrayList<Integer> adj[];
	static int dist[];
	static TreeSet<Integer> set2;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		TreeMap<String, Pair> map = new TreeMap<String, Pair>();
		TreeSet<Integer> set1 = new TreeSet<Integer>();
		set2 = new TreeSet<Integer>();
		
		while((N = sc.nextInt()) != 0) {
			
			map.clear(); set1.clear(); set2.clear();
			words = new String[N];
			adj = new ArrayList[N];
			dist = new int[N];
			
			for(int i = 0; i < N; ++i) adj[i] = new ArrayList<Integer>();
			
			String from = sc.next(), to = sc.next();
			String a, b, c;
			
			for(int i = 0; i < N; ++i) {
				a = sc.next(); b = sc.next(); c = sc.next();
				if(a.equals(from) || b.equals(from)) set1.add(i);
				if(a.equals(to) || b.equals(to)) set2.add(i);
				words[i] = c;
				map.put(c, new Pair(a, b));
			}
			
			for(int i = 0; i < N; ++i)
				for(int j = i + 1; j < N; ++j) {
					Pair p1 = map.get(words[i]);
					Pair p2 = map.get(words[j]);
					if(p1.x.equals(p2.x) || p1.y.equals(p2.y) || p1.x.equals(p2.y) || p1.y.equals(p2.x)) {
						if(words[i].charAt(0) != words[j].charAt(0)) {
							adj[i].add(j);
							adj[j].add(i);
						}
					}
				}
			System.out.println(Arrays.toString(adj));
			int min = INF;
			for(int u : set1) {
				Arrays.fill(dist, INF);
				dijkstra(u);
				//System.out.println(set1);
				//System.out.println(set2);
				System.out.println(Arrays.toString(dist));
				for(int v : set2) {
					min = Math.min(min, dist[v]);
				}
			}
			
			if(min == INF)
				out.println("impossivel");
			else
				out.println(min);
			
		}
		
		out.flush();
		out.close();
	}

	private static void dijkstra(int u) {
		PriorityQueue<P> pq = new PriorityQueue<P>();
		
		dist[u] = words[u].length(); pq.offer(new P(u, dist[u]));
		
		while(!pq.isEmpty()) {
			P U = pq.poll();
			
			if(U.y > dist[U.x]) continue;
			
			for(int V : adj[U.x]) {
				if(dist[V] > dist[U.x] + words[V].length()) {
					dist[V] = dist[U.x] + words[V].length();
					pq.offer(new P(V, dist[V]));
				}
			}
		}
	}

	static class P implements Comparable<P>{
		int x, y;
		
		P(int x, int y) { this.x = x; this.y = y; }

		@Override
		public int compareTo(P p) {
			return Integer.compare(y, this.y);
		}
	}
	
	static class Pair {
		String x;
		String y;
		
		public Pair(String x, String y) {
			this.x = x;
			this.y = y;
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