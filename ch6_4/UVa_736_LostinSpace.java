package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_736_LostinSpace {

	static int N;
	static char grid[][] = new char[50][50];
	static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	static String dir[] = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		String s;
		ArrayList<Integer> chars[] = new ArrayList[95];
		
		while(t-- > 0) {
			N = sc.nextInt();
			for(int i = 0; i < 95; ++i) chars[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < N; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < N; ++j) {
					grid[i][j] = s.charAt(j);
					chars[s.charAt(j) - 32].add(i);
					chars[s.charAt(j) - 32].add(j);
				}
			}
			
			int a, b;
			
			while((s = sc.nextLine()) != null && !s.isEmpty()) {
				char c = s.charAt(0);
				out.println("\n" + s);
				boolean found = false;
				for(int i = 0; i < chars[c - 32].size(); i += 2) {
					a = chars[c - 32].get(i);
					b = chars[c - 32].get(i + 1);
					for(int k = 0; k < 8; ++k) {
						if(exists(a, b, 0, s, k)) {
							found = true;
							out.printf("(%d,%d) - %s\n", a + 1, b + 1, dir[k]);
						}
					}
				}
				if(!found) out.println("not found");
			}
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean exists(int a, int b, int i, String s, int k) {
		int x = a + dr[k], y = b + dc[k];
		
		if(grid[a][b] == ' ') {
			if(isValid(x, y))
				return exists(x, y, i, s, k);
			else
				return false;
		}
		if(grid[a][b] != s.charAt(i)) return false;
		if(i == s.length() - 1) return true;
		if(isValid(x, y))
			return exists(x, y, i + 1, s, k);
		return false;
	}

	static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
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