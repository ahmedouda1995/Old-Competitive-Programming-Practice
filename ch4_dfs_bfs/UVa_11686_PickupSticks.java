package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_11686_PickupSticks {

	static LinkedList<Integer> adj[] = new LinkedList[1000001];
	static int indegree[];
	static ArrayList<Integer> res = new ArrayList<Integer>();
	static int N;
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		int n, m;
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			res.clear();
			for(int i = 1; i <= n; ++i) adj[i] = new LinkedList<Integer>();
			N = n;
			for(int i = 0; i < m; ++i) adj[sc.nextInt()].add(sc.nextInt());
			indegree = new int[N + 1];
			for(int i = 1; i <= n; ++i) for(int v : adj[i]) indegree[v]++;
			
			kahn();
		}
		
		out.flush();
		out.close();
	}
	
	private static void kahn() {
		for(int i = 1; i <= N; ++i) if(indegree[i] == 0) res.add(i);
		
		for(int i = 0; i < res.size(); ++i) {
			for(int v : adj[res.get(i)]) {
				indegree[v]--;
				if(indegree[v] == 0)
					res.add(v);
			}
		}
		
		if(res.size() == N)
			for(int i : res)
				out.println(i);
		else
			out.println("IMPOSSIBLE");
	}

	public static int gcd(int n, int m) {
	    return (n % m) == 0? m : gcd(m, n % m);
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