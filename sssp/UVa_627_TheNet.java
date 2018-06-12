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

public class UVa_627_TheNet {

	static int INF = (int) 1e9, N;
	static int dist[] = new int[301];
	static int p[] = new int[301];
	static ArrayList<Integer> adj[] = new ArrayList[301];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		String s, tmp1[], tmp2[];
		
		while((s = sc.nextLine()) != null) {
			N = Integer.parseInt(s);
			
			for(int i = 1; i <= N; ++i) {
				adj[i] = new ArrayList<Integer>();
			}
			int a; int [] b;
			for(int i = 0; i < N; ++i) {
				tmp1 = sc.nextLine().split("-");
				a = Integer.parseInt(tmp1[0]);
				if(tmp1.length > 1) {
					tmp2 = tmp1[1].split(",");
					b = new int[tmp2.length];
					for(int j = 0; j < tmp2.length; ++j) b[j] = Integer.parseInt(tmp2[j]);
					Arrays.sort(b);
					for(int j = 0; j < tmp2.length; ++j) {
						adj[a].add(b[j]);
					}
				}
			}
			
			a = sc.nextInt();
			int u, v;
			
			out.println("-----");
			for(int i = 0; i < a; ++i) {
				u = sc.nextInt(); v = sc.nextInt();
				
				bfs(u, v);
				
				if(dist[v] == INF)
					out.println("connection impossible");
				else {
					print(v, u);
					out.println();
				}
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static void print(int v, int u) {
		if(u == v)
			out.print(u);
		else {
			print(p[v], u);
			out.print(" " + v);
		}
	}

	private static void bfs(int src, int dest) {
		Arrays.fill(dist, INF);
		Queue<Integer> q = new LinkedList<Integer>();
		dist[src] = 0; p[src] = src; q.offer(src);
		
		while(!q.isEmpty()) {
			int u = q.poll();
			
			if(u == dest) return;
			
			for(int v : adj[u]) {
				if(dist[v] == INF) {
					p[v] = u;
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