package acp_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_10949_KidsinaGrid {

	static char grid[][] = new char[20][20];
	static char s1[], s2[];
	static int N, M;
	static int dp[][] = new int[20001][20001];
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), cases = 1;
		TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
		map.put('N', 0); map.put('E', 1); map.put('S', 2); map.put('W', 3);
		StringBuilder sb;
		
		while(t-- > 0) {
			N = sc.nextInt(); M = sc.nextInt();
			
			String s;
			
			for(int i = 0; i < N; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < M; ++j)
					grid[i][j] = s.charAt(j);
			}
			
			int n = sc.nextInt();
			int x = sc.nextInt() - 1, y = sc.nextInt() - 1;
			sb = new StringBuilder();
			if(n > 0)
				sb.append(grid[x][y]);
			s = sc.nextLine();
			
			int i = 0;
			
			while(n > 0) {
				int a = map.get(s.charAt(i++));
				x += dr[a]; y += dc[a];
				sb.append(grid[x][y]);
				n--;
			}
			
			s1 = sb.toString().toCharArray();
			
			n = sc.nextInt();
			x = sc.nextInt() - 1; y = sc.nextInt() - 1;
			sb = new StringBuilder();
			if(n > 0)
				sb.append(grid[x][y]);
			s = sc.nextLine();
			
			i = 0;
			
			while(n > 0) {
				int a = map.get(s.charAt(i++));
				x += dr[a]; y += dc[a];
				sb.append(grid[x][y]);
				n--;
			}
			
			s2 = sb.toString().toCharArray();
			
			int lcs = LCS();
			
			out.printf("Case %d: %d %d\n", cases++, s1.length - lcs, s2.length - lcs);
		}

		out.flush();
		out.close();
	}
	
	private static int LCS() {
		for(int i = 1; i <= s1.length; ++i) {
			for(int j = 1; j <= s2.length; ++j) {
				if(s1[i - 1] == s2[j - 1]) dp[i][j] = 1 + dp[i - 1][j - 1];
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[s1.length][s2.length];
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