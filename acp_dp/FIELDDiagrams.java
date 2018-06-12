package acp_dp;

import java.util.Arrays;

public class FIELDDiagrams {

	static long memo[][];
	
	public static void main(String[] args) {
		System.out.println(countDiagrams(2));
	}
	
	public static long countDiagrams(int fieldOrder)
	{
		memo = new long[fieldOrder + 1][fieldOrder + 1];
		
		for(int i = 0; i <= fieldOrder; ++i)
			Arrays.fill(memo[i], -1);
		
		return solve(fieldOrder, fieldOrder) - 1;
	}

	private static long solve(int max, int k) {
		if(k == 0) return 1;
		if(memo[max][k] != -1) return memo[max][k];
		
		long count = 0;
		for(int i = 0; i <= max; ++i)
			count += solve(Math.min(k - 1, i), k - 1);
		
		return memo[max][k] = count;
	}
}
