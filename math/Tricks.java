package math;

public class Tricks {

	public static void main(String[] args) {
		System.out.println(ceil(4, 5));
		System.out.println(round(54.0, 15.0));
		System.out.println(numDigits(99));
	}
	
	// ----->  a % (2 ^ n) = a & (n - 1)
	
	static int ceil(int x, int y) { // x / y
		return (x + y - 1) / y;
	}
	
	// round to a multiple of a specified amount (int)
	
	static int round(int am, int mult) {
		return am / mult * mult;
	}

	// round to a multiple of a specified amount (double)
	
	static double round(double am, double mult) {
		return Math.round(am / mult) * mult;
	}
	
	// number of digits
	
	static int numDigits(int n) {
		return (int) Math.floor(Math.log10(n)) + 1;
	}
}
