package network_flow;

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

public class UVA_10779 {

	static int S = 0, T = 1, INF = (int) 1e9;
	static int types[][];
	static ArrayList<Integer> adj[];
	static int res[][];
	static int n, m;
	static int p[];
	
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		for(int c = 1; c <= t; ++c)
		{
			n = sc.nextInt();
			m = sc.nextInt();
			adj = new ArrayList[n + m + 2];
			for(int i = 0; i < adj.length; ++i) adj[i] = new ArrayList<Integer>();
			res = new int[n + m + 2][n + m + 2];
			
			types = new int[n][m];
			
			for(int i = 0; i < n; ++i)
			{
				int k = sc.nextInt();
				while(k-- > 0)
					types[i][sc.nextInt() - 1]++;
			}
			
			adj[S].add(2);
			adj[2].add(S);
			res[S][2] = INF;
			
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < m; ++j)
				{
					if(types[i][j] > 1)
					{
						adj[2 + i].add(n + 2 + j);
						adj[n + 2 + j].add(2 + i);
						
						res[2 + i][n + 2 + j] = types[i][j] - 1;
					}
				}
			
			int ans = m;
			
			for(int j = 0; j < m; ++j)
			{
				if(types[0][j] == 0)
				{
					adj[n + 2 + j].add(T);
					adj[T].add(n + 2 + j);
					
					res[n + 2 + j][T] = 1;
					ans--;
				}
			}
			
			for(int j = 0; j < m; ++j)
			{
				for(int i = 0; i < n; ++i)
				{
					if(types[i][j] == 0)
					{
						adj[n + 2 + j].add(2 + i);
						adj[2 + i].add(n + 2 + j);
						
						res[n + 2 + j][2 + i] = 1;
					}
				}
			}
			
			out.printf("Case #%d: %d\n", c, ans + maxFlow());
		}
		
		out.flush();
		out.close();
	}
	
	private static int maxFlow() {
		int mf = 0;
		
		while(true) {
			p = new int[n + m + 2];
			Arrays.fill(p, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(S); p[S] = S;
			
			while(!q.isEmpty()) {
				int u = q.poll();
				
				if(u == T) break;
				
				for(int v : adj[u]) {
					if(p[v] == -1 && res[u][v] > 0) {
						p[v] = u;
						q.offer(v);
					}
				}
			}
			
			if(p[1] == -1) break;
			
			mf += augment(1, INF);
		}
		
		return mf;
	}

	private static int augment(int v, int minEdge) {
		if(v == S) return minEdge;
		
		minEdge = augment(p[v], Math.min(minEdge, res[p[v]][v]));
		res[p[v]][v] -= minEdge; res[v][p[v]] += minEdge;
		return minEdge;
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