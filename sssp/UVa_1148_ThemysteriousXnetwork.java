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

public class UVa_1148_ThemysteriousXnetwork {

	static int N, INF = (int) 1e9;
	static int dist[] = new int[100_000];
	static ArrayList<Integer> adj[] = new ArrayList[100_000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			N = sc.nextInt();
			
			for(int i = 0; i < N; ++i) {
				adj[i] = new ArrayList<Integer>();
				dist[i] = INF;
			}
			
			int a, m;
			
			for(int i = 0; i < N; ++i) {
				a = sc.nextInt(); m = sc.nextInt();
				
				for(int j = 0; j < m; ++j) adj[a].add(sc.nextInt());
			}
			
			int src = sc.nextInt(), dest = sc.nextInt();
			
			bfs(src, dest);
			out.println(src + " " + dest + " " + (dist[dest] - 1));
			
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static void bfs(int src, int dest) {
		dist[src] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(src);
		
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