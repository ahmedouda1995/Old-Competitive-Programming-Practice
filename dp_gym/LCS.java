package dp_gym;

public class LCS {

	static String s1 = "ABCBDAB";
	static String s2 = "BDCABA";
	static int memo[][] = new int[s1.length()][s2.length()];
	static char sol[][] = new char[s1.length()][s2.length()];
	static int dp[][] = new int[s1.length()][s2.length()];
	static int dp2[][] = new int[s1.length() + 1][s2.length() + 1];
	
	public static void main(String[] args) {
		//for(int i = 0; i < memo.length; ++i) Arrays.fill(memo[i], -1);
		//System.out.println(solve(s1.length() - 1, s2.length() - 1));
		
		//for(int i = 0; i < memo.length; ++i) System.out.println(Arrays.toString(memo[i]));
		
		//printSol(s1.length() - 1, s2.length() - 1);
		//System.out.println();
		System.out.println(LCSBUSpaceSaveTrick());
//		System.out.println(solveBU());
//		for(int i = 0; i < memo.length; ++i) System.out.println(Arrays.toString(dp[i]));
	}

	private static int LCSBUSpaceSaveTrick() {
		if(s1.length() == 0 || s2.length() == 0) return 0;
		dp2[0][0] = 0; dp2[1][0] = 0;
		int curr = 0, prev = 1;
		for(int i = 1; i <= s2.length(); ++i)
			dp2[0][i] = (s1.charAt(0) == s2.charAt(i - 1))?1:dp2[0][i - 1];
		
		for(int i = 2; i <= s1.length(); ++i) {
			curr = 1 - curr; prev = 1 - prev;
			for(int j = 1; j <= s2.length(); ++j) {
				if(s1.charAt(i - 1) == s2.charAt(j - 1))
					dp2[curr][j] = 1 + dp2[prev][j - 1];
				else dp2[curr][j] = Math.max(dp2[prev][j], dp2[curr][j - 1]);
			}
		}
		
		return dp2[curr][s2.length()];
	}

	private static int solveBU() {
		if(s1.length() == 0 || s2.length() == 0) return 0;
		
		dp[0][0] = (s1.charAt(0) == s2.charAt(0))?1:0;
		for(int i = 1; i < s2.length(); ++i)
			dp[0][i] = (s1.charAt(0) == s2.charAt(i))?1:dp[0][i - 1];
		for(int i = 1; i < s1.length(); ++i)
			dp[i][0] = (s1.charAt(i) == s2.charAt(0))?1:dp[i - 1][0];
		
		for(int i = 1; i < s1.length(); ++i)
			for(int j = 1; j < s2.length(); ++j) {
				if(s1.charAt(i) == s2.charAt(j)) dp[i][j] = 1 + dp[i - 1][j - 1];
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		return dp[s1.length() - 1][s2.length() - 1];
	}

	private static void printSol(int i, int j) {
		if(i < 0 || j < 0) return;
		
		if(sol[i][j] == 'D') { printSol(i - 1, j - 1); System.out.print(s1.charAt(i)); }
		else if(sol[i][j] == 'U') printSol(i - 1, j);
		else printSol(i, j - 1);
	}

	private static int solve(int i, int j) {
		if(i < 0 || j < 0) return 0;
		if(memo[i][j] != -1) return memo[i][j];
		
		if(s1.charAt(i) == s2.charAt(j)) {
			sol[i][j] = 'D';
			return memo[i][j] = 1 + solve(i - 1, j - 1);
		}
		
		int x = solve(i - 1, j), y = solve(i, j - 1);
		
		if(x > y) { memo[i][j] = x; sol[i][j] = 'U'; }
		else { memo[i][j] = y; sol[i][j] = 'L'; }
		
		return memo[i][j];
	}
}
