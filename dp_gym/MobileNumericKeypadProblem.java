package dp_gym;

import java.util.Arrays;

public class MobileNumericKeypadProblem {

	static int arr[] = {2, 3, 4, 3, 4, 5, 4, 3, 5, 3};
	
	public static void main(String[] args) {
		System.out.println(solve(5));
	}

	private static int solve(int n) {
		int dp[] = new int[10];
		Arrays.fill(dp, 1);
		
		n--;
		
		while(n-- > 0) {
			for(int i = 0; i < 10; ++i) dp[i] *= arr[i];
		}
		
		int res = 0;
		
		for(int i = 0; i < 10; ++i) res += dp[i];
		return res;
	}
}
