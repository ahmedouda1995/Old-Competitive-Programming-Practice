package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10449_Traffic {

	static int INF = (int) 1e9;
	static int a[] = new int[200];
	static ArrayList<Pair> adj[] = new ArrayList[200];
	static int dist[] = new int[200];
	static boolean negCycle[] = new boolean[200];
	
	public static void main(String[] args) throws IOException{
		//Scanner sc = new Scanner(new FileReader("input.txt"));
		java.util.Scanner sc = new java.util.Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s; int n, cases = 1; StringTokenizer st;
		
		while(sc.hasNextInt()) {
			//st = new StringTokenizer(s);
			n = sc.nextInt();
			for(int i = 0; i < n; ++i) adj[i] = new ArrayList<Pair>();
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			int m = sc.nextInt(), x, y;
			
			for(int i = 0; i < m; ++i) {
				x = sc.nextInt() - 1; y = sc.nextInt() - 1;
				adj[x].add(new Pair(y, (int) Math.pow((a[y] - a[x]), 3)));
			}
			
			Arrays.fill(dist, INF);
			Arrays.fill(negCycle, false);
			bellmanFord(n);
			int q = sc.nextInt(), dest;
			
			out.printf("Set #%d\n", cases++);
			
			for(int i = 0; i < q; ++i) {
				dest = sc.nextInt() - 1;
				if(!negCycle[dest]) {
					if(dist[dest] < 3 || dist[dest] == INF)
						out.println("?");
					else
						out.println(dist[dest]);
				}
				else
					out.println("?");
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static void bellmanFord(int V) {
		dist[0] = 0;
		
		for(int i = 0; i < V - 1; ++i) {
			for(int j = 0; j < V; ++j) {
				for(Pair e : adj[j]) {
					if(dist[j] + e.w < dist[e.v] && dist[j] != INF)
						dist[e.v] = dist[j] + e.w;
				}
			}
		}
		
		boolean mod = true;
		
		for(int i = 0; i < V - 1 && mod; ++i) {
			mod = false;
			for(int j = 0; j < V; ++j) {
				for(Pair e : adj[j]) {
					if(dist[j] + e.w < dist[e.v] && dist[j] != INF) {
						mod = true;
						dist[e.v] = dist[j] + e.w;
						negCycle[e.v] = true;
					}
				}
			}
		}
		
	}

	static class Pair {
		int v;
		int w;
		
		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
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