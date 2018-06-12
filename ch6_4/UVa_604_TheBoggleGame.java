package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class UVa_604_TheBoggleGame {

	static char grid1[][] = new char[4][4];
	static char grid2[][] = new char[4][4];
	static boolean vis[][] = new boolean[4][4];
	static int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	static TreeSet<String> set = new TreeSet<String>();
	static String vowel = "AEOIUY";
	static TreeSet<String> res = new TreeSet<String>();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s;
		StringTokenizer st;
		boolean first = true;
		
		while(!(s = sc.nextLine()).equals("#")) {
			if(first) first = false; else out.println();
			set.clear(); res.clear();
			for(int i = 0; i < 4; ++i) {
				st = new StringTokenizer(s);
				for(int j = 0; j < 4; ++j)
					grid1[i][j] = st.nextToken().charAt(0);
				for(int j = 0; j < 4; ++j)
					grid2[i][j] = st.nextToken().charAt(0);
				s = sc.nextLine();
			}
			
			for(int i = 0; i < 4; ++i)
				for(int j = 0; j < 4; ++j)
					generate(i, j, "", 0, 0);
			
			for(int i = 0; i < 4; ++i)
				for(int j = 0; j < 4; ++j)
					match(i, j, "", 0, 0);
			
			if(res.size() == 0)
				out.println("There are no common words for this pair of boggle boards.");
			else {
				for(String var : res) out.println(var);
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static void match(int a, int b, String s, int vow, int con) {
		vis[a][b] = true;
		
		int x, y;
		
		s += grid2[a][b];
		if(vowel.contains(grid2[a][b] + "")) vow++; else con++;
		
		if(s.length() == 4 && vow == 2 && con == 2 && set.contains(s))
			res.add(s);
		else {
			if((vow <= 2 && con < 2) || (vow < 2 && con <= 2)) {
				for(int k = 0; k < 8; ++k) {
					x = a + dr[k]; y = b + dc[k];
					if(inBounds(x, y) && !vis[x][y])
						match(x, y, s, vow, con);
				}
			}
		}

		
		vis[a][b] = false;
	}

	private static void generate(int a, int b, String s, int vow, int con) {
		vis[a][b] = true;
		int x, y;
		
		s += grid1[a][b];
		if(vowel.contains(grid1[a][b] + "")) vow++; else con++;
		
		if(s.length() == 4 && vow == 2 && con == 2) set.add(s);
		
		if((vow <= 2 && con < 2) || (vow < 2 && con <= 2)) {
			for(int k = 0; k < 8; ++k) {
				x = a + dr[k]; y = b + dc[k];
				if(inBounds(x, y) && !vis[x][y])
					generate(x, y, s, vow, con);
			}
		}
		
		vis[a][b] = false;
	}

	static boolean inBounds(int x, int y) {
		return x >= 0 && y >= 0 && x < 4 && y < 4;
	}
	
//	static boolean isValid(String s) {
//		int v = 0, c = 0;
//		
//		for(int i = 0; i < 4; ++i) {
//			if(vowel.contains(s.charAt(i) +  ""))
//				v++;
//			else
//				c++;
//		}
//		
//		return v == 2 && c == 2; 
//	}
	
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