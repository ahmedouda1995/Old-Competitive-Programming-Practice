package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10116_RobotMotion {
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m, posX, posY;
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0 | (posY = sc.nextInt()) != 0) {
			posX = 0; posY--;
			char a[][] = new char[10][10];
			int b[][] = new int[10][10];
			String s;
			
			for(int i = 0; i < n; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < m; ++j) {
					a[i][j] = s.charAt(j);
				}
			}
			
			boolean done = false;
			int i = 1;
			boolean loop = false;
			while(!done) {
				if(b[posX][posY] != 0) {
					loop = true; 
					break;
				}
					
				b[posX][posY] = i++;
				switch(a[posX][posY]) {
				case 'N': posX--;break;
				case 'S': posX++;break;
				case 'W': posY--;break;
				case 'E': posY++;break;
				}
				
				if(posX < 0 || posX >= n || posY < 0 || posY >= m)
					done = true;
			}
			
			if(loop)
				out.printf("%d step(s) before a loop of %d step(s)\n", b[posX][posY] - 1, i - b[posX][posY]);
			else
				out.printf("%d step(s) to exit\n", i - 1);
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