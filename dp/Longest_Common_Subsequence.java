package dp;

import java.util.Arrays;
import java.util.Scanner;

public class Longest_Common_Subsequence {

	static int [][] memo = new int [20][20];
	static int [][] dp = new int [20][20];
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		//for (int i = 0; i < memo.length; i++)
			//Arrays.fill(memo[i], -1);
		char [] st1 = sc.nextLine().toCharArray(), st2 = sc.nextLine().toCharArray();
		char [] s1 = new char[st1.length + 1], s2 = new char[st2.length + 1];
		System.arraycopy(st1, 0, s1, 1, st1.length);System.arraycopy(st2, 0, s2, 1, st2.length);
		//System.out.println(LCS_TD(s1, s2, s1.length - 1, s2.length - 1));
		//for (int i = 0; i < memo.length; i++)
			//System.out.println(Arrays.toString(memo[i]));
		System.out.println(LCS_BU(s1, s2));
		for (int i = 0; i < dp.length; i++)
			System.out.println(Arrays.toString(dp[i]));
	}

	private static int LCS_BU(char[] s1, char[] s2) {
		for (int i = 0; i < s1.length; i++) {
			for (int j = 0; j < s2.length; j++) {
				if(i == 0 || j == 0)
					dp[i][j] = 0;
				else if(s1[i] == s2[j])
					dp[i][j] = 1 + dp[i-1][j-1];
				else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		return dp[s1.length - 1][s2.length - 1];
	}

	private static int LCS_TD(char[] s1, char[] s2, int i, int j) {
		if(i < 0 || j < 0)
			return 0;
		if(memo[i][j] != -1)
			return memo[i][j];
		if(s1[i] == s2[j])
			return memo[i][j] = 1 + LCS_TD(s1, s2, i - 1, j - 1);
		else {
			return memo[i][j] = Math.max(LCS_TD(s1, s2, i - 1, j), LCS_TD(s1, s2, i, j - 1));
		}
	}
}
