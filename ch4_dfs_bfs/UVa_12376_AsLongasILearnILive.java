package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_12376_AsLongasILearnILive {

	static LinkedList<Integer> adj[] = new LinkedList[100];
	static int v[] = new int[100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n, m, cases = 1;
		
		while(t-- > 0) {
			n = sc.nextInt(); m = sc.nextInt();
			
			for(int i = 0; i < n; ++i) adj[i] = new LinkedList<Integer>();
			for(int i = 0; i < n; ++i) v[i] = sc.nextInt();
			for(int i = 0; i < m; ++i) adj[sc.nextInt()].add(sc.nextInt());
			
			int res = 0;
			
			int max, maxInd;
			int curr = 0;
			
			while(adj[curr].size() > 0) {
				res += v[curr];
				max = -1; maxInd = -1;
				for(int a : adj[curr]) {
					if(v[a] > max) {
						max = v[a]; maxInd = a;
					}
				}
				curr = maxInd;
			}
			res += v[curr];
			out.printf("Case %d: %d %d\n", cases++, res, curr);
		}
		
		out.flush();
		out.close();
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