package math;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeFactorization {

	static ArrayList<Integer> pr = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		pFact(10);
		System.out.println(pr);
		System.out.println(numberOfFactors(20));
		sumOfFactors(10);
	}

	private static void pFact(int n) {
		if(n <= 1) return;
		
		for(int i = 2; i * i <= n; ++i) {
			while(n % i == 0) {
				pr.add(i);
				n /= i;
			}
		}
		if(n > 1)
			pr.add(n);
	}
	
	// number of factors from primes 0 & 1 not handled
	
	private static int numberOfFactors(int n) {
		ArrayList<Integer> arr= new ArrayList<Integer>();
		int cnt = 0;
		for(int i = 2; i * i <= n; ++i) {
			cnt = 0;
			while(n % i == 0) {
				n /= i;
				cnt++;
			}
			if(cnt > 0)
				arr.add(cnt);
		}
		if(n > 1)
			arr.add(1);
		
		int res = 1;
		
		// if(number ^ p) then res *= (v * p + 1)
		
		for(int v : arr)
			res *= (v + 1); 
		
		return res;
	}
	
	// sum of factors
	
	private static void sumOfFactors(int n) {
		int res[] = new int[n + 1];
		
		for(int i = 1; i <= n; ++i) {
			for(int j = i; j <= n; j += i) {
				res[j] += i;
			}
		}
		System.out.println(Arrays.toString(res));
	}
}
