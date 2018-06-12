package dp_gym;

public class UglyNumbers {

	// Ugly numbers are numbers whose only prime factors are 2, 3 or 5
	
	public static void main(String[] args) {
		System.out.printf("The 1500'th ugly number is %d.\n", solveDP(1500));
		//System.out.println(solveNaive(1500));
	}

	private static long solveDP(int n) {
		long dp[] = new long[n];
		
		dp[0] = 1;
		
		int i2, i3, i5;
		i2 = i3 = i5 = 0;
		
		long nextMult2 = 2, nextMult3 = 3, nextMult5 = 5, nextUglyNo = 1;
		
		for (int i = 1; i < n; ++i) {
			nextUglyNo = Math.min(nextMult2, Math.min(nextMult3, nextMult5));
			
			dp[i] = nextUglyNo;
			
			if(nextUglyNo == nextMult2) { i2++; nextMult2 = dp[i2] * 2L; }
			if(nextUglyNo == nextMult3) { i3++; nextMult3 = dp[i3] * 3L; }
			if(nextUglyNo == nextMult5) { i5++; nextMult5 = dp[i5] * 5L; }
		}
		
		return nextUglyNo;
	}

	private static int solveNaive(int n) {
		
		int i = 0; int uglies = 0;
		
		while(uglies < n) {
			i++;
			if(ugly(i)) {
				uglies++;
			}
		}
		return i;
	}

	private static boolean ugly(int n) {
		n = div(n, 2);
		n = div(n, 3);
		n = div(n, 5);
		return n == 1;
	}

	private static int div(int n, int i) {
		while(n % i == 0) n /= i;
		return n;
	}
}
