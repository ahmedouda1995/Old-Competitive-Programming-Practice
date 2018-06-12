package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11831_StickerCollectorRobots {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m, s, posX = 0, posY = 0;
		char [][] a;
		
		int mov [][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0 | (s = sc.nextInt()) != 0) {
			a = new char[n][m];
			String str;
			int dir = 0;
			for(int i = 0; i < n; ++i) {
				str = sc.nextLine();
				for(int j = 0; j < m; ++j) {
					if(str.charAt(j) == 'N' || str.charAt(j) == 'S'
					|| str.charAt(j) == 'L' || str.charAt(j) == 'O') {
						posX = i; posY = j;
						switch(str.charAt(j)) {
						case 'N': dir = 0;break;
						case 'L': dir = 1;break;
						case 'S': dir = 2;break;
						case 'O': dir = 3;break;
						}
					}
					a[i][j] = str.charAt(j);	
				}
			}
			
			str = sc.nextLine();
			int res = 0;
			for(int i = 0; i < str.length(); ++i) {
				
				if(a[posX][posY] == '*') {
					a[posX][posY] = '.';
					res++;
				}
				
				if(str.charAt(i) == 'F') {
					int tmpX = posX, tmpY = posY;
					tmpX += mov[dir][0]; tmpY += mov[dir][1];
					if(!(tmpX >= n || tmpX < 0 || tmpY >= m || tmpY < 0 || a[tmpX][tmpY] == '#')) {
						posX = tmpX; posY = tmpY;
					}
				}
				else if(str.charAt(i) == 'D') { dir = (dir + 1) % 4; }
				else dir = (dir - 1 + 4) % 4;
			}
			if(a[posX][posY] == '*')
				res++;
			out.println(res);
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
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