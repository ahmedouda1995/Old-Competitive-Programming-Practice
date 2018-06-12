package dp_gym;

public class LIS {

	static int arr[] = {1, 3, 2, 6, 7, 4, 5};
	static int dp[] = new int[arr.length];
	static int sol[] = new int[arr.length];
	
	public static void main(String[] args) {
		int index = lis();
		System.out.println(dp[index]);
		printSol(index);
		System.out.println();
	}

	private static void printSol(int index) {
		if(index == sol[index]) System.out.print(index);
		else {
			printSol(sol[index]);
			System.out.print(" " + index);
		}
	}

	private static int lis() {
		if(arr.length == 0) return 0;
		
		dp[0] = 1; sol[0] = 0;
		
		int maxSoFar = 1, index = 0;
		
		for(int i = 1; i < arr.length; ++i) {
			dp[i] = 1; sol[i] = i;
			for(int j = i - 1; j >= 0; --j) {
				if(arr[i] > arr[j]) {
					if(dp[i] <= dp[j]) {
						dp[i] = dp[j] + 1;
						sol[i] = j;
						if(dp[i] > maxSoFar) {
							maxSoFar = dp[i];
							index = i;
						}
					}
				}
			}
		}
		
		return index;
	}
}
