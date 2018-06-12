package acp_dp;

import java.math.BigInteger;

public class MatchNumbersEasy {

//	static int N;
//	static boolean calc[][][] = new boolean[10][51][2];
//	static String memo[][][] = new String[10][51][2];
//	static int arr[];
	
	static int N, arr[];
	static BigInteger memo[] = new BigInteger[51];
	
	public static String maxNumber(int[] matches, int n) {
		N = matches.length; arr = matches;
		return solve(n).toString();
	}

	private static BigInteger solve(int n) {
		if(memo[n] != null) return memo[n];
		BigInteger max = BigInteger.ZERO, ten = BigInteger.TEN;
		
		for(int i = 0; i < N; ++i) {
			if(arr[i] <= n)
				max =  max.max(ten.multiply(solve(n - arr[i])).add(BigInteger.valueOf(i)));
		}
		return memo[n] = max;
	}
	
//	public static String maxNumber(int[] matches, int n) {
//		N = matches.length; arr = matches;
//		for(int i = 0; i < N; ++i)
//			for(int j = 0; j <= n; ++j)
//				Arrays.fill(calc[i][j], false);
//		return solve(N - 1, n, 0);
//	}
//
//	private static String solve(int idx, int n, int taken) {
//		if(idx == -1) return "";
//		if(idx == 0 && taken == 0) return "0";
//		if(calc[idx][n][taken]) return memo[idx][n][taken];
//		
//		String x, y = "", a, b;
//		
//		int tmp = (taken == 1)?tmp = 1:0;
//		
//		x = solve(idx - 1, n, tmp);
//		if(n >= arr[idx]) {
//			y = idx + "";
//			a = solve(idx - 1, n - arr[idx], 1);
//			b = solve(idx, n - arr[idx], 1);
//			if(comp(a, b) >= 0) y += a;
//			else y += b;
//		}
//		calc[idx][n][taken] = true;
//		if(comp(x, y) >= 0)
//			return memo[idx][n][taken] = x;
//		else
//			return memo[idx][n][taken] = y;
//	}
//	
//	public static int comp(String a, String b) {
//		if(a.length() == b.length()) return a.compareTo(b);
//		else if(a.length() > b.length()) return 1;
//		else return -1;
//	}
}
