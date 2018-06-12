package acp_dp;

import java.util.Arrays;

public class ForbiddenStrings {

	static long memo[][][];
	
	public static void main(String[] args) {
		System.out.println(countNotForbidden(2));
	}
	
	public static long countNotForbidden(int n)
	{
		memo = new long[n + 1][4][4];
		
		for(int i = 0; i <= n; ++i)
			for(int j = 0; j < 4; ++j)
					Arrays.fill(memo[i][j], -1);
		
		return solve(n, 0, 0);
	}

	private static long solve(int n, int prev1, int prev2) {
		if(n == 0) return 1;
		
		if(memo[n][prev1][prev2] != -1) return memo[n][prev1][prev2];
		
		long count = 0;
		
		if(prev1 == 0)
		{
			count += solve(n - 1, 1, 0);
			count += solve(n - 1, 2, 0);
			count += solve(n - 1, 3, 0);
		}
		else if(prev2 == 0)
		{
			if(prev1 == 1)
			{
				count += solve(n - 1, 1, 0);
				count += solve(n - 1, 1, 2);
				count += solve(n - 1, 1, 3);
			}
			else if(prev1 == 2)
			{
				count += solve(n - 1, 2, 1);
				count += solve(n - 1, 2, 0);
				count += solve(n - 1, 2, 3);
			}
			else
			{
				count += solve(n - 1, 3, 1);
				count += solve(n - 1, 3, 2);
				count += solve(n - 1, 3, 0);
			}
		}
		else
		{
			if(prev1 != 1 && prev2 != 1)
			{
				if(prev2 == 2)
				{
					count += solve(n - 1, 2, 0);
					count += solve(n - 1, 2, 3);
				}
				else if(prev2 == 3)
				{
					count += solve(n - 1, 3, 0);
					count += solve(n - 1, 3, 2);
				}
			}
			else if(prev1 != 2 && prev2 != 2)
			{
				if(prev2 == 3)
				{
					count += solve(n - 1, 3, 0);
					count += solve(n - 1, 3, 1);
				}
				else if(prev2 == 1)
				{
					count += solve(n - 1, 1, 0);
					count += solve(n - 1, 1, 3);
				}
			}
			else
			{
				if(prev2 == 1)
				{
					count += solve(n - 1, 1, 0);
					count += solve(n - 1, 1, 2);
				}
				else if(prev2 == 2)
				{
					count += solve(n - 1, 2, 0);
					count += solve(n - 1, 2, 1);
				}
			}
		}
		
		return memo[n][prev1][prev2] = count;
	}
}
