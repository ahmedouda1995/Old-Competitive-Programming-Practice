package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_10947_Bear_with_me_again {

	static int K, M;
	static ArrayList<Integer> adj[] = new ArrayList[102];
	static boolean vis[] = new boolean[102];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready()) {
			K = sc.nextInt(); M = sc.nextInt();
			double maxDist = K * M;
			int posX[] = new int[102];
			int posY[] = new int[102];
			int rad[] = new int[102];
			
			posX[0] = sc.nextInt(); posY[0] = sc.nextInt(); rad[0] = sc.nextInt();
			posX[1] = sc.nextInt(); posY[1] = sc.nextInt(); rad[1] = sc.nextInt();
			
			int m = sc.nextInt();
			
			for(int i = 2; i < m + 2; ++i) {
				posX[i] = sc.nextInt();
				posY[i] = sc.nextInt();
				rad[i] = sc.nextInt();
			}
			
			for(int i = 0; i < m + 2; ++i) {
				adj[i] = new ArrayList<Integer>();
				vis[i] = false;
			}
			
			for(int i = 0; i < m + 2; ++i) {
				for(int j = i + 1; j < m + 2; ++j) {
					if(dist(posX[i], posY[i], rad[i], posX[j], posY[j], rad[j]) <= maxDist) {
						adj[i].add(j); adj[j].add(i);
					}
				}
			}
			
			dfs(0, 1);
			
			if(vis[1])
				out.println("Larry and Ryan will escape!");
			else
				out.println("Larry and Ryan will be eaten to death.");
		}
		
		out.flush();
		out.close();
	}

	private static void dfs(int i, int j) {
		vis[i] = true;
		if(i == j) return;
		for(int v : adj[i]) {
			if(!vis[v])
				dfs(v, j);
		}
	}

	static double dist(int x1, int y1, int r1, int x2, int y2, int r2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)) - r1 - r2;
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