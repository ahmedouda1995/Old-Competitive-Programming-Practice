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

public class UVa_558_Wormholes {

	static int INF = (int) 1e9;
	static ArrayList<Pair> adj[] = new ArrayList[1000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n, m;
		
		while(t-- > 0) {
			n = sc.nextInt(); m = sc.nextInt();
			for(int i = 0; i < n; ++i) adj[i] = new ArrayList<Pair>();
			
			for(int i = 0; i < m; ++i)
				adj[sc.nextInt()].add(new Pair(sc.nextInt(), sc.nextInt()));
			
			out.println(negCycleSPFA(n)?"possible":"not possible");
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean negCycleSPFA(int v) {
		int[] dist = new int[v];
		int inQTimes[] = new int[v];
		boolean inQ[] = new boolean[v];
		Arrays.fill(dist, INF);
		
		dist[0] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(0); inQ[0] = true; inQTimes[0]++;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			inQ[u] = false;
			for(Pair e : adj[u]) {
				if(dist[u] + e.w < dist[e.v]) {
					dist[e.v] = dist[u] + e.w;
					if(!inQ[e.v]) {
						q.offer(e.v);
						inQ[e.v] = true; inQTimes[e.v]++;
						if (inQTimes[e.v] > v) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private static boolean negCycle(int v) {
		int[] dist = new int[v];
		Arrays.fill(dist, INF);
		
		dist[0] = 0;
		
		for(int i = 0; i < v - 1; ++i) {
			for(int j = 0; j < v; ++j) {
				for(Pair e : adj[j]) {
					if(dist[j] + e.w < dist[e.v])
						dist[e.v] = dist[j] + e.w;
				}
			}
		}
		for(int j = 0; j < v; ++j) {
			for(Pair e : adj[j]) {
				if(dist[j] + e.w < dist[e.v])
					return true;
			}
		}
		
		return false;
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