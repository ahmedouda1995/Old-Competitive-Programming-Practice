package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11258_StringPartition {

	static long memo[] = new long[200];
	static String s;
	static int N;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			s = sc.nextLine(); N = s.length();
			Arrays.fill(memo, -1);
			out.println(solve(0));
		}
		
		out.flush();
		out.close();
	}
	
	private static long solve(int i) {
		if(i == N) return 0;
		if(memo[i] != -1) return memo[i];
		if(s.charAt(i) == 0) return solve(i + 1);
		
		long max = 0, tmp;
		for(int j = i + 1; j <= Math.min(i + 9, s.length()); ++j) {
			tmp = Long.parseLong(s.substring(i, j));
			max = Math.max(max, tmp + solve(j));
		}
		if(s.length() - i + 1 > 10) {
			tmp = Long.parseLong(s.substring(i, i + 10));
			if(tmp <= Integer.MAX_VALUE)
				max = Math.max(max, tmp + solve(i + 10));
		}
		
		return memo[i] = max;
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