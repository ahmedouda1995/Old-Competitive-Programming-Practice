package dp_gym;

import java.util.Arrays;

public class WordWrapProblem {

	static final int INF = (int) 1e9;
	static String s = "Geeks for Geeks presents word wrap problem";
	static String arr[] = s.split(" ");
	
	public static void main(String[] args) {
		int lineWidth = 15;
		System.out.println(solve(lineWidth));
	}

	private static int solve(int lineWidth) {
		int dp[] = new int[arr.length];
		
		//both ways zero last line and all spaces
//		dp[arr.length - 1] = lineWidth - arr[arr.length - 1].length();
		dp[arr.length - 1] = 0;
		
		for(int i = arr.length - 2; i >= 0; --i) {
			int size = arr[i].length();
			int min = (lineWidth - size) * (lineWidth - size) + dp[i + 1];
			for(int j = i + 1; j < arr.length; ++j) {
				if(size + arr[j].length() + 1 <= lineWidth) {
					size += arr[j].length() + 1;
					int test = (lineWidth - size) * (lineWidth - size);
//					if(j == arr.length - 1 && test < min)
//						min = test;
					if(j == arr.length - 1)	
						min = 0;
					else if(test + dp[j + 1] < min) {
						min = test + dp[j + 1];
					}
				}
				else break;
			}
			dp[i] = min;
		}
		System.out.println(Arrays.toString(dp));
		return dp[0];
	}
}
