package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_614_MappingTheRoute {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m, posX, posY, gX, gY, mazes = 1;
		int a[][], mat[][];
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0 | (posX = sc.nextInt()) != 0 |
			  (posY = sc.nextInt()) != 0 |(gX = sc.nextInt()) != 0 | (gY = sc.nextInt()) != 0) {
			
			 posX--; posY--; gX--; gY--;
			 a = new int[n][m];
			 mat = new int[n][m];
			 
			 for(int i = 0; i < n; ++i) Arrays.fill(mat[i], -2);
			 
			 for(int i = 0; i < n; ++i) {
				 for(int j = 0; j < m; ++j) {
					a[i][j] = sc.nextInt();
				 }
			 }
			 
			 boolean done = false;
			 int i = 1;
			 
			 // w - n - e - s
			// 1 - wall eastern 2 - wall southern 3 - eastern and southern
			 while(!done) {
				 if(posX == gX && posY == gY) {
					 mat[posX][posY] = i;
					 done = true;
				 }
				 else {
					 if((posY - 1) >= 0 && a[posX][posY - 1] != 1 && a[posX][posY - 1] != 3 && mat[posX][posY - 1] == -2) {
						 mat[posX][posY] = i++;
						 posY -= 1;
					 }
					 else if((posX - 1) >= 0 && a[posX - 1][posY] != 2 && a[posX - 1][posY] != 3  && mat[posX - 1][posY] == -2) {
						 mat[posX][posY] = i++;
						 posX -= 1;
					 }
					 else if((posY + 1) < m && a[posX][posY] != 1 && a[posX][posY] != 3  && mat[posX][posY + 1] == -2) {
						 mat[posX][posY] = i++;
						 posY += 1;
					 }
					 else if((posX + 1) < n && a[posX][posY] != 2 && a[posX][posY] != 3  && mat[posX + 1][posY] == -2) {
						 mat[posX][posY] = i++;
						 posX += 1;
					 }
					 else {
						 mat[posX][posY] = -1;
						 if(posX - 1 >= 0 && mat[posX - 1][posY] == i - 1) { posX -= 1; }
						 else if(posX + 1 < n && mat[posX + 1][posY] == i - 1) { posX += 1; }
						 else if(posY - 1 >= 0 && mat[posX][posY - 1] == i - 1) { posY -= 1; }
						 else if(posY + 1 < m && mat[posX][posY + 1] == i - 1){ posY += 1; }
						 else done = true;
						 i--;
					 }
				 }
			 }
			
			out.println("Maze " + mazes++);
			out.println();
			
			out.print("+");
			for(int j = 0; j < m; ++j) out.print("---+");
			out.println();
			for(int j = 0; j < n; ++j) {
				out.printf("|");
				for(int k = 0; k < m; ++k) {
					if (mat[j][k] == -1) {
						out.printf("???");
						if(a[j][k] == 1 || a[j][k] == 3 || k == m - 1)
							out.printf("|");
						else
							out.printf(" ");
					}
					else if(mat[j][k] == -2) {
						out.printf("   ");
						if(a[j][k] == 1 || a[j][k] == 3 || k == m - 1)
							out.printf("|");
						else
							out.printf(" ");
					}
					else {
						out.printf("%3d", mat[j][k]);
						if(a[j][k] == 1 || a[j][k] == 3 || k == m - 1)
							out.printf("|");
						else
							out.printf(" ");
					}
				}
				out.println();
				if(j != n - 1) {
					out.print("+");
					for(int l = 0; l < m; ++l) {
						if(a[j][l] == 2 || a[j][l] == 3)
							out.print("---+");
						else
							out.print("   +");
					}
					out.println();
				}
			}
			out.print("+");
			for(int j = 0; j < m; ++j) out.print("---+");
			out.println();
			//for(int j = 0; j < n; ++j) out.println(Arrays.toString(mat[j])); 
			
			out.println();
			out.println();
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