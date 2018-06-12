package network_flow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVA_11045 {

	static int N, M;
	static String sizes[] = {"XXL", "XL", "L", "M" , "S", "XS"};
	static ArrayList<Integer> adj[];
	static int resid[][];
	static int S = 0, T = 1;
	static final int INF = (int) 1e9;
	static int p[];
	
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i < 6; ++i) map.put(sizes[i], i + 2);
		
		while(t-- > 0)
		{
			N = sc.nextInt() / 6;
			M = sc.nextInt();
			
			adj = new ArrayList[6 + M + 2];
			resid = new int[6 + M + 2][6 + M + 2];
			
			for(int i = 0; i < adj.length; ++i) adj[i] = new ArrayList<Integer>();
			
			int v;
			
			for(int i = 2; i < 8; ++i)
			{
				adj[S].add(i);
				adj[i].add(S);
				resid[S][i] = N;
			}
			
			for(int i = 8; i < M + 8; ++i)
			{
				v = map.get(sc.next());
				adj[i].add(v);
				adj[v].add(i);
				resid[v][i] = INF;
				
				v = map.get(sc.next());
				adj[i].add(v);
				adj[v].add(i);
				resid[v][i] = INF;
				
				adj[i].add(T);
				adj[T].add(i);
				resid[i][T] = 1;
			}
			
			if(maxFlow() == M)
				out.println("YES");
			else
				out.println("NO");
			
		}
		
		out.flush();
		out.close();
	}
	
	private static int maxFlow() {
		int mf = 0;
		
		while(true) {
			p = new int[6 + M + 2];
			Arrays.fill(p, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(S); p[S] = S;
			
			while(!q.isEmpty()) {
				int u = q.poll();
				
				if(u == T) break;
				
				for(int v : adj[u]) {
					if(p[v] == -1 && resid[u][v] > 0) {
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
		
		minEdge = augment(p[v], Math.min(minEdge, resid[p[v]][v]));
		resid[p[v]][v] -= minEdge; resid[v][p[v]] += minEdge;
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