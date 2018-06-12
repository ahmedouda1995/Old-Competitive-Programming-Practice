package acp_dp;

public class NumberofFiboCalls {

	static int dpZero[] = new int[41];
	static int dpOne[] = new int[41];
	
	public static int[] fiboCallsMade(int n) {
		dpZero[0] = 1; dpOne[0] = 0;
		dpZero[1] = 0; dpOne[1] = 1;
		
		for(int i = 2; i <= n; ++i) {
			dpOne[i] = dpOne[i - 1] + dpOne[i - 2];
			dpZero[i] = dpZero[i - 1] + dpZero[i - 2];
		}
		
		return new int[]{dpZero[n], dpOne[n]};
	}
}
