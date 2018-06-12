package ch4_dfs_bfs;

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

public class UVa_10707_2DNim {

	static int N, M;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), k1, k2;
		
		while(t-- > 0) {
			M = sc.nextInt();
			N = sc.nextInt();
			k1 = k2 = sc.nextInt();

			boolean grid1Hor[][], grid1Ver[][];
			boolean grid2Hor[][], grid2Ver[][];
			
			grid1Hor = new boolean[N][M]; grid2Hor = new boolean[N][M];
			grid1Ver = new boolean[N][M]; grid2Ver = new boolean[N][M];
			
			int a, b;
			
			while(k1-- > 0) {
				b = sc.nextInt(); a = sc.nextInt();
				grid1Hor[a][b] = true; grid1Ver[a][b] = true;
			}
			while(k2-- > 0) {
				b = sc.nextInt(); a = sc.nextInt();
				grid2Hor[a][b] = true; grid2Ver[a][b] = true;
			}
			
			ArrayList<Integer> res1 = new ArrayList<Integer>();
			ArrayList<Integer> res2 = new ArrayList<Integer>();
			ArrayList<Integer> res3 = new ArrayList<Integer>();
			ArrayList<Integer> res4 = new ArrayList<Integer>();
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					if(grid1Hor[i][j]) res1.add(dfs(i, j, grid1Hor, true));
				}
			}
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					if(grid1Ver[i][j]) res2.add(dfs(i, j, grid1Ver, false));
				}
			}
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					if(grid2Hor[i][j]) res3.add(dfs(i, j, grid2Hor, true));
				}
			}
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					if(grid2Ver[i][j]) res4.add(dfs(i, j, grid2Ver, false));
				}
			}
			
			Collections.sort(res1); Collections.sort(res2);
			Collections.sort(res3); Collections.sort(res4);
			
			boolean ans = false;
			
			ans |= (check(res1, res3) | check(res2, res4));
			ans |= (check(res1, res4) | check(res2, res3));
			
			out.println((ans)?"YES":"NO");
		}
		
		out.flush();
		out.close();
	}

	private static boolean check(ArrayList<Integer> a, ArrayList<Integer> b) {
		if(a.size() != b.size()) return false;
		
		for(int i = 0; i < a.size(); ++i) if(a.get(i) != b.get(i)) return false;
		return true;
	}

	private static int dfs(int i, int j, boolean[][] a, boolean hor) {
		int res = 0;
		if(hor) {
			while(j < M && a[i][j]) {
				res++;
				a[i][j] = false;
				j++;
			}
		}
		else {
			while(i < N && a[i][j]) {
				res++;
				a[i][j] = false;
				i++;
			}
		}
		return res;
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