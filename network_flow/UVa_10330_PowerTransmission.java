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

public class UVa_10330_PowerTransmission {

	static int N, INF = (int) 1e9;
	static ArrayList<Integer> adj[];
	static int res[][];
	static int p[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready()) {
			N = sc.nextInt();
			
			adj = new ArrayList[(N << 1) + 2];
			for(int i = 0; i < (N << 1) + 2; ++i)
				adj[i] = new ArrayList<Integer>();
			
			res = new int[(N << 1) + 2][(N << 1) + 2];
			
			int cap;
			
			for(int i = 0; i < N; ++i) {
				cap = sc.nextInt();
				
				adj[vin(i)].add(vout(i));
				adj[vout(i)].add(vin(i));
				res[vin(i)][vout(i)] = cap;
				res[vout(i)][vin(i)] = cap;
			}
			
			int m = sc.nextInt(), a, b;
			
			while(m-- > 0) {
				a = sc.nextInt() - 1;
				b = sc.nextInt() - 1;
				cap = sc.nextInt();
				
				if(res[vout(a)][vin(b)] == 0) {
					adj[vout(a)].add(vin(b));
					adj[vin(b)].add(vout(a));
				}
				
				res[vout(a)][vin(b)] += cap;
			}
			
			int x = sc.nextInt(), y = sc.nextInt();
			
			while(x-- > 0) {
				a = sc.nextInt() - 1;
				adj[0].add(vin(a));
				adj[vin(a)].add(0);
				
				res[0][vin(a)] = INF;
			}
			
			while(y-- > 0) {
				a = sc.nextInt() - 1;
				adj[vout(a)].add(1);
				adj[1].add(vout(a));
				
				res[vout(a)][1] = INF;
			}
			
			out.println(maxFlow());
		}
		
		out.flush();
		out.close();
	}
	
	private static int maxFlow() {
		int mf = 0;
		
		while(true) {
			p = new int[(N << 1) + 2];
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
			
			if(p[1] == -1) break;
			
			mf += augment(1, INF);
		}
		
		return mf;
	}

	private static int augment(int v, int minEdge) {
		if(v == 0) return minEdge;
		
		minEdge = augment(p[v], Math.min(minEdge, res[p[v]][v]));
		res[p[v]][v] -= minEdge; res[v][p[v]] += minEdge;
		return minEdge;
	}

	static int vin(int v) { return v + 2; }
	static int vout(int v) { return v + 2 + N; }
	
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