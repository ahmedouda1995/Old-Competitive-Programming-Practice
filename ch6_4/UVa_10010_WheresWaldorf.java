package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_10010_WheresWaldorf {

	static int N, M, L;
	static char grid[][];
	static char pat[];
	static int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		ArrayList<Integer> chars [] = new ArrayList[26];
		
		while(t-- > 0) {
			N = sc.nextInt(); M = sc.nextInt();
			grid = new char[N][M];
			for(int i = 0; i < 26; ++i) chars[i] = new ArrayList<Integer>();
			String s;
			
			for(int i = 0; i < N; ++i) {
				s = sc.nextLine().toLowerCase();
				for(int j = 0; j < M; ++j) {
					grid[i][j] = s.charAt(j);
					chars[s.charAt(j) - 'a'].add(i);
					chars[s.charAt(j) - 'a'].add(j);
				}
			}
			
			int k = sc.nextInt(), a, b;
			
			while(k-- > 0) {
				pat = sc.nextLine().toLowerCase().toCharArray();
				L = pat.length;
				for(int i = 0; i < chars[pat[0] - 'a'].size(); i += 2) {
					a = chars[pat[0] - 'a'].get(i);
					b = chars[pat[0] - 'a'].get(i + 1);
					if(check(a, b)) {
						out.println((a + 1) + " " + (b + 1));
						break;
					}
				}
			}
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}
	
	static boolean valid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
	
	private static boolean check(int a, int b) {
		for(int k = 0; k < 8; ++k) {
			if(valid(a + dr[k] * L - dr[k], b + dc[k] * L - dc[k])) {
				int c = 0;
				for(int i = a, j = b; c < L; i += dr[k], j += dc[k], ++c) {
					if(grid[i][j] != pat[c]) break;
				}
				if(c == L) return true;
			}
		}
		return false;
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