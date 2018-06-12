package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10819_Troubleof_13_Dots {

	static final int INF = (int) 1e9;
	static int N, M;
	static int memo[][] = new int[100][10201];
	static int price[], val[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready()) {
			M = sc.nextInt(); N = sc.nextInt();
			price = new int[N]; val = new int[N];
			
			for(int i = 0; i < N; ++i) { price[i] = sc.nextInt(); val[i] = sc.nextInt(); }
			
			for(int i = 0; i < N; ++i) for(int j = 0; j <= M + 200; ++j) memo[i][j] = -1;
			out.println(solve(0, 0));
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int item, int expense) {
		if(M < 1800 && expense > M) return -INF;
		if(expense > M && expense - M > 200) return -INF;
		if(item == N) {
			if(expense > M && expense <= 2000) return -INF;
			return 0;
		}
		
		if(memo[item][expense] != -1) return memo[item][expense];
		
		return memo[item][expense] =
				Math.max(solve(item + 1, expense), val[item] + solve(item + 1, expense + price[item]));
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