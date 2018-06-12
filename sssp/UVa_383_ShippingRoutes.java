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
import java.util.TreeMap;

public class UVa_383_ShippingRoutes {

	static int M, INF = (int) 1e9;
	static ArrayList<Integer> adj[] = new ArrayList[30];
	static int dist[] = new int[30];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), cases = 1, N, P;
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		out.println("SHIPPING ROUTES OUTPUT");
		out.println();
		
		while(t-- > 0) {
			map.clear();
			M = sc.nextInt(); N = sc.nextInt(); P = sc.nextInt();
			for(int i = 0; i < M; ++i) {
				adj[i] = new ArrayList<Integer>();
				map.put(sc.next(), i);
			}
			
			String a, b;
			
			out.printf("DATA SET  %d\n", cases++);
			out.println();
			
			for(int i = 0; i < N; ++i) {
				a = sc.next(); b = sc.next();
				adj[map.get(a)].add(map.get(b));
				adj[map.get(b)].add(map.get(a));
			}
			
			int w;
			
			for(int i = 0; i < P; ++i) {
				w = sc.nextInt(); a = sc.next(); b = sc.next();
				bfs(map.get(a), map.get(b));
				int test = dist[map.get(b)];
				if(test == INF)
					out.println("NO SHIPMENT POSSIBLE");
				else
					out.printf("$%d\n", (100 * test * w));
			}
			out.println();
		}
		out.println("END OF OUTPUT");
		out.flush();
		out.close();
	}
	
	private static void bfs(int src, int dest) {
		for(int i = 0; i < M; ++i) dist[i] = INF;
		
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