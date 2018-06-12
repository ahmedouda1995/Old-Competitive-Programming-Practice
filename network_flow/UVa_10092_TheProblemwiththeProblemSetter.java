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

public class UVa_10092_TheProblemwiththeProblemSetter {

	static int Nk, Np;
	static int resid[][];
	static int s, t;
	static final int INF = (int) 1e9;
	static int p[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		ArrayList<Integer> adj[];
		
		while((Nk = sc.nextInt()) != 0 | (Np = sc.nextInt()) != 0) {
			resid = new int[Nk + Np + 2][Nk + Np + 2];
			adj = new ArrayList[Nk +Np + 2];
			for(int i = 0; i < Nk +Np + 2; ++i) adj[i] = new ArrayList<Integer>();
			p = new int[Nk + Np + 2];
			
			int cap, check = 0;
			s = Nk +Np; t = s + 1;
			
			for(int i = 0; i < Nk; ++i) {
				cap = sc.nextInt();
				check += cap;
				adj[i].add(t);
				adj[t].add(i);
				resid[i][t] = cap;
			}
			
			int m, a;
			
			for(int i = Nk; i < Nk + Np; ++i) {
				m = sc.nextInt();
				
				adj[s].add(i); adj[i].add(s);
				resid[s][i] = 1;
				
				while(m-- > 0) {
					a = sc.nextInt() - 1;
					adj[i].add(a);
					adj[a].add(i);
					resid[i][a] = INF;
				}
			}
			
			if(check > Np) { out.println(0); continue; }
			
			int mf = 0;
			
			while(true) {
				Queue<Integer> q = new LinkedList<Integer>();
				q.offer(s);
				Arrays.fill(p, -1); p[s] = -2;
				
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
			
			StringBuilder sb;
			
			if(mf == check) {
				out.println(1);
				
				for(int i = 0; i < Nk; ++i) {
					sb	= new StringBuilder();
					
					for(int j = Nk; j < Nk + Np; ++j) {
						if(resid[i][j] == 1)
							sb.append((j - Nk + 1) + " ");
					}
					out.println(sb.toString().trim());
				}
			}
			else
				out.println(0);
			
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