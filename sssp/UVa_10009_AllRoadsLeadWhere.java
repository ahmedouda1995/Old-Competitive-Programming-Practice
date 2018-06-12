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

public class UVa_10009_AllRoadsLeadWhere {

	static int M, N, INF = (int) 1e9;
	static ArrayList<Integer> adj[] = new ArrayList[26];
	static int dist[] = new int[26];
	static int p[] = new int[26];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			
			M = sc.nextInt(); N = sc.nextInt();
			for(int i = 0; i < 26; ++i) adj[i] = new ArrayList<Integer>();
		
			String a, b;
			
			for(int i = 0; i < M; ++i) {
				a = sc.next(); b = sc.next();
				adj[a.charAt(0) - 'A'].add(b.charAt(0) - 'A');
				adj[b.charAt(0) - 'A'].add(a.charAt(0) - 'A');
			}
			
			for(int i  =0; i < N; ++i) {
				a = sc.next(); b = sc.next();
				int src = a.charAt(0) - 'A', dest = b.charAt(0) - 'A';
				bfs(src, dest);
				print(src, dest);
				out.println();
			}
			
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static void print(int src, int dest) {
		if(src == dest) out.print((char) (src + 'A'));
		else {
			print(src, p[dest]);
			out.print(((char) (dest + 'A')));
		}
	}

	private static void bfs(int src, int dest) {
		Queue<Integer> q = new LinkedList<Integer>();
		Arrays.fill(dist, INF);
		dist[src] = 0; q.add(src); p[src] = src;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			
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