package math;

public class RangeFactorization {

	public static void main(String[] args) {
		
	}
	
	// more efficient way is to generate all prime numbers in that range
	// then loop like below adding the prime number at what it can divide
	// then use trick no. 14 in math notes (p1 + 1) * (p2 + 1) * ... for each number
	// then add all the cells together
	
	static int solveNaive(int n)
	{
		int arr[] = new int[n + 1];
		
		for(int i = 1; i <= n; ++i)
		{
			for(int k = i; k <= n; k += i)
				arr[i]++;
		}
		
		int s = 0;
		
		for(int i = 0; i <= n; ++i)
			s += arr[i];
		
		return s;
	}
}
