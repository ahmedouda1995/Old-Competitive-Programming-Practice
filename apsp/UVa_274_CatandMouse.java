package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_274_CatandMouse {

	static int N;
	static boolean cat[][];
	static boolean mouse[][];
	static ArrayList<Integer> adj[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			N = sc.nextInt();
			
			int a, b, catHouse, mouseHouse;
			
			catHouse = a = sc.nextInt();
			mouseHouse = b = sc.nextInt();
			
			catHouse--; mouseHouse--;
			
			cat = new boolean[N][N];
			mouse = new boolean[N][N];
			adj = new ArrayList[N];
			
			for(int i = 0; i < N; ++i) adj[i] = new ArrayList<Integer>();
			
			cat[a - 1][a - 1] = true; mouse[b - 1][b - 1] = true;
			
			while((a = sc.nextInt()) != -1 | (b = sc.nextInt()) != -1) {
				cat[a - 1][b - 1] = true;
			}
			
			String s; StringTokenizer st;
			
			while((s = sc.nextLine()) != null && s.length() > 0) {
				st = new StringTokenizer(s);
				a = Integer.parseInt(st.nextToken()) - 1;
				b = Integer.parseInt(st.nextToken()) - 1;
				mouse[a][b] = true;
				adj[a].add(b);
			}
			
			floyd(true); floyd(false);
			
			boolean ans1 = false, ans2 = false;
			
			boolean vis1[] = cat[catHouse];
			boolean vis2[] = mouse[mouseHouse];
			
			for(int i = 0; i < N; ++i) {
				if(vis1[i] && vis2[i]) {
					ans1 = true; break;
				}
			}
			
			if(!vis1[mouseHouse]) {
				for(int v : adj[mouseHouse]) {
					if(!vis1[v]) {
						ans2 |= dfs(v, mouseHouse, vis1);
					}
				}
			}
			
			if(ans1) out.print("Y "); else out.print("N ");
			if(ans2) out.println("Y"); else out.println("N");
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}

	private static boolean dfs(int src, int mouseHouse, boolean[] vis) {
		if(src == mouseHouse) return true;
		vis[src] = true;
		
		for(int v : adj[src]) {
			if(!vis[v]) {
				if(dfs(v, mouseHouse, vis))
					return true;
			}
		}
		return false;
	}

	private static void floyd(boolean isCat) {
		boolean [][] a = (isCat)? a = cat : mouse;
		
		for(int k = 0; k < N; ++k)
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j) {
					a[i][j] |= (a[i][k] & a[k][j]);
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