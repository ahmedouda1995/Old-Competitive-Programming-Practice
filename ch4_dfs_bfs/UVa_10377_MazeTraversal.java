package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10377_MazeTraversal {
	
	static int mov[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n, m;
		char [][] a;
		while(t-- > 0) {
			n = sc.nextInt(); m = sc.nextInt();
			a = new char[n][m];
			String s;
			for(int i = 0; i < n; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < m; ++j) a[i][j] = s.charAt(j);
			}
			
			int dir = 0;
			
			int posX = sc.nextInt() - 1, posY = sc.nextInt() - 1;
			boolean done = false;
			while((s = sc.nextLine()) != null && s.length() > 0) {
				if(!done) {
					for(int i = 0; i < s.length(); ++i) {
						switch(s.charAt(i)) {
						case 'R': dir = (dir + 1) % 4;break;
						case 'L': dir = (dir - 1 + 4) % 4;break;
						case 'F': 
							int newPosX = posX + mov[dir][0], newPosY = posY + mov[dir][1];
							if(newPosX >= 0 && newPosX < n && newPosY >= 0 && newPosY < m
							&& a[newPosX][newPosY] != '*') {
								posX = newPosX; posY = newPosY;
							}
							break;
						case 'Q': done = true;break;
						}
					}
				}
			}
			char c = '0';
			switch(dir) {
			case 0: c = 'N';break;
			case 1: c = 'E';break;
			case 2: c = 'S';break;
			case 3: c = 'W';break;
			}
			out.println((posX + 1) + " " + (posY + 1) + " " + c);
			if(t > 0)
				out.println();
		}
		
		out.flush();
		out.close();
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