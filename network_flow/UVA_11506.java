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

public class UVA_11506 {

	static final int INF = (int) 1e9;
	static int N;
	static int M, W, S, T;
	static ArrayList<Integer> adj[];
	static int resid[][];
	static int p[];
	
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while((M = sc.nextInt()) != 0 | (W = sc.nextInt()) != 0)
		{
			S = 1;
			T = M;
			int v, val;
			N = 2 * M;
			adj = new ArrayList[N];
			for(int i = 0; i < N; ++i) adj[i] = new ArrayList<Integer>();
			resid = new int[N][N];
			
			for(int i = 2; i <= M - 1; ++i)
			{
				v = sc.nextInt();
				val = sc.nextInt();
				
				adj[vin(v)].add(vout(v));
				adj[vout(v)].add(vin(v));
				
				resid[vin(v)][vout(v)] = val;
				resid[vout(v)][vin(v)] = val;
			}
			
			int u;
			
			for(int i = 0; i < W; ++i)
			{
				u = sc.nextInt();
				v = sc.nextInt();
				val = sc.nextInt();
				
				adj[vout(u)].add(vin(v));
				adj[vout(v)].add(vin(u));
				adj[vin(v)].add(vout(u));
				adj[vin(u)].add(vout(v));
				
				resid[vout(u)][vin(v)] = val;
				resid[vout(v)][vin(u)] = val;
			}
			
			out.println(maxFlow());
		}
		
		out.flush();
		out.close();
	}
	
	static int maxFlow()
	{
		p = new int[N];
		int mf = 0;
		
		while(true)
		{
			Arrays.fill(p, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(S);
			p[S] = S;
			
			while(!q.isEmpty())
			{
				int u = q.poll();
				
				if(u == T) break;
				
				for(int v : adj[u])
				{
					if(p[v] == -1 && resid[u][v] > 0)
					{
						q.add(v);
						p[v] = u;
					}
				}
			}
			if(p[T] == -1)
				break;
			
			mf += augment(T, INF);
		}
		
		return mf;
	}
	
	private static int augment(int i, int minEdge) {
		if(i == S) return minEdge;
		minEdge = augment(p[i], Math.min(minEdge, resid[p[i]][i]));
		resid[p[i]][i] -= minEdge;
		resid[i][p[i]] += minEdge;
		return minEdge;
	}

	static int vin(int i)
	{
		return i;
	}
	
	static int vout(int i)
	{
		if(i == 1 || i == M) return i;
		return M + i - 1;
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