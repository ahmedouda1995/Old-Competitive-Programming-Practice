package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_166_MakingChange_rev {

	static final int INF = (int) 1e9;
	static int [] den = {1, 2, 4, 10, 20, 40};
	static int a[] = new int[6];
	static int memo[][];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		while((a[0] = sc.nextInt()) != 0 | (a[1] = sc.nextInt()) != 0 | (a[2] = sc.nextInt()) != 0
			 | (a[3] = sc.nextInt()) != 0 | (a[4] = sc.nextInt()) != 0 | (a[5] = sc.nextInt()) != 0)
		{
			StringTokenizer st = new StringTokenizer(sc.next(), ".");
			int n = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			n /= 5;
			int min = INF, sum = 0;
			for(int i = 0; i < 6; ++i) sum += den[i] * a[i];

			memo = new int[6][sum + 1];
			for(int i = 0; i < 6; ++i) Arrays.fill(memo[i], -1);
			
			for(int i = n; i <= sum; ++i) {
				min = Math.min(min, solve(0, i) + shopKeeperChange(i - n));
			}
			out.printf("%3d\n", min);
		}
		
		out.flush();
		out.close();
	}
	
	static int shopKeeperChange(int money) {
		for(int i = 5; i >= 0; --i) {
			if(money >= den[i])
				return 1 + shopKeeperChange(money - den[i]);
		}
		return 0;
	}
	
	private static int solve(int item, int money) {
		if(money == 0) return 0;
		if(money < 0 || item >= 6) return INF;
		if(memo[item][money] != -1) return memo[item][money];
		
		int min = INF;
		
		for(int i = 0; i <= a[item]; ++i) {
			min = Math.min(min, i + solve(item + 1, money - i * den[item]));
		}
		
		return memo[item][money] = min;
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