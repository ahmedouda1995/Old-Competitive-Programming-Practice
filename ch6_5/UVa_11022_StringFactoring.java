package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11022_StringFactoring {

	// very important
	// Tried a lot but could not solve it on my own
	// need to be resolved again
	
	static int N;
	static int memo[][] = new int[80][80];
	static char s[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String tmp;

		while(!(tmp = sc.nextLine()).equals("*")) {
			s = tmp.toCharArray();
			N = s.length;
			for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
			out.println(solve(0, N - 1));
		}

		out.flush();
		out.close();
	}
	
	private static int solve(int i, int j) {
		if(i > j) return 0;
		if(i == j) return 1;
		
		if(memo[i][j] != -1) return memo[i][j];
		int min = j - i + 1;
		
		for(int k = i; k <= j; ++k)
			for(int len = 1; len < k - i + 1 || len == k - i + 1 && k != j; ++len)
				if(compress(i, k, len))
					min = Math.min(min, solve(i, i + len - 1) + solve(k + 1, j));
		
		return memo[i][j] = min;
	}

	private static boolean compress(int i, int j, int len) {
		if((j - i + 1) % len != 0)
			return false;
		for(int k = 0, b = i; i <= j; ++i, k = (k + 1) % len)
			if(s[i] != s[b + k])
				return false;
		return true;
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