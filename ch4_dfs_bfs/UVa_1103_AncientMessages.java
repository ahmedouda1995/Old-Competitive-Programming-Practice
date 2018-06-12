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
import java.util.TreeMap;

public class UVa_1103_AncientMessages {

	static char grid[][] = new char[200][200];
	static int dr[] = {0, 0, -1, 1};
	static int dc[] = {1, -1, 0, 0};
	static int N, M, Sr, Sc, Er, Ec;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		TreeMap<Character, String> map = new TreeMap<Character, String>();
		map.put('0', "0000"); map.put('1', "0001"); map.put('2', "0010");
		map.put('3', "0011"); map.put('4', "0100"); map.put('5', "0101");
		map.put('6', "0110"); map.put('7', "0111"); map.put('8', "1000");
		map.put('9', "1001"); map.put('a', "1010"); map.put('b', "1011");
		map.put('c', "1100"); map.put('d', "1101"); map.put('e', "1110");
		map.put('f', "1111");
		char [] symbol = {'W', 'A', 'K', 'J', 'S', 'D'};
		int cases = 1;
		while((N = sc.nextInt()) != 0 | (M = sc.nextInt()) != 0) {
			String s, a;
			for(int i = 0; i < N; ++i) {
				s = sc.nextLine();
				int k = 0;
				for(int j = 0; j < M; ++j) {
					a = map.get(s.charAt(j));
					for(int l = 0; l < 4; ++l) {
						grid[i][k++] = a.charAt(l);
					}
				}
			}
			M = (M * 4);
			for(int i = 0; i < M; ++i) {
				if(grid[0][i] == '0')
					dfs(0, i);
			}
			
			for(int i = 0; i < M; ++i) {
				if(grid[N - 1][i] == '0')
					dfs(N - 1, i);
			}
			
			for(int i = 1; i < N - 1; ++i) {
				if(grid[i][0] == '0')
					dfs(i, 0);
				
				if(grid[i][M - 1] == '0')
					dfs(i, M - 1);
			}
			
			ArrayList<Character> arr = new ArrayList<Character>();
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					if(grid[i][j] == '1') {
						arr.add(symbol[dfs2(i, j)]);
					}
				}
			}
			Collections.sort(arr);
			out.printf("Case %d: ", cases++);
			for(char c : arr) out.print(c);
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static int dfs2(int i, int j) {
		if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] == '2') return 0;
		
		int res = 0;
		if(grid[i][j] == '0') {
			res++;
			dfs(i, j);
		}
		else {
			grid[i][j] = '2';
			for(int k = 0; k < 4; ++k) res += dfs2(i + dr[k], j + dc[k]);
		}
		return res;
	}

	private static void dfs(int i, int j) {
		if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] != '0') return;
		grid[i][j] = '2';
		for(int k = 0; k < 4; ++k) dfs(i + dr[k], j + dc[k]);
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