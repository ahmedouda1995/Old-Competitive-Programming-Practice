package dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KnapSack_bottom_up {

	static int [][] dp = new int[1001][31];
	static int [] wt = new int[1001], val = new int[1001];
	
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int t = sc.nextInt();
		
		Arrays.fill(dp[0], 0);
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		
		while(t-- > 0){
			int n = sc.nextInt();
			for (int i = 1; i <= n; i++) {
				val[i] = sc.nextInt(); wt[i] = sc.nextInt();
			}
			int people = sc.nextInt(), result = 0;
			for (int i = 0; i < people; i++) {
				result += knapSack(sc.nextInt(), n);
			}
			out.println(result);
		}
		out.flush();
		out.close();
	}
	
	private static int knapSack(int weight, int n) {
		
		for(int i=1;i<=n;i++){
			for (int j = 1; j <= weight; j++) {
				if(wt[i] <= j){
					dp[i][j] = Math.max(val[i] + dp[i-1][j - wt[i]], dp[i-1][j]);
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[n][weight];
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
