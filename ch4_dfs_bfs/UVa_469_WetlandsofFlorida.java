package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_469_WetlandsofFlorida {

	static int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	static char grid[][] = new char[99][99];
	static Pair res[][];
	static int N, M;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		sc.nextLine();
		while(t-- > 0) {
			String s;
			int i = 0, j = 0;
			TreeMap<Pair, Integer> map = new TreeMap<Pair, Integer>();
			
			while(!Character.isDigit((s = sc.nextLine()).charAt(0))) {
				j = 0;
				for(; j < s.length(); ++j) grid[i][j] = s.charAt(j);
				i++;
			}
			N = i; M = j;
			res = new Pair[N][M];
			do {
				String tmp[] = s.split(" ");
				int a = Integer.parseInt(tmp[0]) - 1, b = Integer.parseInt(tmp[1]) - 1;
				if(res[a][b] == null) {
					int ans = floodfill(a, b, 'W', '.', a, b);
					out.println(ans);
					map.put(new Pair(a, b), ans);
				}
				else
					out.println(map.get(res[a][b]));
			}while((s = sc.nextLine()) != null && s.length() > 0);
			
			if(t > 0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static int floodfill(int a, int b, char c1, char c2, int refX, int refY) {
		if(a < 0 || a >= N || b < 0 || b >= M) return 0;
		if(grid[a][b] != c1) return 0;
		res[a][b] = new Pair(refX, refY);
		grid[a][b] = c2;
		int res = 1;
		for(int i = 0; i < 8; ++i)
			res += floodfill(a + dr[i], b + dc[i], c1, c2, refX, refY);
		
		return res;
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