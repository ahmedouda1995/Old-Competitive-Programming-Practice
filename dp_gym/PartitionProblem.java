package dp_gym;

import java.util.Arrays;

public class PartitionProblem {

	static int arr[] = {3, 1, 1, 2, 2, 1};
    static int N = arr.length;
	
	public static void main(String[] args) {
		if (findPartition() == true)
            System.out.println("Can be divided into two " +
                               "subsets of equal sum");
        else
            System.out.println("Can not be divided into" +
                            " two subsets of equal sum");
	}

	private static boolean findPartition() {
		int sum = 0;
		
		for(int i = 0; i < N; ++i) sum += arr[i];
		
		if(sum % 2 == 1) return false;
		
		int M = sum / 2;
		
		boolean dp[][]=new boolean[N + 1][M + 1];
		
		for (int i = 1; i <= M; i++)
            dp[0][i] = false;
		
		for (int i = 0; i <= N; i++)
            dp[i][0] = true;
		
		for(int i = 1; i <= N; ++i) {
			for(int j = 1; j <= M; ++j) {
				if(dp[i - 1][j]) dp[i][j] = true;
				else if(j - arr[i - 1] >= 0) {
					if(dp[i - 1][j - arr[i - 1]]) dp[i][j] = true;
				}
				else dp[i][j] = false;
			}
		}
		
		for(int i = 0; i <= N; ++i) System.out.println(Arrays.toString(dp[i]));
		
		return dp[N][M];
	}
}
