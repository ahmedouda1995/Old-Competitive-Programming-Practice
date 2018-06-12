package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_11283_PlayingBoggle {

	static char grid[][] = new char[4][4];
	static int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	static boolean vis[][] = new boolean[4][4];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), cases = 1;
		ArrayList<Integer> chars[] = new ArrayList[26];
		
		while(t-- > 0) {
			for(int i = 0; i < 26; ++i) chars[i] = new ArrayList<Integer>();
			sc.nextLine();
			
			String s;
			for(int i = 0; i < 4; ++i) {
				s = sc.nextLine().trim();
				for(int j = 0; j < 4; ++j) {
					grid[i][j] = s.charAt(j);
					chars[s.charAt(j) - 'A'].add(i);
					chars[s.charAt(j) - 'A'].add(j);
				}
			}
			
			int m = sc.nextInt();
			
			int score = 0;
			
			int a, b;
			
			while(m-- > 0) {
				s = sc.nextLine().trim();
				
				for(int i = 0; i < chars[s.charAt(0) - 'A'].size(); i += 2) {
					a = chars[s.charAt(0) - 'A'].get(i);
					b = chars[s.charAt(0) - 'A'].get(i + 1);
					if(exists(s, a, b, 0, s.length())) {
						score += getScore(s.length());
						break;
					}
				}
			}
			
			out.printf("Score for Boggle game #%d: %d\n", cases++, score);
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean exists(String s, int a, int b, int i, int length) {
		vis[a][b] = true;
		
		boolean ans = false;
		
		if(grid[a][b] == s.charAt(i))
		{
			if(i == length - 1) ans = true;
			else {
				int x, y;
				for(int k = 0; k < 8; ++k)
				{
					x = a + dr[k]; y = b + dc[k];
					if(valid(x, y) && !vis[x][y])
					{
						if(exists(s, x, y, i + 1, length))
						{
							ans = true;
							break;
						}
					}
				}
			}
		}
		
		vis[a][b] = false;
		return ans;
	}

	static boolean valid(int x, int y) {
		return x >= 0 && y >= 0 && x < 4 && y < 4;
	}
	
	static int getScore(int n) {
		int res = 0;
		switch(n) {
		case 3: case 4: res = 1; break;
		case 5: res = 2; break;
		case 6: res = 3; break;
		case 7: res = 5; break;
		default: res = 11;
		}
		return res;
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