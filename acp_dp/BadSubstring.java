package acp_dp;

import java.util.Arrays;

public class BadSubstring {

	static long memo[][];
	
	public static void main(String[] args) {
		System.out.println(howMany(29));
	}
	
	public static long howMany(int length)
	{
		memo = new long[length + 1][2];
		for(int i = 0; i <= length; ++i)
			Arrays.fill(memo[i], -1);
		
		return solve(length, 0);
	}

	private static long solve(int rem, int prevA) {
		if(rem == 0)
			return 1;
		if(memo[rem][prevA] != -1) return memo[rem][prevA];
		
		long count = 0;
		
		count += solve(rem - 1, 1);
		if(prevA == 0)
			count += solve(rem - 1, 0);
		count += solve(rem - 1, 0);
		
		return memo[rem][prevA] = count;
	}
}
