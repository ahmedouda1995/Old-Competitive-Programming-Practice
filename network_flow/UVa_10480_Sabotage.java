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

public class UVa_10480_Sabotage {

	static int N, INF = (int) 1e9;
	static ArrayList<Integer> adj[];
	static int res[][];
	static int p[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int m, a, b;
		
		while((N = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			
			adj = new ArrayList[N];
			for(int i = 0; i < N; ++i) adj[i] = new ArrayList<Integer>();
			res = new int[N][N];
			
			while(m-- > 0) {
				a = sc.nextInt() - 1;
				b = sc.nextInt() - 1;
				
				adj[a].add(b); adj[b].add(a);
				res[a][b] = res[b][a] = sc.nextInt();
			}
			
			maxFlow();
			
			findPath();
			
			for(int i = 0; i < N; ++i) {
				if(p[i] == -1) {
					for(int v : adj[i]) {
						if(res[v][i] == 0 && p[v] != -1)
							out.println((v + 1) + " " + (i + 1));
					}
				}
			}
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static void maxFlow() {
		
		while(true) {
			findPath();
			
			if(p[1] == -1) break;
			
			augment(1, INF);
		}
	}

	static void findPath() {
		p = new int[N];
		Arrays.fill(p, -1);
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(0); p[0] = -2;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			
			if(u == 1) break;
			
			for(int v : adj[u]) {
				if(p[v] == -1 && res[u][v] > 0) {
					p[v] = u;
					q.offer(v);
				}
			}
		}
	}
	
	private static int augment(int v, int minEdge) {
		if(v == 0) return minEdge;
		
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