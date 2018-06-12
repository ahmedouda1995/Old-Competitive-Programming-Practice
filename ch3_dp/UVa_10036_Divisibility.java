package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10036_Divisibility {

	static int N, K;
	static int arr[] = new int[10000];
	static int memo[][] = new int[10000][100];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			N = sc.nextInt(); K = sc.nextInt();
			
			for(int i = 0; i < N; ++i) {
				arr[i] = sc.nextInt();
				Arrays.fill(memo[i], -1);
			}
			
			out.println(solve(1, fix(arr[0]))?"Divisible":"Not divisible");
		}
		
		out.flush();
		out.close();
	}
	
	static int fix(int n) {	return (n % K + K) % K; }
	
	private static boolean solve(int idx, int mod) {
		if(idx == N) return mod == 0;
		if(memo[idx][mod] != -1) return memo[idx][mod] == 1;
		if((solve(idx + 1, fix(mod + arr[idx])) || solve(idx + 1, fix(mod - arr[idx])))) {
			memo[idx][mod] = 1;
			return true;
		}
		else {
			memo[idx][mod] = 0;
			return false;
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