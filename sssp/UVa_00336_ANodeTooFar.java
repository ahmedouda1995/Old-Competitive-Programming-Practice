package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_00336_ANodeTooFar {

	static int INF = (int) 1e9;
	static ArrayList<Integer> adj[] = new ArrayList[30];
	static int dist[] = new int[30];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, cases = 1;
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		while((n = sc.nextInt()) != 0) {
			map.clear();
			for(int i = 0; i < 30; ++i) adj[i] = new ArrayList<Integer>();
			
			int u, v, k = 0;
			
			for(int i = 0; i < n; ++i) {
				u = sc.nextInt(); v = sc.nextInt();
				if(!map.containsKey(u))
					map.put(u, k++);
				if(!map.containsKey(v))
					map.put(v, k++);
				adj[map.get(u)].add(map.get(v)); adj[map.get(v)].add(map.get(u));
			}
			
			int s, t;
			
			while((s = sc.nextInt()) != 0 | (t = sc.nextInt()) != 0) {
				Arrays.fill(dist, INF);
				
				if(!map.containsKey(s))
					out.printf("Case %d: %d nodes not reachable from"
							+ " node %d with TTL = %d.\n", cases++, k, s, t);
				else {
					int src = map.get(s);
					int res = bfs(src, t);
					out.printf("Case %d: %d nodes not reachable from"
							+ " node %d with TTL = %d.\n", cases++, k - res, s, t);
				}
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static int bfs(int s, int t) {
		Queue<Integer> q = new LinkedList<Integer>();
		dist[s] = 0; q.offer(s);
		
		int res = 1;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			if(dist[u] == t) return res;
			for(int v : adj[u]) {
				if(dist[v] == INF) {
					res++;
					dist[v] = dist[u] + 1;
					q.offer(v);
				}
			}
		}
		return res;
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