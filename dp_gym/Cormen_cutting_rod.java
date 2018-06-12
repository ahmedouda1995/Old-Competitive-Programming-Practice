package dp_gym;

import java.util.Arrays;

public class Cormen_cutting_rod {

	static int memo[] = new int[11];
	static int C = 1;
	static int dp[] = new int[11];
	static int sol[] = new int[11];
	static final int INF = (int) 1e9;
	static int prices[] = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
	
	public static void main(String[] args) {
		Arrays.fill(memo, -1);
		System.out.println("TD " + solve(4));
		System.out.println("BU " + slove2(4));
		printSol(4);
		System.out.println(Arrays.toString(sol));
	}

	private static void printSol(int n) {
		System.out.println("-------------");
		while(n > 0) {
			System.out.println(sol[n]);
			n -= sol[n];
		}
		System.out.println("-------------");
	}

	private static int slove2(int n) {
		dp[0] = 0; sol[0] = 0;
		
		for(int i = 1; i <= n; ++i) {
			int max = -INF;
			for(int j = 1; j <= i; ++j) {
				int comp = (i - j == 0)?prices[j] + dp[i - j]:prices[j] + dp[i - j] - C;
				if(comp > max) {
					max = comp;
					sol[i] = j;
				}
			}
			dp[i] = max;
		}
		return dp[n];
	}

	private static int solve(int n) {
		if(n == 0) return 0;
		if(memo[n] != -1) return memo[n];
		
		int max = -INF;
		
		for(int i = 1; i <= n; ++i) {
			max = Math.max(max, prices[i] + solve(n - i));
		}
		
		return memo[n] = max;
	}
}
