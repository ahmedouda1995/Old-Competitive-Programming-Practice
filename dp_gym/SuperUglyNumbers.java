package dp_gym;

public class SuperUglyNumbers {

	static int[] primes = {2, 3, 5};
	
	public static void main(String[] args) {
		System.out.println(solve(11));
	}

	private static int solve(int n) {
		int dp[] = new int[n];
		int iterator[] = new int[primes.length];
		dp[0] = 1;
		int nextUglyNo = 1;
		int nextMultiple[] = new int[primes.length];
		System.arraycopy(primes, 0, nextMultiple, 0, primes.length);
		
		for(int i = 1; i < n; ++i) {
			nextUglyNo = min(nextMultiple);
			dp[i] = nextUglyNo;
			
			for(int j = 0; j < nextMultiple.length; ++j) {
				if(nextMultiple[j] == nextUglyNo) {
					nextMultiple[j] = primes[j] * dp[++iterator[j]];
				}
			}
		}
		
		return nextUglyNo;
	}

	private static int min(int[] nextMultiple) {
		int min = (int) 1e9;
		
		for(int i : nextMultiple) min = Math.min(min, i);
		return min;
	}
}
