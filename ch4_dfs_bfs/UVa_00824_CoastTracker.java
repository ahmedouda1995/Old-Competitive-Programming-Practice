package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_00824_CoastTracker {

	static int dr[] = {0, 0, 1, 2, 2, 2, 1, 0};
	static int dc[] = {1, 0, 0, 0, 1, 2, 2, 2};
	static int mat[][] = new int[3][3];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int posX, posY, dir;
		
		while((posX = sc.nextInt()) != -1) {
			posY = sc.nextInt(); dir = sc.nextInt();
			mat[1][1] = 1;
			int x, y;
			for(int i = 0; i < 8; ++i) {
				x = sc.nextInt(); y = sc.nextInt();
				mat[posY - y + 1][x - posX + 1] = sc.nextInt();
			}
			
			dir = (dir - 2 + 8) % 8;
			
			for(int i = 0; i < 8; ++i) {
				if(mat[dr[dir]][dc[dir]] == 1) { out.println(dir); break;}
				dir = (dir + 1) % 8;
			}
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