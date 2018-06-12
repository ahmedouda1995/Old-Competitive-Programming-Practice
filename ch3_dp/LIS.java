package ch3_dp;

import java.util.Arrays;

public class LIS {

	static int dp[] = new int[1000], sol[] = new int[1000], memo[] = new int[1000];
	
	public static void main(String[] args) {
		int [] a = {3, 7, 2, 1, 9, 4, 5, 6, 10, 8};
		
		System.out.println(">> " + lis(a));
		Arrays.fill(memo, 0, a.length, -1);
		//System.out.println("$ " + lisRec(a.length - 2, a));
		//System.out.println(Arrays.toString(memo));
	}

	private static int lisRec(int i, int[] a) {
		if(i == 0)
			return memo[0] = 1;
		if(memo[i] != -1)
			return memo[i];
		else {
			int max = 1;
			for(int j = i - 1; j >= 0; --j){
				if(a[j] < a[i])
					max = Math.max(lisRec(j, a) + 1, max);
			}
			return memo[i] = max;
		}
	}

	private static int lis(int[] a) {
		if(a.length == 0)
			return 0;
		int max = 0, maxIndex = 0;
		for(int i = 0; i < a.length; ++i) {
			int maxSoFar = 0, maxSoFarIndex = i;
			for(int j = i - 1; j >= 0; --j) {
				if(a[j] < a[i]) {
					if(maxSoFar < dp[j]) {
						maxSoFar = dp[j];
						maxSoFarIndex = j;
					}
				}
			}
			dp[i] = maxSoFar + 1;
			sol[i] = maxSoFarIndex;
			if(dp[i] > max) {
				max = dp[i]; maxIndex = i;
			}
		}
		printSol(maxIndex, a);
		System.out.println();
		return max;
	}

	private static void printSol(int maxIndex, int [] a) {
		if(sol[maxIndex] == maxIndex) {
			System.out.print(a[maxIndex]);
		}
		else {
			printSol(sol[maxIndex], a);
			System.out.print(" " + a[maxIndex]);
		}
	}
}
