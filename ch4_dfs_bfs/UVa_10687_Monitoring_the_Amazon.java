package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_10687_Monitoring_the_Amazon {

	static Pair V[];
	static LinkedList<Integer> adj[] = new LinkedList[1000];
	static boolean vis[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		
		while((n = sc.nextInt()) != 0) {
			vis = new boolean[n];
			for(int i = 0; i < n; ++i) adj[i] = new LinkedList<Integer>();
			V = new Pair[n];
			for(int i = 0; i < n; ++i) V[i] = new Pair(sc.nextInt(), sc.nextInt());
			
			for(int i = 0; i < n; ++i) {
				int a = -1, b = -1, maxA = (int) 1e9, maxB;
				for(int j = 0; j < n; ++j) {
					int tmp = (V[i].x - V[j].x) * (V[i].x - V[j].x)  + (V[i].y - V[j].y) * (V[i].y - V[j].y);
					if(j != i && tmp <= maxA) {
						if(tmp == maxA) {
							if(V[j].x < V[i].x) {
								maxB = maxA;
								maxA = tmp;
								b = a;
								a = j;
							}
							else if(V[j].y < V[i].y) {
								maxB = maxA;
								maxA = tmp;
								b = a;
								a = j;
							}
						}
						else {
							maxB = maxA;
							maxA = tmp;
							b = a;
							a = j;
						}
					}
				}
				if(a != -1)
					adj[i].add(a);
				if(b != -1)
					adj[i].add(b);
			}
			if(dfs(0) == n)
				out.println("All stations are reachable.");
			else
				out.println("There are stations that are unreachable.");
		}
		
		out.flush();
		out.close();
	}
	
	private static int dfs(int i) {
		vis[i]= true;
		int res = 1;
		
		for(int v : adj[i]) {
			if(!vis[v])
				res += dfs(v);
		}
		return res;
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