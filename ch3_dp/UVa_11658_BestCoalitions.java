package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11658_BestCoalitions {

	static int N;
	static int arr[] = new int[100];
	static double memo[][] = new double[100][10001];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int a;
		
		while((N = sc.nextInt()) != 0 | (a = sc.nextInt()) != 0) {
			a--;
			StringTokenizer st;
			for(int i = 0; i < N; ++i) {
				Arrays.fill(memo[i], -1);
				st = new StringTokenizer(sc.nextLine(), ".");
				arr[i] = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			}
			int tmp = arr[a]; arr[a] = arr[0]; arr[0] = tmp;
			
			if(arr[0] > 5000)
				out.println("100.00");
			else
				out.printf("%.2f\n", solve(1, arr[0]));
		}
		
		out.flush();
		out.close();
	}
	
	private static double solve(int idx, int perc) {
		if(idx == N) {
			if(perc > 5000)
				return ((arr[0] * 100.0) / perc);
			else
				return 0;
		}
		if(memo[idx][perc] != -1) return memo[idx][perc];
		
		return memo[idx][perc] =
				Math.max(solve(idx + 1, perc + arr[idx]),
						solve(idx + 1, perc));
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