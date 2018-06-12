package acp_dp;

import java.util.Arrays;

public class CasketOfStar {

	static int stars[];
	static int memo[][] = new int[50][50];
	
	public static int maxEnergy(int[] weight) {
		stars = weight;
		for(int i = 0; i < weight.length; ++i)
			Arrays.fill(memo[i], -1);
		return solve(0, weight.length - 1);
	}

	private static int solve(int i, int j) {
		if(j - i + 1 <= 2) return 0;
		if(j - i + 1 == 3) return stars[i] * stars[j];
		if(memo[i][j] != -1) return memo[i][j];
		
		int max = 0;
		
		for(int k = i + 1; k < j; ++k) {
			max = Math.max(max, stars[i] * stars[j] + solve(i, k) + solve(k, j));
		}
		return memo[i][j] = max;
	}
	
	public static void main(String[] args) {
		System.out.println(maxEnergy(new int[]{1, 2, 3, 4}));
	}
}
