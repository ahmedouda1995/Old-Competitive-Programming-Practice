package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Coin_change_ways_dp {

	static long [][] dp = new long [5][7490];
	static int change[] = {1, 5, 10, 25, 50};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String s = "";
		ways(7489);
		while((s = br.readLine()) != null){
			int money = Integer.parseInt(s);
			out.println(dp[4][money]);
		}
		out.flush();
		out.close();
	}

	private static long ways(int money) {
		for (int i = 0; i < change.length; i++) {
			for (int j = 0; j <= money; j++) {
				if(j == 0 || i == 0)
					dp[i][j] = 1;
				else {
					if(j >= change[i]){
						dp[i][j] = dp[i-1][j] + dp[i][j-change[i]];
					}
					else{
						dp[i][j] = dp[i-1][j];
					}
				}
			}
		}
		return dp[change.length - 1][money];
	}
}
