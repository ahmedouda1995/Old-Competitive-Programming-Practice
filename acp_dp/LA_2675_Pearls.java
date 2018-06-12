package acp_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// LEARNT ALOT

public class LA_2675_Pearls {

	static final int INF = (int)1e9;
	static int C;
	static int price[], amount[];
	static int memo[] = new int[100];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			C = sc.nextInt();
			price = new int[C]; amount = new int[C];
			
			for(int i = 0; i < C; ++i) {
				amount[i] = sc.nextInt();
				price[i] = sc.nextInt();
			}
			
			int dp[] = new int[C];
			dp[0] = (amount[0] + 10) * price[0];
			
			int am, min;
			
			for(int i = 1; i < C; ++i) {
				am = 0; min = INF;
				for(int j = i; j > 0; j--) {
					am += amount[j];
					min = Math.min(min, (am + 10) * price[i] + dp[j - 1]);
				}
				min = Math.min(min, (am + amount[0] + 10) * price[i]);
				dp[i] = min;
			}
			out.println(dp[C - 1]);
			//Arrays.fill(memo, -1);
			//out.println(solve(0));
		}

		out.flush();
		out.close();
	}
	
//	private static long solve(int idx, int total) {
//		if(memo[idx][total] != -1) return memo[idx][total];
//		if(idx == C - 1) return (total + amount[idx] + 10) * price[idx];
//		
//		long min = INF;
//		min = Math.min(min, (total + amount[idx] + 10) * price[idx] + solve(idx + 1, 0));
//		min = Math.min(min, solve(idx + 1, total + amount[idx]));
//		
//		return memo[idx][total] = min;
//	}

//	private static int solve(int currIdx, int prevIdx, int accumSum) {
//		if(memo[currIdx][prevIdx] != -1) return memo[currIdx][prevIdx];
//		if(currIdx == C - 1) return (accumSum + amount[currIdx] + 10) * price[currIdx];
//		
//		int min =
//				(amount[currIdx] + accumSum + 10) * price[currIdx] +
//				solve(currIdx + 1, currIdx + 1, 0);
//		min = Math.min(min, solve(currIdx + 1, prevIdx, accumSum + amount[currIdx]));
//		return memo[currIdx][prevIdx] = min;
//	}

	private static int solve(int idx) {
		if(idx == C) return 0;
		if(memo[idx] != -1) return memo[idx];
		
		int min = INF, sum = 0;
		
		for(int j = idx; j < C; ++j) {
			sum += amount[j];
			min = Math.min(min, (sum + 10) * price[j] + solve(j + 1));
		}
		return memo[idx] = min;
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