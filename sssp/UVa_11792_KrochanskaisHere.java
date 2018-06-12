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
import java.util.TreeSet;

public class UVa_11792_KrochanskaisHere {

	static int INF = (int) 1e9;
	static ArrayList<Integer> adj[] = new ArrayList[10001];
	static int dist[] = new int[10001];
	static TreeSet<Integer> imp = new TreeSet<Integer>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n, s;
		TreeSet<Integer> set = new TreeSet<Integer>();
		
		while(t-- > 0) {
			set.clear(); imp.clear();
			n = sc.nextInt(); s = sc.nextInt();
			
			for(int i = 1; i <= n; ++i) adj[i] = new ArrayList<Integer>();
			
			int m;
			
			while(s-- > 0) {
				int prev = sc.nextInt();
				if(!set.contains(prev))
					set.add(prev);
				else
					imp.add(prev);
				
				while((m = sc.nextInt()) != 0) {
					adj[prev].add(m);
					adj[m].add(prev);
					prev = m;
					if(!set.contains(prev))
						set.add(prev);
					else
						imp.add(prev);
				}
			}
			int min = INF, res = -1;
			
			for(int u : imp) {
				bfs(u);
				int sum = 0;
				for(int v : imp) {
					sum += dist[v];
				}
				if(sum < min) {
					min = sum;
					res = u;
				}
			}
			
			out.println("Krochanska is in: " + res);
		}
		
		out.flush();
		out.close();
	}
	
	private static void bfs(int u) {
		Queue<Integer> q = new LinkedList<Integer>();
		Arrays.fill(dist, INF);
		q.offer(u); dist[u] = 0;
		
		int count = 0;
		
		while(!q.isEmpty()) {
			u = q.poll();
			if(imp.contains(u)) {
				count++;
				if(count == imp.size()) return;
			}
			for(int v : adj[u]) {
				if(dist[v] == INF) {
					q.offer(v);
					dist[v] = dist[u] + 1;
				}
			}
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