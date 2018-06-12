package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CF_347D {
	
	static char [] s1, s2, virus;
	static int lps[];
	static int nxt[][];
	static int memo[][][];
	static final int INF = (int) 1e9;
	static PrintWriter out = new PrintWriter(System.out);
	
	// prefix automiton O(26 * n)
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		
		s1 = sc.nextLine().toCharArray();
		s2 = sc.nextLine().toCharArray();
		virus = sc.nextLine().toCharArray();
		
		lps = kmp(virus);
		nxt = new int[virus.length][26];
		nxt[0][virus[0] - 'A'] = 1;
		
		for(int i = 1; i < virus.length; ++i)
		{
			for(int j = 0; j < 26; ++j)
			{
				if(virus[i] == (char) (j + 'A'))
					nxt[i][j] = i + 1;
				else
					nxt[i][j] = nxt[lps[i - 1]][j];
			}
		}
		
		memo = new int[s1.length][s2.length][virus.length];
		for(int i = 0; i < s1.length; ++i)
			for(int j = 0; j < s2.length; ++j)
				Arrays.fill(memo[i][j], -1);
		
		if(solve(0, 0, 0) > 0)
			construct(0, 0, 0);
		else
			out.println(0);
		
		out.flush();
		out.close();
	}
	
	private static void construct(int i, int j, int k)
	{
		if(i == s1.length || j == s2.length)
			return;
		int res = -INF;
		if(s1[i] == s2[j])
			res = 1 + solve(i + 1, j + 1, nxt[k][s1[i] - 'A']);
		if(solve(i, j, k) == res)
		{
			out.print(s1[i]);
			construct(i + 1, j + 1, nxt[k][s1[i] - 'A']);
			return;
		}
		res = solve(i + 1, j, k);
		if(solve(i, j, k) == res)
			construct(i + 1, j, k);
		else
			construct(i, j + 1, k);
	}

	private static int solve(int i, int j, int k) {
		if(k == virus.length) return -INF;
		if(i == s1.length || j == s2.length)
			return 0;
		if(memo[i][j][k] != -1) return memo[i][j][k];
		int res = -INF;
		if(s1[i] == s2[j])
			res = 1 + solve(i + 1, j + 1, nxt[k][s1[i] - 'A']);
		res = Math.max(res, Math.max(solve(i + 1, j, k), solve(i, j + 1, k)));
		return memo[i][j][k] = res;
	}

	private static int[] kmp(char[] s)
	{
		int n = s.length;
		int kmp[] = new int[n];
		
		int j = 0;
		
		for(int i = 1; i < n; ++i)
		{
			while(j > 0 && s[i] != s[j]) j = kmp[j - 1];
			if(s[i] == s[j]) kmp[i] = ++j;
			else kmp[i] = 0;
		}
		
		return kmp;
	}
	
	static class Pair implements Comparable<Pair>{
		long a;
		int b;
		
		public Pair(long x, int y) {
			a = x;
			b = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(Long.compare(a, p.a) == 0)
				return Integer.compare(b, p.b);
			return Long.compare(a, p.a);
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