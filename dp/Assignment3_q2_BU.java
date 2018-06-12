package dp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Assignment3_q2_BU {

	static int [][] dp = new int [1000][1001];
	static int [] difficulty = new int[1000], value = new int[1000];
	static int maxDifficulty = 0, n;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new File("output.txt"));
		n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			difficulty[i] = sc.nextInt(); value[i] = sc.nextInt();
			maxDifficulty = Math.max(difficulty[i], maxDifficulty);
		}
		
		out.println(bottomUp()); // (item 0, difficulty level 0)
		out.flush();
		out.close();
	}
	
	private static int bottomUp() {
		if(n == 0)
			return 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= maxDifficulty; j++) {
				if(i == 0){
					if(difficulty[i] <= j)
						dp[i][j] = value[i];
				}
				else {
					if(difficulty[i] <= j)
						dp[i][j] = Math.max(value[i] + dp[i-1][difficulty[i]], dp[i-1][j]);
					else
						dp[i][j] = dp[i-1][j];
				}
			}
		}
//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		return dp[n-1][maxDifficulty];
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
