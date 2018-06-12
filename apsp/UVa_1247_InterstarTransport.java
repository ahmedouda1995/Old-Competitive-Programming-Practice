package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_1247_InterstarTransport {

	static int INF = (int) 1e9;
	static int [][] adj = new int[26][26];
	static int par[][] = new int[26][26];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		for(int i = 0; i < 26; ++i) {
			Arrays.fill(adj[i], INF);
			Arrays.fill(par[i], i);
			adj[i][i] = 0;
		}
		
		int s, p;
		
		s = sc.nextInt(); p = sc.nextInt();
		int a, b, c;
		for(int i = 0; i < p; ++i) {
			a = sc.next().charAt(0) - 'A';
			b = sc.next().charAt(0) - 'A';
			c = sc.nextInt();
			adj[a][b] = c; adj[b][a] = c;
		}
		
		floyd();
		
		c = sc.nextInt();

		while(c-- > 0) {
			a = sc.next().charAt(0) - 'A';
			b = sc.next().charAt(0) - 'A';
			print(a, b);
			out.println();
		}
		
		out.flush();
		out.close();
	}

	private static void print(int src, int dest) {
		if(src == dest) out.print((char) (src + 'A'));
		else {
			print(src, par[src][dest]);
			out.print(" " + (char) (dest + 'A'));
		}
	}

	private static void floyd() {
		for(int k = 0; k < 26; ++k)
			for(int i = 0; i < 26; ++i)
				for(int j = 0; j < 26; ++j) {
					if(adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
						par[i][j] = par[k][j];
					}
				}
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