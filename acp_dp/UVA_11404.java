package acp_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_11404 {
	
	static Pair memo[][] = new Pair [1000][1000];
	static char s[];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		//Scanner sc = new Scanner(System.in);

		while(sc.ready())
		{
			s = sc.nextLine().toCharArray();
			for(int i = 0; i < s.length; ++i)
				for(int j = 0; j < s.length; ++j)
					memo[i][j] = null;
			out.println(solve(0, s.length - 1).s);
		}
		
		out.flush();
		out.close();
	}
	
	private static Pair solve(int i, int j) {
		if(i > j) return new Pair(0, "");
		if(i == j) return new Pair(1, s[i] + "");
		if(memo[i][j] != null) return memo[i][j];
		
		int max = 0;
		
		if(s[i] == s[j]) 
		{
			Pair tmp = solve(i + 1, j - 1);
			max = 2 + tmp.n;
			return memo[i][j] = new Pair(max, s[i] + tmp.s + s[j]);
		}
		else
		{
			Pair p1 = solve(i + 1, j);
			Pair p2 = solve(i, j - 1);
			
			if(p1.n > p2.n)
				return memo[i][j] = p1;
			else if(p1.n < p2.n)
				return memo[i][j] = p2;
			else
				return memo[i][j] = (p1.s.compareTo(p2.s) < 0?p1:p2);
		}
	}
	
	static class Pair
	{
		int n;
		String s;
		
		public Pair(int x, String y) {
			n =  x;
			s = y;
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