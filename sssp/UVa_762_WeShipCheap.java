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

public class UVa_762_WeShipCheap {

	static int INF = (int) 1e9, N;
	static ArrayList<Integer> adj[] = new ArrayList[676];
	static int dist[] = new int[676];
	static int p[] = new int[676];
	static PrintWriter out = new PrintWriter(System.out);
	static TreeMap<Integer,String> map2 = new TreeMap<Integer, String>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		boolean first = true;
		
		while(sc.ready()) {
			if(first) first = false; else out.println();
			N = sc.nextInt();
			
			map.clear(); map2.clear();
			
			for(int i = 0; i < 676; ++i) {
				adj[i] = new ArrayList<Integer>();
			}
			
			int k = 0;
			String a, b;
			for(int i = 0; i < N; ++i) {
				a = sc.next(); b = sc.next();
				if(!map.containsKey(a)) {
					map.put(a, k); map2.put(k++, a);
				}
				if(!map.containsKey(b)) {
					map.put(b, k); map2.put(k++, b);
				}
				
				adj[map.get(a)].add(map.get(b));
				adj[map.get(b)].add(map.get(a));
			}
			
			a = sc.next(); b = sc.next();
			
			if(!map.containsKey(a) || !map.containsKey(b))
				out.println("No route");
			else {
				int src = map.get(a), dest = map.get(b);
				
				bfs(src, dest);
				if(dist[dest] == INF)
					out.println("No route");
				else {
					print(p[dest], src, dest);
				}
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static void print(int par, int src, int tmp) {
		if(src == par) out.println(map2.get(par) + " " + map2.get(tmp));
		else {
			print(p[par], src, par);
			out.println(map2.get(par) + " " + map2.get(tmp));
		}
	}

	private static void bfs(int src, int dest) {
		Queue<Integer> q = new LinkedList<Integer>();
		Arrays.fill(dist, INF); dist[src] = 0; q.offer(src);
		p[src] = src;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			
			if(u == dest) return;
			
			for(int v : adj[u]) {
				if(dist[v] == INF) {
					q.offer(v);
					dist[v] = dist[u] + 1;
					p[v] = u;
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