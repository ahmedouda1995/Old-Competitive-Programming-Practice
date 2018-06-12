package ch4_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa_103_StackingBoxes {

	static final int INF = (int) 1e9;
	static int N, K;
	static int box[][];
	static ArrayList<Integer> adj[] = new ArrayList[30];
	static boolean vis[];
	static int dist[], parent[];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));

		while(sc.ready()) {
			K = sc.nextInt();
			N = sc.nextInt();
			
			box = new int[K][N];
			
			for(int i = 0; i < K; ++i) {
				for(int j = 0; j < N; ++j)
					box[i][j] = sc.nextInt();
				Arrays.sort(box[i]);
			}
			
			for(int i = 0; i < K; ++i) adj[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < K; ++i) {
				for(int j = 0; j < K; ++j) {
					if(i != j && comp(box[i], box[j])) {
						adj[i].add(j);
					}
				}
			}
			
			vis = new boolean[K];
			dist = new int[K]; parent = new int[K];
			Arrays.fill(dist, -INF);
			Stack<Integer> st = new Stack<Integer>();
			for(int i = 0; i < K; ++i) {
				if(!vis[i])
					topologicalOrder(i, st);
			}
			
			while(!st.isEmpty()) {
				int u = st.pop();
				if(dist[u] == -INF) {
					dist[u] = 0;
					parent[u] = -1;
					solve(u);
				}
			}
			
			int maxIdx = 0;
			
			for(int i = 1; i < K; ++i) {
				if(dist[i] > dist[maxIdx]) maxIdx = i;
			}
			
			out.println(dist[maxIdx] + 1);
			printSol(maxIdx);
			out.println();
		}

		out.flush();
		out.close();
	}
	
	private static void printSol(int i) {
		if(parent[i] == -1) out.print(i + 1);
		else {
			printSol(parent[i]);
			out.print(" " + (i + 1));
		}
		
	}

	private static void solve(int u) {
		for(int v : adj[u]) {
			if(dist[v] < dist[u] + 1) {
				dist[v] = dist[u] + 1;
				parent[v] = u;
				solve(v);
			}
		}
	}

	private static void topologicalOrder(int i, Stack<Integer> st) {
		vis[i] = true;
		for(int v : adj[i]) {
			if(!vis[v]) {
				topologicalOrder(v, st);
			}
		}
		st.push(i);
	}

	static boolean comp(int [] a, int [] b) {
		for(int i = 0; i < a.length; ++i) {
			if(a[i] >= b[i]) return false;
		}
		return true;
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