package ch9_20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_348_Optimal_Array_Multiplication_Sequence {

	static int N, INF = (int) 1e9;
	static int memo[][];
	static int sol[][];
	static int p[];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		int cases = 1;
		
		while((N = sc.nextInt()) != 0) {
			p = new int[N + 1];
			p[0] = sc.nextInt(); p[1] = sc.nextInt();
			for(int i = 2; i < N + 1; ++i) {
				sc.next();
				p[i] = sc.nextInt();
			}
			
			memo = new int[N][N]; sol = new int[N][N];
			
			for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
			
			solve(0, N - 1);
			out.printf("Case %d: ", cases++);
			printSol(0, N - 1);
			out.println();
		}
		
		out.flush();
		out.close();
	}

	private static void printSol(int i, int j) {
		if(i == j) out.printf("A%d", i + 1);
		else {
			out.print("(");
			printSol(i, sol[i][j]);
			out.print(" x ");
			printSol(sol[i][j] + 1, j);
			out.print(")");
		}
	}

	private static int solve(int i, int j) {
		if(i == j) return 0;
		
		if(memo[i][j] != -1) return memo[i][j];
		
		memo[i][j] = INF;
		
		for(int k = i; k < j; ++k) {
			int q = solve(i, k) + solve(k + 1, j) + p[i] * p[k + 1] * p[j + 1];
			if(q < memo[i][j]) {
				memo[i][j] = q;
				sol[i][j] = k;
			}
		}
		
		return memo[i][j];
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