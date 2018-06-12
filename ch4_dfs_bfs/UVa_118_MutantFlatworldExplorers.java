package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_118_MutantFlatworldExplorers {

	static boolean [][] scent = new boolean[51][51];
	static int mov[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int X, Y;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		X = sc.nextInt(); Y = sc.nextInt();
		while((s = sc.nextLine()) != null) {
			StringTokenizer st = new StringTokenizer(s);
			int posX = Integer.parseInt(st.nextToken());
			int posY = Integer.parseInt(st.nextToken());
			int dir = getDir(st.nextToken().charAt(0));
			
			s = sc.nextLine();
			
			int i;
			for(i = 0; i < s.length(); ++i) {
				if(s.charAt(i) == 'F') {
					if(isSafe(posX + mov[dir][0], posY + mov[dir][1])) {
						posX += mov[dir][0]; posY += mov[dir][1];
					}
					else {
						if(!scent[posX][posY]) {
							scent[posX][posY] = true;
							break;
						}
					}
				}
				else if(s.charAt(i) == 'R') dir = (dir + 1) % 4;
				else dir = (dir - 1 + 4) % 4;
			}
			if(i != s.length())
				out.printf("%d %d %c LOST\n", posX, posY, getChar(dir));
			else
				out.printf("%d %d %c\n", posX, posY, getChar(dir));
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean isSafe(int x, int y) {
		if(x >= 0 && y >= 0 && x <= X && y <= Y)
			return true;
		return false;
	}
	
	private static int getDir(char c) {
		int res = 0;
		switch(c) {
		case 'N':res = 0;break;
		case 'E':res = 1;break;
		case 'S':res = 2;break;
		case 'W':res = 3;break;
		}
		return res;
	}

	private static char getChar(int dir) {
		char res = 'z';
		switch(dir) {
		case 0:res = 'N';break;
		case 1:res = 'E';break;
		case 2:res = 'S';break;
		case 3:res = 'W';break;
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