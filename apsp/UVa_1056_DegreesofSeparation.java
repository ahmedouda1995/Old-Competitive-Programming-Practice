package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_1056_DegreesofSeparation {

	static int V, INF = (int) 1e9;
	static int [][] adj = new int[50][50];
	static boolean vis[] = new boolean[50];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		int r, cases = 1; boolean first = true;
		
		while((V = sc.nextInt()) != 0 | (r = sc.nextInt()) != 0) {
			map.clear();
			for(int i = 0; i < V; ++i) {
				vis[i] = false;
				for(int j = 0; j < V; ++j)
					adj[i][j] = (i == j)?0:INF;
			}
			
			int k = 0;
			String a, b;
			
			for(int i = 0; i < r; ++i) {
				a = sc.next(); b = sc.next();
				if(!map.containsKey(a)) {
					map.put(a, k++);
				}
				if(!map.containsKey(b)) {
					map.put(b, k++);
				}
				
				adj[map.get(a)][map.get(b)] = 1;
				adj[map.get(b)][map.get(a)] = 1;
			}
			
			out.printf("Network %d: ", cases++);
			if(k != V)
				out.println("DISCONNECTED");
			else {
				floyd();
				int tmp = check();
				if(tmp == -1)
					out.println("DISCONNECTED");
				else
					out.println(tmp);
			}
			out.println();
		}
		
		out.flush();
		out.close();
	}

	private static int check() {
		int max = 0;
		for(int i = 0; i < V; ++i)
			for(int j = 0; j < V; ++j) {
				if(adj[i][j] == INF)
					return -1;
				else
					max = Math.max(max, adj[i][j]);
			}
		return max;
	}

	private static void floyd() {
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
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