package dp;

import java.util.ArrayList;
import java.util.Arrays;

public class Longest_increasing_subsequence {

	public static void main(String[] args) {
		ArrayList<Integer> seq = new ArrayList<Integer>();
		int [] a = {-7, 10, 9, 2, 3, 8, 8, 1, 2, 3, 4};
		int [] dp = new int[a.length];
		int result = 0;
		
		for (int i = 0; i < dp.length; i++) {
			dp[i] = 1;
			for(int j=i-1;j>=0;j--)
				if(a[j] < a[i])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			
			result = Math.max(result, dp[i]);
			
		}
		System.out.println(Arrays.toString(dp));
		System.out.println(result);
		int i;
		for(i = dp.length-1;i>=0 && dp[i] != result;i--);
		seq.add(a[i--]);
		result--;
		for(;i>=0 && result>0;i--){
			if(dp[i] == result){
				seq.add(0, a[i]);
				result--;
			}
		}
		System.out.println(seq);
	}
}
