package acp_dp;

import java.util.Arrays;

public class RGBStreet {
	
	static final int INF = (int) 1e9;
	static int memo[][] = new int[20][3];
	static String [] arr;
	static int N;
	
	public static int estimateCost(String[] houses) {
		for(int i = 0; i < houses.length; ++i)
			Arrays.fill(memo[i], -1);
		String [] tmp = houses[0].split(" ");
		arr = houses;
		N = arr.length;
		int r = Integer.parseInt(tmp[0]) + solve(1, 0);
		int g = Integer.parseInt(tmp[1]) + solve(1, 1);
		int b = Integer.parseInt(tmp[2]) + solve(1, 2);
		return Math.min(Math.min(r, g), b);
	}

	private static int solve(int idx, int prevColor) {
		if(idx == N) return 0;
		if(memo[idx][prevColor] != -1) return memo[idx][prevColor];
		
		int min = INF;
		String [] tmp = arr[idx].split(" ");
		
		for(int i = 0; i <= 2; ++i) {
			if(i != prevColor)
				min = Math.min(min, Integer.parseInt(tmp[i]) + solve(idx + 1, i));
		}
		return memo[idx][prevColor] = min;
	}
}