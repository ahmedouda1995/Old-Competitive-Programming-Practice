package acp_dp;

import java.util.Arrays;

public class Stamp {

	static int N;
	static int arr[];
	static int len;
	static final int INF = 50 + 1;
	static int memo[][];
	
	public static void main(String[] args) {
		System.out.println(getMinimumCost("RR*GG", 1, 100000));
	}
	
	public static int getMinimumCost(String desiredColor, int stampCost, int pushCost)
	{
		N = desiredColor.length();
		arr = new int[N];
		for(int i = 0; i < N; ++i)
		{
			if(desiredColor.charAt(i) == 'R')
				arr[i] = 0;
			else if(desiredColor.charAt(i) == 'G')
				arr[i] = 1;
			else if(desiredColor.charAt(i) == 'B')
				arr[i] = 2;
			else
				arr[i] = 3;
		}
		
		memo = new int[N][3];
		
		int min = stampCost + N * pushCost;
		
		for(len = 2; len <= N; ++len)
		{
			for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
			min = Math.min(min, stampCost * len + pushCost * Math.min(solve(0, 0), Math.min(solve(0, 1), solve(0, 2))));
		}
		return min;
	}

	private static int solve(int idx, int prevColor) {
		
		if(idx == N)
			return 0;
		if(memo[idx][prevColor] != -1) return memo[idx][prevColor];
		
		int min = INF;
		if(arr[idx] != 3)
		{
			int am = 1;
			int j;
			for(j = idx + 1; j < N && am < len && (arr[j] == arr[idx] || arr[j] == 3); ++j) am++;
			if(am < len && prevColor == arr[idx] && (len - am) <= idx)
				am = len;
			
			if(am == len)
			{
				if(prevColor == arr[idx] && idx >= len)
				{
					for(int k = idx + 1; k < j; ++k)
						min = Math.min(min, 1 + solve(k , arr[idx]));
				}
				min = Math.min(min, 1 + solve(j , arr[idx]));
			}
		}
		else
		{
			for(int i = 0; i < 3; ++i)
			{
				int am = 1;
				int j;
				for(j = idx + 1; j < N && am < len && (arr[j] == i || arr[j] == 3); ++j)
					am++;
				
				if(am < len && prevColor == i && (len - am) <= idx)
					am = len;
				
				if(am == len)
				{
					if(prevColor == i && idx >= len)
					{
						for(int k = idx + 1; k < j; ++k)
							min = Math.min(min, 1 + solve(k , i));
					}
					min = Math.min(min, 1 + solve(j , i));
				}
			}
		}
		
		return memo[idx][prevColor] = min;
	}
}
