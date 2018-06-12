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

public class UVa_820_InternetBandwidth {

	static final int INF = (int) 1e9;
	static int s, t;
	static int p[];
	static int resid[][];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int n, mf, cases = 1;
		ArrayList<Integer> adj[];

		while((n = sc.nextInt()) != 0) {
			mf = 0;
			s = sc.nextInt();
			t = sc.nextInt();
			
			int c = sc.nextInt();
			
			adj = new ArrayList[n + 1];
			for(int i = 1; i < n + 1; ++i) adj[i] = new ArrayList<Integer>();
			resid = new int[n + 1][n + c + 1];
			
			int a, b, cap;
			
			for(int i = 0; i < c; ++i) {
				a = sc.nextInt(); b = sc.nextInt();
				cap = sc.nextInt();

				if(a != b) {
					if(resid[a][b] == 0) {
						adj[a].add(b); resid[a][b] = cap;
						adj[b].add(a); resid[b][a] = cap;
					}
					else {
						resid[a][b] += cap;
						resid[b][a] += cap;
					}
				}
			}
			
			p = new int[n + 1];
			
			while(true) {
				Arrays.fill(p, -1);
				Queue<Integer> q = new LinkedList<Integer>();
				q.offer(s); p[s] = s;
				
				while(!q.isEmpty()) {
					int u = q.poll();
					
					if(u == t) break;
					
					for(int v : adj[u]) {
						if(resid[u][v] > 0 && p[v] == -1) {
							q.offer(v); p[v] = u;
						}
					}
				}
				
				if(p[t] == -1) break;
				
				mf += augment(t, INF);
			}
			
			out.printf("Network %d\n", cases++);
			out.printf("The bandwidth is %d.\n", mf);
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static int augment(int v, int minEdge) {
		if(v == s) return minEdge;
		
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