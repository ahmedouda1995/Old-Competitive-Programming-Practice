
public class FastExp {

	public static void main(String[] args) {
		System.out.println(exp(3, 1));
	}

	private static int exp(int base, int exp) {  // O(log(exp))
		if(exp == 0) return 1;
		else {
			int res = exp(base, exp >> 1); res *= res;
			if((exp & 1) == 1) res *= base;
			return res;
		}
	}
}
