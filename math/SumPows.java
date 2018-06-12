package math;

public class SumPows {

	public static void main(String[] args) {
		System.out.println(solve(2, 50));
	}
	
	// What about calculating: (a^1+a^2+a^3+a^4+a^5+a^n) ???
	 
	// Let's try even power
	// (a^1+a^2+a^3+a^4+a^5+a^6)       = (a^1+a^2+a^3)+(a^1*a^3+a^2*a^3+a^3*a^3)
	// (a^1+a^2+a^3)+a^3*(a^1+a^2+a^3) = (a^1+a^2+a^3)*(1+a^3)
	// (a^1+a^2+a^3)+a^3*(a^1+a^2+a^3) = (a^1+a^2+a^3)*(1+ a^1+a^2+a^3 - (a^1+a^2))
	//
	 
	// what about odd n
	// (a^1+a^2+a^3+a^4+a^5+a^6+a^7)   = a + a*(a^1+a^2+a^3+a^4+a^5+a^6)
//	                                 = a(1+(a^1+a^2+a^3+a^4+a^5+a^6))
	
	static long solve(long a, int k) // Return a^1+a^1+a^2+.....a^k    in O(k)
	{
		if(k == 0) return 0;
		
		if(k % 2 == 1)
			return a * (1 + solve(a, k - 1));
		
		long half = solve(a, k / 2);
	    return half * (1 + half - solve(a, k / 2 -1) );
	}
}
